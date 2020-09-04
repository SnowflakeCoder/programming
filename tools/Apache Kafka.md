# Apache Kafka

Apache Kafka is a **publish-subscribe messaging system**. Apache Kafka is a software where topics (A topic is a stream of messages) can be defined and further processed. Applications may connect to this system and transfer a message onto the topic. 

**Kafka Broker**

A Kafka cluster usually consists of one or more servers (called as kafka brokers), which are running Kafka over them. **Producers** are processes that publish data (push messages) into Kafka topics within the specified broker. A consumer of topics pulls messages off a Kafka topic.







# Kafka vs RabbitMQ

## 1. Distribution and parallelism

**Kafka** **distributes consumers by topic partitions**. Each consumer from the group is assigned to one partition. The partition mechanism can be used to send different sets of messages by business keys (e.g., location or user ID).

In **RabbitMQ**, the number of consumers can be scaled out, which means that **each queue instance will have many consumers**. This makes message processing spread to all active consumers, but a message can only be processed once.

## 2. High Availability

Both frameworks are highly available. However, **Kafka** has an edge as it **uses Zookeeper to manage the state of the cluster**. A **Zookeeper** keeps track of the status of Kafka cluster nodes, Kafka topics, partitions, etc.

## 3. Performance

**Kafka** supports the strength of **sequential disk I/O** and **requires less hardware**. This leads to a high throughput with a tiny number of nodes.

**RabbitMQ** can also process a million messages per second but it requires more nodes.

### 4. Replication

**Kafka** has **replicated the broker by design**. This means that if the master broker is down, all the work is automatically passed to another broker which has a **full replica of the dead one**; hence, no message is ever lost.

In **RabbitMQ** queues aren’t automatically replicable.

### 5. Message ordering

Since **Kafka** has partitions, **messages can be received by ordering**. This can’t be achieved in **RabbitMQ**.

### 6. Multi subscriber

In **Kafka**, a message can be **subscribed by multiple consumers**.

In **RabbitMQ**, a message can only be consumed once, and once it is consumed, the message disappears and becomes inaccessible.

### 7. Message protocols

**Kafka** supports primitives (int8, int16, int32, int64, string, arrays) and binary messages.

**RabbitMQ** supports almost all **standard queue protocols** like AMQP, STOMP, HTTP, and MQTT.

### 8. Message lifetime

Since **Kafka** is a log, messages are always there. **RabbitMQ** is a queue that **removes messages once they are consumed**.

### 9. Message acknowledgment

Both frameworks give confirmation to the producer when messages arrive in the topic/queue.

### 10. Flexible routing to a topic/queue

In **Kafka**, a message is sent to the topic by a key; however, in **RabbitMQ**, there are more options (e.g., sending a message **using regular expressions**).



------



What are Apache Kafka and RabbitMQ?
Apache Kafka and RabbitMQ are two open-source and commercially-supported **pub/sub systems**.

RabbitMQ is a **general purpose message broker** that **supports protocols including, MQTT, AMQP, and STOMP**. It can deal with high-throughput use cases and an handle background jobs or act as a message broker between microservices.

Kafka is a message bus developed for high-ingress data replay and streams. Kafka is a **durable message broker** that enables applications to process, persist and re-process streamed data. Kafka has a **straightforward routing approach** that uses a **routing key to send messages to a topic**.

## Kafka vs RabbitMQ 

### Differences in Architecture

#### RabbitMQ Architecture

- **General purpose message broker**—uses variations of request/reply, point to point, and pub-sub communication patterns.
- **Smart broker / dumb consumer model**—consistent delivery of messages to consumers, at around the same speed as the broker monitors the consumer state.
- Communication—can be synchronous or asynchronous.
- Deployment scenarios—provides distributed deployment scenarios.
- Multi-node cluster to cluster federation—does not rely on external services, however, specific cluster formation plugins can use DNS, APIs, Consul, etc.

#### Apache Kafka Architecture

- **High volume publish-subscribe messages and streams platform**—durable, fast and scalable.
- Durable message store—like a log, run in a server cluster, which keeps streams of records in topics.
- Messages—made up of a value, a key and a timestamp.
- Dumb broker / smart consumer model—**does not try to track which messages are read by consumers** and only keeps unread messages. Kafka keeps all messages for a set period of time.
- Requires external services to run—in some cases **Apache Zookeeper**.

### Pull vs Push Approach

#### Apache Kafka: Pull-based approach

Kafka uses a pull model. Consumers **request batches of messages from a specific offset**. Kafka permits **long-pooling**, which prevents tight loops when there is no message past the offset.

A pull model is logical for Kafka because of its partitions. Kafka provides message order in a partition with no contending consumers. This allows users to leverage the batching of messages for effective message delivery and higher throughput.

RabbitMQ: Push-based approach
RabbitMQ uses a push model and stops overwhelming consumers through a prefetch limit defined on the consumer. This can be used for low latency messaging.

The aim of the push model is to distribute messages individually and quickly, to ensure that work is parallelized evenly and that messages are processed approximately in the order in which they arrived in the queue.

How Do They Handle Messaging?


Kafka vs RabbitMQ Performance
Apache Kafka:
Kafka offers much higher performance than message brokers like RabbitMQ. It uses sequential disk I/O to boost performance, making it a suitable option for implementing queues. It can achieve high throughput (millions of messages per second) with limited resources, a necessity for big data use cases.

RabbitMQ:
RabbitMQ can also process a million messages per second but requires more resources (around 30 nodes). You can use RabbitMQ for many of the same use cases as Kafka, but you’ll need to combine it with other tools like Apache Cassandra.

What are the Best Use Cases?
Apache Kafka Use Cases
Apache Kafka provides the broker itself and has been designed towards stream processing scenarios. Recently, it has added Kafka Streams, a client library for building applications and microservices. Apache Kafka supports use cases such as metrics, activity tracking, log aggregation, stream processing, commit logs and event sourcing.

The following messaging scenarios are especially suited for Kafka:

Streams with complex routing, throughput of 100K/sec events or more, with “at least once” partitioned ordering
Applications requiring a stream history, delivered in “at least once” partitioned ordering. Clients can see a ‘replay’ of the event stream.
Event sourcing, modeling changes to a system as a sequence of events.
Stream processing data in multi-stage pipelines. The pipelines generate graphs of real-time data flows.
RabbitMQ Use Cases
RabbitMQ can be used when web servers need to quickly respond to requests. This eliminates the need to perform resource-intensive activities while the user waits for a result. RabbitMQ is also used to convey a message to various recipients for consumption or to share loads between workers under high load (20K+ messages/second).

Scenarios that RabbitMQ can be used for:

Applications that need to support legacy protocols, such as STOMP, MQTT, AMQP, 0-9-1.
Granular control over consistency/set of guarantees on a per-message basis
Complex routing to consumers
Applications that need a variety of publish/subscribe, point-to-point request/reply messaging capabilities.
Kafka and RabbitMQ: Summing Up
This guide has covered the major differences and similarities between Apache Kafka and RabbitMQ. Both can consume several millions of messages per second, though their architectures differ, and each performs better in certain environments. RabbitMQ controls its messages almost in-memory, using big cluster (30+ nodes). Comparatively, Kafka leverages sequential disk I/O operations and thus demands less hardware.

You have streaming data in Kafka. What’s next?
Getting your data in Kafka is just the first step – to actually drive value from it, you need a way to easily store, manage and analyze your streams. To learn how that’s achieved, check out our popular post on Apache Kafka with or without a Data Lake; or start a free trial of Upsolver to start seeing value from your Kafka streams today.