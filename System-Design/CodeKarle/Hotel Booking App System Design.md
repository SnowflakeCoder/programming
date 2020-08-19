# Hotel Booking App System Design

**Airbnb** | **Booking.com** | Hotel Booking App System Design and Architecture

------

------

## Functional requirements

We have **two major consumers** of this application. 

- **hotel side of users** (Hotel management standpoint)
  - **Onboarding**: they should be able to onboard onto our platform.
  - **update hotel properties**: like add a new room, change the pricing, add new images etc.
  - see **Booking details and revenue**.
- **consumers who want to book** the hotel  (User standpoint)
  - **Search for a hotel** in a particular location with some search criteria : like price , 3 star or 5 star etc. 
  - **book the hotel**.
  - check past/upcoming bookings.

## Non-functional requirements

- Low latency, High availability, High consistency

## Scale Standpoint

- We can assume that there are roughly **500K hotels** and **10-12M rooms** in all these hotels world wide.

**Assumptions** - there will never be a case  such that there is just one room available and there are thousands of users wanting to book that. So at max there is one room and there are 2-3 users who are trying to book that. we will be able to use that assumption for our leverage at later point in time.

## Diagram

![Hoel Booking System.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Hoel%20Booking%20System.png?raw=true)

## Data Flow

- **Onboarding**: **Hotel UI/App** => **LB** => **hotel service** => **Kafka cluster**
  - The whole business flow starts at **hotel UI/App**. This UI talks to a **Load Balancer** through which it talks to the **hotel service**.
  - Any modification that is happening within **hotel service** will flow through a **kafka cluster** and there'll be multiple consumers who will **populate their data store** (**elastic search** for search consumer) using these events. This data will be used for serving the search traffic and for other traffic as well. **e.g:** let's say a new hotel comes in, so we want to bubble up this hotel to the users who are using the search.
- **Search Flow**: **User UI/App** => **LB** => **Search Service** => **ES** <= **Search Consumer** <= **Kafka cluster**
  - **Search flow** - Whenever the user search for a particular hotel, the User UI talks to an **LB** through which it talks to the **search service**.
- **Booking flow**: **User UI/App** => **LB** => **Booking Service** => **Kafka cluster**
  - **Booking flow**: booking request also first comes to the **LB** and then talks to **Booking Service**.
- **View bookings**: **Hotel/User UI/App** => **LB** => **Booking Mgmt Service** => **Redis + Cassandra DB** 



## Components

### Hotel UI/APP

This is a UI (either **a website or a mobile app**) that we give out to the hotel managers. Through this UI **they would come and onboard onto our platform**. They can use the same UI to **modify any property**. 

### Hotel Service

A service which **manages the hotel part** which is basically the onboarding and the management. This is a **horizontally scalable component** so there could be multiple instances (nodes) of this service to handle the spike in traffic.

Hotel data is mostly **relational data** and it doesn't have a scale problem because number of hotels are not so big (~500K). So we can use a **clustered MySQL** here with one master and multiple staves. More Slaves can be added if there's a huge spike in Read traffic.

Hotels can also add images & videos about their rooms, building etc. All those would be stored into a CDN and the <u>reference to the CDN which is basically a URL of the image</u> would be stored in the database and that URL would be sent out to customers and whenever they want to **render an image that would be looked up directly from the CDN**. 

### Search Consumer

Search consumer pulls up the events from Kafka cluster and stores into its own database and this database would be used to power the **search service**. So here we are using an **elastic search DB** because it can also support **fuzzy search**. So all the data of each individual hotel, flows through the kafka via the search consumer into this elastic search cluster. Again if there is a spike in traffic, we can increase the number of nodes in kafka cluster or number of search consumers or number of nodes in elastic search cluster. So all these components are **horizontally scalable**.

### Search Service

Search Service is the service which powers the search on the Website/App. This service sits on top of the **Search ES DB**. This search service also provide few search criteria like location, date range, price range and some tags. These **tags would be the properties of the hotels** like five star property is a tag. So the search on ES would be happening on these tags and ranges that are selected by the user. 

### Booking Service

Booking request also comes from the same user UI, talks to the LB and then talks to **Booking Service**. Booking Service also use a **MySQL DB cluster**. So whenever a booking request come, that booking gets stored into this MySQL and then it talks to a Payment Service. Once the payment is success, it will update the MySQL again and mark the booking confirm.

Whenever a booking is happening, the data is flowing into the same **kafka**. Let's just say there was just one room available in a hotel and that room is now booked. So the system now want to make sure that this hotel is not available for search in that same date range. So all those information is again sent to the same kafka which is read by **Search Consumer** and then it takes care of removing the hotels which are now completely booked.

### Booking Management Service

A user might want to see their old bookings or a hotel might want to see all the bookings that they have, more like a read-only view and that will be powered by the Booking Management Service. So this service will talks to two different data sources. It talks to the **MySQL cluster** for all the active bookings, which are to happen sometime in future and to the **Cassandra cluster**, for the bookings that have already happened.

We also need to add a **Redis** on top of this **Booking DB MySQL** to reduce the load on this MySQL. So Redis will act as a Cache and whenever I have a query like get bookings of a user, I can cache this result into this Redis. And it'll be a **write-through cache**, so whenever a new booking is coming in, this will get updated.

### Booking and Hotel Storage Difference

Its better to use **two different MySQL clusters** for hotel service and booking service because we are talking about a fairly large system so its better that we take care of the scaling separately.

We did not keep a Redis cache on top of the **Hotel DB MySQL** but we kept one on the Booking DB MySQL. We could have kept the Cache on top of this too and all these Hotel GET APIs could have been a bit more faster. But this is **not coming in the critical path** of any **high throughput business interaction** because all the customers are not querying this database or this hotel service. They are always querying the **Search Service**, so if this service is a little bit slow that's okay but adding a Redis Cluster is a cost.

You always need to do a **trade-off analysis** between what cost are you adding of an infrastructure and what benefit it adds to you. I don't think it is worth adding a Redis cluster here.

### Archival Service

**Booking service** is only storing the **live data** into MySQL. Live data means the information about the bookings that are done but have not been completed. Once the booking moves to a **terminal state** means the  booking is either cancelled or completed, then it will move through the archival service to a **Cassandra cluster**. Thereby making sure that the **MySQL DB is having a low scale** that it can easily handle.

The reason we are using a Cassandra here is, Cassandra is a very good database which can **handle a huge amount of reads and writes**. But Cassandra <u>cannot support all kinds of queries</u>, so we **cannot use Cassandra as a source of truth database**. It has a **constraint** that it needs **a partition key on which all the queries should happen**. So if you want to search by a "booking_id" then the partition key has to be "booking_id". Once it is archived we just need to do GETs using those partition key, therefore Cassandra makes a good choice over here. 

### Notification Service

Once the booking is done we need to notify all the people, then comes the Notification Service. So whenever a booking is made or any changes are happening into a booking or it moves into a terminal state, Notification Service will consumes these events from the kafka and notifies the people. For example on each booking, we need to **notify the hotel** or whenever a booking is cancelled by the hotel we need to notify the consumer or in fact on each booking we need to notify the consumer with an invoice. So all of those is taken care by this Notification Service.

### Analytics 

Let's just say a business person wants to know how much revenue I'm making or how many bookings I'm having or what are my best performing hotels and stuff like that. So they need to do a lot of analytics. Mostly <u>while designing the system we'll never always know **what kind of analytics is required**</u>. So we can use a **Hadoop Cluster**, on which push in all the events that are going into the kafka. These events are basically the information about all hotels, about all bookings and all the transactions that happen in the system. So we run a **Spark Streaming Consumer** that reads from this kafka and puts all the data into a Hadoop Cluster on which we can do Hive queries or any different kind of queries and **build up a lot of reporting**. We can also run a **machine learning model** onto this **Hadoop cluster** and do some **supply demand analytics** and then come up with the **optimal price** for each room/hotel.

### Hotel Service - Detailed

#### Hotel Service API

Hotel Service is basically a **CRUD Service** which provides Create, Update, Read, Delete operations on the hotel data store and it is the source of truth for hotel data. So let's look at some of the APIs.

- **/hotels** => A **POST API** to create a hotel which will be part of their **onboarding process**.
- **/hotel/{hotel_id}** => A **GET API** with an id which will **give back the information** of the hotel.
- **/hotel/{hotel_id}** => A **PUT API** which will be used to **update any information** of a hotel.
- **/hotel/{hotel_id}/room/{room_id}** => A **PUT API** which would be used to **update the room information** or create new rooms and all of that.

#### Hotel DB schema

- **hotel table** => [id, name, locality_id, description, **original_images**, **display_images**, is_active]
  - locality_id is a foreign key to locality table and is_active is a **soft delete flag**.
  - Original_images is basically the artifact that the people have uploaded and display_images could be a compressed version of that or It could be a version that we have uploaded on the CDN. But we still need to keep both of them so we have stored it here. 
- **rooms table** => [id, hotel_id, **display_name**, is_active, quantity, **price_min**, **price_max**]
  - hotel_id is references into the hotel table and display_name explains what kind of a room it is. is_active is a soft delete flag and quantity tells how many such rooms are there in the hotel.
  - **price_min and price_max** could be the ranges which the hotel provides, wherein the price could be **fluctuated by the system**. let's say there's a lot of demand and few rooms left, so here we can increase the price. If there are too many rooms and very few customers we reduce the price.
- **facilities table**
  - A list of all the facilities that a hotel and a room can possibly have. These are basically mapping tables with many-to-many relationship between a hotel_id/room_id and a facility_id.
  - **hotel-facilities table** =>  [id, hotel_id, facility_id, **is_active**]
  - **room-facilities table** =>  [id, room_id, facility_id, **is_active**]
  - **is_active** flag is basically a soft delete flag. 

We can also add other information like **auditing information**, **book keeping information** like created_on, updated_time etc if required. 

### Booking Service - Detailed

#### Booking DB Schema

- **available_rooms** => [room_id, date, initial_quantity, available_quantity] 
  - initial_quantity comes from the hotel service.
  - available_quantity : number of rooms that are remaining for that room_id for that particular date.
  - We can add a constraint saying available_quantity cannot go negative. (**advantage of using MySQL**)
- booking => [booking_id, room_id, user_id, start_date, end_date, no_of_rooms, status, invoice_d]
  - Using this table structure we can clearly understand that one booking cannot contain different room types but you can have multiple rooms of the same room type.
  - Status column has four values - reserved, booked cancelled and completed. Canceled and completed are the terminal statuses. Booking first created with reserved status. then based on the payment success it can either move to book or cancel. if the user stays in that room, it moves to complete.

#### Booking Service API

- **/book**: A POST API with attributes [room_id, user_id, start_date, end_date, no_of_rooms]

1. Check in **available_rooms table**
   1. When Booking Service gets a request to do a booking it first query the **available_rooms table** and check whether I have that many number of rooms remaining. If there are no rooms left for that particular room_id for that particular date, there's no point of proceeding so we can error out from there. But if we have rooms then we go ahead and **block the room temporarily** and if the payment is success will book the room. 
2. Insert a record in **booking** table and reduce quantity in **available_rooms**.
   1. So if we have enough rooms then a row is created in **booking table** with a booking_id, room_id etc. and **status = RESERVED** at this point in time. invoice_id at this point in time would be NULL because there is no invoice created till now. 
   2. After creating this record we are **reducing the quantity in [available_rooms]** and we have to bound this as part of **one transaction** to use the constraint which says that quantity cannot be negative. So only one of the transaction will be success and no two users will be redirected to payment for the same room.
3. Insert a key in **Redis** with **TTL**
   1. We cannot keep this room reserved for an infinite amount of time. If the payment is success in next few minutes, its good, if not then we'll assume that the payment will not go through and will unblock the room so that somebody else could book it. Will implement this using the **TTL feature of Redis**. So we'll put the key in Redis saying some booking_id expires at some timestamp. So whenever a key is getting expired you'll get a notification (**Redis callback** feature) and you can do whatever you need to do at that point in time.
4. If payment is a **success**
   1. If you get a Success notification from payment service then you will mark the booking status as **BOOKED** and we'll get an invoice_id from Payment Service and update the invoice_id column also.
   2. We will be sending **Kafka events** also saying the booking is success, in case somebody wants to do something on that.
5. If payment **fails**
   1. The booking status will become CANCELLED and there would be no invoice_id.
   2. If the payment did not go through, we need to revert the available_quantity. 
6. Cancel because of **Redis key expiry** and status = RESERVED: So the user was redirected to payment screen and there was no response from payment service and then we get a expiry call back from Redis. Now the payment has not gone through we will follow the same process as payment failure we will mark this CANCELLED and increment the quantity in available_quantity so that the room is now available for somebody else to use. Again in that scenario there is no invoice generated.
7. **Redis key expiry** and payment is also success - There are two conditions. 
   1. if the payment has already been successful and the booking has already been moved to BOOKED status, after that if you get this key expired event then you don't do anything.
   2. but if key expired first so you move the booking to CANCELLED state but then you get a notification saying payment is success. You could either **revert the payment** saying we were not able to book the room so here's your payment back or you could say that now I have anyway got the payment from the user, I can check if there are rooms available and I'll book them. 

### Redis TTL Delay

The <u>Redis TTL is not a very precise measure</u>, you will probably **never ever get a call back at exact point in time** it will **always have some delay**. There's a background process that runs in Redis for keys that are not accessed and whenever that process gets to access a particular key is when it will expire it. So it is not necessary that it will acquire it at exactly the same time. But if you wanted it to be totally precise then instead of doing a TTL based approach you could in fact **implement a queue using this Redis** and have a **poller** that kind of queries Redis every one second and whichever one it it finds has expired then you could kind of delete that. So you'll have to build a **polling mechanism** and it will be continuously bombarding Redis every one second. So there's a lot of CPU being utilized on the cron side, and on the Redis side.

**Redis optimizations**: let's just say payment is success/Failure then you don't need to keep that key In Redis, you can evict the key. So these optimizations can make this implementation even more better. 

