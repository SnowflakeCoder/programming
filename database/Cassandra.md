## Cassandra



Apache Cassandra is a **NoSQL** database. A **highly scalable**, high-performance **distributed** database designed to handle large amounts of data (**structured data**) across many commodity servers, providing **high availability** with **no single point of failure**. 

- An open source, decentralized/distributed storage system, created at **Facebook**. 
- It is **scalable, fault-tolerant, and consistent**.
- It is a **column-oriented database**.
- Its distribution design is based on **Amazon’s Dynamo** and its data model on **Google’s Bigtable**. Cassandra implements a **Dynamo-style replication model** with no single point of failure, but adds a more powerful **“column family” data model**.

### Features of Cassandra

- **Elastic scalability** - Cassandra is highly scalable;
- **Always on architecture** - Cassandra has **no single point of failure** and is **continuously available**.
- **Fast linear-scale performance** - Cassandra is linearly scalable, i.e., it **increases your throughput as you increase the number of nodes** in the cluster. Therefore it maintains a **quick response time**.
- **Flexible data storage** - Cassandra **accommodates all possible data formats** including: structured, semi-structured, and unstructured. It can **dynamically accommodate changes to your data structures** according to your need.
- **Easy data distribution** - Cassandra provides the flexibility to distribute data where you need by **replicating data across multiple data centers**.
- **Transaction support** - Cassandra supports Atomicity, Consistency, Isolation, and Durability (**ACID**).
- **Fast writes** - Cassandra was designed to run on cheap commodity hardware. It performs blazingly fast writes and can store hundreds of terabytes of data, without sacrificing the read efficiency.

### Cassandra - Architecture

Cassandra has **peer-to-peer distributed system** across its nodes, and data is distributed among all the nodes in a cluster. **All the nodes in a cluster play the same role**. Each node is independent and at the same time interconnected to other nodes. **Each node in a cluster can accept read and write requests**, regardless of where the data is actually located in the cluster. When a node goes down, read/write requests can be served from other nodes in the network.

#### Data Replication in Cassandra

In Cassandra, one or more of the **nodes in a cluster act as replicas for a given piece of data**. If it is detected that some of the nodes responded with an **out-of-date value**, Cassandra will return the most recent value to the client. After returning the most recent value, Cassandra performs a **read repair in the background to update the stale values**. Cassandra uses the **data replication among the nodes in a cluster to ensure no single point of failure**. Cassandra uses the **Gossip Protocol** in the background to allow the nodes to communicate with each other and **detect any faulty nodes** in the cluster.

#### Components of Cassandra

- **Node** − It is the place where **data is stored**.
- **Data center** − It is a **collection of related nodes**.
- Cluster − A cluster is a component that **contains one or more data centers**.
- **Commit log** − The commit log is a **crash-recovery mechanism** in Cassandra. **Every write operation** is written to the commit log.
- **Mem-table** − A mem-table is a **memory-resident data structure**. **After commit log**, the data will be written to the mem-table. Sometimes, for a **single-column family**, there will be multiple mem-tables.
- **SSTable** − It is a disk file to which the **data is flushed from the mem-table** when its **contents reach a threshold value**.
- **Bloom filter** − These are **quick, nondeterministic, algorithms** for testing **whether an element is a member of a set (SStable)**. Bloom filters are **accessed after every query**.
- **Compaction** - The **process of freeing up space** by **merging large accumulated data files is called compaction**. During compaction, the data is merged, indexed, sorted, and stored in a new **SSTable**. Compaction also **reduces the number of required seeks**.

#### Query Language (CQL)

Users can access Cassandra **through its nodes** using CQL. CQL treats the database (Keyspace) as a container of tables. Programmers use **cqlsh**: a prompt to work with CQL or separate application language drivers to access Cassandra. Clients approach any of the nodes for their read-write operations. That node (coordinator) **plays a proxy** between the client and the nodes holding the data.

##### Write Operations

Every write activity of nodes is captured by the **commit logs** written in the nodes. Later the data will be captured and stored in the **mem-table**. Whenever the mem-table is full, data will be written into the **SStable data file**. All writes are automatically partitioned and replicated throughout the cluster. Cassandra **periodically consolidates the SSTables**, discarding unnecessary data.

##### Read Operations

During read operations, Cassandra gets **values from the mem-table** and checks the bloom filter to find the appropriate **SSTable** that holds the required data and gets values from SSTables.

#### Cassandra - Data Model

The data model of Cassandra provides an overview of how Cassandra stores its data.
Cluster
Cassandra database is distributed and the outermost container is known as the Cluster. For failure handling, every node contains a replica, and in case of a failure, the replica takes charge. Cassandra arranges the nodes in a cluster, in a ring format, and assigns data to them.
Keyspace
Keyspace is the outermost container for data in Cassandra. The basic attributes of a Keyspace in Cassandra are –
	Replication factor − It is the number of machines in the cluster that will receive copies of the same data.
	Replica placement strategy − It is nothing but the strategy to place replicas in the ring. We have strategies such as simple strategy (rack-aware strategy), old network topology strategy (rack-aware strategy), and network topology strategy (datacenter-shared strategy).
	Column families − Keyspace is a container for a list of one or more column families. A column family, in turn, is a container for an ordered collection of rows. Also each row is an ordered collection of columns. Column families represent the structure of your data. Each keyspace has at least one and often many column families.

The syntax of creating a Keyspace is −
CREATE KEYSPACE Keyspace name WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};
Differentiate a column family from a table of relational databases.
Relational Table
	A schema in a relational model is fixed. Once we define certain columns for a table, while inserting data, in every row all the columns must be filled at least with a null value.
	Relational tables define only columns and the user fills in the table with values.	
Cassandra column Family
	In Cassandra, although the column families are defined, the columns are not. You can freely add any column to any column family at any time. Cassandra does not force individual rows to have all the columns.
	In Cassandra, a table contains columns, or can be defined as a super column family.
A Cassandra column family has the following attributes −
	keys_cached − It represents the number of locations to keep cached per SSTable.
	rows_cached − It represents the number of rows whose entire contents will be cached in memory.
	preload_row_cache − It specifies whether you want to pre-populate the row cache.
Column : A column is the basic data structure of Cassandra with three values, key or column name, value, and a time stamp.
Structure of a Column = [name<byte[]>, value<byte[]>, time-stamp]
SuperColumn : A super column is a special column, therefore, it is also a key-value pair. But a super column stores a map of sub-columns.
Structure of a Super Column = [name<byte[]>, value<map<byte[], column>>]
Generally column families are stored on disk in individual files. Therefore, to optimize performance, it is important to keep columns that you are likely to query together in the same column family, and a super column can be helpful here. 
Differentiate data model of Cassandra from that of an RDBMS.
RDBMS
	RDBMS deals with structured data. It has a fixed schema.
	In RDBMS, a table is an array of arrays. (ROW x COLUMN)
	Database is the outermost container that contains data corresponding to an application.
	Tables are the entities of a database. 
	Row is an individual record in RDBMS and Column represents the attributes of a relation.
	RDBMS supports the concepts of foreign keys, joins.

Cassandra
	Cassandra deals with unstructured data. Cassandra has a flexible schema.
	In Cassandra, a table is a list of “nested key-value pairs”. (ROW x COLUMN key x COLUMN value)
	Keyspace is the outermost container that contains data corresponding to an application.
	Tables or column families are the entity of a keyspace.
	Row is a unit of replication in Cassandra and Column is a unit of storage in Cassandra.
	Relationships are represented using collections.
Start Cassandra
Navigate to Cassandra-home directory/home and run the following command to start your Cassandra server.
$ cd $CASSANDRA_HOME
$./bin/cassandra -f 

Using the –f option tells Cassandra to stay in the foreground instead of running as a background process. 
Cassandra - Cqlsh
By default, Cassandra provides a prompt Cassandra query language shell (cqlsh) that allows users to communicate with it. Using this shell, you can execute Cassandra Query Language (CQL). Using cqlsh, you can define a schema, insert data, and execute a query.
Starting cqlsh : Start cqlsh using the command cqlsh. It gives the Cassandra cqlsh prompt as output.
Creating a Keyspace using Cqlsh
A keyspace in Cassandra is a namespace that defines data replication on nodes. A cluster contains one keyspace per node. The CREATE KEYSPACE statement has two properties: replication and durable_writes. The syntax for creating a keyspace.
CREATE KEYSPACE <identifier> WITH <properties>
CREATE KEYSPACE “KeySpace Name” WITH replication = {'class': ‘Strategy name’, 'replication_factor' : ‘No.Of  replicas’} AND durable_writes = ‘Boolean value’;

Replication 
This option is to specify the Replica Placement strategy and the number of replicas wanted. 
Replica placement strategies
	Simple Strategy: Specifies a simple replication factor for the cluster.
	Network Topology Strategy : Using this option, you can set the replication factor for each data-center independently.
	Old Network Topology Strategy : This is a legacy replication strategy.
cqlsh> CREATE KEYSPACE tutorialspoint WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};
You can verify whether the table is created or not using the command Describe.
cqlsh> DESCRIBE keyspaces;  =>  will display all the keyspaces created
Durable_writes
Using this option, you can instruct Cassandra whether to use commitlog for updates on the current KeySpace. This option is not mandatory and by default, it is set to true, however it can be set to false. You can verify whether the durable_writes property of test KeySpace was set to false by querying the System Keyspace. 
cqlsh> SELECT * FROM system.schema_keyspaces;   =>  Gives you all the KeySpaces along with their properties.
Using a Keyspace
Syntax:USE <identifier>     => You can use a created KeySpace using the keyword USE. 
Example : cqlsh> USE tutorialspoint;
Creating a Keyspace using Java API
Important classes in Cassandra
Note : All these classes belongs to com.datastax.driver.core package.
	Cluster : This class is the main entry point of the driver. 
o	Session connect()  : It creates a new session on the current cluster and initializes it.
o	void close() : It is used to close the cluster instance.
o	static Cluster.Builder builder() : It is used to create a new Cluster.Builder instance.
	Cluster.Builder : This class is used to instantiate the Cluster.Builder class.
o	Cluster.Builder addContactPoint(String address) : This method adds a contact point to cluster.
o	Cluster build() : This method builds the cluster with the given contact points.
	Session : This interface holds the connections to Cassandra cluster. Using this interface, you can execute CQL queries. 
o	void close() : This method is used to close the current session instance.
o	ResultSet execute(Statement statement) : This method is used to execute a query. It requires a statement object.
o	ResultSet execute(String query) : This method is used to execute a query. It requires a query in the form of a String object.
o	PreparedStatement prepare(RegularStatement statement) :  This method prepares the provided query. The query is to be provided in the form of a Statement.
o	PreparedStatement prepare(String query) : This method prepares the provided query. The query is to be provided in the form of a String.
You can create a Keyspace using the execute() method of Session class.
	Step1: Create a Cluster Object
o	First of all, create an instance of Cluster.builder class.
o	Add a contact point (IP address of the node) to the Cluster.Builder object.
o	Using the new builder object, create a cluster object by calling build() in the Cluster.Builder class.
Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();  
	Step 2: Create a Session Object.
o	Create an instance of Session object using the connect() method. This method creates a new session and initializes it. If you already have a keyspace, you can set it to the existing one by passing the keyspace name in string format to this method.
Session session = cluster.connect( );	OR
Session session = cluster.connect(“ Your keyspace name ” );
	Step 3: Execute Query to create a KeySpace.
o	You can execute CQL queries using the execute() method of Session class. Pass the query either in string format or as a Statement class object to the execute() method. Whatever you pass to this method in string format will be executed on the cqlsh.
	Step4 : Use the KeySpace
o	You can use a created KeySpace using the execute() method.
   public static void main(String args[]){
      //creating Cluster object
      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
    //Creating Session object
      Session session = cluster.connect(); 
      //Executing the query
      String query = "CREATE KEYSPACE tp WITH replication " 
+ "= {'class':'SimpleStrategy', 'replication_factor':1};";
      session.execute(query);
      //using the KeySpace
      session.execute("USE tp");
      System.out.println("Keyspace created"); 
   }

Alter Keyspace using Cqlsh

ALTER KEYSPACE can be used to alter properties such as the number of replicas and the durable_writes of a KeySpace. The properties of ALTER KEYSPACE are same as CREATE KEYSPACE : replication and durable_writes.

ALTER KEYSPACE testKeySpace WITH REPLICATION = {'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3} AND DURABLE_WRITES = true;

You can also alter a keyspace using the execute() method of Session class (using Java API).  Here We are altering a keyspace named tp, replication option to Network Topology Strategy and durable_writes to false

      String query = "ALTER KEYSPACE tp WITH replication " + "= {'class':'NetworkTopologyStrategy', 'datacenter1':3}" 
+ "AND DURABLE_WRITES = false;";
      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();   
      Session session = cluster.connect(); 
      session.execute(query);

Dropping a Keyspace using Cqlsh

You can drop a KeySpace using the command DROP KEYSPACE. 

DROP KEYSPACE “KeySpace name”
Example : cqlsh> DROP KEYSPACE tutorialspoint;

cqlsh> DESCRIBE keyspaces;   =>  Verify the keyspaces using the command Describe and check whether the table is dropped.

You can also drop a keyspace using the execute() method of Session class (using Java API). 

      String query = "Drop KEYSPACE tp";
      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
      Session session = cluster.connect();
      session.execute(query);



Creating a Table using Cqlsh

You can create a table using the command CREATE TABLE. You can define a column as column_name data type.
CREATE (TABLE | COLUMNFAMILY) <tablename>  ('<column-definition>' , '<column-definition>') 
(WITH <option> AND <option>)

Primary Key
The primary key is a column that is used to uniquely identify a row. Therefore,defining a primary key is mandatory while creating a table. A primary key is made of one or more columns of a table. 
CREATE TABLE tablename(
   column1_name datatype PRIMARY KEY,
   column2_name data type, 
   column3_name data type
   )

Example
cqlsh> USE tutorialspoint;
cqlsh:tutorialspoint>; CREATE TABLE emp(
   emp_id int PRIMARY KEY,
   emp_name text,
   emp_sal varint
   );

Verify the table using the select statement. The select statement will give you the schema. 
cqlsh:tutorialspoint> select * from emp;

Creating a Table using Java API

      //Query
      String query = "CREATE TABLE emp(emp_id int PRIMARY KEY, "
         + "emp_name text, "    + "emp_city text, "    + "emp_sal varint, "  + "emp_phone varint );";
    	
      Session session = cluster.connect("tp");
      session.execute(query);

Altering a Table using Cqlsh
You can alter a table using the command ALTER TABLE. 
ALTER (TABLE | COLUMNFAMILY) <tablename> <instruction>

Using ALTER command, you can perform the following operations: Add a column and Drop a column

Adding a Column
While adding columns, you have to take care that the column name is not conflicting with the existing column names and that the table is not defined with compact storage option. 

ALTER TABLE table_name ADD  new_column datatype;
cqlsh:tutorialspoint> ALTER TABLE emp ADD emp_email text;

Dropping a Column
Before dropping a column from a table, check that the table is not defined with compact storage option. 

ALTER table name DROP column name;
cqlsh:tutorialspoint> ALTER TABLE emp DROP emp_email;
Dropping a Table using Cqlsh

You can drop a table using the command Drop Table.
DROP TABLE <tablename>
cqlsh:tutorialspoint> DROP TABLE emp;

Use the Describe command to verify whether the table is deleted or not. Since the emp table has been deleted, you will not find it in the column families list.
cqlsh:tutorialspoint> DESCRIBE COLUMNFAMILIES;

Truncating a Table using Cqlsh
You can truncate a table using the TRUNCATE command. When you truncate a table, all the rows of the table are deleted permanently. 

TRUNCATE <tablename>
cqlsh:tp> TRUNCATE student;

Verify whether the table is truncated by executing the select statement. 
cqlsh:tp> select * from student;

Creating an Index using Cqlsh
You can create an index in Cassandra using the command CREATE INDEX. 
CREATE INDEX <identifier> ON <tablename>
Given below is an example to create an index to a column ‘emp_name’ in a table named emp.
cqlsh:tutorialspoint> CREATE INDEX name ON emp (emp_name);

Dropping an Index using Cqlsh
You can drop an index using the command DROP INDEX. 
DROP INDEX <identifier>
cqlsh:tp> drop index name;

Executing Batch Statements Using Cqlsh
Using BATCH, you can execute multiple modification statements (insert, update, delete) simultaneiously. 
BEGIN BATCH
<insert-stmt>/ <update-stmt>/ <delete-stmt>
APPLY BATCH
Example
cqlsh:tutorialspoint> BEGIN BATCH
... INSERT INTO emp (emp_id, emp_city, emp_name, emp_phone, emp_sal) values(  4,'Pune','rajeev',9848022331, 30000);
... UPDATE emp SET emp_sal = 50000 WHERE emp_id =3;
... DELETE emp_city FROM emp WHERE emp_id = 2;
... APPLY BATCH;

Batch Statements using Java API
Batch statements can be written programmatically in a table using the execute() method of Session class. 

      String query =" BEGIN BATCH INSERT INTO emp (emp_id, emp_city,
         emp_name, emp_phone, emp_sal) values( 4,'Pune','rajeev',9848022331, 30000);"
         + "UPDATE emp SET emp_sal = 50000 WHERE emp_id =3;"
         + "DELETE emp_city FROM emp WHERE emp_id = 2;"      + "APPLY BATCH;";
    
      session.execute(query);






Creating Data using Cqlsh

INSERT INTO <tablename> (<column1 name>, <column2 name>....) VALUES (<value1>, <value2>....) USING <option>
cqlsh:tutorialspoint> INSERT INTO emp (emp_id, emp_name, emp_city) VALUES(1,'ram', 'Hyderabad');

Updating Data using Cqlsh

While updating rows, if a given row is unavailable, then UPDATE creates a fresh row.
UPDATE <tablename> SET <column name> = <new value>, <column name> = <value> WHERE <condition>
cqlsh:tutorialspoint> UPDATE emp SET emp_city='Delhi',emp_sal=50000 WHERE emp_id=2;

Reading Data using Select Clause

SELECT FROM <tablename>
cqlsh:tutorialspoint> select * from emp;
cqlsh:tutorialspoint> SELECT emp_name, emp_sal from emp;

Java API - Get the ResultSet Object
You can execute CQL queries using execute() method of Session class. The select queries will return the result in the form of a ResultSet object, therefore store the result in the object of RESULTSET class as shown below.

String query = "SELECT * FROM emp";
ResultSet result = session.execute( );

Delete Data

DELETE FROM <identifier> WHERE <condition>;
cqlsh:tutorialspoint> DELETE emp_sal FROM emp WHERE emp_id=3;
Cassandra - CQL Datatypes
CQL provides a rich set of built-in data types, including collection types. Along with these data types, users can also create their own custom data types. 

CQL Collections
Collection data Types - Using these Collection types, you can store multiple values in a single variable.

List
A list is a collection of one or more ordered elements. You can get the values of a list data type using the index of the elements in the list. List is used in the cases where 
•	the order of the elements is to be maintained, and
•	a value is to be stored multiple times.

Creating a Table with List
Create a table with two columns, name and email. To store multiple emails, we are using list.
cqlsh:tutorialspoint> CREATE TABLE data(name text PRIMARY KEY, email list<text>);

Inserting Data into a List
While inserting data into the elements in a list, enter all the values separated by comma within square braces [ ].
cqlsh:tutorialspoint> INSERT INTO data(name, email) VALUES ('ramu', ['abc@gmail.com','cba@yahoo.com'])

Updating a List
Here we are adding another email to the list.
cqlsh:tutorialspoint> UPDATE data SET email = email +['xyz@tutorialspoint.com']  where name = 'ramu';

SET
A set is a collection of one or more elements. The elements of a set will be returned in a sorted order.

Creating a Table with Set
Create a table with two columns, name and phone. For storing multiple phone numbers, we are using set.
cqlsh:tutorialspoint> CREATE TABLE data2 (name text PRIMARY KEY, phone set<varint>);

Inserting Data into a Set
While inserting data into the elements in a set, enter all the values separated by comma within curly braces { }.
cqlsh:tutorialspoint> INSERT INTO data2(name, phone)VALUES ('rahman',    {9848022338,9848022339});

Updating a Set
Here we are adding another phone number to the set.
cqlsh:tutorialspoint> UPDATE data2 SET phone = phone + {9848022330} where name = 'rahman';

MAP
Map is a data type that is used to store a key-value pair of elements.

Creating a Table with Map
Create a table with two columns, name and address. For storing multiple address values, we are using map.
cqlsh:tutorialspoint> CREATE TABLE data3 (name text PRIMARY KEY, address map<text, text>);

Inserting Data into a Map
While inserting data into the elements in a map, enter all the key : value pairs separated by comma within curly braces { }.
cqlsh:tutorialspoint> INSERT INTO data3 (name, address)  VALUES ('robin', {'home' : 'hyderabad' , 'office' : 'Delhi' } );

Updating a Set
Here we are changing the value of the key office (changing the office address) of a person named robin.
cqlsh:tutorialspoint> UPDATE data3 SET address = address+{'office':'mumbai'} WHERE name = 'robin';

CQL User Defined Datatypes

Cqlsh provides users a facility of creating and using user-defined data types. You can create a data type to handle multiple fields. The name used for user-defined data type should not coincide with reserved type names.

Creating a User-defined Data Type
The command CREATE TYPE is used to create a user-defined data type. 
CREATE TYPE <keyspace name>. <data typename> ( variable1, variable2).

Example for creating creating a card_details data type containing the following details.
cqlsh:tutorialspoint> CREATE TYPE tutorialspoint.card_details (num int, pin int, name text, phone set<int>);

Use the DESCRIBE TYPE command to verify whether the type created has been created or not.
cqlsh:tutorialspoint> describe type card_details;

Altering a User-defined Data Type
Using ALTER TYPE command, you can add a new field or rename an existing field.

Adding a new Field to a Type
ALTER TYPE typename ADD field_name field_type; 
cqlsh:tutorialspoint> ALTER TYPE card_details ADD email text;  => adds a new field (email) to the Card_details.

Renaming a Field in a Type
ALTER TYPE typename RENAME existing_name TO new_name;
cqlsh:tutorialspoint> ALTER TYPE card_details RENAME email TO mail;  => renaming the field email to mail.

Deleting a User-defined Data Type
DROP TYPE is the command used to delete a user-defined data type.  Before deleting, verify the list of all user-defined data types using DESCRIBE_TYPES command.
cqlsh:tutorialspoint> DESCRIBE TYPES;

cqlsh:tutorialspoint> drop type card; => delete the type named card.

List of built-in data types available in CQL.

Data Type	Constants	Description
ascii	strings	Represents ASCII character string
bigint	bigint	Represents 64-bit signed long
blob	blobs	Represents arbitrary bytes
Boolean	booleans	Represents true or false
counter	integers	Represents counter column
decimal	integers, floats	Represents variable-precision decimal
double	integers	Represents 64-bit IEEE-754 floating point
float	integers, floats	Represents 32-bit IEEE-754 floating point
inet	strings	Represents an IP address, IPv4 or IPv6
int	integers	Represents 32-bit signed int
text	strings	Represents UTF8 encoded string
timestamp	integers, strings	Represents a timestamp
timeuuid	uuids	Represents type 1 UUID
uuid	uuids	Represents type 1 or type 4
varchar	strings	Represents uTF8 encoded string
varint	integers	Represents arbitrary-precision integer