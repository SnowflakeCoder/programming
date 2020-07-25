# E-commerce Application System Design

https://www.youtube.com/watch?v=EpASu_1dUdE&t=173s

Design an ecommerce application similar to Amazon.

## Functional Requirements

- People should be **able to search** for whatever product they want to buy and along with searching we should also be able to tell them whether we can deliver it to them or not or if you are delivering then when should we be able to deliver it.
- There should be a concept of a **cart or a wishlist** so that people can add a particular item into a cart.
- People should be able to check out which means making a payment and then completing the order.
- People should be able to view all of their past orders, both delivered not been delivered also.

## Non-functional Requirements

- The system should have a very **low latency** because it will be a bad user experience if it's slow. 
- It should be **highly available**.
- It should be **highly consistent**.

High availability, high consistency and low latency all three together will be very difficult. But not every components needs to have all three requirements. For example payment and inventory counting services need to be **highly consistent at the cost of availability**. Services like search needs to be **highly available maybe at the cost of consistent** at times and overall most of the user facing components should have a low latency.

## Diagram

![E-commerce Application Design.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/E-commerce%20Application%20Design.png?raw=true)

## Main Components

### User Interfaces. 

We mainly have two user interfaces. One is the **home screen** which would be the first screen that a user comes to. It would by default have some recommendations based on some past record of that user. On case of new user some general recommendations. In case of **search page** it would just be a text box where in user can put in a text and will give them the search results.

### Data Flow

#### Inbound Service

A company like Amazon would have **various suppliers** that they would have integrated with. These suppliers would be managed by various services on the supplier front. So an **inbound service** basically talk to various supplier system and get all of the data. Let's say a new item has been coming or the supplier is basically **onboarding a new item**, that information comes through inbound service into a Kafka. Now there are multiple consumers on that Kafka which will process that information and flow it to the user world.

#### Item Service

Item service is basically the **source of truth for all items** in the ecosystem and it interact with many other services. Item service **listen to above kafka topic** (kafka consumer) and **on board a new item**. It  also provide various search APIs to get an item by item ID, add a new item, remove an item, update details of a particular item and it will also have bulk APIs to fetch details of a lot of items using item IDs. 

Item service sits on top of a mongo DB. **Why mongo DB** ? because item information is fundamentally very **non structured**. Different item types will have different attributes and all of that needs to be stored in a **queryable format**. So if we try to model that in a structured form into MySQL kind of database that will not be an optimal choice so that's why we've used the Mongo. For example a shirt would have a size attribute, color attribute etc. A bread could have a type and weight like 400 grams of bread of type wheat bread or a sweet bread etc. So its such a non structured data and that's the reason we use a Mongo database.

#### Search Consumer

A search consumer is another kafka consumer, Whenever a new item comes in, this search consumer is responsible for making sure that this **item is now available for the users to query** on. So all the items that are coming in, search consumer **reads through all of those items details** and puts that in a format that the search system understands and stores it into its database. Search uses **elastic search** which is again a NoSQL database and is very **efficient at doing text-based queries**. This search will happen either on the product name or product description and maybe it will have some filters on the product attributes. Elastic search is very good at serving those type of queries and it also supports **fuzzy search**.

#### Search Service

Search service is basically an interface that talks to the front end or any other component that wants to search anything within the ecosystem. Search service sits on top of elastic search (explained above) and it provides various kinds of APIs to filter products, to search by a particular string or anything of that sort. The **contract between search service and search consumer** is fixed so both of them understand what is the type of data that is stored within elastic search. So consumer is able to write it and the service is able to search for it.

#### Serviceability and TAT Service

If a user wants to search something, there are two main aspects around it. one is basically trying to **figure out the right set of items** that needs to be displayed but another important aspect is that we **should not show items that we cannot deliver** to the customers. We should not really show such search results to the user because otherwise it's a bad experience that he will see the result but he will not be able to order it. So search service talks to **serviceability** and **TAT service**.

This service basically try to figure out that where exactly the product is, in what all warehouses. Then it tries to see **do I have a way to deliver products** from this warehouse to the customer's pin code and if I have what kind of products can I carry on that route. So all of those filtering stays within this serviceability and TAT service. It also tells you. **in how much time it will be able to deliver** the product like in number of hours/ days etc. If serviceability tells that it cannot deliver, search will simply filter out those items and ignore that and return the rest of the remaining list.

#### User Service

**User service** is basically a service that is the **source of true so for the user** data. Search service will talk to user service so that User service can query the user DB to fetch some attributes of the user like a default address or something. This data can be passed as an **argument to serviceability service** to check whether an item can be deliver or not. After all these filtering, search service returns the list of items to the end user.

user service is basically a repository of all the users. It provides various APIs to get details of a user, update details of a user etc. User service sit on top of a **MySQL database** and a **Redis cache** on top of it. So it will first query Redis to get the details of the user, if the user is present it will return from there. But if the user is not present in Redis, it will then query one of the slaves of that MySQL cluster, get the user information, stored it in Redis and return it back.

#### Recommendation System

Each time a search happens an event is basically put into Kafka the reason behind this is whenever somebody searching for something, they are basically telling you an **intent to buy a kind of product**. That is a very good source for **building a recommendation**. This is an input into the recommendation engine and we will be using a kafka to pass it along. So each search query goes into kafka saying this user ID searched for this particular product.

#### Wishlist and Cart Service

From the search screen user could be able to wishlist a particular product or add it to cart and buy it. All of those could be done using the wish list service and cart service. Wishlist service is the repository of all the wish lists in the ecosystem and the cart service is the repository of all the carts in the ecosystem. Both these services are built in exactly the same way and they provide APIs to add a product into user cart or wish list, get a user cart or whishlist or delete a particular item from that.

They both would have a very similar data model and they are both sitting on their own MySQL databases. Its better to use two separate Hardware just in case wishlist size becomes too big and it needs to scale out so we can scale this particular cluster accordingly. Otherwise functionally both of them are totally same. Each time is a person is putting a product into wishlist they are again giving you signal, each time they are adding something into their cart they are again giving a signal about their preferences, things that they like, that they want to buy and all of that. All of those could again be put into kafka for a very similar kind of analytics.

#### Spark Cluster

A spark streaming consumer will be reading all these events from this kafka. This service come up with some kind of **reports on what products people are buying** right now. Also coming up with the report saying what was the most bought item in the last 30 minutes or what was the most wishlisted item in last 30 minutes etc. so all of those could be inferred by this spark stream service.

This spark stream also puts all these data in to Hadoop, saying this user has liked this product, this user has searched for this product, anything that happens. On top of this hadoop we could run various kind ml algorithms. Given a user and a certain kinds of products that they like, we could be able to figure out **two kinds of information**. One is what other products this user might like and the other thing is how similar is this user to other users and based on products that other users have already purchased we would recommend a particular product to this user. So all of those is calculated by this **spark cluster**, on top of which we can run various ml jobs to come up with this data.

#### Recommendation Service

Once we calculate those recommendations this spark cluster basically talk to recommendation service which is basically repository for all the recommendations and it has various kinds of recommendations. One is given a user ID if you store **general recommendations** saying what are the most recommended products for this user and it will also store the same information **for each category** saying for electronics for this user these are the recommendations. So when a person is actually on home page they will first see all the general recommendations and if they navigate into a particular category they'll see the recommendations which are specific to the category.

#### Logistics and Warehouse Service

Normally these two components come in **once the order is placed**. Also the **serviceability service** will query the Warehouse service to get all the items that are in the warehouse and it will also query logistic service to get details of all the pin codes that are existing or maybe details of all the courier partners that work in a particular locality. With all of that informations this serviceability service will basically **create a graph** kind of a thing saying what is the shortest path to go from point A to point B and in how much time can I get there. 

Important thing is, serviceability service **doesn't really do any calculation at runtime**, it will store all the information in a cache and whenever anybody queries it will query the cache and return the results. No runtime calculation because those kind of calculations are fairly slow. It will calculate all possible requests, so if there are N inputs and M warehouses it will do a **M X N** and calculate all possible combinations of requests that can come to the service and store it in a cache 

#### User Purchase Flow

Let's look at what happens when a user tries to **place an order**. The user interaction to place an order is represented as **User Purchase Flow**. This can be accessed from mobile apps or web browsers. 

##### Order Taking Service

Whenever user ready to place an order the request basically goes to the **order taking service**. Order taking service is part of order management system which takes the order. Order management system sits on top of a MySQL database. **Why MySQL?** because if you look at order and the table form for order it will have a lot of tables. Some with order information, some with customer information, some with item information etc and there are a lot of updates that happen on to the order database for each order. But we also need to make sure that those **updates are atomic** and they can **form a transaction** so that there are no partial updates happening. So we use MySQL here. 

Whenever OT service gets called the first thing that happens is a record gets created for an order and an order ID gets generated. Next thing is we put an entry into the **Redis** saying this order ID O1 was created at T1 and the record expires at T2. Now the record that goes into this MySQL database has an initial status say created so `O1 : T1 : Created`. 

##### Inventory Service

Next step is we call the **inventory service** because we want to **block the inventory**. When a user wanted to purchase an item we will first reduce the inventory count then send the request to payment section. Why do we do that ? Let's just say there was just one TV and three users want to buy at the same time, now we want to make sure only one of them buy the TV and for two of them it should show that it is out of stock.

We can easily enforce that through this inventory service by having a **constraint on to the table** which has the count column saying that **count cannot go negative**. When a user place an order and if the count is one, only one of the users will be able to place an order where the count reduces. For others it'll be a **constraint violation exception** because the count cannot be negative. 

##### Payment Service

Once the inventory is updated then the user is taken to a **payment service**. This service talks to different payment gateways and takes care of all of the interactions with the payment. The interaction with the payment service can lead multiple outcomes. One of them is that payment service could comeback saying that the payment was successful or it could say that the payment failed due to some reason or the person could just close the browser window after going to the payment gateway and not come back, in which case payment service would not give a response. 

Let's just say at 10:01, we got a confirmation from payment service saying **payment was successful**. In that case the order would get confirmed and the order status is now **placed**. So once the payment has been successful we update the order status in the database saying that order is placed. At that time an event would be send in to Kafka saying an order has been placed with details. 

If the payment service say **payment failed** then first of all we need to cancel the order. So the order status is **canceled**. If the payment has failed we also need to increment the inventory count again. So we call the inventory service saying that you decremented the quantity for this particular order ID now let's increment it back. So that's a **rollback transaction** kind of a thing. 

Also we'll have a **reconciliation service** that sits on top of this whole system which **reconciles**. Basically this service checks every once in a while that if there were ten orders overall I do have the **correct inventory count at the end**, just to handle cases where due to some reason we **missed update of inventory count** or the write fail. So all of those scenarios can be handled by the reconciliation flow. 

Last scenario is payment service **could not at all come back**. The user close the browser window, the flow ends there and payment service doesn't respond back. We can't keep the inventory block because the item is still physically available in our warehouse but the database says that it's not available so we need to bring it back. So for that we use the **Redis**. At Redis, this record will get expired and Redis will have a **expiry callback** which say that this particular record you inserted has got expired now do whatever you want. At that point in time order taking service will cache that event and say that this particular record has expired and so we follow the same flow that was followed for payment cancellation. So time out the payment and mark it cancelled. So this order would be moved to cancel State in the database and the inventory would get updated back again. 

##### Risk Conditions

But there could be **potential risk conditions** what happens if your payment success event and your expiry happens at the same time. First scenario is that payment success comes first and then expiry happens. This is a normal scenario that is bound to happen for all the orders. So one optimization we could do here is, each time we get a payment successful or payment failure event we can **delete the entry from Redis** to make sure that expiry event does not come in. Also this will help us to **save the memory footprint** as well because the lesser amount of data we have in Redis, the lesser RAM it will consume and it will be much more efficient. 

Other scenario is that expiry comes first and then payment success. Whenever we get the expiry event we would have canceled the order and decremented the inventory count. But now the payment has happened so we could either refund the money back to the customer saying that `we are not able to process so keep your money back`. Alternatively we could also say that, now we have got the money from the customer and we know what the person was trying to order, so we could create a new order mark this payment for that order and then put the status `placed`. 

One thing about **Redis expiry** is it is **not very accurate**, the callback will not get come at the exact time because of the way Redis expiry work. Redis doesn't expire all the records at that point in time, it basically checks all the records every once in a while for expiry and whenever it finds some of the keys that are expired then it expire that. So it might happen that it expires after a few minutes. Now in this scenario it doesn't really matter. But if you are trying to do something which is much more **mission-critical** then you have to do a different approach like implement a queue and keep polling that queue every second. 

All the events like payment has happened or not happened etc, you would put all of those **events into kafka**. One more reason for this is, let's say there was this one item left and somebody has purchased that. Now you want to make sure that **nobody else is able to see that item** because anyway the inventory count has become zero so you cannot take an order. So you need to make sure that it should be removed from search. The **search consumer** would take care of this, so as soon as an item goes out of stock it would basically remove that element from the listing. 

#### MySQL DB bottleneck

There is one problem, the Order MySQL DB can very quickly become a bottleneck because there are a lot of orders in a day and normally you would have to keep order data for a couple of years for **auditing purposes**. The whole use case of having MySQL is to take care of the acid properties for orders that are being updated. But for the orders that are not being updated we don't really need any of these functionalities. So once the order reaches a **terminal status**, let's just say an order is delivered or cancelled we can basically move it to **Cassandra**. So there is an **archival service** which is a **cron service** and runs every day at some frequency and it pulls all the orders which have reached the terminal status from this MySQL and puts it into Cassandra. 

There are two other services, **order processing service** and **historical order service**, these two are components of the **order management system**. Order processing service takes care of the whole **lifecycle of the order**. So once the order has been placed, any changes to that order happen through this. This will also provide API to get the orders if somebody wants to get order details. Historical order service will provide all kinds of APIs to fetch information about historical orders. 

Archival service basically query order processing service to fetch all the orders who have reached terminal status, get all of those details and then call historical order service to insert all of those entries into its Cassandra and once it has got a success saying I've inserted into Cassandra, replicated in all the right places then it will basically call order processing service to delete that. If there is some error in between it will retry the whole thing and because it is **idempotent** it doesn't really matter we can replace as many number of times as we want. 

#### Orders View

The user can go and see their past orders, that will be powered by the **orders view**. So the order view service which is the backend of order view, talks to order processing service and historical order service. This service query order processing service to fetch information about all the **live orders** who are in transit and it will call historical order service for all the orders that have been completed, it will merge all the data and it will return back the data to display on the App or website. 

Why did we **use a Cassandra** here? because historical order service will be **used in a very limited set of query patterns** like get information of a particular order ID or get all the orders of a particular user ID or get all the orders of a particular seller. So there will be a small number of queries that will turn on this Cassandra and Cassandra is very good when you have that **finite number of queries** but a large set of data. So that's the reason we have used the Cassandra. 

Whenever an order is placed we want to notify the customer that your order has be successfully placed, it will be delivered to you in few days etc. You'll also have to notify a seller and you might have to notify somebody else or when something happens like say an order is cancelled by the seller so you want to notify the customer. So all of those notifications would be taken care by this **notification service**. It handles all various kinds of notification like SMS, emails and all of that. 

#### spark streaming consumer

While the user is placing all the orders and all of those things are happening, all the events are going into the Kafka on which we are running a **spark streaming consumer**. One of the very few thing that it does is it can **publish a report** saying in the last one hour what are items which have been ordered the most or which category has generated the most amount of revenue. So it can publish out some of the reporting metrics that we want to quickly look at. 

It will also put the whole of the data into Hadoop cluster, so we have real order information of a user which is a very good thing for recommendations. On top of Hadoop cluster we will have some spark jobs which will run some standard algorithms like **ALS algorithm (Alternating Least Squares)** which would be predicting that this particular user, given that he's ordered certain things in the past, what are the next set of things this person might order or because this customer looks like another customer and that customer ordered so-and-so product so this customer might also order so-and-so product. So there could be a lot of models that you can run to predict what are the right set of recommendations for this user which would be dense into this **recommendation service**. It will store these recommendations into its **Cassandra** and the next time in the user comes in, we will show them those kind of recommendations. 

