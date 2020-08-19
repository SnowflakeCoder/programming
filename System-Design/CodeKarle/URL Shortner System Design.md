# URL Shortner System Design

Design a URL shortening service like **tinyurl.com** / **Bitly**. 

## Functional Requirements

- Given a long URL you **return a short URL**.
- When somebody hits a short URL then you basically **redirect to the longer URL**.

## Non-functional Requirements 

- Service needs to be **highly available**.
- It should work at a very **low latency**.

## Short URL Length

The length of the short URL totally depends upon the **scale that we need to build** because we **should not run out of the short URL**. Another option is we build a system **agnostic of the length** and we start from somewhere and it keeps on incrementing. But its better to start with a fixed length. So the questions you need to ask is 

- **What is the traffic** you are looking at? 
- **How many unique URLs** will come for shortening probably every day / every month.
- Until **what duration you need to support** that.

Let's just assume that, once a URL is converted into a shorter URL you need to save it for at least next ten years. If there are X number of requests that comes to you every second, then per year it will be **X\*60\*60\*24\*365**. So you should be able to build a system which can handle these number of unique requests.

## CharacterSet & Length

**What are characters** can you include in the short URL so you should come up with a **character set that is allowed for short URLs**. Generally people take the capital A-Z and small a-z and numbers. So these are 62 alphabets which you can use in the shortURLs. Now you need to find the length which can handle these number of unique URLs (unique combinations of these 62 characters).  So basically you need to come up with a number N where 

```java
pow(62, N) > X*60*60*24*365*10  // to support for 10 years.
N >= log62(X*60*60*24*365*10)
```

## Diagram

![URL shortner design.jpg](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/URL%20shortner%20design.jpg?raw=true)

## Short URL Service

First API is when somebody hits a long URL as input and gives back the short URL. So it will call the **short URL service** which somehow generate a short URL and stores it in the database and returns the short URL to the customer.

Second API is when somebody hits a short URL where the short URL service would fetch long URL from the database and then redirect the user to the longer URL.

## Generate short URL

### Collision & Redis Solution

So there are multiple instances of the Short URL service and all of them are getting some requests. Then we will **end up with a collision** if two services generate a same short URL for two different long URLs. It's a problem because we cannot have one short URL pointing to two long URLs. Possible solution could be that we **first check in the database** and then retry but then that's **not really efficient**.

So what we need is a **predictable way to generate a short URL** knowing that there would be no collisions at all. One very simple way to do that is using the **one of the features of rediff**. So let's use a Redis cluster and all of these services **request a number from Redis**. Redis make sure that it will always return a unique number. So basically what it will do is it will start the counting from 1 all the way up to billions, trillions and each time it gets a request it increments the counter and response backs. So now we will make sure that all of these services are getting unique numbers and from unique number **at base 10 they can convert to base 62** and generate the short URL.

### Redis  - single point of failure

But we still have a problem because everybody is connecting to Redis. So **Redis under a huge amount of load** and with all the requests that have to generate a short URL this **Redis become a single point of failure** and there is no backup to it. So if this goes down then there is no way we can't recover. Moreover if the <u>scale goes beyond the capacity of one Redis</u> machine, let's say if one Redis is not able to scale to the latency requirements that we wanted to, this will again start choking the whole system.

Another alternate is that we **keep multiple Redis** so that will give us high availability and better performance. But then these Redis may start **generating duplicate numbers** if both are starting from the same index. So we need to make sure that both these readies do not generator same numbers. One very simple way is to **give a series to each Redis to start with**. But what if you have to add a third Redis then it becomes complicated. So now you need somebody to manage what series are being given to whom.

### Without using a Redis

So if you have that management system which can decide who should generate what series then we can easily get away from the Redis design. So we basically need to make sure that our Short URL service should be able to generate a unique number which will **never ever get repeated even by any other instance** of the service. So each of these service instances generate a unique number, convert that number from base 10 to base 62 and return back.

## Long to Short UI

Using this UI Somebody can give a long URL and expect to get a short URL as an output. This UI talks to a load balancer which talks to multiple instances of short URL service. So all these services needs to have a number and making sure that none of the other services generate the same number. One simple way is to do that is **assign ranges to each of the services**, so I have introduced a **token service**. So the short URL service will request a token range from token service. Each time token service gets a request it will turn on a **single threaded model** and it will give a range to any of the service instances. 

So the short URL services call token service either when they start up or when they are running out of the range. This token service can be built on top of a **MySQL** because it runs at a **very low scale**. This token service can return a larger range so that it will get a call once in a couple of hours. So now the token service has given the ranges to each of these services. So the short URL service takes the first number from the range, **convert that into base62**, save the shortURL and longURL mapping into **Cassandra** and return the output. If next request comes, it increment the number and follow the same steps.

When the service are closing the end range, it query token service and get a next range. Token service will <u>keep a range as a record and keep a flag whether it is assigned or not</u>. When you get a request you take the first unassigned token range from MySQL, **change state to assigned** and return that. Because it sit on top of MySQL database it will make sure that it is always unique.

Let's say there is a **massive amount of traffic**, all we need to do is keep multiple instances of token service at max. We can also can **increase the length of the range** so that it doesn't get bombarded too often and anyway this service will be distributed in **multiple geographies and multiple data centers** so that this doesn't become a single point of failure. So the short URL service will be iterating over the range in-memory and assigning one token to each request. What if this service got a range started working for a while, used a couple of token and then it kind of got shut down. So those **tokens gone forever** as there is no track record of how many tokens are being used. So when it will get started up again the next time it will get another token range. With the **length of 7 for a short URL** we are able to support **3.5 trillion unique numbers**. So even if this happens a couple of times in a day, and if you lose out a couple of thousands of tokens out of this range it **doesn't really matter**. But if you start tracking those tokens then it will become a **fairly complicated system**. So when the system shuts down all those tokens go away and we don't need them we will get a new range of token and work out of that. 

## Short to Long UI

When a short URL is hit all you do is, you get a request in short URL you hit your database and you get the longer URL you send it back to the short URL service. The service does a redirect and the user gets redirected to the main URL.

## Cassandra

**Why did I use a Cassandra?**  Ideally I could have **used any database**. All we need to do is to keep a database which can handle these many number of unique URLs. A MySQL DB at these number of Records would start giving some problems, but we can **shard** it probably and make it work.

## Metrics

This system should also give us some metrics about how it is being used, what kind of urls are the top most URLs, what kind of geographies request come from, what kind of hits are you getting or what kind of user agents or devices the people are connecting from etc. All of those things would be valuable information for any person who's generating a short URL. Similarly for us let's say if we have four data centers and we want to choose any two of them as primary and two of them a stand by data centers and they are spread across the globe. We could make a **data-driven** decision over here depending upon what kind of traffic we are getting from what kind of geography and wherever we are getting most amount of traffic from the data centers closer to those geographies could be made as primary data center. So its important to have a good enough amount of analytics that the system emits out.

Whenever we get a request with a short URL, we query our database get the longer URL from the database based on the short URL and return it back to the client which then redirects the user to the longer URL. What we essentially do is each time the request comes to us that it will give us some **origin header** saying what is the platform (FB/linkedIn) from where this request has come in. It will also have information about the user agent which is calling let's say if it's an Android application iOS application or a browser it'll have that kind of an information as well and it will also have a source IP address. All of these information before sending out the response we will also put that into a **Kafka** which will be used to then power the analysis. Based on the IP address we'll also be able to come up with what country it is so all of those things can be taken care of on the analysis side.

## Asynchronous & in_memory cache Kafka

But if we start putting into Kafka on each request when we get the request it will impact our non-functional requirement of latency. Because now we are doing an additional a step in the process and that length is the latency so I would not recommend doing that what instead we could do as a fairly nice solution is make that kafka request as a **parallel call** so you can have a different thread in which you can send the Kafka right and it is returned back to the user and asynchronously it can be returned to copper now what is the problem in doing an asynchronous thing there potential possibility that the Casca right could sell for whatever reason and you have returned back to the user so you'll miss certain analytics right now in this kind of a system because payment and all is not involved it's just some simple analytics we should be okay if we are losing out on certain events I think it's okay if we build it this way also worst case we'll just lose out on some analytics which is fine right but can we improve it even further so the problem with this approach is each time we write to Kafka it involves some IO right there's the CPU involved there's a I you and well there's a network traffic and there's a network transfer in what way can we avoid all of this doing all of this on each call probably so what we could do is instead of writing into cache on each request we could maybe aggregate that information locally in a machine so maybe you can have a queue or some kind of a data structure in which you are persisting each record that we got a request for this short URL with count 1 the request again comes in you increment the count to count or something of that sort you could implement it or you could simply make it as a queue of all the requests now whenever the size of the data structure process some threshold or you could do it a time base and also laying every 10 seconds will flush that so in whatever condition you can flush the data into that data factor into one column Kafka so basically you are reducing the i/o that you are doing the dish request and doing it as a batch right the benefit you get is you can have now drive much more performance out of that single machine so thereby helping us in the non-functional requirements of high availability low latency all of that and secondly it will improve the overall performance of the system again the drawback here is now you can lose out not just one event but a lot of events now based on your conversation with your interview and decide on is that ok or not personally I would say it's a fairly ok thing if you lose out on 10 20 30 events that's perfectly fine cool so now we have put all the events into kaftan now what happens so there are a bunch of ways you can implement analytics one very simple approaches you can dump all of this data and to huddle and then build some hive queries on some kind of queries on top of it which will generate you aggregate results right alternatively you could also have a start steaming job running which comes up every 10 minutes let's say and takes all the returned in last 10 minutes and does some aggregate analysis on top of it saying which URL was it how many times with geography people came in how many times something of that sort and dumps the aggregate information into a datastore which can then be used to power various kinds of analytics that user can see so you could implement it in either ways you want based on the conversation with your interview so yeah I think this should be it for a URL shortening system thanks for watching this video if you have any suggestions on what videos we should make next or how we could improve this one do let us know by commenting here and don't forget to subscribe to this channel like the videos and share the videos with your friends while we keep working to get more such content to you happy learning

