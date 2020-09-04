# Database

Relational Database Management System(RDBMS) is not the correct choice when it comes to **handling big data** by the virtue of their design since **they are not horizontally scalable**. If the database runs on a single server, then it will reach a **scaling limit**. NoSQL databases are more scalable and provide superior performance.

| RDBMS  | Mongo      | Cassandra |
| ------ | ---------- | --------- |
| Table  | Collection |           |
| Tuple  | Document   |           |
| Column | Field      |           |

# What is NoSQL?

Data came in all shapes and sizes—**structured, semistructured, and polymorphic**—and defining the schema in advance became nearly impossible. NoSQL databases allow developers to **store huge amounts of unstructured data**, giving them a lot of flexibility. NoSQL databases are **non tabular** / **non relational** databases that store data differently than relational tables. They provide **flexible schemas** and scale easily with large amounts of data and high user loads. NoSQL databases can also store relationship data and when compared with SQL databases modeling relationship data in NoSQL databases to be easier than in SQL databases, because **related data doesn’t have to be split between tables**. NoSQL data models allow related data to be nested within a **single data structure**.

# 4 Types of NoSQL DBs

NoSQL databases come in a **variety of types based on their data model**. Four major types of NoSQL databases are : document databases, key-value databases, wide-column stores, and graph databases.

- **Document databases** store data in documents similar to JSON (JavaScript Object Notation) objects. Each document contains **pairs of fields and values**. They can **horizontally scale-out** to accommodate large data volumes. **MongoDB** is an example of a document database.
- **Key-value databases** are a simpler type of database where each item contains **keys and values**. A value can typically only be retrieved by referencing its key. Key-value databases are great for use cases where you need to store large amounts of data but you **don’t need to perform complex queries** to retrieve it. **Redis** and **DynamoDB** are popular key-value databases.
- **Wide-column databases** store data in tables, rows, and dynamic columns. In Wide-column databases each row is **not required to have the same columns**. Wide-column stores are great for when you need to store large amounts of data and **you can predict what your query patterns will be**. **Cassandra** and **HBase** are two of the most popular wide-column stores.
- Graph databases store data in **nodes and edges**. Nodes typically store information about people, places, and things while **edges store information about the relationships** between the nodes. Graph databases excel in use cases where you need to **traverse relationships to look for patterns** such as social networks, fraud detection, and recommendation engines. **Neo4j** and **JanusGraph** are examples of graph databases.





References

https://www.mongodb.com/nosql-explained







