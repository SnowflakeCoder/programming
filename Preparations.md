# Preparations

## Cheat Code

Leaky bucket algorithm
Circuit Breaker Pattern
DNS load balancing 
Geo location based
Weighted - Mainly used for Canary (Blue/Green)
Scaling Databases
Horizontal
Adding more DB Instances - Sharding the data. 
Netflix’s DynomiteDB can convert any DB in to horizontally scalable DB
Vertical
Buy bigger Instance

Resiliency for DB
Read and Write Segregation
Master - with Multi AZ failover enabled
Read replicas
SQL DB’s with NoSQL like feature
Postgres/MySQL both support JSON columns but only Postgres has indexing on JSON columns.

## General

BDD - behaviour  , domain driven design,  aggregate , bounded conduct, domain event

Acceptance criteria, TDD

Hexagonal architecture, port and adapter

------

mockito (object mocking), wiremock (integration mock)

spring integration - contract testing

JDBC - spring data (program to interface), DAO

Configuration class

Git => **incremental commit**

------

trunk based development, feature branch development, PR branch (pull request)

rebase command.

unit testing, integration testing, functional testing.

Solar cube - code coverage.

MVN clean build - run full test cases.

------

Test pyramid

------

start.spring.io => skeleton project

suits of tests

------

Spring Boot's Actuator endpoints.

pair-programming types.

 Binary Heap

-----------------------------------------

interview process was great, i was asked to design binary tree search and then an alogrithm to get the total number of sales an amazon plant does in an year. the interview process was well organized

Interview Questions

binary tree seacrh

Coding exercise: Make a function that parses a JSON string into an object of key/value pairs  

find if word is composite for a given dictionary  

How to use cache api

There is a strong focus on Data Structures and Algorithms

Coding exercise: Make a function that parses a JSON string into an object of key/value pairs  

StackofPlates, Create a class StackofPlates and pop first two plates and return the StackofPlate  

a lot of BQ. I believe it takes at least 40% of the whole interviews. You are expecting several BQs for each round.  

Alien Dictionary  

Counter number of islands in a matrix of 0s and 1s.
Do pow(x, y) where y is very large.

given as string and and width of of a grid of alphabet as input print the corresponding direction string for given string. e.g input is YES and width 5. alphabet grid will be 5 char in each row for all letters starting from A.
For YES you have to start from A to Y print EEEEESSSS, Y to E NNNN and E to S WSSSS and print the direction to reach each letter.  

Questions about trees during telephonic. Onsite was mostly easy, arrays, and some hashtable related questions. There was one about how to calculate expressions. 



Problem :2d grid , each node has 1+ colors; find number of clusters of a given color.

[[{red, blue, green}], [{blue, green}], [{green}]]
    [[{blue}], [{yellow, green}], [{green}]]
    [[{red, green}], [{red, green}], [{blue, green}]]

Output:

{ red -> 2, blue -> 2, green -> 1, yellow -> 1 }  



get a list of K place that is closest to [0, 0] in a 2d array  


Write a parser for super huge JSON file with n number of subtrees. 

 island problem (count the islands in a matrix).

 How to implement a function that could return a list of matching words, given a prefix, like what is typed into a search bar in a web browser.

 Merge two sorted linked list  

 We are calling a web service method that will sometimes fail. The problem is to change the client so that if there are 5 failures in less than 10 seconds, the client should stop attempting to make the call to the web method and just return a failure right away.  

************

https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/solution/

https://leetcode.com/tag/design/

https://leetcode.com/problems/design-twitter/

https://leetcode.com/problems/reverse-words-in-a-string/solution/

https://github.com/kdn251/interviews/tree/master/company/yelp

https://leetcode.com/problems/reverse-words-in-a-string/

https://leetcode.com/problems/word-break/

-----------------------------------------------------------------------------------------------------------------------------------------------------
https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/
https://www.geeksforgeeks.org/hashset-in-java/
https://www.geeksforgeeks.org/linkedhashmap-removeeldestentry-method-in-java/
https://www.geeksforgeeks.org/print-characters-and-their-frequencies-in-order-of-occurrence-using-a-linkedhashmap-in-java/?ref=rp
https://www.geeksforgeeks.org/tag/java-linkedhashmap/
https://www.geeksforgeeks.org/treemap-in-java/?ref=rp
https://www.geeksforgeeks.org/differences-treemap-hashmap-linkedhashmap-java/
https://media.geeksforgeeks.org/wp-content/uploads/comparisonTable.png
https://www.geeksforgeeks.org/hashmap-vs-weakhashmap-java/?ref=rp
https://www.geeksforgeeks.org/java-util-linkedlist-poll-pollfirst-polllast-examples-java/?ref=rp
https://www.geeksforgeeks.org/linked-list-in-java/?ref=lbp
https://www.geeksforgeeks.org/copyonwritearraylist-in-java/
https://www.geeksforgeeks.org/java-util-concurrent-phaser-class-in-java-with-examples/?ref=rp
https://www.geeksforgeeks.org/java-util-function-longpredicate-interface-in-java-with-examples/?ref=rp
https://www.geeksforgeeks.org/java-util-linkedlist-offer-offerfirst-offerlast-java/?ref=rp



------



There are 2 types of traffic shaping algorithms:

Leaky Bucket
Token Bucket

https://www.geeksforgeeks.org/an-introduction-to-software-development-design-principles/?ref=rp

https://medium.com/better-programming/restful-api-design-step-by-step-guide-2f2c9f9fcdbf

Hashmap has Logarithmic complexity O(Log(N)).

Inverted Index, Btree index
complex events processing.
Kafka K tables.


HackerRank 
https://www.hackerrank.com/test/9ijiaf4khqs/feedback/done

https://docs.oracle.com/cd/E13924_01/coh.340/e13819/readthrough.htm#:~:text=9.6%20Refresh%2DAhead%20Caching&text=The%20asynchronous%20refresh%20is%20only,store%20to%20refresh%20its%20value.

***************************************

https://www.coursera.org/learn/gcp-fundamentals#about

Arrays.fill()  - method.

 dh-content-faiss-commons-api.dailyhunt.in, dh-content-faiss-hi-ogcnews-api.dailyhunt.in, dh-content-faiss-en-ogcnews-api.dailyhunt.in
these are private rules configured with HTTPS also but so far only HTTP is been used
can we remove HTTPS configuration?

********************************************************************************

**************************************************
https://developer.okta.com/blog/2017/05/17/develop-a-mobile-app-with-ionic-and-spring-boot

https://medium.com/walmartglobaltech/field-level-encryption-in-azure-cosmosdb-documents-c701f605754e

****************************************


java 8
Testing
Rest & Http (DS)
JVM
spring boot
problem solving

coding section

clean coding

**************************

cyclomatic complexity

java 11,

spring boot

kafka

MySQL

*****************************

coding challenge

2 technical  (java, system design)

2 behaviour

******************************

SNS vs SQS

EC2

N4 serious

kubernets

kubernetes vs docker

Kubernetes pod

CQRS stands for Command Query Responsibility Segregation.



Kafka (zoo keeper / RabbitMQ)
Spring & Spring boot
Site Reliability Engineering(sre and slo)
What is the difference between SLO and SLA?
SLI
SDLC

microservices

-- Strangular pattern

-- Circuit breaker pattern and Histix implementation 

-- Service discovery pattern

-- Saga pattern

How to enforce coding formats in Git.
sonar cube, findbugs in maven.

*****************************************
Java changes in each version
Garbage Collection
Stack and heap

String implementation Flyweight design pattern
How to get ThreadDump
find deadlock
TheadMXBean

garbage collection details.

concurrency and multithreading

p2 architect

write-through cache



Interview

Five mins for introduction; 5 mins to ask about big data pipeline in my resume: why use Flink, why use Kafka. 30 mins to finish 2 algorithm questions. The algorithm questions are not so complicated, but I was asked many complexity analysis questions.

Interview Questions

why do you use Flink, why do you use Kafka? Can you give a more detailed explanation?

***************************

https://engineeringblog.yelp.com/2019/09/breaking-down-technical-interview.html

https://github.com/kdn251/interviews

https://github.com/kdn251/interviews/tree/master/company/yelp

https://github.com/andrewjli/yelp-test

https://www.glassdoor.co.in/Interview/Yelp-Interview-Questions-E43314.htm?countryRedirect=true

https://github.com/raymondborkowski/YELP

https://github.com/Cici-Zhong/Yelp-Toronto

https://www.kaggle.com/c/yelp-recruiting/data

https://github.com/checkcheckzz/system-design-interview

https://www.edureka.co/blog/interview-questions/git-interview-questions/

https://www.glassdoor.co.in/Interview/Yelp-Interview-Questions-E43314.htm

https://www.glassdoor.co.in/Interview/Yelp-Software-Engineer-Interview-Questions-EI_IE43314.0,4_KO5,22.htm?countryRedirect=true

https://www.glassdoor.ie/Interview/Yelp-Interview-Questions-E43314.htm?filter.jobTitleExact=Back-end+Software+Engineer

https://algodaily.com/companies/yelp#questions



how the teammae talks abt you

how to present an idea

how u act if they rejected.

Why Do You Want to Work Here?

Why are you interested in the company?
Why are you interested in the job?

Learn about your career goals and how this position fits into your plan
Make sure that you are sincerely interested in the job and will be motivated to perform if hired
Find out what you know about the company, industry, position (and if you took the time to research)
Understand your priorities and preferences — which aspects of the company and/or job are appealing to you and why?

***********************
A CI/CD pipeline automates your software delivery process. The pipeline builds code, runs tests (CI), and safely deploys a new version of the application (CD). Automated pipelines remove manual errors, provide standardized feedback loops to developers, and enable fast product iterations.

Adopt best practices in software engineering: design, testing, version control, documentation, build, deployment, monitoring and operations. Build flexible systems choosing simple, straightforward solutions over more complex ones 

[109] It was one of the first to build self-service tills and use cameras to reduce queues, and an early adopter of NFC contactless payment card technology.[110] In 2016, Tesco developed a mobile payment wallet, PayQwiq using both NFC contactless and barcode technology to allow payment using mobile phones in-shop (along with supporting other contactless mobile wallets such as ApplePay).[111]

*******************************

How do you deal with a failed deadline?
Why do you want to work for Amazon?
Tell me about a situation where you had a conflict with a teammate.
In your professional experience have you worked on something without getting approval from your manager?
Tell me a situation where you would have done something differently from what you actually did.
What is the most exceedingly bad misstep you've made at any point?
Describe what Human Resources means to you.
How would you improve Amazon's website?



https://interviewing.io/recordings/

https://tech.deliveryhero.com/preparing-for-your-technical-interview-delivery-hero/



Rest service / Rest APIs  => best practices

Leaky bucket algorithm (too many req)

circuit breaker pattern

*********************************************************************************

One of the key advantages of REST APIs is that they provide a great deal of flexibility. Data is not tied to resources or methods, so REST can handle multiple types of calls, return different data formats and even change structurally with the correct implementation of hypermedia.

key advantages of REST APIs is that they provide a great deal of flexibility. Data is not tied to resources or methods, so REST can handle multiple types of calls, return different data formats and even change structurally with the correct implementation of hypermedia.



collision, sharding

sportify model, squad



Expert understanding of CI principles
The ability to implement CI pipelines from scratch
Expert in designing and architecting solutions for performance testing, component level testing, stubbing, tools POC and monitoring
The ability to spot shortcomings of tooling and enhance automation and process adherence through enhancing tooling
The ability to rapidly implement PoCs and evaluate suitability for the problem space


Static code analyzer

Pair programming

Scrum

Unit Tests

Integration Tests

As an experienced software developer, they play a key role in our agile team. You develop quality-oriented software with continuous integration in the Java enterprise environment.
In coordination with the product management you develop solutions for new requirements of our core product X4 Suite.
After extensive training, you will advise the product management and our project teams on technological solutions and the range of services.
You work together with a self-organized team in an agile software development process with Scrum.



1) order id qn

2) count of keywords ,position by count. 

Rate limiting design (coupons)

Caching
sharding


3) 

what is not there in CV but interested in talking?

2) huge impact technically

3) Conflict and how u solved ?

4) highest qn / 2nd highest qn.



Find the closest coordinates to the origin.  

Check if two strings can represent the same word.  


https://www.toptal.com/back-end/server-side-io-performance-node-php-java-go

https://github.com/javadroider/interview-prep/blob/master/interview/concepts/System%20Design%20Cheatsheet.md

https://github.com/javadroider/interview-prep/blob/master/interview/questions/Real%20Interview%20Questions.md



java standard parctise

domain , concersn, interfaces

crafts man principles, 


java principles

dockers and kubernets

**********************

client side caching - redis 6



How To Design Google Docs
System design interviews can be quite open-ended and require a wide range of knowledge.

To prepare well for such kind of interviews, it’s important to cover different areas instead of focusing on a single topic. We’ve spent a lot of time selecting system design questions to analyze, our main criteria are:

The question is popular and classic
We care about the diversity of questions we select
The analysis can be helpful to other interview questions

This week, we’d like to discuss how to design Google Docs. You will find it quite different from the analysis of our previous questions.

 

Question – How to design Google Docs
I’ll assume everyone knows what Google Docs is and won’t waste time introducing this product.

The question looks quite general at first glance and it indeed is. Google Docs is a huge system with tons of features. If you spend few minutes thinking about his problem, you may realize that Google Docs is much more complex than it seems to be.

As an interviewer, I don’t like to limit the scope of discussion to a specific feature of this product. Instead, I lean toward making the question broad and general so that I can know how candidates will address a vague problem step by step.

 

Divide into components
We’ve emphasized many times in our previous posts that it’s recommended to provide high-level solutions when the question is big. And one way to abstract your solution is dividing a big system into smaller components.

Apparently, Google Docs is a huge system that has a bunch of features, including doc storage, share docs, formatting, editing and so on. In fact, I can barely address such a big problem without separating it to different sub-problems.

If you haven’t thought about this problem, please spend 5-10min to have your own answer before checking our analysis. Also, it’s worth to note that it’s absolutely okay if your solution is different from ours since the question is open-ended.

how to design google docs

We can divide the whole system into the following major components:

File storage. Since Google Docs is part of Google Drive, I include the storage feature as well. The system allows users to group files (docs) into folders and support features like edit/create/remove etc.. It works like an OS.
Online editing and formatting. There’s no doubt that one of the core features of Google Docs is online editing. It supports almost everything of Microsoft Office and maybe more.
Collaboration. It’s truly amazing that Google Docs allows multiple people to edit a single doc simultaneously. This is a technical challenge for sure.
Access Control. You can share docs with your friends and give different permissions (owner, read-only, allow comment etc.).
A bunch of less important features are ignored here, like add-ons, spell-checking, publish to the web and so on.

 

Storage and Formatting
I put these two topics together since with storage and formatting implemented, a very basic and naive version of Google Docs is created. Even if there’s no access control and collaboration, a single user can still use it to edit docs.

Also, storage and formatting can be regarded as backend and front-end to some extent.

IMHO, the storage system of Google Docs (or Google Drive) is very close to an operating system. It has notions like folders, files, owners etc..

Therefore, to build such system, the basic building block is a file object, which contains content, parent, owner and some other meta data like creation date. Parent denotes the folder relation and the root directory has empty parent. I won’t discuss how to scale the system as building a distributed system can be extremely complicated. There are tons of things to be considered like consistency, replication.

For the front-end formatting, an interesting question is how you would store documents with corresponding formats. If you know Markdown, it’s definitely one of the best solutions. Although Google Docs can be more complicated, the basic idea of Markdown still applies.

 

Concurrency
how to design google docs

One of the coolest features of Google Docs is that multiple people can edit a single doc simultaneously. How would you design this feature?

This is not an easy problem, to be honest. You can’t just let each person work on his own and then merge everyone’s copy or the last one to edit wins. If you have tried the collaborative editing feature, you can actually see what the other person is doing and you get instant feedback.

If you have used Git for version control, some of the ideas here can be similar. First, let’s consider the simplest case – only 2 people are editing the same doc. Assuming the doc is “abc”.

Basically, the server can keep 2 copies of the same doc to each person and tracks the full revision history as well. When A edits the doc by adding “x” in the beginning, this change will be sent to the server together with the last revision seen by A. Suppose at this time, B deletes the last character “c” and this change is sent to the server as well.

Since the server knows the change is made on which revision, it will adjust the change accordingly. More specifically, B’s change is deleting the 3rd character “c”, which will be transformed to deleting the 4th character as A adds “x” in the beginning.

This is called operational transformation. It’s okay if you never heard of this. The basic idea is to transform each person’s mutation based on its revision and revisions from other collaborators.

 

Access Control
Google Docs allows you to invite collaborators to each doc with different level of permissions.

A naive solution shouldn’t be hard. For each file, you can keep a list of collaborators with corresponding permissions like read-only, owner etc.. When one wants to do specific actions, the system checks his permission.

Usually, I’d like to ask what are challenges to scale such access control system.

As is known to all, when scaling system to millions of users, there can be a lot of issues. Few things I’d like to mention here are:

Speed. When the owner updates the permission of a folder (e.g. remove a specific viewer), this update should be propagated to all its children. speed can be a concern.
Consistency. When there are multiple replications, it’s non-trivial to keep every replica consistent especially when multiple people update the permission at the same time.
Propagation. There can be a lot of propagation cases. Besides updating the permission of a folder should reflect on all its children, if you give read permission of a doc D to someone, he may have read permission to all the parents of doc D as well. If someone deleted doc D, we may revoke his read permission of D’s parents (maybe not, this is more of a product decision).


Summary
Again, none of us at Gainlo has ever worked on Google Docs. This post is not teaching you how to build Google Docs from scratch.

Instead, I’d like to use this post to give you more ideas of how system design interviews are conducted and how you can address a vague problem.

Designing a complex system like Google Docs can be intimidating. But once you divide the system into smaller components, it becomes much simpler.

If you find this post helpful, I would really appreciate if you can share it with your friends. Also you can check more system design interview questions and analysis here.



cap theorem

Algorithm



imagine navigation system

every 2 sec the position of the person


google spread sheet

stock trading portal

food delivery application

hotel booking, whatsapp, twitter

driver heat map

***************************************
message broking service

in-memory task scheduler

routing problem - some problem to solve wit hashmap and graph

***********************************
relevant qns related to scale

collect fn ad non dn

priorities - p0 and p1 

HL block design 

explain choice of technology

CAP theaorem

why kafka over Active MQ



***************************
URL redirect

kubernetes

autoscaling in kubernetes

ec2 service of aws

cloudWatch

CDN using akamai

HLD
LLD

Websocket connection.

SaaS development





company

We are into(company) 

what we do 

multi tenant applications, cloud based

Technology - java, springBoot

DB- MySQL, Redis, Cassandra, Mongo, Kafka

Micro based service architecture

lead a 

15 days sprint.

High level estimate of each stories

Design and reviews and code reviews

Escalations and POCs

******************************************************************

one of the complex APIs u have supported

10K transactions per second

*******************************************************************

https://stackoverflow.com/jobs/416262/software-development-engineer-iii-amazon-web-services



spring cloud config

https://coursehunters.online/t/educative-io-design-gurus-grokking-the-system-design-interview-part-5/584

***********************

Load sharing
Prevent noisy neighbour 
Pluggable model or Flag based feature enabled

Every request contains some identifier about features for a customer in the request context 

Monitoring can be done at per customer level by generating custom analytical events 

Tools like New Relic and prometheus will do the infra level. Since the infra is shared 

************************************

SLA

reconciliation

market placed application, e-commerce website

class diagram - entities ?

customer

****************************************

properties of multitenant application.

project description

weired issues(latency issues)

******************************************
epsilon GC  garbage collecting - java 11

flyweiht design pattern

draw.io

system to manage conscent? what are the concent ? 

concent management system

whr u would host the content

decision tree

TTL for cassandra

FaaS => function as a service

AWS lamda

**************************

codekarle

googleMaps, Zoom and Uber

city as 2D graph

digikstras, prince, kutkels

******************************************************************************************

voldermart database

googleMap design

https://www.youtube.com/playlist?list=PLalrWAGybpB-L1PGA-NfFu2uiWHEsdscD

*******************

https://github.com/javadroider/interview-prep/blob/master/interview/questions/Behavioral.md

shortest path dijkstra algorithm

*************************************

Co- Routines ( Java Fibres)-> light 


Deeplink url domain
please stop using this URL for stage yo app - please check patra comment

for stage url => stage-dhsocial.dailyhunt.in


Ad delivery system

lambda architecture
********************************************
326000

sept - 25th **** 
infinia.services@hdfcbank.com
***************************************

(MVH22HN/A) MacBook Air-13"inch,1.1GHz quad core,10th Generation,intel core i5 processor,8GB,512 GB Space grey.- Rs.94760/- taxes extra.
APP-Rs.11100/- taxes extra

*******************
google spreadsheet

zoom

in-mem que scheduling


kafka

database schema - CAP

B2C

*******************************************************
https://www.youtube.com/watch?v=0Vmtmqa9Og0

******************************

enum ? how it works

singleTon using spring.

F:\Arun\Surya\interview-prep\src\main\java\com\javadroider\interviewprep\designpatterns\creational\singleton

https://github.com/javadroider/interview-prep/blob/master/interview/README.md

****************************

which sites to follow

mechanical sympathy


spring questions

spring singleton

different types of injections



cap thearem

architectural patterns
service oriented pattern, micro service

************************************************
uber & google maps
netflix 
whatsapp

******************************
consistent hashing
*******************************
linked learning kafka 

kafka, cassandra, core java, DS, docker, solr

DB optimization technique (MySQL)

kubernets / docker

**********************
private key / public key communication

symmetric/Asymmetric encryption

sessionkey 

KYC

*****************************

He may be looking for a State machine 
A orchestration framework defines the State machine
And based on the States different MIcroservices will talk to the FE. FE is dumb it should show only what the backend asks the information for.

Something like Uber’s Cadence or Netflix’s Conductor AWS’s Step Functions  

FE -----> Micro service 1 for county X
     ----->  Micro service 1 for county X

https://medium.com/@pepelephew/a-look-at-how-private-messengers-handle-key-changes-5fd4334b809a
	 
decision tree	 

https://towardsdatascience.com/decision-tree-algorithm-explained-83beb6e78ef4
https://www.geeksforgeeks.org/decision-tree/?ref=lbp


code review, design review, architectural review

https://www.geeksforgeeks.org/decision-tree/?ref=lbp


UI Plugins as Widget 
Use a plugable system, implement every new compliance as a plugin 

JsonLogic
Velocity template
Camel template
*****************************
versioning

**************************

strengths are - prepare a plan and commit to it, learning new things. improve things i already implemented

spring & spring boot

****************************

micro service architecture

config Service (all services can read from that)

side car pattern
******************************
micro frontend
******************************

Uber’s

highly available hight performance system

latency

scaling

streaming

API gateway

GRPC protocol

cassandra, kafka

RDBMS

google spanner

java, GOLang

***************************************

5-6 rounds

coding
************
multithread 
backend services

1) chat service (rest end points) with send time and recieve time


2nd rounds

routing problem

DS and Algotithm

3 & 4) Architecture

uber specific problem

uber edge application

hotel booking 

component & class wise design

scaling, sharding - 

*****************************************************************************

Big data technology

large messaging, meesage Queueing, database schema

*******************************************************************************
current architecture, contribution, how depth u went with ur scale

*******************************************************************************

explained abt ur proejct, in-depth understanding 

*************************
bar raiser interview

cultural aspects, team leading,

*************************************
edge streaming
*************************************


fulfilfment platform

gateway & streaming



https://lethain.com/introduction-to-architecting-systems-for-scale/


https://www.geeksforgeeks.org/overlapping-subproblems-property-in-dynamic-programming-dp-1/

https://www.nicolasmerouze.com/middlewares-golang-best-practices-examples

*****************************************************************

When using distributed caching to optimize performance, it may happen that the number of caching servers changes (reasons may be a server crashing, or the need to add or remove a server to increase or decrease overall capacity). By using consistent hashing to distribute keys between the servers, we can rest assured that should that happen, the number of keys being rehashed—and therefore, the impact on origin servers—will be minimized, preventing potential downtime or performance issues.

**********************************
Java Native Interface
**********************************

micro services.
memory model, garbage collector 9shanandoca garbage collector, jeevan gc)
cms gc

kafka offset

akka stream coneipt

message queue (event based) - rabbit queue


skyscanner =>

boeing => glass door

behavoiral qn

system design

educative.io, broking system design

url service, nearby, elf

narendra (youtuve video)

how to handle sudden burst of req - token bucket algorithm, cdn, - request throttling

flight alert system - how will you scale , what happen if it goes down

prometheus

blue green deployment

cubernets



********************
thundering herd problem

read:: key collision in redis

*****************************
The code had to <u>follow Clean Code principles and practices</u> and had to be covered with tests first and foremost.

I have used **clean code architecture MVVM** and databinding for the app.

Prioritization of vanity metrics such as **test coverage**.



Garbage Collection Roots

Talk abt different GC algorithms

Co- Routines ( Java Fibres)-> light  

Apache  Storm

Cassandra



service-oriented system

Experience with containers (Docker preferred)

Hands-on experience with cloud providers (like Amazon Web Services, Azure, or Google Cloud Platform)

Knowledge in distributed computing and microservices





Java 8, 
micro-services (Micro-service architecture with REST APIs)
dockerized micro-services on AWS ECS with Spring (Spring Boot, Spring Cloud)
- OAuth2 implementation with JWT

- Asynchronous processing with AWS SQS, SNS and Lambda

- Transcoding Video Master to DASH/HLS compatible formats

------------------------------------------------
java
System design
Cloud
Design patterns
Docker

spring security
AD
-------------------------------------------------------------------------
Your new role
Architect, develop, QA, ship, monitor and maintain throughout the whole software development life cycle in our service oriented platform with currently 300+ restful microservices
Use your experience, hands-on and can-do attitude to solve complex problems 
Lead by example and mentor those around you to push boundaries and excel 
Collaborate with various engineering and product teams to ensure our high business standards, and take the chance to get involved with other technologies and other fields of software development

Your skills
6+ years of professional experience in java programming with spring-based technologies
You have a deep understanding of JVM
Strong experience in building restful APIs
Hands on experience with Spring Boot, ideally, you also have experience in Spring Cloud

A hypervisor (or virtual machine monitor, VMM, virtualizer) is computer software, firmware or hardware that creates and runs virtual machines. A computer on which a hypervisor runs one or more virtual machines is called a host machine, and each virtual machine is called a guest machine.

https://www.geeksforgeeks.org/hypervisor/

https://www.geeksforgeeks.org/microservices-architecture-on-aws/?ref=leftbar-rightbar

https://www.geeksforgeeks.org/domain-name-system-dns-in-application-layer/



TLS authentication

https://www.geeksforgeeks.org/docker-compose-tool-to-run-multi-container-applications/?ref=rp

https://www.geeksforgeeks.org/how-to-create-your-own-docker-customised-image/?ref=rp



- Very healthy Work atmosphere. Good work life balance.
- Very good example of a multidisciplinary team.
- A core part of the success is built on its innovative use of technology.
- Good Exposure to new technologies and very strict to design and architecture principles.

I'm really driven by results. I like it when I have a concrete goal to meet and enough time to figure out a strong strategy for accomplishing it. 
I was responsible for several projects where I directed development teams and implemented repeatable processes. The teams achieved 100% on-time delivery of software products. I was motivated both by the challenge of finishing the projects ahead of schedule and by managing the teams that achieved our goals.





kubernetes pod

https://www.whizlabs.com/blog/java-11-features/#:~:text=Dynamic%20Allocation%20of%20Compiler%20Threads,in%20the%20tiered%20compilation%20mode.

https://www.marcobehler.com/guides/spring-boot

https://www.marcobehler.com/guides

https://www.marcobehler.com/guides/a-guide-to-java-versions-and-features#_java_features_8_14

https://dzone.com/articles/a-guide-to-java-versions-and-features

https://www.javatpoint.com/New-features-in-java#:~:text=There%20are%20many%20new%20features,functional%20interface%2C%20method%20references%20etc.

https://www.infoworld.com/article/3291322/oracle-switch-now-from-nashorn-javascript-engine-to-graalvm.html
https://www.infoworld.com/article/3534133/jdk-15-the-new-features-in-java-15.html
https://www.journaldev.com/37273/java-14-features
https://www.geeksforgeeks.org/java-11-features-and-comparison/
https://advancedweb.hu/a-categorized-list-of-all-java-and-jvm-features-since-jdk-8-to-14/
https://www.infoworld.com/article/3436795/jdk-14-the-new-features-in-java-14.html
https://www.atlassian.com/continuous-delivery/software-testing/types-of-software-testing
https://senlainc.com/blog/the-main-types-of-software-testing-methodologies-and-testing-based-on-business-objectives/#:~:text=The%20main%20software%20testing%20methodologies%20are%20the%20Agile%20model%2C%20the,testing%20and%20non%2Dfunctional%20testing.

https://smartbear.com/learn/automated-testing/software-testing-methodologies/

https://click-labs.com/6-different-types-software-testing-methodologies/
https://www.softwaretestinghelp.com/types-of-software-testing/
https://www.geeksforgeeks.org/software-engineering-black-box-testing/
https://www.geeksforgeeks.org/types-software-testing/?ref=lbp
https://www.geeksforgeeks.org/software-testing-basics/?ref=lbp

https://www.tutorialspoint.com/software_testing_dictionary/api_testing.htm









