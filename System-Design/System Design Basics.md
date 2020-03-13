# System Design Basics

### Key Characteristics of Distributed Systems

Key characteristics of a distributed systems include Scalability, Reliability, Availability, Efficiency, and Manageability. 

#### Scalability

Scalability is the capability of a system, process or a network to grow and manage increased demand. A scalable system would like to achieve this scaling (increased demand or growing amount of work) without performance loss. Generally, the performance of a system declines with the system size, due to the management or environment cost. For instance, network speed may become slower or some tasks may not be distributed because of their **inherent atomic nature**. Such cases would limit the speed-up obtained by distribution. A scalable architecture avoids this situation and attempts to balance the load on all the participating nodes evenly.

##### **Horizontal vs. Vertical Scaling**

Horizontal scaling means that you scale by adding more servers into your pool of resources whereas Vertical scaling means that you scale by **adding more power (CPU, RAM, Storage, etc.)** to an existing server.

Horizontal-scaling is easier by adding more machines into the existing pool; Vertical-scaling is usually limited to the **capacity of a single server**, scaling beyond that capacity often involves **downtime** and comes with an **upper limit**. Good examples of horizontal scaling are Cassandra and MongoDB. A good example of vertical scaling is **MySQL**.

#### Reliability

Reliability is the **probability a system will fail in a given period**. A distributed system is considered reliable if it <u>**keeps delivering its services** even when one or several of its software or hardware components fail</u>. In such systems any failing machine can always be replaced by another healthy one, ensuring the completion of the requested task. For instance, any user transaction should never be canceled due to a failure of the machine that is running that transaction. A reliable distributed system <u>achieves this through **redundancy**</u> of both the software components and data. If one server fails, another server that has the exact replica of the data should replace it. So, redundancy has a cost, and a reliable system has to pay that to <u>achieve such **resilience** for services</u> by <u>eliminating every **single point of failure**</u>. 

#### Availability

Availability is the time a <u>system remains operational</u> to perform its required function, in a specific period. That means the percentage of time that a system, service, or a machine remains operational under normal conditions. Availability takes into account <u>maintainability, repair time, spares availability, and other logistics considerations</u>. 

#### Reliability Vs. Availability

If a system is **reliable, it is available**. However, if it is available, it is not necessarily reliable. <u>Reliability is availability **over time**</u> considering the full range of possible real-world conditions that can occur. It is possible to achieve a high availability even with an unreliable product by <u>minimizing repair time and ensuring that spares are always available</u> when they are needed.  

#### Efficiency

Let’s assume an operation that runs in a distributed manner, and delivers a set of items as result. Two standard measures of its efficiency are the **response time** (or **latency**) that denotes the <u>delay to obtain the first item</u>, and the **throughput** (or **bandwidth**) which denotes the <u>number of items delivered in a given time unit</u> (e.g., a second). 

#### Serviceability or Manageability

Serviceability or manageability is the <u>simplicity and speed with which a system can be repaired or maintained</u>; if the time to fix a failed system increases, then availability will decrease. Things to consider for manageability are the <u>ease of diagnosing and understanding problems when they occur</u>, ease of making updates or modifications, and how simple the system is to operate (i.e., does it routinely operate without failure or exceptions?). <u>Early detection of faults can decrease or avoid system downtime</u>. For example, some enterprise systems can automatically call a service center (without human intervention) when the system experiences a system fault.

## Load Balancing

Load balancer (LB) helps to improve **responsiveness and availability** of applications by <u>distributing load(traffic) across multiple resources</u> according to some **metric** (random, round-robin, random with weighting for memory or CPU utilization, etc.). Thus, a load balancer <u>reduces individual server load</u> and prevents any one application server from becoming a **single point of failure**, thus improving overall application availability and responsiveness. LB also keeps <u>track of the status of all the resources</u> while distributing requests. If a server is not available to take new requests or is not responding or has elevated error rate, LB will stop sending traffic to such a server.

To utilize <u>full scalability and redundancy</u>, we can **try to balance the load at each layer of the system**. We can add LBs at three places:

- Between the user and the web server
- Between web servers and an internal platform layer, like application servers or cache servers
- Between internal platform layer and database.

![%D0%B7%D0%B0%D0%B2%D0%B0%D0%BD%D1%82%D0%B0%D0%B6%D0%B5%D0%BD%D0%BD%D1%8F%20(51)](https://coursehunters.online/uploads/default/optimized/1X/6f5ed705d98c5aa5d0d9f643cac1ea339d762b11_2_690x171.png)

#### Benefits of Load Balancing

- Users experience <u>faster, uninterrupted service</u>. Users won’t have to wait for a single struggling <u>server to finish its previous tasks</u>. Instead, their requests are immediately passed on to a more readily available resource and decrease wait time for users.
- Service providers experience <u>less downtime and higher throughput</u>. Even a <u>full server failure won’t affect the end user experience</u> as the load balancer will simply route around it to a healthy server.
- Smart load balancers provide benefits like **predictive analytics** that determine **traffic bottlenecks** before they happen. These are key to automation and can help drive business decisions.
- By distributing load equally system administrators experience <u>fewer failed or stressed components</u>. Instead of a single device performing a lot of work, load balancing has several devices perform a little bit of work.

#### Load Balancing Algorithms

**Choose the backend server** - Load balancers consider two factors before forwarding a request to a backend server. They will first ensure that the server they choose is actually responding appropriately to requests and then use a pre-configured algorithm to select one from the set of **healthy servers**. 

**Health Checks** -  To monitor the health of a backend server, “health checks” regularly attempt to connect to backend servers to <u>ensure that servers are listening</u>. If a server fails a health check, it is automatically removed from the pool, and traffic will not be forwarded to it until it responds to the health checks again.

1. **Least Connection Method** — This method directs traffic to the server with the fewest active connections. 
2. **Least Response Time Method** — This algorithm directs traffic to the server with the fewest active connections and the lowest average response time.
3. **Least Bandwidth Method** - This method selects the server that is currently serving the least amount of traffic measured in megabits per second (Mbps).
4. **Round Robin Method** — This method cycles through a list of servers and sends each new request to the next server. When it reaches the end of the list, it starts over at the beginning. It is most useful when the <u>servers are of equal specification</u> and there are not many persistent connections.
5. **Weighted Round Robin Method** — The weighted round-robin scheduling is designed to better handle servers with <u>different processing capacities</u>.
6. **IP Hash** — Under this method, a **hash of the IP address of the client** is calculated to redirect the request to a server.

#### Redundant Load Balancers

The **load balancer can be a single point of failure**; to overcome this, a second load balancer can be connected to the first to form a cluster. <u>Each LB monitors the health of the other</u> and, since both of them are equally capable of serving traffic and failure detection, in the event the main load balancer fails, the second load balancer takes over.

#### Types of load balancers

1. **Smart Clients**
A smart client will take a pool of service hosts and balances load across them. It also detects hosts that are not responding to avoid sending requests their way. Smart clients also have to <u>detect recovered hosts, deal with adding new hosts</u>, etc. So creating a smart client is very complex.

2. **Hardware Load Balancers**
The **most expensive**–but **very high performance**–solution to load balancing is to buy a dedicated hardware load balancer (eg: **Citrix NetScaler**). Hardware solutions are remarkably expensive, and they are also <u>"non-trivial" to configure</u>. Mostly companies use them only as the **first point of contact** for user requests to their infrastructure and use other mechanisms (smart clients or the hybrid approach called software load-balancers) for load-balancing for traffic within their network.

3. **Software Load Balancers**
**HAProxy** is one of the popular open source software LB. If we can control the machine where the client is running, HAProxy could be running on the same machine, if not HAProxy can run on an intermediate server. Each service we want to load balance can have a locally bound port (e.g., localhost:9000) on that machine, and the client will use this port to connect to the server. This port is, actually, managed by HAProxy; every client request on this port will be received by the proxy and then passed to the backend service in an efficient way (distributing load). HAProxy manages health checks and will remove or add machines to those pools. 

For most systems, we should start with a software load balancer and move to smart clients or hardware load balancing as need arises.

#### Load Balancing and SSL

Secure Sockets Layer (SSL) is the **standard security technology** for establishing an encrypted link between a web server and a browser. SSL traffic is often **decrypted at the load balancer**. When a load balancer decrypts traffic before passing the request on, it is called **SSL termination**. The load balancer saves the web servers from having to expend the extra CPU cycles required for decryption. This improves application performance. However, SSL termination comes with a security concern. The traffic between the load balancers and the web servers is no longer encrypted. This can expose the application to possible attack. However, the risk is lessened when the load balancer is within the same data center as the web servers.

Another solution is the **SSL pass-through**. The load balancer passes an encrypted request and then the web server does the decryption. This uses more CPU power on the web server. But organizations that **require extra security** may find the extra overhead worthwhile.

#### Load Balancing and Security

Load Balancing plays an important security role as computing moves evermore to the cloud. The <u>off-loading function of a load balancer</u> defends an organization against **distributed denial-of-service (DDoS) attacks**. It does this by shifting attack traffic from the corporate server to a public cloud provider. Hardware defense, such as a perimeter firewall, can be costly and require significant maintenance. Software load balancers with cloud offload provide efficient and cost-effective protection.

In the **seven-layer Open System Interconnection (OSI) model**, network firewalls are at levels one to three (L1-Physical Wiring, L2-Data Link and L3-Network). Meanwhile, load balancing happens between layers four to seven (L4-Transport, L5-Session, L6-Presentation and L7-Application).

Load balancers have different capabilities, which include:

L4 — directs traffic based on data from network and transport layer protocols, such as IP address and TCP port.
L7 — adds <u>content switching to load balancing</u>. This allows routing decisions based on attributes like HTTP header, uniform resource identifier, SSL session ID and HTML form data.
**GSLB — Global Server Load Balancing** extends L4 and L7 capabilities to servers in different geographic locations.

## Caching

Load balancing helps you scale horizontally, but caching will enable you to make vastly better use of the resources you already have. Caches take advantage of the **locality of reference principle**: <u>recently requested data is likely to be requested again</u>. A cache is like short-term memory: it has a limited amount of space, faster than the original data source and contains the most recently accessed items. <u>Caches are often found at the level **nearest to the front end**</u>, where they are implemented to return data quickly without taxing downstream levels.

**Purpose**

- avoid network calls ( for Commonly used data).
- avoid complex re-computation.
- avoid load on DB

### 1. Application server cache

Placing a cache directly on a request layer node enables the <u>local storage of response data</u>. Each time a request is made to the service, the node will quickly return local, cached data if it exists. If it is not in the cache, the requesting node will query the data from disk. The cache on one request layer node could also be located both in memory (which is very fast) and on the node’s local disk (faster than going to network storage).

If the request layer is expanded to multiple nodes, it’s still quite possible to have each node host its own cache. However, if your load balancer randomly distributes requests across the nodes, the same request will go to different nodes, thus increasing **cache misses**. Two choices for overcoming this hurdle are **global caches** and **distributed caches**.

### 2. Distributed cache

In a distributed cache, each of its nodes **own part of the cached data**. Typically, the cache is divided up using a **consistent hashing function**, such that if a request node is looking for a certain piece of data, it can <u>quickly know where to look</u> within the distributed cache to determine if that data is available. In this case, each node has a small piece of the cache, and will then send a request to another node for the data before going to the origin. Therefore, one of the advantages of a distributed cache is the <u>ease by which we can increase the cache space</u>, which can be achieved just by adding nodes to the request pool.

A disadvantage of distributed caching is **resolving a missing node**. Some distributed caches get around this by <u>storing multiple copies of the data on different nodes</u>; but, its a very complicated logic, especially when you add or remove nodes from the request layer. Although even if a node disappears and part of the cache is lost, the requests will just pull from the origin—so it isn’t necessarily catastrophic!

### 3. Global Cache

In a global cache all the nodes use the same single cache space. This involves adding a server, or file store of some sort, faster than your original store and accessible by all the request layer nodes. This kind of caching scheme can be a problem when the number of clients and requests increase, but is very effective in some architectures (particularly ones with specialized hardware that make this global cache very fast, or that have a **fixed dataset** that needs to be cached).

There are **two common forms of global caches**. First, when a cached response is not found in the cache, the cache itself becomes responsible for retrieving the missing piece of data from the underlying store. Second, it is the responsibility of request nodes to retrieve any data that is not found in the cache.

In the first type, the cache itself manages eviction and fetching data to prevent a flood of requests for the same data from the clients. If the cache is being used for very large files, a low cache hit percentage would cause the cache buffer to become overwhelmed with cache misses; in this situation, it helps to have a large percentage of the total data set (or hot data set) in the cache. Another example is an architecture where the files stored in the cache are static and shouldn’t be evicted. (This could be because of application requirements around that data latency—certain pieces of data might need to be very fast for large data sets—where the application logic understands the eviction strategy or hot spots better than the cache.)

### 4. Content Distribution Network (CDN)

CDNs are a kind of cache that comes into play <u>for sites serving large amounts of static media</u>. In a typical CDN setup, a request will first ask the CDN for a piece of static media; the CDN will serve that content if it has it locally available. If it isn’t available, the CDN will query the back-end servers for the file and then cache it locally and serve it to the requesting user.

If the system we are building isn’t yet large enough to have its own CDN, we can ease a future transition by serving the static media off a separate subdomain (e.g. [static.yourservice.com](http://static.yourservice.com/)) using a lightweight HTTP server like **Nginx**, and cutover the DNS from your servers to a CDN later.

### Cache Invalidation

Caching require some maintenance for keeping cache <u>coherent with the source of truth</u> (e.g., database). If the data is modified in the database, it should be invalidated in the cache, if not, this can cause inconsistent application behavior. Solving this problem is known as cache invalidation, there are three main schemes that are used:

**Write-through cache:** Under this scheme <u>data is written into the cache and the corresponding database at the same time</u>. The cached data allows for fast retrieval, and we will have complete data consistency between cache and storage. Also, this scheme ensures that nothing will get lost in case of a crash, power failure, or other system disruptions. Although write through minimizes the risk of data loss, since <u>every write operation must be done twice</u> before returning success to the client, this scheme has the disadvantage of **higher latency for write operations**.

**Write-around cache:** This technique is similar to write through cache, but data is written directly to permanent storage, bypassing the cache. This can reduce the cache being flooded with write operations that will not subsequently be re-read, but has the disadvantage that a read request for recently written data will create a “cache miss” and must be read from slower back-end storage and experience higher latency.

**Write-back cache:** Under this scheme, data is written to cache alone, and completion is immediately confirmed to the client. The write to the permanent storage is done after specified intervals or under certain conditions. This results in low latency and high throughput for write-intensive applications, however, this speed comes with the risk of data loss in case of a crash or other adverse event because the only copy of the written data is in the cache.

### Cache eviction policies

**Two important questions (to decide cache policy)**

- when do i load data into cache ?
- when do i evict data out of the cache ?

The way in which you decide how to perform above 2 is based on your **cache policy**. Cache performance is depends on this cache policy. Most commonly used are LRU, LFU, **sliding window based policy**. 

1. First In First Out (FIFO): The cache evicts the first block accessed first without any regard to how often or how many times it was accessed before.
2. Last In First Out (LIFO): The cache evicts the block accessed most recently first without any regard to how often or how many times it was accessed before.
3. Least Recently Used (LRU): Discards the least recently used items first.
4. Most Recently Used (MRU): Discards, in contrast to LRU, the most recently used items first.
5. Least Frequently Used (LFU): Counts how often an item is needed. Those that are used least often are discarded first.
6. Random Replacement (RR): Randomly selects a candidate item and discards it to make space when necessary.

### Cache Thrashing

Cache thrashing is the **eviction of the useful data**. Example is constantly inputting and outputting into the cache without ever using the results.

### Write Through and write back cache

·     Write through – first update the cache before updating the actual database.

·     Write back – first save it in the database and then update the cache.

## Sharding or Data Partitioning

Data partitioning (also known as sharding) is a technique to **break up a big database (DB) into many smaller parts**. It is the process of splitting up a DB/table across multiple machines to <u>improve the manageability, performance, availability and load balancing of an application</u>. Because after a certain scale point, it is cheaper and more feasible to scale horizontally by adding more machines than to grow it vertically by adding beefier servers.

### 1. Partitioning Methods

There are many ways to decide how to break up an application database into multiple smaller DBs.

#### a. Horizontal partitioning: 

In this scheme, we put different rows into different tables. For example, if we are storing different places in a table, we can decide that locations with ZIP codes less than 10000 are stored in one table, and places with ZIP codes greater than 10000 are stored in a separate table. This is also called a range based sharding, as we are storing different ranges of data in separate tables.

The key problem with this approach is that if the value whose range is used for sharding isn’t chosen carefully, then the partitioning scheme will lead to unbalanced servers. In the previous example, splitting location based on their zip codes assumes that places will be evenly distributed across the different zip codes. This assumption is not valid as there will be a lot of places in a thickly populated area like Manhattan compared to its suburb cities.

#### b. Vertical Partitioning:

 In this scheme, we divide our data to store tables related to a specific feature to their own server. For example, if we are building Instagram like application, where we need to store data related to users, all the photos they upload and people they follow, we can decide to place user profile information on one DB server, friend lists on another and photos on a third server.

Vertical partitioning is straightforward to implement and has a low impact on the application. The main problem with this approach is that if our application experiences additional growth, then it may be necessary to further partition a feature specific DB across various servers (e.g. it would not be possible for a single server to handle all the metadata queries for 10 billion photos by 140 million users).

#### c. Directory Based Partitioning: 

A loosely coupled approach to work around issues mentioned in above schemes is to create a lookup service which knows your current partitioning scheme and abstracts it away from the DB access code. So, to find out where does a particular data entity resides, we query our directory server that holds the mapping between each tuple key to its DB server. This loosely coupled approach means we can perform tasks like adding servers to the DB pool or change our partitioning scheme without having to impact your application.

### 2. Partitioning Criteria

#### a. Key or Hash-based partitioning: 

Under this scheme, we apply a hash function to some key attribute of the entity we are storing, that yields the partition number. For example, if we have 100 DB servers and our ID is a numeric value that gets incremented by one, each time a new record is inserted. In this example, the hash function could be ‘ID % 100’, which will give us the server number where we can store/read that record. This approach should ensure a uniform allocation of data among servers. The fundamental problem with this approach is that it effectively fixes the total number of DB servers, since adding new servers means changing the hash function which would require redistribution of data and downtime for the service. A workaround for this problem is to use Consistent Hashing.

#### b. List partitioning: 

In this scheme, each partition is assigned a list of values, so whenever we want to insert a new record, we will see which partition contains our key and then store it there. For example, we can decide all users living in Iceland, Norway, Sweden, Finland or Denmark will be stored in a partition for the Nordic countries.

#### c. Round-robin partitioning: 

This is a very simple strategy that ensures uniform data distribution. With ‘n’ partitions, the ‘i’ tuple is assigned to partition (i mod n).

#### d. Composite partitioning: 

Under this scheme, we combine any of above partitioning schemes to devise a new scheme. For example, first applying a list partitioning and then a hash based partitioning. Consistent hashing could be considered a composite of hash and list partitioning where the hash reduces the key space to a size that can be listed.

### 3. Common Problems of Sharding

On a sharded database, there are certain extra constraints on the different operations that can be performed. Most of these constraints are due to the fact that, operations across multiple tables or multiple rows in the same table, will no longer run on the same server. Below are some of the constraints and additional complexities introduced by sharding:

#### a. Joins and Denormalization: 

Performing joins on a database which is running on one server is straightforward, but once a database is partitioned and spread across multiple machines it is often not feasible to perform joins that span database shards. Such joins will not be performance efficient since data has to be compiled from multiple servers. A common workaround for this problem is to denormalize the database so that queries that previously required joins can be performed from a single table. Of course, the service now has to deal with all the perils of denormalization such as data inconsistency.

#### b. Referential integrity: 

As we saw that performing a cross-shard query on a partitioned database is not feasible, similarly trying to enforce data integrity constraints such as foreign keys in a sharded database can be extremely difficult.

Most of RDBMS do not support foreign keys constraints across databases on different database servers. Which means that applications that require referential integrity on sharded databases often have to enforce it in application code. Often in such cases, applications have to run regular SQL jobs to clean up dangling references.

#### c. Rebalancing: 

There could be many reasons we have to change our sharding scheme:

The data distribution is not uniform, e.g., there are a lot of places for a particular ZIP code, that cannot fit into one database partition.
There are a lot of load on a shard, e.g., there are too many requests being handled by the DB shard dedicated to user photos.
In such cases, either we have to create more DB shards or have to rebalance existing shards, which means the partitioning scheme changed and all existing data moved to new locations. Doing this without incurring downtime is extremely difficult. Using a scheme like directory based partitioning does make rebalancing a more palatable experience at the cost of increasing the complexity of the system and creating a new single point of failure (i.e. the lookup service/database).

## Indexes

Indexes are used to improve the <u>speed of data retrieval operations</u> on the data store. An index makes the <u>trade-offs of increased storage overhead, and **slower writes**</u> (since we not only have to write the data but also have to update the index) for the benefit of faster reads. Indexes are used to quickly locate data without having to examine every row in a database table. Indexes can be created using one or more columns of a database table, providing the basis for both rapid random lookups and efficient access of ordered records.

An index is a data structure that can be perceived as a table of contents that points us to the location where actual data lives. So when we create an index on a column of a table, we store that column and a pointer to the whole row in the index. Indexes are also used to create different views of the same data. For large data sets, this is an excellent way to <u>specify different filters</u> or sorting schemes without resorting to creating multiple additional copies of the data.

Like traditional relational data store, we can also apply indexes to larger data sets. We must carefully consider how users will access the data. In the case of data sets that are many TBs in size but with very small payloads, indexes are necessity for optimizing data access since we can’t possibly iterate over that much data in any reasonable time. Also such a large data set can be spread over several devices so we need some way to find the correct physical location of the desired data. Indexes are the best way to do this.

## Proxies

A proxy server is an **intermediary piece of hardware/software** that sits <u>between the client and the back-end server</u>. It receives requests from clients and relays them to the origin servers. Typically, proxies are used to <u>filter requests or log requests</u>, or sometimes <u>transform requests</u> (by adding/removing headers, encrypting/decrypting, or compression). Another advantage of a proxy server is that its <u>cache can serve a lot of requests</u>. If multiple clients access a particular resource, the proxy server can cache it and serve all clients without going to the remote server.

Proxies are also extremely helpful when <u>coordinating requests from multiple servers</u> and can be used to <u>optimize request traffic from a system-wide perspective</u>. For example,  If requests are routed through the proxy, then we can collapse the same (or similar) data access requests into one request and if that data is not in the cache and then return the single result to the user; this scheme is called **collapsed forwarding**.

Another great way to use the proxy is to <u>collapse requests for data that is spatially close together</u> in the storage. This strategy will result in **decreasing request latency**. For example, let’s say a bunch of servers request parts of file: part1, part2, part3, etc. We can set up our proxy in such a way that it can recognize the spatial locality of the individual requests, thus collapsing them into a single request and reading complete file, which will greatly minimize the reads from the data origin. Such scheme makes a big difference in request time when we are doing random accesses across TBs of data. Proxies are useful under **high load situations**, or when we have **limited caching** since proxies can mostly batch several requests into one.

## Redundancy and Replication

Redundancy means <u>duplication of critical data or services</u> with the intention of **increased reliability** of the system. If we have a critical service in our system, ensuring that multiple copies or versions of it are running simultaneously can <u>secure against the failure of a single node</u>. Creating redundancy in a system can <u>remove **single points of failure** and provide backups</u> if needed in a crisis. For example, if we have two instances of a service running in production, and if one fails or degrades, the system can failover to the other one. These failovers can happen automatically or can be done manually.

Another important part of service redundancy is to create a **shared-nothing architecture**, where each node can **operate independently** of one another. There should not be any central service managing state or orchestrating activities for the other nodes. This helps a lot with scalability since new servers can be added without special conditions or knowledge and most importantly, such systems are more resilient to failure as there is **no single point of failure**.

## SQL vs. NoSQL

SQL and NoSQL - or **relational databases** and **non-relational databases**. Both of them differ in the way they were built, the kind of information they store, and how they store it. Relational databases are <u>structured and have predefined schemas. Non-relational databases are <u>unstructured, distributed and have a dynamic schema</u>.

### SQL

Relational databases store data in rows and columns. Each row contains all the information about one entity, and each column represents a data point about that entity. Some of the most popular relational databases are MySQL, Oracle, MS SQL Server, SQLite, Postgres, MariaDB, etc.

### NoSQL

Following are most common types of NoSQL:

- **Key-Value Stores**: Data is stored in an array of key-value pairs. The ‘key’ is an attribute name, which is linked to a ‘value’. Well-known key value stores include Redis, Voldemort and Dynamo.
- **Document Databases**: In these databases data is **stored in documents**, instead of rows and columns in a table, and these documents are grouped together in collections. Each document can have an entirely different structure. Document databases include the CouchDB and MongoDB.
- **Wide-Column Databases**: Instead of ‘tables,’ in columnar databases we have **column families**, which are containers for rows. Unlike relational databases, <u>we don’t need to know all the columns up front, and each row doesn’t have to have the same number of columns</u>. Columnar databases are best suited for analyzing large datasets - big names include Cassandra and HBase.
- **Graph Databases**: These databases are used to store data whose relations are best represented in a graph. Data is saved in graph structures with nodes (entities), properties (information about the entities) and lines (connections between the entities). Examples of graph database include Neo4J and InfiniteGraph.

### High level differences between SQL and NoSQL

- **Storage**: SQL stores data in tables (rows and columns). NoSQL databases have different data storage models. The main ones are key-value, document, graph and columnar.
- **Schema**: In SQL, each record conforms to a fixed schema, meaning the columns must be decided and chosen before data entry and each row must have data for each column. <u>NoSQL, schemas are dynamic</u>. Columns can be added on the fly, and each ‘row’ doesn’t have to contain data for each ‘column.’
- **Querying**: SQL databases uses SQL (structured query language) for defining and manipulating the data. In NoSQL database, queries are focused on a collection of documents. Sometimes it is also called UnQL (Unstructured Query Language). Different databases have different syntax for using UnQL.
- **Scalability**: In most common situations, SQL databases are **vertically scalable**, which can get very expensive. It is possible to scale a relational database horizontally , but this is a challenging and time-consuming process. NoSQL databases are **horizontally scalable**, thus making it a lot more cost-effective than vertical scaling.
- **Reliability or ACID Compliancy** (Atomicity, Consistency, Isolation, Durability): The vast majority of relational databases are ACID compliant. So, when it comes to data reliability and safe guarantee of performing transactions, SQL databases are best. Most of the NoSQL solutions <u>sacrifice ACID compliance for performance and scalability</u>.

### SQL VS. NoSQL - Which one to use?

There’s <u>no one-size-fits-all solution</u> many businesses rely on both relational and non-relational databases for different needs. NoSQL databases are gaining popularity for their <u>speed and scalability</u>.

#### Reasons to use SQL database

We need to ensure <u>**ACID** compliance</u>. ACID compliance reduces anomalies and protects the integrity of your database by prescribing exactly how transactions interact with the database, so for many e-commerce and financial applications, SQL remains the preferred option. Your data is structured and unchanging and your business is **not experiencing massive growth** and if you’re only working with data that’s consistent, then there may be no reason to use a NoSQL system designed to support a variety of data types and high traffic volume.

#### Reasons to use NoSQL database

When all the other components of our application are fast and seamless, NoSQL databases prevent data from being the bottleneck. Big data is contributing to a large success for NoSQL databases, mainly because it handles data differently than the traditional relational databases. 

**Storing large volumes of data** that often have little to no structure. A NoSQL database sets no limits on the types of data we can store together and allows us to add different new types as the need changes. With document-based databases, you can store data in one place without having to define what “types” of data those are in advance.
**cloud computing and storage** - Cloud-based storage is an excellent cost-saving solution but requires data to be easily spread across multiple servers to scale up. Using commodity hardware on-site or in the cloud saves you the hassle of additional software, and NoSQL databases like Cassandra are designed to be scaled across multiple data centers out of the box without a lot of headaches.
**Rapid development** - NoSQL is extremely useful for rapid development. If you’re working on quick iterations of your system which require making <u>frequent updates to the data structure</u> without a lot of downtime between versions, a relational database will slow you down.

## CAP Theorem

CAP theorem states that it is impossible for a distributed software system to simultaneously provide more than two out of three of the following guarantees (CAP): **Consistency**, **Availability** and **Partition tolerance**. When we design a distributed system, <u>trading off among CAP is almost the first thing we want to consider</u>. CAP theorem says while designing a distributed system we can **pick only two of**:

**Consistency**: All nodes see the same data at the same time. Consistency is achieved by <u>updating several nodes before allowing further reads</u>.

**Availability**: Every request gets a response on success/failure. Availability is achieved by <u>replicating the data across different servers</u>.

**Partition tolerance**: System <u>continues to work despite message loss or partial failure</u>. A system that is partition-tolerant can sustain any amount of network failure that doesn’t result in a failure of the entire network. Data is sufficiently replicated across combinations of nodes and networks to keep the system up through intermittent outages.

![img](https://miro.medium.com/max/621/1*UgttbELFVn3Z-uc7LeghbA.png)

We cannot build a general data store that is continually available, sequentially consistent and tolerant to any partition failures. We can only build a system that has any two of these three properties. Because, to be consistent, all nodes should see the same set of updates in the same order. But if the network suffers a partition, updates in one partition might not make it to the other partitions before a client reads from the out-of-date partition after having read from the up-to-date one. The only thing that can be done to cope with this possibility is to stop serving requests from the out-of-date partition, but then the service is no longer 100% available.

## Consistent Hashing

### Hash Function

A hash function is a function that maps one piece of data to another piece of data, typically an integer, known as hash code, or simply **hash**. Since there are way more possible inputs than outputs, any given number will have many different data mapped to it, a phenomenon known as **collision**. Good hash functions should generate the hash code in such a way that the outputs for different input values are spread as evenly as possible over the output range.

### Distributed hashing

In some situations, it may be necessary to split a hash table into several parts, hosted by different servers to <u>bypass the memory limitations of using a single computer</u>. Such setups consist of a pool of caching servers that host many key/value pairs and are used to provide fast access to data originally stored (or computed) elsewhere. For example, to reduce the load on a database server and at the same time improve performance, an application can be designed to first fetch data from the cache servers, and only if it’s not present there—a situation known as **cache miss**—resort to the database, running the relevant query and caching the results with an appropriate key, so that it can be found next time it’s needed.

**Distributed Hash Table (DHT)** is one of the fundamental component used in distributed scalable systems. Hash Tables need key, value and a hash function, where <u>hash function maps the key to a location where the value is stored</u>. Given ‘n’ cache servers, an intuitive hash function would be ‘**key % n**’ (using the ‘mod’ as the hash function). That is, **server = hash(key) mod N**, where N is the size of the pool. To store or retrieve a key, the client first computes the hash, applies a modulo N operation, and uses the resulting index to contact the appropriate server (probably by using a lookup table of IP addresses). Note that the hash function used for key distribution must be the same one across all clients, but it need not be the same one used internally by the caching servers. But it has two major drawbacks:

- It is **NOT horizontally scalable**. Whenever a new cache host is added to the system, all existing mappings are broken. It becomes difficult to maintain and need a downtime to update all caching mappings.
- It **may NOT be load balanced**, especially for non-uniformly distributed data. When the <u>data is not distributed uniformly</u>, it translates into some caches becoming hot and saturated while the others idle and almost empty.

### The Rehashing Problem

What happens if one of the servers crashes or becomes unavailable? Keys need to be redistributed to account for the missing server. The same applies if one or more new servers are added to the pool;keys need to be redistributed to include the new servers. But the problem with our simple modulo distribution is that when the number of servers changes, most hashes modulo N will change, so most keys will need to be moved to a different server. So, even if a single server is removed or added, all keys will likely need to be rehashed into a different server. This would means, all of a sudden, the keys won’t be found because they won’t yet be present at their new location. So, most queries will result in misses, and the original data will likely need retrieving again from the source to be rehashed, thus placing a heavy load on the origin server(s) (typically a database). This may very well degrade performance severely and possibly crash the origin servers.

### The Solution: Consistent Hashing

Consistent Hashing is a distributed hashing scheme that **does not depend directly on the number of servers**, so that, when adding or removing servers, the number of keys that need to be relocated is minimized. Consistent Hashing operates independently of the number of servers or objects in a distributed hash table by assigning them a **position on an abstract circle**, or **hash ring**. This allows servers and objects to scale without affecting the overall system.

Consistent hashing will **minimize reorganization** when nodes are added or removed. Hence, making the caching system <u>easier to scale up or scale down</u>. In Consistent Hashing when the hash table is resized (e.g. a new host is added to the system), only **k/n keys need to be remapped**, where k is the total number of keys and n is the total number of servers. <u>In consistent hashing objects are mapped to the same host if possible</u>. When a host is removed from the system, the objects on that host are shared by other hosts; and when a new host is added, it takes its share from a few hosts <u>without touching other’s shares</u>.

### How it works?

Consistent hashing also maps a key to an integer. Suppose the output of the hash function is in the range of [0, 256). Imagine that the integers in the range are placed on a ring such that the values are wrapped around.

- First we need to place the servers on the edge of the circle. Given a list of cache servers, hash them to integers in the range. A convenient way of doing this is by hashing the server name (or IP address, or some ID).
- To map a key to a server, compute its hash, and find out where it lies on the circle’s edge. Move clockwise on the ring until finding the first cache it encounters. That cache is the one that contains the key. 

Now we have the keys for both the objects and the servers on the same circle, we can define a simple rule : Each object key will belong in the server whose key is closest. In other words, to find out which server to ask for a given key, we need to <u>locate the key on the circle</u> and move in the ascending angle direction until we find a server.

![%D0%B7%D0%B0%D0%B2%D0%B0%D0%BD%D1%82%D0%B0%D0%B6%D0%B5%D0%BD%D0%BD%D1%8F%20(58)](https://coursehunters.online/uploads/default/optimized/1X/f00c79a5be747836d9763c35a5628c2761ead844_2_690x324.png)

To add a new server, say D, keys that were originally residing at C will be split. Some of them will be shifted to D, while other keys will not be touched. To remove a cache or if a cache failed, say A, all keys that were originally mapping to A will fall into B, and only those keys need to be moved to B, other keys will not be affected. From a programming perspective, what we would do is keep a sorted list of server values (based on hash code), and walk this list (or use a binary search) to find the first server with a value greater than, or equal to, that of the desired key. <u>If no such value is found, we need to wrap around, taking the first one from the list.</u>

For load balancing, the real data is essentially randomly distributed and thus may not be uniform. It may make the keys on caches unbalanced. To handle this issue, we add “**virtual replicas**” for caches. Instead of mapping each cache to a single point on the ring, we map it to multiple points on the ring, i.e. replicas. This way, <u>each cache is associated with multiple portions of the ring</u>. When the number of replicas increases, the keys will be more balanced.

**To ensure object keys are evenly distributed among servers**, we need to assign not one, but many labels (hash values) to each server. So instead of having labels A, B and C, we could have, say, A0 .. A9, B0 .. B9 and C0 .. C9, <u>all interspersed along the circle</u>. The factor by which to increase the number of labels (server keys), known as **weight**, depends on the situation to adjust the <u>probability of keys ending up on each</u>. For example, if server B were twice as powerful as the rest, it could be assigned twice as many labels, and as a result, it would end up holding twice as many objects (on average). For bigger pools may need even higher weights.

![Content Hashing Example 5](https://uploads.toptal.io/blog/image/129309/toptal-blog-image-1551794743400-9a6fd84dca83745f8b6ca95a2890cdc2.png)

Imagine server C is removed. To account for this, we must remove labels C0 .. C9 from the circle. This results in the object keys formerly adjacent to the deleted labels now being randomly labeled Ax and Bx, reassigning them to servers A and B. But what happens with the ones that originally belonged in A and B? Nothing. The absence of Cx labels does not affect those keys in any way. So, removing a server results in its object keys being randomly reassigned to the rest of the servers, leaving all other keys untouched:

If we wanted to add server D (say, as a replacement for C), we would need to add labels D0 .. D9. The result would be that roughly one-third of the existing keys (all belonging to A or B) would be reassigned to D, and, again, the rest would stay the same. This is how consistent hashing solves the rehashing problem. In general, only k/N keys need to be remapped when k is the number of keys and N is the number of servers (more specifically, the maximum of the initial and final number of servers).

## Long-Polling vs WebSockets vs Server-Sent Events

Long-Polling, WebSockets, and Server-Sent Events are popular **communication protocols** between a client like a web browser and a web server. 

### HTTP web request

Following are a sequence of events for a standard HTTP web request :

1. Client opens a connection and requests data from the server.
2. The server calculates the response.
3. The server sends the response back to the client on the opened request.

### Ajax Polling

Polling is a standard technique used by majority of **AJAX applications**. The basic idea is that the client repeatedly polls (or requests) a server for data. The client makes a request and waits for the server to respond with data. If no data is available, an empty response is returned.

1. Client opens a connection and requests data from the server using regular HTTP.
2. The requested webpage sends requests to the server at regular intervals (e.g., 0.5 seconds).
3. The server calculates the response and sends it back, just like regular HTTP traffic.
4. Client repeats the above three steps periodically to get updates from the server.

Problem with Polling is that the <u>client has to keep asking the server for any new data</u>. As a result, a lot of <u>responses are empty</u> creating **HTTP overhead**.

### HTTP Long-Polling

This polling technique allows the server to push information to a client, whenever the data is available. With Long-Polling, the client requests information from the server like normal polling, but the **server may not respond immediately**. That’s why this technique is sometimes referred to as a “**Hanging GET**”.

- If the server does not have any data available for the client, <u>instead of sending an empty response, the server holds the request</u> and waits until some data becomes available.
- Once the data becomes available, a full response is sent to the client. The client then immediately re-request information from the server so that the <u>server will almost always have an available waiting request</u> that it can use to deliver data in response to an event.

The basic life cycle of an application using HTTP Long-Polling is as follows:

1. The client makes an initial request using regular HTTP and then waits for a response.
2. The server <u>delays its response until an update is available</u>, or until a **timeout** has occurred.
3. When an update is available, the server sends a full response to the client.
4. The client typically sends a new long-poll request, either immediately upon receiving a response or after a pause to allow an <u>acceptable latency period</u>.
5. Each Long-Poll request has a timeout. The client has to reconnect periodically after the connection is closed, due to timeouts.

### WebSockets

WebSocket provides Full **duplex (bi-directional) communication channels** over a single **TCP connection**. It provides a **<u>persistent connection** between a client and a server</u> that both parties can use to start sending data at any time. The client establishes a WebSocket connection through a process known as the **WebSocket handshake**. If the process succeeds, then the server and client can <u>exchange data in both directions at any time</u> with **lower overheads**, facilitating real-time data transfer from and to the server. So the server can send content to the browser without being asked by the client, and allowing for messages to be passed back and forth while keeping the connection open. 

### Server-Sent Events (SSEs)

Under SSEs the client establishes a <u>persistent and long-term connection with the server</u>. The server uses this connection to send data to a client. If the client wants to send data to the server, it would require the use of another technology/protocol to do so.

1. Client requests data from a server using regular HTTP.
2. The requested webpage opens a connection to the server.
3. The server sends the data to the client whenever there’s new information available.

SSEs are best when we need real-time traffic from the server to the client or if the server is generating data in a loop and will be sending multiple events to the client.

## Queues

Queues are used to <u>effectively manage requests</u> in a large-scale distributed system where writes (or some tasks) can take a long time. In such cases, <u>achieving high performance and availability</u> requires different components of the system to **work in an asynchronous way**; a common way to do that is with queues. 

When the server gets more requests than it can handle, then each client is forced to wait for other clients’ requests to finish before a response can be generated. This kind of <u>synchronous behavior can severely degrade client’s performance</u>; the <u>client is forced to wait</u>, effectively doing zero work, until its request can be responded. Adding extra servers to address high load does not solve the problem either; even with effective load balancing in place, it is <u>very difficult to ensure the fair and balanced distribution of work required to maximize client performance</u>. Further, if the server processing the requests is unavailable, or fails, then the clients upstream will fail too.

In a processing queue, all incoming tasks are added to the queue, and as soon as any worker has the capacity to process, they can pick up a task from the queue. Queues are implemented on the **asynchronous communication protocol**, meaning <u>when a client submits a task to a queue they are no longer required to wait for the results</u>; instead, they **need only acknowledgment** that the request was properly received. This acknowledgment can later serve as a reference for the results of the work when the client requires it. Queues have implicit or explicit limits on the size of data that may be transmitted in a single request and the number of requests that may remain outstanding on the queue.

Queues are also used for **fault tolerance** as they can provide some **protection from service outages and failures**. For example, we can create a highly robust queue that can <u>retry service requests that have failed</u> due to transient system failures. It is <u>preferable to use a queue to enforce quality-of-service guarantees than to expose clients directly to intermittent service outages</u>, requiring complicated and often inconsistent client-side error handling.

Few open source implementations of queues available are RabbitMQ, ZeroMQ, ActiveMQ, and BeanstalkD.