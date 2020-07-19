## WhatsApp (Chat Application S/M)

Design a **chat application** like a whatsapp or a facebook messenger

https://www.youtube.com/watch?v=RjQjbJ2UJDg

now let's look at some functional and non-functional requirements that this platform needs to support

### Functional Requirements

- support **one-to-one chat** where person can send a message to another person
- support **group chat** where somebody could make a group of people and whenever anybody sends a message to the group that message is received by everybody in the group.
- messages could contain text / images / videos or any other kind of documents
- **read receipt**
  - **single tick** when the message is sent
  - **double tick** when the message is delivered to the user
  - **blue tick** when the person sees the message
- show **last scene time** of a particular user

### Non-functional requirements

- Very **low latency** 
  - for a chat application it needs to look **very real-time** so while you are sending a message the other person should immediately be able to see that message.
- Very **high availability** 
  - system should not go down no matter what happens.
- **No lag** 
  - needs to be a very real-time system wherein i am typing something to you and you can see it then and there and then respond to it right.

### Scalability Numbers

- Total 2 billion users
- mau - 1.6 billion unique users
- per day 65 billion messages being sent.

### Diagram

![img](https://raw.githubusercontent.com/SnowflakeCoder/programming/master/images/whatsapp-design.png)

Users can use mobiles, web browsers, smartwatches etc to connect to WhatsApp.

#### Websocket Handler

A web socket handler basically is a **server on our backend** which is keeping **open connections with all the active users**. These are the live users who are having an active internet connection. Each machine has **65000 some ports** so it can handle 65000 active connections. Even if we keep 1K connections for our internal use and let's say 3-4 K connections for talking to everything else in the system we can still handle roughly **60K users per machine**. So based on the number of active users at any given point in time you can calculate the total number of servers required and all of these would be **distributed across the globe** so that people can always talk to nearby servers (handler) and this will **reduce the latency**.

The connections between user device and handler are **websocket connections** which is basically a **bi-directional** connection (there's no client and server here) so any party can send a message so that's
how even server is able to send a message to the client and these are built on top of **tcp**.

#### Websocket Manager

Websocket manager is a **repository** of, which **handler device is connected to what all users**. Now there are probably thousands of handler devices and we need to keep that information at some **central place** which is this websocket manager service. It sits on top of a **redis** which stores two information mainly.

- **User to handle connection** : It shows that user1 is connected to websocket handler1.
-  **List of user ids** that a particular handler is connected : It shows handler1 is connected to user1, user10, user20 etc like that.

Let's say a connection breaks between a user and a handler and now the user is connected to a different handler so that information is updated through this websocket manager into this redis.

#### Message Service

Message service is basically the **repository of all the messages** in the system and it will have different APIs like get a message by message id, get the message by user ids and **various filters** so if you want to fetch **all the unread messages by a particular user** and all of that that would be powered by this message service and it sits on top of a **cassandra database**.

**why Cassandra** because we have **billions of users** who will send many many billions of messages so Cassandra is a fairly good data store to store that kind of a volume plus the query pattern that we have
fairly aligns with the **query pattern that Cassandra** is good at. So that's the reason we choose Cassandra.

Now basically there are **two kind of messaging platforms**. One is like facebook which stores all of your messages so in that case everything would be stored in a cassandra permanently and then there is whatsapp which stores the messages only till the time it is not delivered to the user and once it gets a delivery acknowledgement then it deletes the message so if we want to build a whatsapp kind of system we could delete the messages from here also. Now **deletes are a bit inefficient in Cassandra** so to build that kind of a system we might choose some other data store as well.

### One to One User flow

- user1 wants to send a message to user2 with a message id m1.
  - user1 is connected  to **websocket handler1** and it sends this message to handler1 saying send this message m1 to user2.
  - when this happens websocket handler talks to websocket manager and message service parallely.
  - **Message service** will save the message into cassandra and give the message id m1.
  - **Websocket manager** will tell websocket handler1 that user2 is connected to websocket handler2.
  - Now **websocket handler1 will talk to websocket handler2** saying that i've got a message for user2 and I believe that user2 connected to you so go send them that message.
- When Websocket handler2 sends this message m1 to user2
  - If the user is not using the app actively but the message got delivered so the user device will send a message to handler2 saying that I have received the message (**double tick**).
  - If the person actually open on the chat of this user1 and could see the message then the device would send a message saying I have received and seen the message m1 (**blue tick**).
- Websocket handler2 again queries **websocket manager** saying that this message was now either delivered or read now I need to inform user1 so tell me which machine is talking to user1.
  - Now handler2 get the new server IP saying this server is now handling user1 and handler2 will send message to this new server saying this message id m1 has now been read or received so go and inform the user and this message will be informed to the user1.

### Websocket Handler Cache

In this whole process we queried websocket manager a lot of times and if the users sends multiple messages then again we'll try to do the same. So its better to cache this information. Each handler will cache two kinds of things.

- cache the **list of users** that are connected to itself so if user2 was also connected to the same machine it would have the data in memory it would never have to call this manager.

- Handler also cache the information about conversations that it has had recently for a very short duration of a time. So if user2 was connected to handler2,  handler1 will keep it in cache saying user2 is on this server (say handler2) because of this when user1 sends another message to this user2 it now need not call this web socket manager. 

  **Note:** this cache needs to be stored with a **very low time to live** and it should expire every 30 or seconds because when the communication between user device and the handler might break sometimes and the user might get connected to a different handler next time so to handle these cases keep this cache at a very **low ttl**.

### Message Status

If you want to store the read receipts (receipts that sent to user1 and user2) we can store that as a **message-status** in Cassandra in the same table saying this particular message has a status as sent then it will move to received then later it will move to read. We need to decide whether we want to store them permanently or not. But you still need to store it for at least some time because let's just say that user1 send a message to user2 and then went offline now the receipts wouldn't be received so at least for a short duration of time those read receipts need to be stored in the database and whenever user one comes online then he will get that.

Let's say user1 wanted to send a message m2 to user3 now again it will store in through message service and it will call websocket manager to get the handler for user3 but user3 is **offline** so there is no handler that is talking to user3 so websocket manager will return a response saying **no handler** and the flow ends there. When user3 comes online gets connected to handler3 what this websocket handler do is to **query message service** and ask for "are there any messages for user3 that are not in received or read status". This message service will respond back and user3 will see all those messages and those received and read communications will flow back through the same flow as above.

### Race Condition

Let's say when user1 was sending a message for user3 and assume at the exact same time user3 came online. First User1 will figure out the handler used for user3(websocket manager) and also store the message (message service), but these are **parallel calls**.

1. seq1 : websocket manager returned that there is no machine that is talking to user u3. 
2. seq2 : user u3 comes online and queries message service saying that get me all the messages.
3. seq3 :  New handler now stores into websocket manager saying that this handler3 is handling user3.
4. seq4 :  this message gets stored into this message service.

Here websocket handler3 thinks that it has fetched all the messages for user3 and displayed it on the device and handler1 thinks that user3 is offline and I stored the message and done my work but fundamentally this message m2 has not received user3 because before that message was was stored this websocket handler retrieved all the messages. So these kind of race conditions could happen when you are doing parallel calls. There are multiple **ways to handle** it.

- you can solve it by running it sequentially but that will **increase the latencies**. 
- Another solution is this websocket handler keeps pulling the server once in a while saying are there any messages that I still missed. So all of those messages could be pulled but that would happen at a **fairly low frequency** and that those **could be bulk calls** something like "are there **any messages for for all the active users** that this machine is connected to" which are in **send status**. So if it finds any messages it will send all of those messages as well.

### User device was offline

How it would handle scenarios when the device was offline : Let's say you want to send a message to somebody while you are offline, but internet is not there so it will not be able to connect to any servers. In that scenario all the **messages would be stored in a local database** which is persisted onto the device disk. For an android application it could use an **android database like a sqlite** which store all those messages on the disk. Whenever the device is online, it will pull those messages from the database and sends those messages.

### Handle Group messages

In case of groups the behavior is slightly different, the websocket handler is a very lightweight service and **wouldn't take care of fanning out to all the groups** . It just takes a message from a user and sends it to somebody or gets a message from somebody and sends it to users.

Let's say user u1 wanted to send a message m1 to group g1 

- For group messages, handler will **send a message to message service** saying user u1 wants to send a message m1 to group g1.
- Message service will **store the message into Cassandra** along with that it will also put all of those **messages into a kafka topic**.
- Now there will be a **group message handler**, this is basically a **kafka consumer** which is listening on to the same topic and it is **responsible for sending out all the group messages**.
- It does some basic validations first and then queries **group service** to get all the users that are into group g1. It gets the list of users then it will remove the user U1 (sender) from that list and now it has a list of the users that are supposed to get this message.
- After this it **follows the exact same flow that was used for one-to-one messages**, for all of these users. So it basically calls websocket managers saying give me all the handlers that these users have connected with then it will send the messages to individual handlers saying to this user send this message and then the receipts and all will follow the exact same way that was handled earlier.

### Encryption

When someone send a message, **encryption also happens right at device** and the encrypted messages are sent out and when the encrypted messages are received they're decrypted.

### Uploading assets

When people are **uploading assets** like images or videos or any content, first thing is there is some kind of a compression that happens at the user device (mobile or anything). 

Let's say u3 is sending a image to U2. This is basically a **two-step process**. 

- **upload content**: first step is upload an image to a server and get an image id. 

  So after compression of the content user device will talk to **asset service** through the load balancer. So all the other authentication / authorization will happen at this LB layer and asset service now gets the image or whatever content. Asset service will use **s3 as a data store** and store all the images there. Based on which image is getting what kind of hits on s3, it might decide to **move some of the images on cdn** and it could also decide to **move replicate into multiple cdns** or some series from where it is getting traffic. 

- and then send that **image id via the regular route** to user U2 and then U2 can fetch the image.

  After uploaded the image on data store (like s3) asset service sends a response saying imageID is I1 and asset service will send imageID I1 as a regular **message to U2 through the websocket handler** and the receipts also will happen in the same way mentioned earlier.

#### Optimization

Let's say lot of people are sharing the same image but we don't need to upload the same image multiple times. So before even uploads to asset service, the **device will take a hash** of those images and **send the hash content to asset service** asking do you have this image already or not if you have it then just send it to user U2 if you don't have it then the image needs to be uploaded and then it will follow the regular route.

When using hashing there would be various problems like **duplicate hash outputs** and **collisions** and all so instead of taking one hash we could **use five different hash algorithms** and use all of them to get five different hashes and if all the five hashes are matching against an image then image is already there. 

### Components

- **User App : **it could be a browser as well which talk to all of these below components.

- **User Service :** A service which **owns the user profile information** like name, userID, profile picture and other user preferences. All of this data would be residing in a **clustered mysql (say userDB)**. All of those user profile information would also be **cached in Redis cluster** against a key (userID).

- **Group Service :**  A service basically **maintains all the group information** (information like which group has what all users). This will be kind of like a many-to-many table with a user id and a group id and a time when user was acted and added into the group and maybe a status saying whether the user is admin or not etc. Again this information would be **stored in the mySQL cluster** which would be geographically distributed and it will have multiple slaves through which the read queries will be done. It will also have a Redis cluster sitting on top of it so all the APIs in the group service would be powered by this Redis cluster. Each time group service queries Redis and does not find the data, they query one of the slaves databases, fetch the information put it into the Redis and respond back. Group service was used in the group messaging workflow also.

- **Analytics Service :** Each action that a user is taking on their app gives a lot of information. For example if a person is visiting some user's profile a lot of times or a particular person is talking a lot about sports etc. So all events that are happening either they are events within the app or there are messages or anything of that sort would be **sent either to this analytic service and then to kafka** or directly to kafka in case of messages. There are two parts.

  - one is doing some **logic based on some streaming content** that could be done by a simple spark streaming consumer and that consumer would also put that data into **hadoop**. This **spark streaming consumer** could do a lot of inferences like message classifications (like a message is a sports or a general message or a politics message) and then try to classify users based on that.
  - You can also run a lot of analytics queries on **hadoop cluster**, like which are the people who talks about sports a lot or which are the people who just talk about a particular kind of political party etc. 

- **Last Seen Service :** This service used to **store the last seen time of a user**. Each time a person does an activity on the app those events are **listened by last seen service**. This service sit on top of a **Cassandra** database which has a column called user id and a column called last seen time. Alternatively we could **use Redis here if you have finite amount of data** but along with last seen time you want to store certain other information then **redis might have memory issues** so in that scenario you can use a Cassandra.

  **why don't we use the mysql here** : Let's say a user is on the app so every five seconds the app will send a notification that the user is live. So every five seconds per each user will have a insert into the database. So we need an **extremely high throughput** database. mySQL might not be able to provide that because these are all the writes which will go into the master node of mySQL but if it's a Cassandra it could be spread out easily across a lot of users. **Redis** might also have those update problem because for all the users you are doing a lot of inserts so **Redis might not be able to scale** but if it's a Cassandra it should **easily be able to scale** to any amount of traffic or any amount of users at any frequency.

There are two kinds of events that are spawned.

- **Events that are spawned by the app : **Example could be as soon as you turn the internet on the app would make a websocket connection and pull in some requests. Now that is not a user activity so that would not be captured in the last seen service.
- **Events that are spawned by the users : ** but as soon as the person opens the app or closes the app or does anything within the context of an app those are actually the events that last seen service would be looking at.

All of those services are horizontally scalable so when your number of users or anything increases you just add more number of nodes and that should be able to handle itself.

### Monitoring and Alerting

You obviously need to do a lot of monitoring and alerting to ensure that you are able to get to your non functional requirements of **no lag, low latency and high availability**. From a monitoring perspective, you should keep a track of **cpu and memory utilization** of your services, disk utilization in the databases, throughputs and latency of web services and the **disk utilization and lag in kafka**. 

All of those metrics you can capture, store it into a **grafana** kind of a store and **plot some graphs** out of it and also you can **build some alerting** out of it in case your numbers go beyond a certain threshold. For example if your throughput is increasing significantly you could add more machines and in fact those things could be **automated** as well if you're using **aws**. So you can write a script which looks at your throughput and adds more notes or removes certain nodes. 

All of those things are very important when it comes to a chat application because it needs to be actually real time if it's not real time then it will give a very bad experience and then people will kind of drop out of your platform. 





