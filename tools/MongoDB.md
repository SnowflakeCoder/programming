# MongoDB

MongoDB is an **open-source document-oriented database**. This format of storage is called BSON ( similar to JSON format). MongoDB is a NoSQL database that scales by adding more and more servers and increases productivity with its flexible document model. MongoDB provides a **default ‘_id’** (if not provided explicitly) which is a **12 byte hexadecimal number** which assures the uniqueness of every document, similar to the Primary key in RDBMS.

##  RDBMS vs MongoDB:

- RDBMS has a typical schema design that shows number of tables and the relationship between these tables whereas MongoDB is document-oriented. There is **no concept of schema or relationship**.
- **Complex transactions are not supported** in MongoDB because complex join operations are not available.
- MongoDB allows a **highly flexible and scalable document structure**. Eg: one data document of a collection in MongoDB can have two fields whereas the other document in the same collection can have four.
- MongoDB is **faster** as compared to RDBMS due to **efficient indexing and storage techniques**.

## Features of MongoDB:

- **Document Oriented**: MongoDB stores the main subject in the minimal number of documents and not by breaking it up into multiple relational structures like RDBMS.
- **Indexing:** Without indexing, a database would have to scan every document of a collection to select those that match the query which would be inefficient. So, `for efficient searching Indexing is a must` and MongoDB uses it to process huge volumes of data in very less time.
- **Scalability**: MongoDB **scales horizontally using sharding** (partitioning data across various servers). Data is partitioned into **data chunks** using the **shard key**, and these data chunks are evenly distributed across shards that resides across many physical servers. Also, new machines can be added to a running database.
- **Replication and High Availability**: MongoDB increases the data availability with multiple copies of data on different servers. By providing **redundancy**, it protects the database from hardware failures. If one server goes down, the data can be retrieved easily from other active servers which also had the data stored on  them.
- **Aggregation**: Aggregation operations process data records and return the computed results. It is similar to the GROUPBY clause in SQL. A few aggregation expressions are sum, avg, min, max, etc.

## Where do we use MongoDB?

- **Big Data**: If you have huge amount of data to be stored in tables, think of MongoDB before RDBMS databases. MongoDB has **built in solution for partitioning and sharding your database**.
- **Unstable Schema**: Adding a new column in RDBMS is hard whereas **MongoDB is schema-less**. **Adding a new field, does not effect old documents** and will be very easy.
- **Distributed data**: Since multiple copies of data  are stored across different servers, recovery of data is instant and safe even if there is a hardware failure.

## Terminologies

A MongoDB Database can be called as the **container for all the collections**. **Collection** is a bunch of MongoDB documents. **Document** is made of fields, and it has dynamic schema here. Documents of the same collection need not have the same set of fields.

## Important parts of MongoDB –

- **Drivers**: Drivers are present on your server that are **used to communicate with MongoDB**. The drivers support by the MongoDB are C, C++, C#, and .Net, Go, Java etc.
- **MongoDB Shell**: MongoDB Shell or mongo shell is an **interactive JavaScript interface for MongoDB**. 
- **Storage Engine**: It generally used to **manage how data is stored in the memory and on the disk**. MongoDB can have multiple search engines. You are also allowed to use your own search engine. The default search engine is known as **WiredTiger**.

## How MongoDB works ?

MongoDB work in **two layers** – **Application Layer and Data layer**

**Application Layer** is also known as the **Final Abstraction Layer**, it has two-parts, first is a Frontend (User Interface) and the second is Backend (server). The frontend is the place where the user uses MongoDB with the help of a Web or Mobile. The backend contains a server which is used to perform server-side logic and also contain drivers or mongo shell to interact with MongoDB server with the help of queries.

These queries are sent to the MongoDB server present in the **Data Layer**. Now, the MongoDB server receives the queries and **passes the received queries to the storage engine**. MongoDB server itself does not directly read or write the data to the files or disk or memory. After passing the received queries to the storage engine, the **storage engine is responsible to read or write the data in the files or memory** basically it manages the data.

## Data Modelling

Data in MongoDB has a flexible schema. Documents in the same collection <u>do not need to have the same set of fields</u> or structure. <u>Common fields in a collection’s documents may **hold different types of data**</u>. MongoDB provides **two types of data models**.

### Embedded Data Model

In this model, you can have (embed) all the **related data in a single document**, it is also known as de-normalized data model.

### Normalized Data Model

In this model, you can <u>refer the sub documents in the original document, **using references**</u>.

```json
//Employee:
{
	_id: <ObjectId101>,
	Emp_ID: "10025AE336"
}
//Personal_details:
{
	_id: <ObjectId102>,
	empDocID: " ObjectId101",
	First_Name: "Arun",
	Last_Name: "Kumar",
}
```

**Considerations while designing Schema in MongoDB**

- <u>Combine objects into one document if you will use them together</u>. Otherwise separate them (but make sure there **should not be need of joins**).
- Duplicate the data (but limited) because disk space is cheap as compare to compute time.
- **Do joins while write, not on read**.
- Do complex aggregation in the schema.

## MongoDB Projection

MongoDB provides a special feature that is known as **Projection**. It allows you to **select only the necessary data rather than selecting whole data** from the document. One can use **projection with db.collection.find()** method. In this method, the second parameter is the **projection parameter**, which is used to specify which fields are returned in the matching documents.

```CQL
db.collection.find({}, {field1: value2, field2: value2, ..})
```

## Mongod

mongod is the **primary daemon process** for the MongoDB system. It **handles data requests, manages data access**, and performs **background management operations**. 

## Replication

Replica sets provide **redundancy** and **high availability**. With multiple copies of data on different database servers, replication provides a level of **fault tolerance** against the loss of a single database server. In some cases, replication can **provide increased read capacity** as clients can send read operations to different servers. Maintaining copies of data in different data centers can **increase data locality** and availability for distributed applications. You can also maintain additional copies for dedicated purposes, such as **disaster recovery, reporting, or backup**.

### Primary, Secondary Nodes

<u>A replica set in MongoDB is a **group of mongod instances** that maintain the **same data set**</u>. A replica set contains **several data bearing nodes** and optionally **one arbiter node**. Of the data bearing nodes, one and <u>only one member is deemed the primary node</u>, while the <u>other nodes are deemed secondary nodes</u>. The <u>primary node receives all write operations</u>. The primary records all changes to its data sets in its **operation log, i.e. oplog**.

```
db.isMaster() //you will get the roles of each node.
```

The secondaries **replicate the primary’s oplog** and apply the operations to their data sets such that the secondaries’ data sets reflect the primary’s data set. <u>If the primary is unavailable, an eligible secondary will hold an election to elect itself the new primary</u>. 

### Arbiter Nodes

In some circumstances (such as you have a primary and a secondary but **cost constraints prohibit adding another secondary**), you may choose to add a mongod instance to a replica **set as an arbiter**  to vote in elections. An **arbiter participates in elections but does not hold data** (i.e. does not provide data redundancy). They can, however, participate in elections. An **arbiter will always be an arbiter** whereas a primary may step down and become a secondary and a secondary may become the primary during an election. Arbiters have minimal resource requirements and **do not require dedicated hardware**. You can deploy an arbiter on an application server or a monitoring host.

**IMPORTANT** : Do not run an arbiter on systems that also host the primary or the secondary members of the replica set.

**WARNING**: In general, **avoid deploying more than one** arbiter per replica set.





### Asynchronous Replication

Secondaries replicate the primary’s oplog and apply the operations to their data sets asynchronously. By having the secondaries’ data sets reflect the primary’s data set, the replica set can continue to function despite the failure of one or more members.

For more information on replication mechanics, see Replica Set Oplog and Replica Set Data Synchronization.



## Types of Nodes

A MongoDB instance can have different roles:

- Config server
- Router (mongos)
- Data server
- Arbiter server (for replica sets)

**db.serverStatus()** can be used identify instances. If an instance is a router, the process value is **mongos**. But for config servers, arbiters and data nodes the process value is **mongod**.



## References

https://www.geeksforgeeks.org/mongodb-an-introduction/

https://www.geeksforgeeks.org/how-mongodb-works/?ref=rp



















# NoSQL Database

 

Databases can be divided in 3 types:

Ø RDBMS (Relational Database Management System)

Ø OLAP (Online Analytical Processing)

Ø NoSQL

 

NoSQL Database is a non-relational database. It provides a mechanism for storage and retrieval of data other than tabular relations model used in relational databases. NoSQL database doesn't use tables for storing data. It is generally used to store big data and real-time web applications. Relational database could not handle big data (unordered, schema-less).

 

Advantages of NoSQL

Ø It supports query language.

Ø It provides fast performance.

Ø It provides horizontal scalability.

 

### Types of NoSQL databases

NoSQL database can be classified as 4 basic types:

Ø Key value store NoSQL database

Ø Document store NoSQL database

Ø Column store NoSQL database

Ø Graph base NoSQL databse

There are many NoSQL databases. MongoDB, Cassandra, CouchBD, Hypertable, Redis, Riak, Neo4j, HBASE, Couchbase, MemcacheDB, Voldemort, RevenDB etc. are the examples of NoSQL databases.

 

# MongoDB

### JSON

 

JavaScript Object Notation (JSON) is an open, human and machine-readable standard. Along with XML it is the main format for data interchange used on the modern web. JSON supports all the basic data types: numbers, strings, and boolean values, arrays and hashes. A JSON can be directly parsed by JavaScript.

### Binary JSON (BSON)

MongoDB represents JSON documents in **binary-encoded format** called BSON. BSON extends the JSON model to provide additional data types, ordered fields, and to be efficient for encoding and decoding within different languages. The MongoDB BSON implementation is lightweight, fast and highly traversable. Like JSON, MongoDB's BSON implementation supports embedding objects and arrays within other objects and arrays. MongoDB can even 'reach inside' BSON objects to build indexes and match objects against query expressions on both top-level and nested BSON keys. This means that MongoDB gives users the ease of use and flexibility of JSON documents together with the speed and richness of a lightweight binary format.

### MongoDB

MongoDB is a No SQL database. It is an open-source, cross-platform, document-oriented database written in C++. MongoDB stores data as documents, so it is known as document oriented database. MongoDB provides high performance, high availability, and automatic scaling.

 

Features of MongoDB

Ø Support ad hoc queries

·     In MongoDB, you can search by field, range query and it also supports regular expression searches.

Ø Indexing

·     You can index any field in a document.

Ø Replication

·     MongoDB supports Master Slave replication. A master can perform Reads and Writes and a Slave copies data from the master and can only be used for reads or back up.

Ø Duplication of data

·     MongoDB can run over multiple servers. So the data is duplicated to keep the system up and also keep its running condition in case of hardware failure. Built in replication for high availability.

Ø Load balancing

·     It has an automatic load balancing configuration because of data placed in **shards**. Auto-sharding for horizontal scalability.

Ø  Supports map reduce and aggregation tools.

Ø Uses JavaScript instead of Procedures.

Ø Stores files of any size easily without complicating your stack.

Ø Easy to administer in the case of failures.

Ø It also supports         JSON data model with dynamic schemas

 

MongoDB is a document based, non relational database provider. A Relational database has a typical schema design that shows number of tables and the relationship between these tables, while in MongoDB there is no concept of relationship.

 

MongoDB Advantages

Ø MongoDB is schema less. It is a document database in which one collection holds different documents.

Ø There may be difference between number of fields, content and size of the document from one to other.

Ø Structure of a single object is clear in MongoDB. There are no complex joins in MongoDB.

Ø MongoDB provides the facility of deep query because it supports a powerful dynamic query on documents.

Ø It is very easy to scale.

Ø It uses internal memory for storing working sets and this is the reason of its fast access.

 

Distinctive features of MongoDB

Ø Easy to use

Ø Light Weight

Ø Extremely faster than RDBMS

 

Performance analysis of MongoDB and RDBMS

Ø In relational database (RDBMS) tables are using as storing elements, while in MongoDB collection is used.

Ø In the RDBMS, we have multiple schema and in each schema we create tables to store data while, 

Ø MongoDB is a document oriented database in which data is written in BSON format which is a JSON like format.

Ø MongoDB is almost 100 times faster than traditional database systems.

 

 

 

 

![Description: http://cdncontribute.geeksforgeeks.org/wp-content/uploads/mongo-300x187.png](file:///C:\Users\ARUNKU~1.MS\AppData\Local\Temp\msohtmlclip1\01\clip_image001.png)

 

 

 

 

MongoDB Datatypes

 

Ø String  String is the most commonly used datatype. It is used to store data. A string must be UTF 8 valid in mongodb.

Ø Integer Integer is used to store the numeric value. It can be 32 bit or 64 bit depending on the server you are using.

Ø Boolean        This datatype is used to store boolean values. It just shows YES/NO values.

Ø Double Double datatype stores floating point values.

Ø Min/Max Keys This datatype compare a value against the lowest and highest bson elements.

Ø Arrays  This datatype is used to store a list or multiple values into a single key.

Ø Object Object datatype is used for embedded documents.

Ø Null    It is used to store null values.

Ø Symbol It is generally used for languages that use a specific type.

Ø Date   This datatype stores the current date or time in unix time format. It makes you possible to specify your own date time by creating object of date and pass the value of date, month, year into it.

### MongoDB Shell

MongoDB have a JavaScript shell that allows interaction with MongoDB instance from the command line. The shell is useful for performing administrative functions and running instances. To start the shell, open command prompt, run it as a administrator then run the mongo executable:

$ mongo  

 

You should start mongoDB before starting the shell because shell automatically attempt to connect to a MongoDB server on startup. The shell is a full-featured JavaScript interpreter. It is capable of running Arbitrary JavaScript program. You can also use the JavaScript libraries. You can also define and call JavaScript functions

### Data Modeling in MongoDB

In MongoDB, data has a flexible schema. It is totally different from SQL database where you had to determine and declare a table's schema before inserting data. MongoDB collections do not enforce document structure.

 

While designing the schema in MongoDB

Ø Always design schema according to user requirements.

Ø Do join on write operations not on read operations.

Ø Objects which you want to use together, should be combined into one document. Otherwise they should be separated (make sure that there should not be need of joins).

Ø Optimize your schema for more frequent use cases.

Ø Do complex aggregation in the schema.

 

 

### MongoDB Create Database

There is no create database command in MongoDB. Because MongoDB will create it automatically when you save the value into the defined collection at first time. But you can create collection manually by "db.createCollection()" but not the database.

 

use DATABASE_NAME 

 

If the database already exists, it will return the existing database.

 

Create a database "javatpointdb"**.**

 

\>use javatpointdb 

Swithched to db javatpointdb

 

To check the currently selected database, use the command db:

\>db 

 

To check the database list, use the command show dbs:

\>show dbs 

### MongoDB Drop Database

The dropDatabase command is used to drop a database. It also deletes the associated data files. It operates on the current database.

 

Syntax: db.dropDatabase() 

This syntax will delete the selected database. In the case you have not selected any database, it will delete default "test" database.

 

If you want to delete the database "javatpointdb", use the dropDatabase() command as follows:

 

\>use javatpointdb 

switched to the db javatpointdb

 

\>db.dropDatabase() 

{ "dropped": "javatpointdb", "ok": 1}

### MongoDB Create Collection

In MongoDB, db.createCollection(name, options) is used to create collection. But usually you don’t need to create collection. MongoDB creates collection automatically when you insert some documents. The operation will create the collection if the collection does not currently exist.

 

Syntax:  db.createCollection(name, options) 

 

Ø Name: is a string type, specifies the name of the collection to be created.

Ø Options: is a document type, specifies the memory size and indexing of the collection. It is an optional parameter.

 

List of options that can be used.

 

| **Field**   | **Type** | **Description**                                              |
| ----------- | -------- | ------------------------------------------------------------ |
| Capped      | Boolean  | (Optional) If it is set  to true, enables a capped collection. Capped collection is a fixed size  collecction that automatically overwrites its oldest entries when it reaches  its maximum size. If you specify true, you need to specify size parameter  also. |
| AutoIndexID | Boolean  | (Optional) If it is set  to true, automatically create index on ID field. Its default value is false. |
| Size        | Number   | (Optional) It specifies a  maximum size in bytes for a capped collection. Ifcapped is true, then you  need to specify this field also. |
| Max         | Number   | (Optional) It specifies  the maximum number of documents allowed in the capped collection. |

 

Create a collection name SSSIT.

\>use test 

\>db.createCollection("SSSIT") 

 

To check the created collection, use the command "show collections".

\>show collections 

SSSIT

### MongoDB Drop collection

In MongoDB, db.collection.drop() method is used to drop a collection from a database. It completely removes a collection from the database and removes all the indexes associated with the dropped collection. The db.collection.drop() method does not take any argument. Note: The drop command returns true if it successfully drops a collection. It returns false when there is no existing collection to drop.

 

Syntax:  db.COLLECTION_NAME.drop() 

 

drop the collection with the name SSSIT:

\>db.SSSIT.drop() 

### MongoDB insert documents

In MongoDB, the db.collection.insert() method is used to add or insert new documents. 

**Upsert operation**

There are also two methods "db.collection.update()" method and "db.collection.save()" method used for the same purpose. These methods add new documents through an operation called **upsert**. Upsert is an operation that performs either an update of existing document or an insert of new document if the document to modify does not exist.

Syntax : >db.COLLECTION_NAME.insert(document) 

 

Example : insert a document into a collection named javatpoint. This operation will automatically create a collection if the collection does not currently exist.

db.javatpoint.insert( 

  { 

​        course: "java", 

  } 

) 

After the successful insertion of the document, the operation will return a **WriteResult object** with its status.

WriteResult({ "nInserted" : 1 })

Here the nInserted field specifies the number of documents inserted. If an error is occurred then the WriteResult will specify the error information.

If the insertion is successful, you can view the inserted document, use the find() command.

Syntax: db.collection_name.find()

\>db.javatpoint.find() 

Note: Here, the ObjectId value is generated by MongoDB itself.

**MongoDB insert multiple documents**

If you want to insert multiple documents in a collection, you have to pass an array of documents to the db.collection.insert() method. 

Example : Define a variable named Allcourses that hold an array of documents to insert. Pass this Allcourses array to the db.collection.insert() method to perform a bulk insert.

var Allcourses =   [  { …. }, { …. }, { …. } ]; 

\> db.javatpoint.insert( Allcourses ); 

After the successful insertion of the documents, this will return a **BulkWriteResult** object with status.

BulkWriteResult({ "writeErrors" : [ ],   "writeConcernErrors" : [ ],   "nInserted" : 3,   "nUpserted" : 0,   "nMatched" : 0,   "nModified" : 0,   "nRemoved" : 0,   "upserted" : [ ] })

**Insert multiple documents with Bulk**

MongoDB provides a Bulk() API that can be used to perform multiple write operations in bulk. 

Steps

Ø **Initialize a bulk operation builder**

o  var bulk = db.javatpoint.initializeUnorderedBulkOp();  => initialize a bulk operation builder for the collection javatpoint.

o  This operation returns an unorder operations builder which maintains a list of operations to perform .

Ø **Add insert operations to the bulk object.**

o  bulk.insert( {  course: "java"} ); 

Ø Execute the bulk operation

o  bulk.execute();  => Call the execute() method on the bulk object to execute the operations in the list.

o  After the successful insertion of the documents, this method will return a **BulkWriteResult object** with its status.

### MongoDB update documents

In MongoDB, update() method is used to update or modify the existing documents of a collection.

Syntax:  db.COLLECTION_NAME.update(SELECTIOIN_CRITERIA, UPDATED_DATA) 

Update the existing course "java" into "android":

\>db.javatpoint.update({'course':'java'},{$set:{'course':'android'}}) 

###  MongoDB Delete documents

In MongoDB, the db.colloction.remove() method is used to delete documents from a collection. The remove() method works on two parameters.

\1. Deletion criteria: With the use of its syntax you can remove the documents from the collection.

\2. JustOne: It removes only one document when set to true or 1.

Syntax:  db.collection_name.remove (DELETION_CRITERIA) 

**Remove all documents**

If you want to remove all documents from a collection, pass an empty query document {} to the remove() method. The remove() method does not remove the indexes.

db.javatpoint.remove({})  => remove all documents from the "javatpoint" collection.

**Remove all documents that match a condition**

If you want to remove a document that match a specific condition, call the remove() method with the <query> parameter.

db.javatpoint.remove( { type : " language" } )  => remove all documents from the javatpoint collection where the type field is equal to language.

**Remove a single document that match a condition**

If you want to remove a single document that match a specific condition, call the remove() method with **justOne parameter** set to true or 1.

db.javatpoint.remove( { type : " language" }, 1 )  => remove a single document from the javatpoint collection where the type field is equal to programming language.

 

### MongoDB Query documents

In MongoDB, the db.collection.find() method is used to retrieve documents from a collection. This method returns a cursor to the retrieved documents.

db.collection.find()  => retrieves documents containing all their fields.

db.collection.findOne()  => method to return a single document

db.COLLECTION_NAME.find({})   => Retrieve all documents from a collection

### MongoDB limit() Method

In MongoDB, limit() method is used to limit the fields of document that you want to show. The MongoDB limit() method is used with find() method.

Syntax: db.COLLECTION_NAME.find().limit(NUMBER)  

**MongoDB skip() method**

In MongoDB, skip() method is used to skip the document. It is **used with find() and limit()** methods.

Syntax : db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)  

db.javatpoint.find().limit(1).skip(2) = > Scenario: The collection javatpoint has three documents. This  query retrieve only one document and skip 2 documents.

**MongoDB sort() method**

In MongoDB, sort() method is used to sort the documents in the collection. This method accepts a document containing list of fields along with their sorting order. By default sort() method displays the documents in ascending order. If you don't specify the sorting preference, it will display documents in ascending order. The sorting order is specified as 1 or -1.

Ø 1 is used for ascending order sorting.

Ø -1 is used for descending order sorting.

Syntax:  db.COLLECTION_NAME.find().sort({KEY:1}) 

db.javatpoint.find().sort({"Course":-1}) 

 

4) Is MongoDB better than other SQL databases? If yes then how?

MongoDB is better than other SQL databases because it allows a **highly flexible and scalable document structure**. MongoDB database are faster than SQL databases due to efficient indexing and storage techniques.

7) Why MongoDB is known as best NoSQL database?

MongoDb is the best NoSQL database because, it is:

Ø Document Oriented

Ø Rich Query language

Ø High Performance

Ø Highly Available

Ø Easily Scalable

8) Does MongoDB support primary-key, foreign-key relationship?

No. By Default, MongoDB doesn't support primary key-foreign key relationship. But we can achieve primary key-foreign key relationship by embedding one document inside another. 

10) Does MongoDB need a lot of RAM?

No. There is no need a lot of RAM to run MongoDB. It can be run even on a small amount of RAM because it dynamically allocates and de-allocates RAM according to the requirement of the processes.

11) Explain the structure of ObjectID in MongoDB.

ObjectID is a 12-byte BSON type. These are:

Ø 4 bytes value representing seconds

Ø 3 byte machine identifier

Ø 2 byte process id

Ø 3 byte counter

12) Is it true that MongoDB uses BSON to represent document structure?

Yes.

 

13) What are Indexes in MongoDB?

In MondoDB, Indexes are used to execute query efficiently. Without indexes, MongoDB must perform a collection scan, i.e. scan every document in a collection, to select those documents that match the query statement. If an appropriate index exists for a query, MongoDB can use the index to limit the number of documents it must inspect.

14) By default, which index is created by MongoDB for every collection?

By default, the_id collection is created for every collection by MongoDB.

15) What is a **Namespace in MongoDB**?

Namespace is a concatenation of the database name and the collection name. Collection, in which MongoDB stores BSON objects.

16) Can journaling features be used to perform safe hot backups?

Yes.

17) Why does Profiler use in MongoDB?

MongoDB uses a database profiler to perform characteristics of each operation against the database. You can use a profiler to find queries and write operations

18) If you remove an object attribute, is it deleted from the database?

Yes, it be. Remove the attribute and then re-save() the object.

19) In which language MongoDB is written?

MongoDB is written and implemented in C++.

20) Does MongoDB need a lot space of Random Access Memory (RAM)?

No. MongoDB can be run on small free space of RAM.

23) Do the MongoDB databases have schema?

Yes. MongoDB databases have dynamic schema. There is no need to define the structure to create collections.

24) What is the method to configure the cache size in MongoDB?

MongoDB's cache is **not configurable**. Actually MongoDb uses all the free spaces on the system automatically by way of **memory mapped files**.

25) How to do Transaction/locking in MongoDB?

MongoDB doesn't use traditional locking or complex transaction with Rollback. MongoDB is designed to be light weighted, fast and predictable to its performance. It keeps transaction support simple to enhance performance.

26) Why 32 bit version of MongoDB are not preferred ?

Because MongoDB uses memory mapped files so when you run a 32-bit build of MongoDB, the total storage size of server is 2 GB. But when you run a 64-bit build of MongoDB, this provides virtually unlimited storage size. So 64-bit is preferred over 32-bit.

27) Is it possible to remove old files in the moveChunk directory?

Yes, These files can be deleted once the operations are done because these files are made as backups during normal shard balancing operation. This is a manual cleanup process and necessary to free up space.

28) What will have to do if a shard is down or slow and you do a query?

If a shard is down and you even do query then your query will be returned with an error unless you set a **partial query option**. But if a shard is slow them Mongos will wait for them till response.

29)Explain the covered query in MongoDB.

A query is called covered query if satisfies the following two conditions:

The fields used in the query are part of an index used in the query.

The fields returned in the results are in the same index.

30) What is the importance of covered query?

Covered query makes the execution of the query faster because indexes are stored in RAM or sequentially located on disk. It makes the execution of the query faster.

Covered query makes the fields are covered in the index itself, MongoDB can match the query condition as well as return the result fields using the same index without looking inside the documents.

**31) What is sharding in MongoDB?**

In MongoDB, Sharding is a procedure of storing data records across multiple machines. It is a MongoDB approach to meet the demands of data growth. It creates horizontal partition of data in a database or search engine. Each partition is referred as shard or database shard.

32) What is replica set in MongoDB?

A replica can be specified as a group of mongo instances that host the same data set. In a replica set, one node is primary, and another is secondary. All data is replicated from primary to secondary nodes.

33) What is primary and secondary replica set in MongoDB?

In MongoDB, primary nodes are the node that can accept write. These are also known as master nodes. The replication in MongoDB is single master so, only one node can accept write operations at a time.

Secondary nodes are known as slave nodes. These are read only nodes that replicate from the primary.

34) By default, which replica sets are used to write data?

By default, MongoDB writes data only to the primary replica set.

36) In which format MongoDB represents document structure?

MongoDB uses BSON to represent document structures.

37) What will happen when you remove a document from database in MongoDB? Does MongoDB remove it from disk?

Yes. If you remove a document from database, MongoDB will remove it from disk too.

38) Why are MongoDB data files large in size?

MongoDB doesn't follow file system fragmentation and pre allocates data files to reserve space while setting up the server. That's why MongoDB data files are large in size.

39) What is a **storage engine** in MongoDB?

A storage engine is the part of a database that is used to manage how data is stored on disk.

For example: one storage engine might offer better performance for read-heavy workloads, and another might support a higher-throughput for write operations.

40) Which are the storage engines used by MongoDB?

MMAPv1 and WiredTiger are two storage engine used by MongoDB.

41) What is the usage of profiler in MongoDB?

A database profiler is used to collect data about MongoDB write operations, cursors, database commands on a running mongod instance. You can enable profiling on a per-database or per-instance basis.

The database profiler writes all the data it collects to the system. profile collection, which is a capped collection.

42) Is it possible to configure the cache size for MMAPv1 in MongoDB?

No. it is not possible to configure the cache size for MMAPv1 because MMAPv1 does not allow configuring the cache size.

 

43) How to configure the cache size for WiredTiger in MongoDB?

For the WiredTiger storage engine, you can specify the maximum size of the cache that WiredTiger will use for all data. This can be done using storage.wiredTiger.engineConfig.cacheSizeGB option.

 

44) How does MongoDB provide concurrency?

MongoDB uses reader-writer locks for concurrency. Reader-writer locks allow concurrent readers shared access to a resource, such as a database or collection, but give exclusive access to a single write operation.

 

45) What is the difference between MongoDB and Redis database?

Difference between MongoDB and Redis:

 

Redis is faster than MongoDB.

Redis has a key-value storage whereas MongoDB has a document type storage.

Redis is hard to code but MongoDB is easy.

For more information: click here

 

46) What is the difference between MongoDB and CouchDB?

Difference between MongoDB and CouchDB:

 

MongoDB is faster than CouchDB while CouchDB is safer than MongoDB.

Triggers are not available in MongoDB while triggers are available in CouchDB.

MongoDB serializes JSON data to BSON while CouchDB doesn't store data in JSON format.

For more information: click here

 

48) Is there any need to create database command in MongoDB?

You don't need to create a database manually in MongoDB because it creates automaically when you save the value into the defined collection at first time.

 

For more information: click here

 

 

### Compare MongoDB and CouchDB

Although both of these databases are **document oriented**, MongoDB is a better choice for applications which need **dynamic queries** and good performance on a very big database. On the other side, CouchDB is better used for applications with occasionally changing queries and pre-defined queries

 

 

 

 

 

 

 

 

 

 

## Cassandra vs MongoDB

Ø MongoDB is cross-platform document-oriented database system while Cassandra is high performance distributed database system.

Ø MongoDB is written in C++ while Cassandra is written in Java.

Ø MongoDB is easy to administer in the case of failure while Cassandra provides high availability with no single point of failure.

Cassandra is a distributed database system designed to handle large amount of data and known for its high scalability and high performance. While, MongoDB is document oriented database which also provides high scalability, high performance and automatic scaling. In terms of simplicity, databases can be divided in two types:

Development simplicity

Operational simplicity

While MongoDB is known for an easy out-of-the-box experience, Cassandra is known for easy to manage at scale.

Index  Cassandra      Mongodb

1)      Cassandra is high performance distributed database system.   MongoDB is cross-platform document-oriented database system.

2)      Cassandra is written in Java.    MongoDB is written in C++.

3)      Cassandra stores data in tabular form like SQL format.  MongoDB stores data in JSON format.

4)      Cassandra is got license by Apache.     MongoDB is got license by AGPL and drivers by Apache.

5)      Cassandra is mainly designed to handle large amounts of data across many commodity servers.        MongoDB is designed to deal with JSON-like documents and access applications easier and faster.

6)      Cassandra provides high availability with no single point of failure.      MongoDB is easy to administer in the case of failure.

Key Points of Apache Cassandra

Cassandra is highly scalable, high performance, consistent and fault-tolerant database system. Cassandra is a column-oriented database.

Cassandra provides easy data distribution.

Cassandra supports ACID properties i.e. Atomicity, Consistency, Isolation, and Durability.

Cassandra follows the distribution design of Amazon?s dynamo and its data model design is based on Google's Bigtable.

Cassandra was initially created at Facebook for inbox search and now it is being used by some of the biggest companies like Facebook, Twitter, ebay, Netflix, Cisco, Rackspace etc.

Key Points of MongoDB

MongoDB is well suited for Bigdata and mobile & social infrastructure.

MongoDB provides Replication, High availability and Auto-sharding.

 

****************************************

Reasons to opt for MongoDB :

 

It supports hierarchical data structure 

It supports associate arrays like Dictionaries in Python.

Built-in Python drivers to connect python-application with Database. Example- PyMongo

It is designed for Big Data.

Deployment of MongoDB is very easy.

 

 

 

MongoDB : An introduction

MongoDB, the most popular NoSQL database, is an open-source document-oriented database. The term ‘NoSQL’ means ‘non-relational’. It means that MongoDB isn’t based on the table-like relational database structure but provides an altogether different mechanism for storage and retrieval of data. This format of storage is called BSON ( similar to JSON format) .

 

 

 

mongodb

 

A simple MongoDB document Structure:

 

{

 

title: ‘Geeksforgeeks’,

 

by: ‘Harshit Gupta’,

 

url: ‘https://www.geeksforgeeks.org’,

 

type: ‘NoSQL’

 

}

 

SQL databases store data in tabular format. This data is stored in a predefined data model which is not very much flexible for today’s real-world highly growing applications. Modern applications are more networked, social and interactive than ever. Applications are storing more and more data and are accessing it at higher rates.

 

Relational Database Management System(RDBMS) is not the correct choice when it comes to handling big data by the virtue of their design since they are not horizontally scalable. If the database runs on a single server, then it will reach a scaling limit. NoSQL databases are more scalable and provide superior performance. MongoDB is such a NoSQL database that scales by adding more and more servers and increases productivity with its flexible document model.

 

 RDBMS vs MongoDB:

 

RDBMS has a typical schema design that shows number of tables and the relationship between these tables whereas MongoDB is document-oriented. There is no concept of schema or relationship.

Complex transactions are not supported in MongoDB because complex join operations are not available.

MongoDB allows a highly flexible and scalable document structure. For example, one data document of a collection in MongoDB can have two fields whereas the other document in the same collection can have four.

MongoDB is faster as compared to RDBMS due to efficient indexing and storage techniques.

There are a few terms which are related in both the databases. What’s called Table in RDBMS is called a Collection in MongoDB. Similarly, a Tuple is called a Document and A Column is called a Field. MongoDB provides a default ‘_id’ (if not provided explicitly) which is a 12 byte hexadecimal number which assures the uniqueness of every document. It is similar to the Primary key in RDBMS.

Features of MongoDB:

 

 Document Oriented: MongoDB stores the main subject in the minimal number of documents and not by breaking it up into multiple relational structures like RDBMS. For example, it stores all the information of a computer in a single document called Computer and not in distinct relational structures like CPU, RAM, Hard disk, etc.

Indexing: Without indexing, a database would have to scan every document of a collection to select those that match the query which would be inefficient. So, for efficient searching Indexing is a must and MongoDB uses it to process huge volumes of data in very less time.

Scalability: MongoDB scales horizontally using sharding (partitioning data across various servers). Data is partitioned into data chunks using the shard key, and these data chunks are evenly distributed across shards that resides across many physical servers. Also, new machines can be added to a running database.

Replication and High Availability: MongoDB increases the data availability with multiple copies of data on different servers. By providing redundancy, it protects the database from hardware failures. If one server goes down, the data can be retrieved easily from other active servers which also had the data stored on them.

Aggregation: Aggregation operations process data records and return the computed results. It is similar to the GROUPBY clause in SQL. A few aggregation expressions are sum, avg, min, max, etc

Where do we use MongoDB?

 

MongoDB is preferred over RDBMS in the following scenarios:

 

Big Data: If you have huge amount of data to be stored in tables, think of MongoDB before RDBMS databases. MongoDB has built in solution for partitioning and sharding your database.

Unstable Schema: Adding a new column in RDBMS is hard whereas MongoDB is schema-less. Adding a new field, does not effect old documents and will be very easy.

Distributed data Since multiple copies of data are stored across different servers, recovery of data is instant and safe even if there is a hardware failure.

Language Support by MongoDB:

 

MongoDB currently provides official driver support for all popular programming languages like C, C++, C#, Java, Node.js, Perl, PHP, Python, Ruby, Scala, Go and Erlang.

 

Installing MongoDB:

 

Just go to http://www.mongodb.org/downloads and select your operating system out of Windows, Linux, Mac OS X and Solaris. Detailed explation about the installation of MongoDB is given on their site.

 

For Windows, a few options for the 64-bit operating systems drops down. When you’re running on Windows 7, 8 or newer versions, select Windows 64-bit 2008 R2+. When you’re using Windows XP or Vista then select Windows 64-bit 2008 R2+ legacy.

 

Who’s using MongoDB?

 

MongoDB has been adopted as backend software by a number of major websites and services including EA, Cisco, Shutterfly, Adobe, Ericsson, Craigslist, eBay, and Foursquare.

Next Article : 

MongoDB and Python

For more information visit their website: https://www.mongodb.com/nosql-explained

 

 

CouchDB vs. MongoDB

Comparison Feature    CouchDB       MongoDB

Data Model    It follows the document-oriented model and data is presented in JSON format.        It follows the document-oriented model but data is presented in BSON format.

Interface       CouchDB uses HTTP/REST based interface. It is very intuitive and very well designed.   MongoDB uses binary protocol and custom protocol over TCP/IP.

Object Storage In CouchDB, database contains documents.    In MongoDB, database contains collections and collection contains documents.

Query Method CouchDB follows Map/Reduce query method. (JavaScript+others)     MongoDB follows Map/Reduce (JavaScript) creating collection + object-based query language.

Replication     CouchDB supports master-master replication with custom conflict resolution functions.        MongoDB supports master-slave replication.

Concurrency   It follows MVCC (Multi Version Concurrency Control).  Update in-place.

Preferences    CouchDB favors availability.     MongoDB favors consistency.

Performance Consistency      In CouchDB is safer than MongoDB     In MongoDB, database contains collections and collection contains documents.

Consistency    CouchDB is eventually consistent.      MongoDB is strongly consistent.

Written in      it is written in Erlang.   it is written in C++.

 

 

***********************************************

 

Redis Vs MongoDB

Comparison Index      Redis   MongoDB

Introduction    Redis is in-memory data structure store, used as database, cache and message broker. MongoDB is one of the most popular NoSQL database which follows the document stores structure.

Primary database model        Redis follows key-value store model.   MongoDB follows document store model.

Official Website        redis.io www.mongodb.com

Technical Documentation       You can get technical documentation of Redis on redis.io/documentation      You can get technical documentation of MongoDB on docs.mongodb.com/manual

Developed By  Redis is developed by Salvatore Sanfilippo.     MongoDB is developed by MongoDB Inc.

Initial Release  Redis is initially released in 2009.        MongoDB is also initially released in 2009.

Licence Redis is subscription based and open-source.   MongoDB is free to use and open-source.

Cloud based    No     No

Implementation Language      Redis is written and implemented in C language.        MongoDB is written and implemented in C++ language.

Server operating systems      BSD, Linux, OS X, Windows     Linux, OS X, Solaris, Windows

Data Scheme   schema-free   schema-free

Secondary Indexes     No     Yes

SQL    No     No

APIs and other access methods Redis follows proprietary protocol.     MongoDB follows proprietary protocol using JSON.

Supported programming languages     C, C#, C++, Clojure, Crystal, D, Dart, Elixir, Erlang,Fancy, Go, Haskell, Haxe, Java, JavaScript (Node.js), Lisp, Lua, MatLab, Objective-C, OCaml, Perl, PHP, Prolog, Pure Data, Python, R, Rebol, Ruby, Rust, Scala, Scheme, Smalltalk, Tcl    Actionscript, C, C#, C++, Clojure, ColdFusion, D, Dart, Delphi, Erlang, Go, Groovy, Haskell, Java, JavaScript, Lisp, Lua, MatLab Perl, PHP, PowerShell, Prolog, Python, R, Ruby, Scala, Smalltalk

Server-side scripts      Lua     JavaScript

Triggers        No     No

Partitioning methods   Redis uses Sharding for partition.       MongoDB also uses Sharding for partition.

Replication methods   Redis follows master-slave replication. MongoDB also follows master-slave replication.

MapReduce    No     Yes

Consistency concepts  Eventual Consistency and Immediate Consistency      Eventual Consistency

Foreign keys   No     No

Transaction concepts   Optimistic locking, atomic execution of commands blocks and scripts.   No

Concurrency   Yes     Yes

MapReduce    No     Yes

Durability       Yes     Yes

In-memory capabilities Yes     Yes

User concepts  Simple password-based access control. Access rights for users and roles.

Special Characteristics  Redis is ranked as world?s fastest database. It reduces application complexity, simplifies development, accelerates time to market and provides unprecedented flexibility to developers with its visionary data structures and modules.        MongoDB is considered as the next-generation database. It successfully helped many businesses to transform their industries by providing big data. The world?s most sophisticated organizations, from cutting-edge startups to the largest companies, use MongoDB to create applications never before possible, at a very low cost.

Comparing Advantages Redis is an in-memory database platform provides support of wide range of data structures such as strings, hashes, sets, lists, sorted sets, bitmaps, hyperloglogs, and geospatial indexes. Redis provides effortless scaling in a fully automated manner by overseeing all the operations of sharding, re-sharding, migration. It also includes persistence, instant automatic failure detection, backup and recovery, and in-memory replication across racks, zones, datacenters, regions, and cloud platforms.   MongoDB provides the best of traditional databases as well as flexibility, scale, and performance required by today?s applications. MongoDB is a database of giant ideas. MongoDB keeps the most valuable features of Relational database i.e. strong consistency, expressive query language and secondary indexes. It facilitates developers to build highly functional applications faster than NoSQL databases.

Key Customers Key customers of Redis are: Verizon, Vodafone, Atlassian, Trip Advisor, Jet.com, Nokia, Samsung, HTC, Docker, Staples, Intuit, Groupon, Shutterfly, KPMG, TD Bank, UnitedHealthcare, RingCentral, The Motley Fool, Bleacher Report, HipChat, Salesforce, Hotel Tonight, Cirruspath, Itslearning.com, Xignite, Chargify, Rumble Entertainment, Scopely, Havas Digital, Revmob, MSN, Bleacher Report, Mobli, TMZ, Klarna, Shopify etc. Key customers of MongoDB are: ADP, Adobe, AstraZeneca, BBVA, Bosch, Cisco, CERN, Department of Veteran Affairs, eBay, eHarmony, Electronic Arts, Expedia, Facebook?s Parse, Forbes, Foursquare, Genentech, MetLife, Pearson, Sage, Salesforce, The Weather Channel, Ticketmaster, Under Armour, Verizon Wireless etc.

Market Metrics Redis Labs consists of more than 60000 customers globally and is consistently ranked as a leader in top analyst reports on NoSQL, in-memory and operational databases. Redis is rated as no. 1 cloud database, no.1 database in Docker, no.1 NoSQL datastore, most popular NoSQL database in container.     20 million downloads (growing at thousands downloads per day). More than 2,000 customers including over one third of the Fortune 100. Named a leader in the Forrester Wave?: Big Data NoSQL, Q3 2016. Highest placed non-relational database in DB Engines rankings

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 



