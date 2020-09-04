## Cassandra

Apache Cassandra is a **NoSQL** database. A **highly scalable**, high-performance **distributed** database designed to handle large amounts of data (**structured data**) across many commodity servers, providing **high availability** with **no single point of failure**. 

- An open source, written in **Java**, decentralized/distributed storage system, created at **Facebook**. 
- It is **scalable, fault-tolerant, and consistent**.
- It is a **column-oriented database**.
- Its distribution design is based on **Amazon’s Dynamo** and its data model on **Google’s Bigtable**. Cassandra implements a **Dynamo-style replication model** with no single point of failure, but adds a more powerful **“column family” data model**.

### Features of Cassandra

- **Easy data distribution** – **flexibility** to distribute data by replicating data across multiple data centers.
- **Elastic scalability** - Cassandra is **highly scalable** and easily allows to add more hardware;
- **Always on architecture (high availability)** - Cassandra has **no single point of failure** and is **continuously available**. In Cassandra, **High Availability via data replication** at the different-different location and on different data centers which gives the high availability. The **masterless, peer-to-peer architecture** means that every node can perform read and write operations. This enables data to quickly be replicated across data centers and geographies so if one data center goes down but your system will be available and can access from different data center.
- **High Fault Tolerance** : In Cassandra, applications never slow down or fail when nodes get knocked offline. There’s **no need for any manual intervention** when a node fails, **fault tolerance is automatic** because the replica of data is available on different nodes and If one node goes down then data will be accessible from another node.
- **Fast linear-scale performance** - Cassandra is linearly scalable, i.e. it **increases the throughput as you increase the number of nodes** in the cluster, therefore maintains a **quick response time** and keeps the service running **during heavily trafficked periods**.
- **Flexible data storage** - Cassandra **accommodates all possible data formats** including: structured, semi-structured, and unstructured. It can **dynamically accommodate changes to your data structures** according to your need.
- **Easy data distribution** - Cassandra provides the flexibility to distribute data where you need by **replicating data across multiple data centers**.
- **Transaction support** - Cassandra supports like Atomicity, Consistency, Isolation, and Durability (ACID), properties of transactions.
- **Fast writes** - Cassandra was designed to run on cheap commodity hardware. It performs **blazingly fast writes** and can store hundreds of terabytes of data, without sacrificing the read efficiency.

#### Multi-Data Center and Hybrid Cloud Support :

In Cassandra, you can access multiple data center and also you can use hybrid cloud support. Cassandra is designed as a distributed system with multiple data centers. key to multi-data center deployment :
**N Replication factor and replica placement strategy** – In Cassandra, you can define Network Topology Strategy which has capabilities for fine-grained adjustment of the **number and location of replicas** at the data center and rack level.
**N Snitch** – In Cassandra, For multi-data center deployments, you need to make sure the snitch has complete and accurate information about the network, either by **automatic detection (Rack Inferring Snitch)** or details specified in a properties file (**Property File Snitch**).
**N Consistency level** – In Cassandra, consistency levels that are specifically designed for scenarios with multiple data centers.

### Cassandra - Architecture

Cassandra has **peer-to-peer distributed system** across its nodes, and data is distributed among all the nodes in a cluster. **All the nodes in a cluster play the same role**. Each node is independent and at the same time interconnected to other nodes. **Each node in a cluster can accept read and write requests**, regardless of where the data is actually located in the cluster. When a node goes down, read/write requests can be served from other nodes in the network.

#### Data Replication in Cassandra

In Cassandra, one or more of the **nodes in a cluster act as replicas for a given piece of data**. If it is detected that some of the nodes responded with an **out-of-date value**, Cassandra will return the most recent value to the client. After returning the most recent value, Cassandra performs a **read repair in the background to update the stale values**. Cassandra uses the **data replication among the nodes in a cluster to ensure no single point of failure**. Cassandra uses the **Gossip Protocol** in the background to allow the nodes to communicate with each other and **detect any /ty nodes** in the cluster.

#### Components of Cassandra

- **Node** − Basic component in Cassandra, a place where **data is stored**. 
- **Data center** − It is a **collection of related nodes**.
- Cluster − A cluster is a component that **contains one or more data centers**.
- **Commit log** − The commit log is a **crash-recovery mechanism** in Cassandra. **Every write operation** is written to the commit log.
- **Mem-table** − A mem-table is a **memory-resident data structure**. **After commit log**, the data will be written to the mem-table. Sometimes, for a **single-column family**, there will be multiple mem-tables.
- **SSTable** − It is a disk file to which the **data is flushed from the mem-table** when its **contents reach a threshold value**.
- **Bloom filter** − These are **quick, nondeterministic, algorithms** for testing **whether an element is a member of a set (SStable)**. Bloom filters are **accessed after every query**.
- **Compaction** - The **process of freeing up space** by **merging large accumulated data files is called compaction**. During compaction, the data is merged, indexed, sorted, and stored in a new **SSTable**. Compaction also **reduces the number of required seeks**.

#### Replica placement strategies

These are strategy to **place replicas in the ring**. **Simple strategy** (rack-aware strategy), **LocalStrategy**, **old network topology strategy** (rack-aware strategy), and **network topology strategy** (datacenter-shared strategy) are Replication strategy options supported by Cassandra  in which we generally use Simple and NetworkTopology Strategy.

- **SimpleStrategy**: It is a simple strategy that is recommended for multiple nodes over multiple racks **in a single data center**. Simple Strategy specifies a **single replication factor** for the cluster. It determines the number of nodes that should contain a copy of each row. For example, a SimpleStrategy and replication_factor is 2 means there are two redundant copies of each row (two different nodes should store a copy of each row) in a single data center. It **treats all nodes identically**, ignoring any configured datacenters or racks.
- **LocalStrategy**: LocalStrategy is **used for system** only, a replication strategy for internal purposes, used for system and sys_auth keyspaces (these are internal keyspaces). In Cassandra internal keyspaces implicitly handled by Cassandra’s storage architecture for managing authorization and authentication. It is **not permissible** to creating keyspace with LocalStrategy class if we will try to create such keyspace then it would give an error like “**LocalStrategy is for Cassandra’s internal purpose only**”.

3. **NetworkTopologyStrategy**: Using this option, we can **store multiple copies of data on different data centers as per need**, you can set the **replication factor for each data-center** independently. Even if your cluster only uses a single datacenter. This is one important reason to use NetworkTopologyStrategy when multiple replica nodes need to be placed on **different data centers**. This Strategy make it **easier to add new physical or virtual datacenters** to the cluster later.

- **Old Network Topology Strategy** : This is a legacy replication strategy.

#### Gossip Protocol

Cassandra cluster use a ring system where each node contains a certain partition of each table in the database and can only communicate with adjacent nodes. In Cassandra, all nodes are the same and have **masterless, peer-to-peer architecture**. In Cassandra all **nodes communicating with each other via a gossip protocol**.

**Gossip is the message system** that Cassandra nodes, virtual nodes used to **make their data consistent** with each other, and is used to **enforce the replication factor** in a cluster. Gossip is a peer-to-peer communication protocol in which nodes **periodically exchange state information** about themselves and about other nodes they know about (i.e. other nodes that they have gossiped about), so all nodes quickly learn about other nodes in the cluster. The Gossip protocol **runs every second** and exchange state messages with **up to three other nodes** in the cluster. This is how Gossip protocol works if any node goes down (state information).

**Gossip protocol definition**: Gossip message has a **version associated with it**, so that during a Gossip exchange, other information is overwritten with the most current state for a particular node.

### Query Language (CQL)

Users can access Cassandra **through its nodes** using CQL. CQL treats the **database (Keyspace)** as a container of tables. Programmers use **cqlsh**: a prompt to work with CQL to access Cassandra. Clients approach any of the nodes for their read-write operations. That node (coordinator) **plays a proxy** between the client and the nodes holding the data.

##### Write Operations

- Step-1: As soon as we receives write request then it is **first dumped into commit log** to make sure that data is saved. Every write activity of nodes is captured by the **commit logs** written in the nodes. Commit log is the **first entry point** while writing to disk or memTable. The purpose of commit log in Cassandra is to solve **server sync issues** if a data node is down.
- Step-2: Insertion of data into table that is also written in **MemTable** that holds the data till it’s get full. After data written in Commit log then data is written in Mem-table.
- Step-3: Whenever the mem-table is full (reaches its threshold), data will be written (flushed) into the **SSTable disk file**. All writes are **automatically partitioned and replicated throughout the cluster**. Cassandra **periodically consolidates the SSTables**, discarding unnecessary data.

##### Read Operations

During read operations, Cassandra gets **values from the mem-table** and checks the **bloom filter to find the appropriate SSTable** that holds the required data and gets values from SSTables. In Read Operation there are **three types of read requests** that a coordinator can send to a replica. The node that accepts the read/write requests called **coordinator** for that particular operation.

- **Direct Request**: In this operation coordinator node sends the read request to one of the replicas.
- **Digest Request**: In this operation coordinator will contact to replicas specified by the **consistency level**. For Example: CONSITENCY TWO; means that Any two nodes in data center will acknowledge.
- **Read Repair Request**: If there is any case in which data is not consistent across the node then **background Read Repair Request initiated** that makes sure that the most recent data is available across the nodes.

### Cassandra - Data Model

The data model of Cassandra provides an overview of **how Cassandra stores its data**.

#### Cluster

Cassandra database is distributed and the **outermost container is known as the Cluster**. For failure handling, **every node contains a replica**, and in case of a failure, the replica takes charge. Cassandra arranges the nodes in a cluster, in a **ring format**, and assigns data to them.

#### Keyspace

Keyspace is the **outermost container for data in Cassandra**. The basic attributes of a Keyspace are –

- **Replication factor** − is the number of machines in the cluster that will receive copies of the same data.
- **Replica placement strategy** − strategy to **place replicas in the ring**.
- **Column families** − Keyspace is a container for a list of one or more column families.

In Cassandra, You **can’t alter the name of a keyspace**. It is always a good practice after changing the replication factor or any modification you can execute the repair command : **nodetool repair -full**
You can execute the following CQL query for full repair.
nodetool repair -full

#### Column family

A column family, in turn, is a **container for an ordered collection of rows**. Also **each row is an ordered collection of columns**. Column families **represent the structure of your data**. Each Keyspace has at least one and often many column families.

| Relational Table                                             | Cassandra column Family                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| A **schema in a relational model is fixed**. Once we define certain columns for a table, while inserting data, in every row all the columns must be filled at least with a null value. | In Cassandra, although the column families are defined, the columns are not. You can **freely add any column** to any column family at any time. Cassandra **does not force individual rows to have all the columns**. |
| Relational tables **define only columns** and the user fills in the table with values. | In Cassandra, a table contains columns, or can be defined as a super column family. |

A Cassandra column family has the following attributes −

- **keys_cached** − It represents the number of locations to **keep cached per SSTable**.
- **rows_cached** − It represents the number of rows whose **entire contents will be cached** in memory.
- **preload_row_cache** − It specifies whether you want to **pre-populate the row cache**.

**Column** : A column is the basic data structure of Cassandra with **three values, key or column name, value, and a time stamp**. Structure of a Column => `[name<byte[]>, value<byte[]>, time-stamp]`

**SuperColumn** : A super column is a **special column**, it is also a **key-value pair**. But a super column stores a map of sub-columns. Structure of a Super Column => `[name<byte[]>, value<map<byte[], column>>]`

Generally **column families are stored on disk in individual files**. Therefore, to optimize performance, it is important to **keep columns that you are likely to query together** in the same column family, and a super column can be helpful here. 

#### Data Models of Cassandra and RDBMS

| RDBMS                                                        | Cassandra                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| RDBMS deals with **structured data** and has a fixed schema. | Cassandra deals with **unstructured** data and has a flexible schema. |
| In RDBMS, a table is an array of arrays. (ROW x COLUMN)      | In Cassandra, a table is a list of “nested key-value pairs”. (ROW x COLUMN key x COLUMN value) |
| **Database is the outermost container** that contains data corresponding to an application. | **Keyspace is the outermost container** that contains data corresponding to an application. |
| Tables are the entities of a database.                       | Tables or column families are the entity of a keyspace.      |
| Row is an individual record in RDBMS.                        | Row is a **unit of replication** in Cassandra.               |
| Column represents the attributes of a relation.              | Column is a **unit of storage** in Cassandra.                |
| RDBMS supports concepts of foreign keys, joins.              | Relationships are represented using **collections**.         |

### Cassandra Operations

#### Start Cassandra

```cassandra
$ cd $CASSANDRA_HOME
$./bin/cassandra -f 
//Using the –f option tells Cassandra to stay in the foreground instead of running as a background process. 
```

#### Cassandra - Cqlsh

By default, Cassandra provides a prompt **Cassandra query language shell** (cqlsh) that allows users to communicate with it. Using this shell, you can execute CQL commands like define a schema, insert data etc.

```CQL
[hadoop@linux bin]$ cqlsh
Connected to Test Cluster at 127.0.0.1:9042.
cqlsh>
```

#### Keyspace

A keyspace in Cassandra is **a namespace that defines data replication on nodes**. A cluster contains **one keyspace per node**. The CREATE KEYSPACE statement has **2 properties** : **replication and durable_writes**. 

- **Replication :** The replication option is to specify the **Replica Placement strategy** and the number of replicas wanted. Basically it is used for backup **to ensure no single point of failure**. Cassandra uses replication to achieve high availability and durability. **Each data item is replicated at N hosts**, where N is the replication factor **configured per-instance**.
- **Durable_writes :** Using this option, you can instruct Cassandra **whether to use commitlog for updates** on the current KeySpace. This option is not mandatory and by default, it is set to true, however it can be set to false.  You **cannot set this property to simple strategy**. You can verify whether the durable_writes property of test KeySpace was set to false by **querying the System Keyspace**. This query gives you all the KeySpaces along with their properties.

You can verify **create keyspace** result using the command **Describe** : display all the keyspaces created.

```CQL
//The syntax for creating a keyspace.
//CREATE KEYSPACE “KeySpace Name” WITH replication = {'class': ‘Strategy name’, 'replication_factor' : ‘No.Of  replicas’} AND durable_writes = ‘Boolean value’;
cqlsh> CREATE KEYSPACE tutorialspoint WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

cqlsh> DESCRIBE keyspaces;
tutorialspoint system system_traces 

//cluster1 is a keyspace using NetworkTopologyStrategy and there are two data centers one is east with RF( Replication Factor) = 2 and second is west with RF = 3.
CREATE KEYSPACE cluster1 WITH replication = {'class': 'NetworkTopologyStrategy', 
               'east' : 2, 'west' : 3};

// System Keyspace query gives you all the KeySpaces along with their properties.
cqlsh> SELECT * FROM system_schema.keyspaces;

//You can use a created KeySpace using the keyword USE
cqlsh> USE tutorialspoint;
cqlsh:tutorialspoint>

//ALTER KEYSPACE can be used to alter properties, number of replicas and durable_writes.
cqlsh.> ALTER KEYSPACE test WITH REPLICATION = {'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3} AND DURABLE_WRITES = true;
//after changing the RF or any modification, please execute the repair full command
nodetool repair -full

//You can drop a KeySpace using the command DROP KEYSPACE.
cqlsh> DROP KEYSPACE tutorialspoint;
```

#### Table

The primary key is **mandatory while creating a table**. A primary key is **made of one or more columns**. 

```CQL
// Using the keyspace tutorialspoint and  Creating a table named emp
cqlsh> USE tutorialspoint;
cqlsh:tutorialspoint>; CREATE TABLE emp(
   emp_id int PRIMARY KEY, emp_name text, emp_sal varint );
```

#### Cassandra - CQL Datatypes

CQL provides a rich set of **built-in data types**, including **collection date types**. Along with these data types, users can also create their own **custom data types**. Using these Collection types, you can **store multiple values in a single variable**.

##### List

A list is a **collection of one or more ordered elements**. You can get the values of a list data type **using the index** of the elements in the list. List is used in the cases where the order of the elements is to be maintained, and a value is to be stored multiple times. While inserting data into the elements in a list, enter all the values separated by comma within **square braces [ ]**.

```CQL
cqlsh:tutorialspoint> CREATE TABLE data(name text PRIMARY KEY, email list<text>);
cqlsh:tutorialspoint> INSERT INTO data(name, email) VALUES ('ramu',
['abc@gmail.com','cba@yahoo.com'])
```

##### SET

A set is a collection of one or more elements. The elements of a set will be **returned in a sorted order**. While inserting data into a set, enter all the values separated by comma within **curly braces { }**.

```CQL
cqlsh:tutorialspoint> CREATE TABLE data2 (name text PRIMARY KEY, phone set<varint>);
cqlsh:tutorialspoint> INSERT INTO data2(name, phone)VALUES ('rahman',    {9848022338,9848022339});
```

##### MAP

Map is a data type that is used to **store a key-value pair of elements**. While inserting data into the elements in a map, enter all the key : value pairs separated by comma within **curly braces { }**.

```CQL
cqlsh:tutorialspoint> CREATE TABLE data3 (name text PRIMARY KEY, address
map<timestamp, text>);
cqlsh:tutorialspoint> INSERT INTO data3 (name, address)
   VALUES ('robin', {'home' : 'hyderabad' , 'office' : 'Delhi' } );
```

##### CQL User Defined Datatypes

Cqlsh provides users a facility of creating and using user-defined data types. You can create a data type **to handle multiple fields**. The name used for user-defined data type **should not coincide with reserved type names**. The command **CREATE TYPE** is used to create a user-defined data type. Using **ALTER TYPE** command, you can add a new field or rename an existing field. Use the **DESCRIBE TYPE** command to verify whether the type created has been created or not.

```CQL
//CREATE TYPE <keyspace name>. <data typename> ( variable1, variable2).
cqlsh:tutorialspoint> CREATE TYPE card_details ( num int, name text, phone set<int>);
cqlsh:tutorialspoint> ALTER TYPE card_details ADD email text;
//renaming the field email to mail
cqlsh:tutorialspoint> ALTER TYPE card_details RENAME email TO mail;
cqlsh:tutorialspoint> describe type card_details;
cqlsh:tutorialspoint> drop type card_details;
```









