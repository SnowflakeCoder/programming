# Cab Booking System

## Functional Requirements

- When a customer opens the app he should be to **see what cabs** are around him.
- If you want to book a cab from point A to B, You should know how much **time** it will take and whats the **approximate price**.
- Should be able to **book a cab**.
- very good **Location tracking** of what driver was at what place at what point in time.

## Non-Functional Requirements

- This platform should be global and it should be accessible to people of all countries. So you need to have servers in a lot of geographies to make sure that people from a certain geography accessing the servers near to them and by reducing the latency.
- Should work at a fairly low latency
- High availability. System should not go down.
- High consistency

## Scale

- 100M active unique users are using uber on a monthly basis.
- 14M Rides/day

## Diagram

![Cab Booking System (Uber).png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Cab%20Booking%20System%20(Uber).png?raw=true)

## User App / User Flow

### Cabs Near to Customer

When you have a customer location who wants to book a cab, first you will try to find out some drivers who are very near to that location and then using some logic, try to come up with the best driver who is suited for this customer. So how do we find the closest drivers to a Customer ? 

Will try to solve this problem using **mapping segment**. The idea is to basically **divide the entire city into rectangular segments**. Then for a given certain co-ordinates of the segment boundaries and given certain co-ordinates of the cab you should be able to figure out **which segment does the cab belongs to**. Also remember that **cabs are continuously moving** and their locations are continuously changing, so make sure that we get continuous ping from all the cabs and keep a track which segment they belonging. This information will be **calculated at runtime** as and when we are getting ping from the cab.

### Maps Service

- Maps service is responsible for dividing the city into the segments and taking care of these segments.  
- Given a lat-long of a cab and given a lat-long of a customer, this service say which segment this user belongs to at this point in time. This service also used to **calculate ETA & route** from point A to point B and thus even the distance.
- All the **segment management (divide/merge segments)** remains with in this service. **Idea of a segment** is it should be a small set of drivers that are in the segment. Suppose there is a huge amount of traffic / huge amount of cabs in a particular segment then it become unmanageable. In this case Maps service will **divide this segment into multiple parts**. If the traffic is less in few nearby segments then this service will also **merge these segments into one single segment**.

### User Service

How individual users and drivers get connected to the system? All the users get connected through the **user app** which talks to a load balancer which talks to **User service**.  The user service is the repository of all the user information. It also works as proxy that will try to connect other services to get any information that a user wants. So if a user wants to see the profile or update the profile, all the APIs to do that are powered by User service. If any other service wants to fetch user information then they should use User Service APIs. Also if the user wants to see the trip, then the user service will talk to the **trip service**, fetch all the trip for the user and then send it back to the user.

User service run on top of a **MySQL cluster** which stores all the user information and it uses a **Redis** for caching the same information. So it first queries the Redis if not found then query the MySQL DB, fetches the information, stores it in Redis and then return back the result.

### Cab Request Service

This service will get called when the user tries to book a cab. This service will make a **websocket connection with the user App** which display them few cabs in the UI which are around them. It also make a request to the **cab finder service** to find a cab for the user and whenever cab finder response back with a cab, cab request service will send the response to the user App through the websocket connection, saying that the cab is booked and these are the details.

## Driver App / Driver Flow

Driver talks via the Driver app

### Driver Service

This is exactly **similar to User service** but contains all the information about the drivers. All the APIs for getting/updating all the driver information is powered by this service. If the Driver wants to see their payment information / Payment history, the driver service expose an API which Driver App will call and driver service will **internally call the payment service** to get that information and respond back. Similarly it will call the **trip service** to get trip details of a driver / many driver (bulk APIs). Driver service also use **MySQL and Redis combination** exactly the same way as user service.

### Location Service

Location service manages all the location related information. Driver app talks to **Location Service** through a series of **websocket handler** servers (using a websocket connection with these servers). When a driver is moving through the city, their location is send to the location service through a websocket handler, which then query the **map service** to find out which segment the driver belongs to. When the customer places a cab request, customer and drivers segments are calculated by the **cab finder** to give the best suited cab for that trip.

Location service stores the driver location information into **Cassandra**. Why Cassandra? There will be millions of drivers sending their location updates every time intervals so there is a **lot of updates** happening and Cassandra should be **able to scale up to that number**. 2 kinds of information is get stored here. **Live location or last known location** of a driver and the **route followed** while doing a trip with the customer for auditing and billing purposes. Once we know the route the driver followed, we can calculate the actual distance travelled and the price.

Location service also talk to Map service for finding the segment. Map service maintain all the segments information and it also gives the ETA(time taken), the distance and the route that should be followed from point A to B. When location service gets a ping from a driver it queries Map service, find the segment and stores it into Redis saying this **segment has these drivers - {s1 : d1, d2, d3}.** Update happens only when a driver segment changes. Map service also keep a mapping of which all are the segments surrounding a particular segment.

### Websocket Handler

All the active drivers (ready for trip) are connected to one of the **websocket handler** through a **websocket connection**. Why websocket connection? Each active driver continuously send location ping to the backend service (location service) and each time creating a connection here is a heavy operation. In some cases the server might also want to talk to the drivers like when a trip is assigned to a driver we need to inform the driver. So here we can use the same websocket connection. So in the real world there will be lots of websocket handlers interfacing with all the active drivers which are throughout the world geographically.

### Websocket Manager

When a trip is assigned to a driver, to reach out the driver first we need to know which websocket handler to connect. For that we use **websocket manager** which is a distributed service manages the data that which server is connected to what and all drivers. When a **new driver is active** then the websocket handler will inform the websocket manager, saying a new driver is active from this handler. Same happens when a driver is offline also. Websocket manager will store all these data in **Redis Cluster**, this Redis will also store these data in a persistent storage(disk) also. This Redis will store **both kinds of mapping**, driver to handler and handler to drivers also.

### Trip service

Trip service is basically the source of truth for all the trip information. It uses a **MySQL DB** and a **Cassandra**. It uses **MySQL for all the live information**, like information about all the trips that are either about to happen in near future or are in progress. Why MySQL? trip would have a lots of information like userInfo, driverInfo, tripInfo (startTime, endTime, distance, price), routeInfo etc, so for each event we end updating lots of tables and its better to have some transactional properties (**ACID properties**). So that's why we are using MySQL for all the **trips that will be updated**. Once the trip is completed then it can basically move to Cassandra using an **Trip Archival Service**, a cron service which runs every fixed intervals. We need to this past trip information for few years so its better to store in Cassandra.

Trip service will also expose all the APIs required for a trip like trip by id or trip by a driver etc. Trip service can query from both MySQL and Cassandra if required and merge the data then return it back.

Customer Request a Cab

Customer App will basically raise a request to Cab Request Service using a websocket connection and with information including **source lat-long** and **destination lat-long**. cab Request Service query cab finder service which is responsible for find the cab. So cab finder responds back with tripId and driverId and cab request service send the response back to customer App. Cab finder will also put an **event into kafka** whether or not he was able to find a driver. This can be used for further analysis like list of locations where more customers less drivers for a given day. 

Cab finder will have a source lat-long and it will query Location Service saying get me the segment for this customer and along with that give me a list of drivers that are near this customer. Location service query Maps service with customer lat-long and find the segment then it also query surrounding segments of that segment. **Why surrounding segment?** because sometime the customer will be standing near the border of a segment and the driver from the surrounding segment will be more close to him than any other driver from the same segment. Then location service find all the drivers in these segments and tells **Map service to get the closest N drivers** to this customer out of that list. Maps service will find the distance and ETA between customer and driver and prepare the N drivers close this customer and Location service return this list to cab finder. Map service is good at finding distance and time taken between 2 points.

Now we need to identify one driver from the list who will do the trip. This will be decided by **mode** of the cab request. For example for a premium customer we pick the best driver from the list. If its an average customer then we might wanna broadcast to all the drivers and whoever accepts first we can assign that driver. So **cab finder decides which mode** to run and each mode requires different information like for **best driver mode** we need to start ranking each drivers. All of these are handled by **Driver priority Engine**. So cab finder tells Driver Priority Engine saying this is the list of drivers for this customer so rank them and return it. Cab finder then find the driver using the list and Mode then it query websocket manager to find the handler for that driver to pass the information. At the same time Cab finder also pass the same information via Cab request service to customer saying this your trip and driver details.

Once the driver is assigned now trip has to be created and updated. So cab finder will update the trip service saying trip is created with this customer and this driver and other details. Trip service store that into the MySQL DB.

As part of the booking flow, many events are inserted into the kafka like whether cab finder was able to find a cab or not, how many drivers we got from a segment at this time, location update events, trip update events etc. Whenever a trip is completed we need to initiate a payment to the driver aggregated over a  few hours or days. **Payment service** listens to all **trip completed events** from the Kafka and as soon as a trip is completed it would insert a record in the Payment MySQL DB with details like driverId, tripId, userid etc.. and amount of money that needs to be paid to this driver, then payment service could talk to a **payment gateway** to deliver a payment or if its need to be aggregated then it can start a cron which does the same. Payment service also gives APIs for getting payment details for a trip or a driver etc.

On top of this Kafka cluster we can run few **spark streaming jobs** and save all those results in a **Hadoop cluster** for further analysis. One common usecase for spark job is **finding a HeatMap** (due to some events) based on number of requests and pass this info to driver App, so drivers can move to those locations to get more trips. On top of this Hadoop cluster we can run various ML jobs to find things like **customer classification/customer profiling** (premium, every day, regular etc), driver classification etc. **Driver priority engine** also could use these ML outputs for ranking drivers based rating, feedback, ETA etc. We can also use these ML model outputs to feed the **Fraud Service**. Fraud service can find the customers who rejects the trip very often or a **driver exploiting incentives from the company**  by making fake rides etc. **Maps service** also can use these inputs for finding new roads or updating current ETA, also making decisions like whether to divide a segment or merge few (like some place will have more requests at some particular time) etc.



