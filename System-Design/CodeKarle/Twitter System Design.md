# Twitter System Design

## Functional Requirements

- **Tweet** - We should be able to Tweet, which means we should be able to **post a content of 140 characters** length. It could contains an image, video or links to external websites.
- **Re-Tweet** - If somebody has tweeted something, you should be able to Re-Tweet that.
- **Follow** - should be able to **follow someone**. Now that is a **unidirectional** relationship.
- **Search** - You should be able to **search for some content** within all the tweets that have been posted on Twitter by some users. This would be usually used to do some **trend analysis** or to figure out **what's happening in the world** or you want to read about a particular topic. 

## Non-Functional Requirements

- **Read heavy platform** - Twitter has **100 times** more read traffic compared to the write traffic. If one user is posting something that is seen by a lot of users.
- **Render very fast** - So when user look at their home screen /home timeline, it should render in **less than a second**. This will improve the user experience. In **some cases lag is okay**, if somebody posted a tweet and I might be able to see it after 20 seconds, so that is okay. But whenever I am seeing it, it should render/load very fast.
- **Fast Tweet** - When you are tweeting something that flow should be fast. So you should get a success response saying that your tweet has been posted successfully. So the system should be having a very **low latency** and should be **always available**.

## Scale

- Twitter has roughly **150M** Daily Active Users(DAU), on the reader path at least and **350M** MAU.
- Overall Twitter has roughly 1.5B User Accounts.
- Twitter has roughly 500M Tweets coming per day.

## 5 User Categories

We can divide all the users into **5 categories** and will handle each category in a different way.

- **Famous** : people who have **huge number of followers** on Twitter.
- **Active** : people who are accessing Twitter in last couple of days or a couple of hours.
- **Live** : **subset of active users**, people who are actually accessing the platform right now. As soon as a person goes offline, they are moved back to active status.
- **Passive** : people having **active accounts**, but not accessed Twitter in the last 3 days and are not famous.
- **Inactive** : They are the **deleted accounts**, but we don't delete any users, we do a soft delete (deactivate). We don't really do any actions for those users.

## User Action Flow

The whole system is fairly big with lot of components.  So I have **divided it into three components**.

- **user onboarding flow** : explains how do they come into the system.
- **Tweeting flow** : talks about **how do they post a Tweet**, and **how do they get the various screens** (home timelines and user timelines).
- **search & analytics** :



**Load Balancer** : Assume along with load balancing, Load Balancers also take care of being a **reverse proxy** and **authentication, authorization layer** in between so that they can **authenticate all the API calls** coming from outside system into our system. 

### Diagram

![Twitter Design User Action Flow.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Twitter%20Design%20User%20Action%20Flow.png?raw=true)

### User Onboarding Process (MySQL & Redis)

This is the place where user come in and register themselves on our platform. 

**User Service** is the **source of truth of all the user related information** in our system and this Service will power the **login flow**, **registered flow**, **user profile screen** etc. User Service will also provide APIs required to get information about a user, set of **GET APIs** like get by userID, get by emailID etc and It'll also have some **post APIs to update the details** of a user and **bulk get API** to fetch the information of a lot of users.

**Storage** : User information is a very much **relational information** and Twitter has millions of users, but they are still finite enough and it **will not go unboundedly**. So we use a **clustered MySQL DB**.

Also this user information **doesn't change too frequently**, so reads can be powered by using a **Redis cache**, which has a key of user ID. So when someone hits User Service to fetch details of a user it first tries to look up in Redis, if it has, it returns from there else it will query one of the **MySQL read slaves**, get the information, store it in Redis, and respond back to the client. So next time when the requests come for the same users they can be served out of this Redis cache.

### User Follow Process (MySQL & Redis)

Any user of this platform can go to someone else's profile and then follow them. They might also want to get certain information like **who do I follow** or **how many user follow me**. All of these APIs are powered by **User Follow Graph Service**. The reason it's called Graph Service is because it creates a **network of how everybody in the ecosystem is connected**. This Service will have APIs to **add a new follow** link, **get all the followers** of a person, get **all the people who this person is following** and similar **bulk APIs**.

**Storage** : This service also sits on top of another **MySQL DB**, but this can be a **fairly larger data**, so we will have to probably **shard data**. It will have one table with userID, followerID and a timestamp on which the transactions happened.

Again this data also **does not get updated very frequently** so it **makes sense to cache** this information as well. Now there are **two kinds of information** that we need to cache here. 

- Given a userID what are the list of users that this person follows. (**User follow list**)
- Given a userID who are the list of users that follow this particular user (followers of this user).

These are the set of two information that we are storing in **another Redis**. Again similar to User Service, all the APIs first try to get data from Redis, if they don't find the information, query the MySQL, persist the data into Redis and respond back. 

### User events flow / Analytics (Kafka)

Whenever a user is interacting with Twitter, say they are browsing through a list of tweets or spend a lot of time on one of the tweets, they are kind of telling that they have interest in these kinds of tweet. There are lot of such information that we can gather based on the way people access the whole platform. So whenever we find some important user action this UI (browser/app) send an event to **Analytics Service**, which we'll be putting into a **Kafka** and we can use it later for analytics.

### User Live Events

Live Users are the users who are using our platform right now. Let's say if I'm using Twitter right now and one of the people who I follow posted something or somebody tagged me in a tweet and  if I **get notified** about that then that would be a **nice user experience**. So that is done through this service. This service basically keeps a **(WebSocket) connection** **open with all the live users**, so whenever any event happens that needs to be sent to this user it happens via that WebSocket.

As part of the interactions between the user and the server, we are also capturing till how much **time the user is actually live** and when this communication between the two parties stop, we do realize that the person is not live anymore. If the **person is not live** we cannot send them this kind of a notification. So that information will be put into **Kafka** saying the user is not live anymore, he was last live at a particular time stamp. That information would go through all the way to **User Service**. It will store it in the cache saying the **user type has now moved from a live user, to active user**. His **last access time** was some particular timestamp and then other systems can use that information to modify the behavior in certain cases. 

## Tweeting Flow

### Diagram

![Twitter Design Tweeting Flow.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Twitter%20Design%20Tweeting%20Flow.png?raw=true)

### Post a Tweet

#### Asset Service (CDN)

Tweets could contain text, images, videos and links. Asset Service is **responsible for all the multimedia content**, like videos and images. It takes care of uploading it to wherever it needs to and even when somebody needs to see it this is responsible for displaying the content. Asset Service is basically our **video hosting platform**.

#### Short URL Service

Tweet has a **constraint of 140 characters**, so whenever you want to **post a big URL**, there will be a Short URL Service, which will give you a short URL instead of the big URL and you could post that.

#### Tweet Ingestion Service (Cassandra)

Tweet Ingestion Service is the entry point of all the Tweets into our system. This service take care of storing the text of a Tweet, and retrieving it when we need to. So each time a person posts a Tweet, **first thing** Tweet Ingestion Service does is stores it in a permanent Datastore, **Cassandra**. So the Tweet Ingesting Service is just responsible for storing a tweet. It **doesn't provide a get API** and all. The Tweet table has a tweetID, userID, Tweet content, and a couple of other attributes okay. Once it has posted to Cassandra, it puts an event into **Kafka** saying it got a new tweet with a TweetID and it has been posted by this userID and this is the content. TweetID is **auto-generated by this service**. We are using a **Cassandra** for storing the Tweets, because we have a massive amount of data (millions of tweets per day) and Cassandra can easily handle that scale. Other than Cassandra we could also use HBase here but setting up of Cassandra is fairly easy. 

#### Tweet Service

Tweet Service is basically a source of truth for all the Tweets. So this basically sits on top of the **Cassandra** cluster and **owns the schema, and the data** within that. This Tweet Service will provide you all set of APIs that you need to display any Tweet. This service **only reads from Cassandra**.

#### User Views / Timeline

Timeline is basically a **series of Tweets**, ordered by something (generally by timestamps). There are **two kind of views** that a person can have

- **User Timeline** is basically **your own timeline**, contains all the tweets and all the retweets, that you have posted. So construction of user timeline is : 

  - ```CQL
    SELECT(*) from tweets on Cassandra WHERE userID = 'your userID';
    ```

- **Home Timeline** is what you see on your **home page**. So all the people that you are following, all their tweets will show up in your Home Timeline. So construction of home timeline is :

  - ```CQL
    SELECT(*) from tweets in Cassandra WHERE userID in [all the people you follow];
    ```

Now users can follow thousands of people and making this query at runtime would not scale and that too hitting this API call multiple times in a day will slow down things. Instead of that we are trying to **cache the user's timeline**. we **pre-calculate all the active user's timelines** and just show it to them then and there. So whenever a person comes on the home screen, they should be able to see their timeline very quickly.

#### Tweet Processor

whenever people are tweeting something which is going to a Kafka. Say User1 has tweeted something Tweet Processor (a **kafka consumer**) will get all the followers (who follow) of that user and update their timelines. So Tweet Processor needs to get a list of all the people who are being followed, so for that it will query the **Follow Graph Service**. Then It will **create the timeline for all of those users and put it in this cache**. When those users try to come on their home timeline they just do a cache look up and there view is sorted and this tweet will now show up in all of those user's home timelines. So the flow is all the tweets are coming here, this service is **identifying all the followers** and then latest tweets will be **appended at the beginning** of all the people's timelines.

Now Redis is an **in-memory solution**, so if we try to keep timelines of all the users in memory, that's very inefficient because that needs **massive amounts of RAM**. So **only active users timelines** would be stored in Redis. If a user is passive, we know that user will not come on Twitter for a while, so why bother to create the timeline. We create the timeline when the user logs in. It is easy to figure out who are active users by querying **User Service**, which has a **user to type mapping** in its own cache.

#### Timeline Service

Users will request for timelines through **Users Timeline UI** and **Home Timeline UI**, those requests will come to Timeline Service. Timeline Service queries **Redis** for all the active users and get the latest timeline. If Timeline Service figures out that the user is a passive user, it tries to creates the timeline(do not have the data in Redis). So first of all it queries **Follow Graph Service** saying that I have got this new user, give me all the users who this user follows. Now it queries the **Tweet Service** to get tweets of all of those users. It **wont query all the tweets**, it'll be some tweets till some time in the future and then it can be paginated. Once it gets tweets of all those users, it now arranges them as per the timestamps, **stores it back in the Redis**, and returns to the UI.

#### Live Users

**Live users** are the users who are online right now and they don't need to follow all of this process. All the tweets are anyway coming to the **Kafka** and the **Tweet Processor** will update all the active users timelines in the **Redis**. But while updating Redis it will also get to know that this user is a live user. So it will **put back an event into Kafka**, saying whoever is responsible for sending this **notification** down to the user, go send right away. Then the **User live Service (WebSocket)** can send a notification to the app. Through this route, it will come to the user app and the UX should be able to handle it saying you have more tweets and just scroll up or scroll down. So for <u>live users, they don't need to ever even query Redis</u>. While they are on the app, they will keep getting those notifications and newer tweets will keep showing on their UI.

#### Famous Users

**Famous users** are users who have millions of subscribers or followers. So when they makes a tweet, there needs to be millions of updates. So that's fairly inefficient if we start updating millions of records on the update of one person, and if he starts tweeting very frequently then we are in for big trouble. So instead of that, **Timeline Service** will take care of **merging everything**. Timeline Service knows that **Redis has data only of tweets of normal users**. So once it gets the data of all the users, it now tries to figure out that what are the **tweets from famous users** should be shown to this user. So Timeline Service queries **Follow Graph Service** and tries to figure out who are the famous users that this person was following and get their tweets as well using **Tweet Service**. So now Timeline Service also has the tweets of normal users and it can show it the UI. 

Before showing it to the UI, it can update in Redis, saying that It captured tweets from the regular Users and also the famous users. It will also add a flag saying that It updated the data of this user's timeline with tweets of famous users at a particular **timestamp**. So now the next time we get a request from the same user, Timeline Service looks at the timestamp and if this timestamp was **5-10 minutes** old then it can again **query the Tweets of famous users** from Tweet Service. Because those users could have again tweeted in the last 5-10 minutes. If the time stamp is not very old then we can safely assume that there is no new tweet and just return the timeline directly from the **Redis**.

Now there could be some famous users who follow some other famous users. So both of their Tweets will not go to the Redis route, normally. But if a famous user Tweets, then other famous user should get notified at least. So the famous users, within themselves should get to know of each user. This could be handled by Tweet Processor, so whenever a Tweet of a famous user comes in, don't update the cache of all the users, but just **update the cache of other famous users** that are following this user.

**Inactive users** accounts are anyway deactivated, so they can't even log in and we don't need to do any of these things for those kind of users.

## Possible Bottlenecks

Cassandra could be a bottleneck because there are a lot of times when you're querying this. **Timeline Redis** is definitely needs to scale up because of huge data and then Kafka off course. Also on Redis we should have an enough **TTL** so that we don't keep really old junk data. But all these components Redis, Cassandra, Kafka are horizontally scalable. So when the number of tweets increases, we need to add more machines.

## Search and Analytics

### Diagram

![Twitter Design Search.png](https://github.com/SnowflakeCoder/programming/blob/master/System-Design/CodeKarle/images/Twitter%20Design%20Search.png?raw=true)

How does **search** work ? So if somebody wants to search for a particular text, we want to show them all the Tweets, that have that particular string in that, ordered by some relevance score. After saving the post in Cassandra, Tweet Ingestion Service puts an event into **Kafka**. Search Consumer basically consumes all the Tweets that are coming into the system and stores it in an **Elastic Search** DB, which would then be used for searching. So we need to identify all the Tweets which have a particular search string that the user has mentioned and **rank them in order of relevance**. A very basic implementation is something called **tf-idf**, which is Term Frequency-Inverse Document Frequency. Which basically tries to look at how many times a particular word has come in a document against all other words and try to rank it accordingly.

Once Search Consumer processes all the data and stores it in to ES, it is basically ready for search. A user can then give a search string as an input using the search UI. It would talk to a LB and talk to this Search Service. The Search Service would then query Elastic Search, get the results and give it back to the user. So Search Consumer is basically writing, and Search Service is reading from the ES data store. 

let's say some event happens in the world, a lot of people start searching for it together and then slowly after a while that whole thing dies out, and then nobody's keep searching for that word. So with this assumption what we can say is if something is searched for, it is **possible that somebody else would also search** for the same thing. Now we don't need to compute the search results again and again. We don't need to show the latest set of data always here, so once the Search Service gets a set of results from Elastic Search, it will put it in the **Redis**. It will put it in the Redis with a TTL of 2-3 minutes. When Search Service gets a request, it is first query Redis, if it gets a result it will return then and there. If it does not get a result, then query ES, get the search results, store it in Redis, and then respond back. 

One very common feature of Twitter is "**what is trending right now**". On top of this Tweet Kafka, run a Spark Streaming Consumer which basically just checks for what were the most common keyword in the last one hour. It basically tokenizes the Tweet and get a list of words then dump all of that list of words somewhere and then aggregate over last one hour to try to come up with the words that were use the maximum. Now very likely these will be very common words like "a", "and", "the", "is", "of", so remove these **stop-words**, and then come up with a relevant set of words which are actually something that people are talking about. That can be used to power the trending kind of a features. So Spark Streaming will write to Trends service every half an hour. It looks at last one hour of data, and dumps in what all are the trending things right now. And there could be a Trends UI, which can be build on top of this Service and it kind of powers that UI. We could again use a Redis for storing Trends results as a cache. Every half an hour Spark Streaming will again give a list of trending words to this Service again.

These trends would also be calculated at a **geographical level** saying what are people from India talking about or any such thing. Let's say all the Tweets that we have, we might as well put it into a Hadoop cluster Because there will be lot of analytics done, like who is right now the most engaging Twitter user, or which is a person whose Tweets have been re-tweeted the most etc. All of those could be answered by various kinds of queries which can be powered by Hadoop. 

## Newsletter

A very common use case could be sending newsletters. There are a lot of companies which send weekly newsletter about various things like business, new promotions etc. Let's just say we have a **weekly cron job** which pull back all the passive users. So passive users are the ones who are not using the platform frequently. So to engage those users, Twitter could start a weekly email which talks about some of the very popular tweets in the last one week. Now those popular tweets if it's just one list, it would not be personalized and it will not make sense to a lot of people. 

So we can do an ML kind of a thing there, saying for which passive user, what are the most relevant tweets in the last seven days, that this person would be interested in. Get the list of top five tweets and send them a notification right, saying these are the tweets that you have missed, why don't you come on the platform. So this could be used as a **re-engagement kind of a platform**. 

There would be a Notification Service that will be invoked, saying this is the user, these are the tweets, go send the content. The notification service can talk to the **User Service**, to get the emailID, phone number and stuff like that. It can then find the user's communication preference from it's own Datastore, and then send out a notification whether it's the email or SMS or maybe in-app notification, whatever that is.

