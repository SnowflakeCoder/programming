# Facebook System Design

Facebook System Design | Instagram System Design | Social Network System Design

## Functional Requirements 

- **Make a Post** : Post is anything that we want to update on the platform. It could have an image, video, or just text. 
- Like, Comment & Share a Post.
- **Adding a Friend** : So a person should be able to go on somebody else's profile and add them as friend and this is a **non-directional relationship**. So the **friendship is non-directional** but there is a concept of **weightage of friendship** that would be a directional concept.
- See my **Timeline** : let's say I have hundred friends, so I would see all of that data posted by them in my timeline. So that's the **timeline functionality**.
-  **Able to see other post and others profiles**: Other's profile mean it's just a profile page through which you add them as friend. Other's post is basically a timeline of a particular user. 
- **Activity log**:  We should be able to log everything that a user does on this platform. For example if a user like posts something, like something, comment, share, search all of that should be tracked and should be visible to that user.

## Non-functional 

- Any social network is a very **read heavy system**. So for one write that is happening, there are probably hundreds of reads that are happening.
- **Very fast rendering and posting time**:  So if post something, I should get a **success message very quickly** and whenever I am looking at somebody's profile, my timeline all of that should also **render very quickly**. But **some lag is okay**. Now **lag and latency are two very different things**. **Latency should be low but lag is okay**. What lag means is a user post something and it **takes 10-20 seconds for their friends** to see that post.  But whenever they see it, the page should render very quickly.
- **Access Patterns**: In any social networks, when a post is created, it will suddenly get a lot of views, likes and comments. It reaches its peak, then slowly over time the post will **start to decay** and maybe after a couple of days or weeks there would be absolutely no interaction on to that post. We will use this **specifically for handling images and videos** because that is the heavy content and we might be **able to optimize on cost** by using this property of the access pattern.
- **Global**: This platform needs to be built in a way that can **be global**. It means that there are a huge variety of devices through which people will access it. Second thing is the platform should be **available in multiple languages** and able to handle **content in multiple languages**. Also it should not happen that it is **very slow in some geographies** and so we need to make sure that we have enough servers in that geography, so that they are able to access this platform in a fast enough way.

## Scale Numbers

Facebook has 1.7B Unique DAU, means users access facebook on to the read path at least and there are 2.6B MAU. **95%** of these people **access through mobile phones**. So we need to do some kind optimizations that are very specific to mobile devices. 

Every minute, these users upload **150K images**, which needs to be uploaded onto our server spread out in various CDNs and made available for the users. They update **300K statuses** which needs to be distributed across all the user base and they post **500K comments** every minute. Because of the sheer volume of data we **might not be able to cache everything** so there are some changes we'll do from the Twitter design.

## 5 User Segments

We have categorized users into five segments.

- **Famous users** are the users who have huge number of friends.
- **Active user** is a person who has access Facebook in the last few days.
- **Live users** are the ones actually browsing through the platform right now. So instead of waiting for them to request for new posts or content, we will send them notifications if a new content comes in. 
- **Passive users** are the users who are not regularly active, so we will not cache any content for them. Whenever they come into the system will try to generate whatever we need to do for them, okay. 
- **Inactive users** are basically the deactivated accounts, it could be fake profiles, it could be people who are requested to get their account deleted and these are people who **do not access Facebook**. We just have their accounts so these are **soft deleted accounts**. 

## User Flow

### Diagram

![Facebook Design User Action Flow.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Facebook%20Design%20User%20Action%20Flow.png?raw=true)

### User Onboarding Flow

#### User Service

User onboarding and login and user interactions like looking at their profile, creating their profile, updating their profile and all of that is taken care by **User Service**. User Service is the primary source of data for any user information. It provides all the APIs related to User information. User service sits on top of a clustered **MySQL DB**, because of the volume we might need to **shard the data** across multiple hardwares. It also sits on top of a **Redis cluster** which caches all the user information.

User service also stores what is the **user type against a user ID** and it would also able to update that. User service would be able to identify the User-Type by using events from other systems. So somebody would tell the service that user is now live, make it live or user is now become famous, make them famous etc.. This service also stores **Last Access Time** against UserID. Last Access Time mainly showing how much time back a user was online.

For each user actions like log in, account created or account updated, an event is pushed into **Kafka**. So that other components can take necessary actions. For example for new accounts we will be doing a **fraud check**, just to validate if they are not fake accounts. Also any updates to the account (like email id, phone number) we might want to inform the notification service. So all of that would be taken care of by this Kafka.

### Add Friend (Relation Graph Service)

A user can go onto somebody else's profile and add them as a friend. All of those interactions are taken care of by the **Relation Graph Service**. Graph Service maintains the whole graph of how the network is maintained. It keeps their relationships and also the weightages of the relationships. This is basically source of truth if you want to infer how close are two people or which users connected to what all people, Graph Service will expose API for all those kind of functionalities. Graph Service also sits on top of a clustered **MySQL Database** and need to be sharded. All the user relationships would also be cached in a Redis in which the key user ID, and value is basically "list of friends".

## Post Flow

Let's look at how does a user post content and how do they retrieve their timeline and other user posts.

### Diagram

![Facebook Post Design.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Facebook%20Post%20Design.png?raw=true)

### Short URL Service

Short URL Service converts at any URL into a short URL and then short URL would be posted on the platform. When a person clicks on short URL then it would be redirected to the long URL.

### Asset Service

A post can contain an image and a video. Asset service is basically your **Video Serving Platform**. It takes care of **file conversions to multiple formats, to multiple bandwidths, to multiple aspect ratios**. It takes care of streaming the content and all of that. 

It also consider **access pattern** while saving the content. So once the access to that content dies down, then we don't really need to keep it on CDN, so that the capacity can be used for some other content. So that is also taken care by Asset Service, saying that with **which particular content would reside on CDN and which would reside on S3**. In some cases like **festival images** suddenly some old photos will get popular, which was a very old photo which was removed from CDN. But now that it has become popular again, so this service would keep a track of how CDNs are being accessed, what is the hit rate on each content and if a content is access frequently into from S3, it will basically pull it from S3 and put it into all the CDNs wherever it is getting accessed from. So this is basically a Video/Image Streaming Optimization to take care of the **global geographical data**.

### Post Injestion Service

when a user try to post a content that is being taken care by **Post Injection Service**. If the post has audio/video content, Post Injestion Service will talk to **Asset Service**. If it has URLs, it'll talk to **Short URL Service**. And then it will have a final content that it will actually need to persist. Now for storing all post kind of data, I'm using a **Cassandra** here. The volume of posts are very high, so a Cassandra is a good choice here because it can handle that kind of traffic on both write path and the read path.

So whenever a post comes in, Post Injection Service reads the post, Puts it into Cassandra and then posts an event into a **Kafka** and from there on it will be notify other systems which will do their own jobs.

### Post Service

Post Service is the **source of truth service** and the owner of this post information. And this is a Service that will **provide APIs** to everybody else to access a particular post. 

### Analytics

Analytics is a collection of components, one is a Streaming Consumer which will keep listening to all the events that are coming in the kafka, and it will try to **tag it using a ML model** saying that this post is of what kind of a category. So basically it has a **classification model** that is ready and it applies the post on that model and come up with some tags for that particular post, which will be used to score to post on various relevant parameters. Once this analytics cluster tags a particular post ,it'll **put it back into Kafka** from where it will be processed further. So if somebody adds a post and others see it after a few seconds is okay and during that few seconds we can figure out who are the right set of users who should see this post. 

#### Relevance Tag

The idea of **Relevance Tags** is: so there's a lot of information that can be shown on

Facebook. Let's say if I update a status about sports, instead of sending that to

all of the friends, if we restrict that to only those friends who were actually

interested in sports, then that will be a very engaging...

then that could lead to an engaging conversation between me and them. But if

there are people who are totally not interested in sports and they also get

to see that, they will just scroll through it and that would not be a good

experience for them because they are seeing junk content because they don't

like that, plus it is opportunity loss for Facebook because it could have shown

something relevant which could have initiated a conversation through which some

more interactions would have happened, user would have probably spend more time

on Facebook, right. So Relevance Tags is something that will be powered through

analytics will get to know of Relevance Tags, and it will be used at the time of

figuring out, that who or needs to be shown a particular status or anything.

### Post Processor

So once this Analytics puts it back into Kafka, Post Processor will looks at the content. So it gets the event from Kafka, which has the content of the post and some tags along with that, and obviously the user ID who posted the content. It then queries **User Service** and **Group Service**, so this Post Service can fetch all the friends of this particular user, who potentially can see this post. Along with that it will also find out the **Relevance Tags stored against the user**. So we also do some **user profiling** to determine **classification of users into some tags**. 

So Post Processor fetches all the information about a post, all the users that this person is friends with, and tries to come up with a **subset of users who should see this post**. It's then puts all the relevant posts into Redis. So this **Redis** now contains a **timeline of all the users**. So the post processor will add the new posts into the timeline of these subset of users. So when user accesses their timeline, they can look up directly from Redis and don't need to do anything else.

### Timeline Service

So there are two kinds of timelines that a person can see. 

One is if they go to somebody else's profile and want to see all of their posts. So it basically calls **Timeline Service**. Timeline Service would then query **Post Service**, saying give me all the posts of this particular user and it displays it back.

#### Active User Post

When they want to see **their own timeline**. Which basically contains posts of all of their friends. This again calls **Timeline Service** which gets all the timeline from **Redis** for that particular user. But Redis would not contain data of all the users, it'll contain only **normal user's data**.

#### Famous User Post

Right now **we don't have a problem with famous user** because we just have friends, but let's say we introduce a concept of followers and famous users start having multiple millions of followers, then the problem is this Post Processor will have to update Redis for millions of users. And that is not scalable, so that's the reason we will basically give that responsibility to **Timeline Service** to merge the data for normal users and famous users. So whenever a particular user's timeline needs to be shown it queries **User and Group Service**, figures out the list of users that this person is friends with and it queries the user timeline from here, which is the post of all the regular users (normal users), and then it basically know the famous users that this person has friends with. It queries Post Service and ask the Post Service to give all the posts of the famous users. Then it aggregates the information and sent it back. Now before sending it back, it could also **persist this into Redis**, along with a **time stamp**. Now the next time when a request comes in we will basically look at this timestamp and if this time stamp is not so old we'll return the data as it is. But if its very old we again queries the Posts of famous users just in case they would have posted something.

#### Live User Timeline

So when an event is posted and Post Processor figures out that what all friends this guy has and who all needs to get the relevant posts. It queried User Service, so it also knows that the user is now actually live. So along with sending in to Redis, this service will put another event in a different topic into the Kafka, saying this user is live and this is the timeline that he needs to see now with this new additional content, somebody go tell given this information. Then comes the job of **Live User Service**, which is the service which has open connections based on **WebSockets** with all the clients(Users), who are on the platform right now and this Service has an open connection with all the users. Now whenever this service gets a particular event saying this user's view needs to be updated, it will send the event to the app via the WebSocket protocol and the app would then show up the new post.

### Archival Service

Now this Redis cannot contain data for a lot of period of time. We'll put only today's data(post) into this Redis. For everything in the past, we'll use something called as Archival Service. The timeline for a particular day once it is created, new things get added to it. But old things don't change from there, they remain the way they are. So we can cache this timeline. But instead of using an in-memory solution like Redis, we could use a **Cassandra** for storing that. So what archival service does is at some point in time during a day it basically fetches all the users timeline whatever have been created, it puts it into something called an **aggregated timeline Cassandra** and clears this cache. Now this makes sure that we have a **finite amount of data within Redis**, which is small. Now for the users where timeline doesn't (not active users) exist, it will try to create that kind of a timeline by calling Timeline Service and get the time line and save it for those users. When an actual time line needs to be created, **Timeline Service** not only query Redis and Post Service, it will also query from Archival Service if the user scrolls down a lot, and needs to see older data. All of the older data can be retrieved from Cassandra.

Now when we are saying about caching post, we are **just storing the post IDs**, we are not showing the actual content, and to get the actual content Post Service can be queried to construct the whole payload.

### Cassandra Hotspots

Now where we've used Cassandra, there is a potential problem that it could create hot spots. Cassandra is basically a distributed storage, across a lot of machines and if you **don't choose a partition key properly**, it could happen that only **one machine is serving most of the live traffic**, all the updates and reads are happening on one machine and all the **other machines are just lying idle** with no work. This would happen if you have a **partition key like a date range**. So all the updates and reads of today would happen on the partition which is handing today's data. So we need to be a bit careful about choosing the partition keys, a user ID based key would be a fairly good thing.

### Like

Whenever a user presses a like button, the request goes to this **Like Service**. Now this Like Service is our owner of all the like's information. So all the APIs related to like are powered by this Like Service. Whenever a person puts a like, that information gets stored into **Cassandra**. So a table contains userID, postID and likeType, where liketypes are upvote, downvote etc.

We also use a **Redis** here. Why? generally whenever a post is displayed, along with the post the number of likes are also shown. So the **number of likes is being powered by Redis**. Redis stores information of the count of likes on the recent posts. Once a post becomes outdated, this particular entry will have some TTL and that will get expired. So whenever a like comes into the system, it gets stored into Cassandra and Redis update gets called. So there's a **update and get in Redis**(INCR operation) which does the **atomic update into Redis**. 

Now once a like has been posted that information would also be put into **Kafka** saying this user has liked this particular post  for further analytics.

### Comment

Comment Service is the owner of all the comments into the system. This uses a Cassandra as a Datastore. It will have a postID, userID, timestamp and a comment text. We **don't really need a caching here**, we used Like-Redis because it was storing **aggregate information**. Doing a SELECT COUNT(*) query from Cassandra is bad. But here in case of comments, it is anyway look up by ID (PostID) and it's not too slow even if we use Cassandra. Whenever a comment is added it also gets posted into Kafka for further analytics.

**Sharing of post** would exactly be implemented in a way a post is implemented. So a shared post would be just another post with a parent ID of the original post so that it can be referenced that from where is this post coming from. 

### Activity Log

We want to capture all the activity done by a person on Facebook. So all that activity information is coming into this Kafka, all we need to do is to store that information against a userID, with the timestamp that this was the action taken, and some attributes of that action. So this **Cassandra** of Activity Tracker would primarily have a userID, a timestamp, an action. So this Activity Tracker would have both insert APIs into Cassandra and get APIs to fetch the data from Cassandra and show it on to an Activity UI. This Activity Tracker would listen to the same topic that everybody is writing to.

![Facebook Design Like+Comment.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Facebook%20Design%20Like+Comment.png?raw=true)

### Search

All the events are coming to Kafka and **search consumer** reads all those events, stores it into an **Elastic Search DB** which is very efficient for text-based searching and there is a Search Service that sits on top of that Elastic Search, run the query and returns the data. If we might want to cache the results of the search response, before sending it to the user we might cache it in a **Redis** as well. Also while a person is searching an information, an event also gets pushed into **Kafka** so that can be logged by **Activity Tracker**.



what kind of analytics can

be done. So let's say a person posts a content. That content can be

classified using some very standard classification ML Algorithms into

certain Tags. So for example a post could be tagged as a sports related post

right now if a person is having a lot of sports related posts, that means the

person is giving us a signal that that person is interested in to sports, right.

So what we will actually do is - you will have this Spark Streaming Sonsumer running

which will put all the information about all the activities and by user into this

Hadoop Cluster. That would include the post, the comments, the likes, what not.

And then we'll have some algorithm which basically do this user profiling

saying if a user is liking a lot of sports related posts, that means the

person is interested in that. If the person is commenting on similar posts,

that also gives the same signal, right. So all of those information could be used

to generate a user profile which classifies users interests. Now in real

Facebook we have the option to explicitly like a particular thing, right.

Let's say I like a sports page that means I'm explicitely giving a signal. But in case

of this scenario, we did not include it as part of functional requirements, so we

can use this data to infer all of those things. So that basically creates a

**user profile**. Once the user profile is created that is put back into Kafka, and

remember the very first section where we had a User Service which was storing a

User Profile Tag kind of a thing, User Service basically reads that event

and stores those tags against the user which is then used for various kind of

things in the system that we talked about in that section too.

### Graph Weight

Now there is something called Graph Weight. So what is that? - so let's say myself and

Elon must have friends on Facebook! hypothetically :) Now I might like a

lot of his posts, but he might not really like mine, right. What that tells is I

like to content that he posts but he does not like the content that I post.

So even though we are friends, even though will be shown some of the content

that both of us might post, but there is some affinity of mine towards seeing his

post, rather than somebody else's, right. That information can be captured by the

Graph Weight Job, which basically say that out of all my friends whose post have I

liked most or whose posts have a commented the most on. And bases that

logic, it will create a profile of mine saying I am more inclined towards these

two three people and less inclined towards the rest of them, so I'll see more

posts of these people. That becomes your Graphs Weight Job.

Now this runs on top of a Spark Cluster can query the data within Hadoop, run a

couple of spark jobs, do all the maths and publish data, right.

### Trends

It basically tells us what people on Facebook are talking about right now.

Basically what this Streaming Job will do, is basically it'll take all the

posts, all the comments, tokenize them and split them by space characters, remove

some very common stop words, like a' , 'and', 'of', 'the', 'for', 'is', and all of that, and then the

remaining words will be put into the Trends Service stackedranked by

their count. So at any point in time we will know **what word is the most used word**

right now. And that will give us some inferences about what kind of

things people are talking about and it cannot just be words it can be phrases

also and we can a lot of intelligence to figure out what

people are talking about and what people are interested in right now, and all

of that information could be stored in a **Redis** cache, because anyway it's a

temporary information for right now and it will get updated in a while and there

can be a UI kind of a thing that can we build on top of it if there's a need.





 there are a lot of

different kind of technologies used here, and we need to do a lot of **alerting monitoring** on all of these things.

We need to measure the latency that it is having, the

throughput that it is having, the CPU and memory usage within that, the disk usage

and then if anything kind of spikes up then we need to send out an alert.



Similarly for all the databases we need to keep a track on how many... what are the

throughput and latency are, how many queries are getting executed, what is the

disk space, we don't want a database disk to be full at any cost. So all of.. again

CPU, memories anyway are there, so all of those things on the database, on the caches, on

the web servers, on Kafka, all of these those things need to be monitored on and

as soon as they **go beyond a particular threshold**, there needs to be some good

enough alerting, so that the people get to know that something is going beyond

limits and can look into it proactively so as to make sure that we are

able to maintain the uptime and latency and everything

