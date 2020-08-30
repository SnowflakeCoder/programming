- [Choosing the Best Database](#choosing-the-best-database)
  * [Three factors](#three-factors)
  * [Caching Solution - Redis](#caching-solution---redis)
  * [File Storage Options (Blob = S3+CDN)](#file-storage-options--blob---s3-cdn-)
  * [Text Searching capabilities (elastic search and solar)](#text-searching-capabilities--elastic-search-and-solar-)
    + [Support for Fuzzy search](#support-for-fuzzy-search)
    + [Search Engine are Not Primary Data Source](#search-engine-are-not-primary-data-source)
  * [Time Series Database (TSDB)](#time-series-database--tsdb-)
  * [Analytics Requirements](#analytics-requirements)
  * [Relational Database](#relational-database)
  * [Document Database](#document-database)
  * [Column Oriented Database(Columnar DB)](#column-oriented-database-columnar-db-)
  * [Combination of Databases](#combination-of-databases)
    + [E-commerce platform](#e-commerce-platform)
    + [Reporting Application](#reporting-application)
  * [Summary](#summary)
  * [Reference](#reference)



## Choosing the Best Database

Database is generally **do not impact your functional requirements** but the **non-functional** requirements are the ones which are impacted. The choice of database would impact **how well your design can scale** up to the requirements that are given as part of your non-functional requirements. 

### Three factors

These are **three factors** we should consider while making a database choice.

- **structure of the data** : whether it's a very structured data or a totally non structured data. 
- **query pattern** 
- **amount of scale** that you need to handle.

### Caching Solution - Redis

Whichever system you are designing you would <u>definitely have to use some caching solution</u> there. There are a lot of use cases for caching like 

- you are **querying a database** and you do not want to query the database a lot of time you could catch the value in a cache.
- if you are **making a remote call** to a different service and that is having a **high enough latency** you might want to cache the response of that system locally at your end in a caching solution 

Generally the way caching works is you have a key and you have a value, <u>key normally is whatever your where clauses</u> in a query or whatever your query/request params are when you're making an API call and value is basically the response that you are expecting from the other system. So normally all of these kind of values would be stored in a key value stores. Very common used solutions are **Redis**, **memcache**, **hazelcast** etc. Normally I tend to use **Redis** because that is a very **battle-tested solution** and it is **used by all the big companies** in the world and it is **fairly stable**. So Redis is the solution for caching.

### File Storage Options (Blob = S3+CDN)

Let's say you are designing a system like Amazon/Netflix where you are having various products, and the sellers would be uploading product **images and videos**. So you need a data store to **store those images and videos**. Wherever you have a **image, video kind of a thing** we'll use a **blob storage**. These are **not really databases**. Databases are fundamentally meant for things where you can query on. These files are not something which you normally query on but you will **just serve it as it is**. There are a lot of **providers for blob storage** (storing images, videos), one of the most common ones and a fairly cheaper one is **Amazon S3** data store and used by a lot of companies.  

Along with S3 you might want to use a **content delivery network** (**CDN**). CDN is generally used for **distributing the same image geographically in a lot of location**. For example, you have a product image that is stored in Amazon s3 as a **primary data source** and there are a lot of people coming from throughout the globe who are accessing that product. So you might want to **distribute that image into various servers** across the globe so that people can **query them in a much faster way** as compared to querying an s3 which is probably located in a couple of locations. So we will be using CDN for sending out images/videos throughout the whole world. So for all blob kind of content you would be using something like **S3 + CDN**.

### Text Searching capabilities (elastic search and solar)

If you're building a product like Amazon then you need to provide **text searching capabilities** on various products. Searching would be provided on a text of title and on the text of description of various products. Similarly when you design a Netflix you want the users to search on movie name, titles, Genres etc. So for all of these use cases you would be using a **text search engine**. Very common implementation of a **text search engine** is provided by **elastic search** and **solar** and both of them are built on top of **Apache Lucene**. Lucene fundamentally provides these text searching capabilities and that is then being used by both elastic search and solar.

#### Support for Fuzzy search

If you are designing an uber or Google Maps, then you may want to provide text searching capability with **support for fuzzy search**. Let's say if you are searching for the word "Airport" and the user typed it with the **wrong spelling**. Now if a user searches with the wrong spelling word and you do not return back any results then that's a **bad user experience**. So you need your database to be able to figure out that the user did not really meant this word. **How does that database identify** this? This wrong word can be converted into the **correct spelling by changing few characters**. This is called **Edit distance**. So you can provide a **level of fuzziness** that your search engine needs to support. **Fuzziness factor** depends on a lot of factors and one of them is the Edit distance and in most cases, fuzzy search is implemented by changing few characters (edit distance). So the **idea of using elasticsearch** is that it supports **fuzzy search**. So we can <u>use fuzzy search to handle all the typos and spelling mistakes</u> and also want to give a <u>similarity kind of a thing</u>.

#### Search Engine are Not Primary Data Source

Both elastic search and solar **are not databases**, these are search engines. The difference is whenever you write something in a database database gives you a **guarantee that that data wouldn't be lost** but search engines don't give you any such guarantee. But they claim that they are giving a **good enough availability** and **redundant** in all the data but **potentially data could be lost**. So you should **not keep any of these as your primary source of truth**. Your primary data source should be somewhere else and you could load that data into either of these systems to provide the searching capabilities and these two are very efficient at search.

### Time Series Database (TSDB)

If you are building an **application metrics tracking system** like Graphite, Grafana or Prometheus where a lot of applications are pushing metrics data related to their throughput, CPU utilization, latencies etc. So if you want to build a system that store these **metrics kind of a data**, then use a **time series database**. Time series database is like an **extension of relational databases** but with not all the functionalities and certain additional functionalities. The regular relational databases would have the ability to update/query random records. But whenever you are building a **metrics monitoring system** you would **never do random updates** you would **always do sequential updates** in **append-only mode**.

So if you have put an entry at time t1 the next entry would be at time T2 which is greater than T1. The next entry would be at time T3 which is greater than T1 and T2. So it's an **append-only write mode**. Also the read queries are kind of **bulk read queries with the time range**. Like query for last few minutes of data or few hours of data or few days of data but you **don't do a random read or a random update**. Time series databases are **optimized for this kind of a query pattern** and input pattern. There are a lot of time series databases like **InfluxDB, OpenTSDB, Prometheus** etc, you could use if you have that kind of a use case. 

### Analytics Requirements

let's just take an example where you want to provide analytics on all the transactions like analytics on how many orders, what geographies are giving what revenues, which is the most sought after item etc. So when you have **lot of information** and you want to store all of that information in a certain kind of a data store for various kind of **analytics requirements** there you need a **data warehouse**. Its basically a large database in which you can **dump all of that data** and provide various **querying capabilities on top of that data** to serve a lot of reports. These are generally **not used for transactional system**, these are generally **used for offline reporting**. So if you have that kind of usecase then you can use **Hadoop** where you put in a lot of data from various transactional systems and then build a lot of systems that can provide reporting on top of that data.

### Relational Database

Suppose you want to choose between a **relational and a non-relational database**, the first choice that we need to make is whether we have a **structured data or a non-structured data**. So if you have a very structured information then possibly a relational database make sense. A structured information would be an information that you can **easily model inform of tables** and tables would have rows and columns of information. For example if you want to store user profile, it would have name, email address, city, phone number etc a bunch of very standard information that each user will have then that would be a structured information. 

Suppose we have a structured data then next question is, do we need any **ACID guarantees** from the database or not. Let's say that you're building a payment system and fundamentally it will have two queries, one of them will reduce the amount from one account and the other query would add the same amount into the beneficiary's account. So your database should be able to provide you certain guarantees which wrap both these queries into **transactional boundaries** saying either both of them would execute or both of them would not execute. It should also provide you some **consistency** thing like if you have done a transaction then the next time you fetch the account balance it should reflect the amount. If you have that kind of requirement (ACID guarantees) then you need to use a relational database and there are multiple providers of relational database like mySQL, Oracle, SQL server, Postgres etc. 

Let's say you have relational (structured) data but you **do not need ACID guarantees** then you could use a relational / non-relational database. It wouldn't make much of a difference because its **easier to map a structured data into a NOSql model** so either of these scenarios would be fine.

### Document Database

Let's say you are trying to build a **catalog kind of a system** for an e-commerce platform like Amazon which has **information of all the items that are available** on that platform. Here you do **not have structured data**. Let's say there is an item like shirt and each item would have certain attributes. A shirt would have attributes like a size, color etc. When you are on an e-commerce platform you not only need to see these attributes but you would also **also want to query that**. Querying on a JSON or random attributes is a bit tricky on the traditional databases. So we need to use a document DB and these are certain kinds of databases that are optimized for those kind of queries. So when you have a lot of data not just data in terms of volume but **in terms of structure** and you have lot of attributes that can come in and a wide variety of queries that can come in then you need to use a **document DB**. There are a lot of providers of documentDB like **MongoDB, Couchbase** etc. Elastic search and Solr (for text searching) are also special cases of document database. Even if your **data is not relational** and you **do not have complex queries** and only have a couple of straightforward queries you could still use a document DB. 

### Column Oriented Database(Columnar DB)

Let us take an example of Uber were all the drivers of Uber are continuously sending location. So they kind of translate into X number of location records per day. But this X would not be a constant, it would be a growing number because the number of drivers of Uber are increasing day by day. It would be increasing **but in a more than a linear fashion**. So that is what an **ever increasing data** plus if you have **finite number of queries** like you want to track location pings of drivers. So the query that you will do is find all the location ping of a driver by driver ID. So if you have **less number of queries** but a large amount of data then **columnar databases** or column oriented database would be the best choice for you.

**Cassandra** and **HBase** are the most used and most stable options. Both are **equally competent** enough to handles the data of this scale, **performance wise both of them are roughly similar**. I generally prefer using **Cassandra over HBase**, the only reason being **Cassandra is easy to deploy**. HBase generally have a **lot of components** so it has a lot of **operational overhead** in terms of **deployment and maintaining** it over time. If you want to set up Hbase, we will require the **zookeeper**, a **Hadoop cluster** etc, and that adds a lot of maintenance. Every data in cassandra is sharded across a **partition key** so each **query has to happen on a partition key**. 

### Combination of Databases

#### E-commerce platform

Let's build an **e-commerce platform** like an Amazon. Now when you are **managing inventory** on that side you want to make sure that you are **not over selling items**. So let's say there is just one quantity of a particular item left and there are 10 users who want to buy that. You want to have this **acid properties** there to make sure that only one of the users should be able to commit the transaction other users should not be able to commit the transaction by putting constraints and all. Now it would make sense to use a **RDBMS** database for the **inventory management system** of a system like Amazon. But the data of Amazon it is an **ever interesting data** because the number of orders are additive each day and they cannot purge the data due to lot of legal reasons plus the number of orders are also increasing so it naturally fits into **Cassandra**. So what you could use is a **combination of both of these databases**. You could use mySQL (or any other a RDBMS alternate) for storing data about the orders just placed and not yet deliver to the customer once the item is delivered to the customer you can then **remove from the RDBMS and put it into Cassandra** as a permanent store. 

#### Reporting Application

let's say if you want to **build a reporting kind** of a thing which lets you query something like it get me all the users who have bought sugar in last five days. But there are a lot of sellers selling different sugar alternates of different companies. So sugar would then be like a lot of item IDs.  On top of those lot of item IDs there must be lot of orders. As explained above orders would either be in Cassandra or MySQL. But if you are **doing random queries** where you might want to query on who bought sugar or who bought TV etc logically should be using a **documentDB**. So what you could do is you could store a **subset of order information** (basically store the **querying part** over there) into a **MongoDB** (documentDB). This querying part can be userID, orderID for a particular date range. On this database you could run a query which will return you a list of users and a list of orders and then you could take those orderIDs and query on both of these systems (Cassandra & MySQL) . So here we are using all the **three databases in combination** to provide various querying capabilities. So in any real world scenario you would have to use a combination of such databases to fulfill the functional and non-functional requirements that you have.

### Important Points

**Cassandra instead of MySQL**: we would not have had access to the transactions and the constraints and all of that you would have to **implement it on application side**. If you use the important features of MySQL then it will help us to make the code on application side much more smoother.

**Monitoring (Grafana)**: We definitely need to monitor how are our CPUs and Memory is behaving. Across the whole infrastructure we need to keep an eye on how my CPU usage percentage is, how my memory usage percentage is, how my disk usage is etc. All of these monitoring could be done through a **Grafana** kind of a tool on which I can set up alert. So if the let's say a particular metric has some threshold the moment it cross that threshold or with certain conditions it could send out an alert and the team could get notified that something is potentially wrong and they need to look at that. This will help us to **achieve our NFRs** that we talked about of latency and high availability. Let's just say memory utilized more than what we expected, eventually it will lead to some machines going down and it will lead to us having a lower availability. So these are the things that we need to **monitor and alert on**. 

**Multiple Datacenters**: Suppose the whole application is spread across geographies and there's an **earthquake** in one of the data centers and everything just goes away out of the blue. So let's say we have these four data centers data center 1, data center 2 data center 3, and data center 4 which are located in **different geographical regions across the globe**. Now we want to create a **topology** in a way that we do get low latency and high availability.

- one very simple approach that we could do is say that DC 1 is our primary and all the three DC's are our secondary data centers and **data is replicated** to all the three data centers in **near real time**. But it's not very good design because we are just using 25% of our capacity as primary which is active and rest three data centers are sitting idle
- So what we could instead do is **divide the data centers and thus the globe into two parts** (R1 & R2). The countries or people accessing our services who are closer to this region(R1) will connect to this region(R1) and the people who are closer to that region(R2) will connect to that region(R2). We can use this approach if all the **data in the system is fairly specific to a geography**. So we are kind of **bifurcate the data as per geography**.

Let's just say DC1 is the primary in this region(R1) and DC3 is the primary in R2 okay. If DC1 goes down all the data in DC1 is getting replicated to DC2 in near real time and DC2 can become active and all the clients who are connecting to DC1 are now connect to DC2. Clients are connected via some **DNS** to DC1. If this link goes down **DNS can flip and connect to DC2**. So we are basically dividing our infrastructure into two halves thereby clients who are closer to this region are connecting to the servers that are closer to them thus giving them **lower latency**. So to **reduce the latency and increase the availability** we can increase the number Datacenters (Regions). 



### Summary

![Database Choice.jpg](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Database%20Choice.jpg?raw=true)

### Reference

https://www.youtube.com/watch?v=cODCpXtPHbQ