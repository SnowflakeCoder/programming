# Zalando

Hi AA,

I am a Java Engineering Manager with 5+ years of management/Leadership experience, ability to simultaneously manage various projects and teams. 14+ years of hands-on experience in Java/J2EE Technologies and Frameworks. I also worked with GoLang, Cassandra, Redis, MongoDB, Kafka etc. 

You can find the high-level summaries of my job history in my attached resume below.

https://drive.google.com/file/d/1dUscGjMZFKeK_XaaI8yIsUizmr5T4Ydm/view

I am actively looking for a Senior Java Engineering job in Germany and it would be a great help if you can share my resume with your colleagues.

Thanks in advance.
Arun

I tried applying directly through LinkedIn, but didn't work. My application got rejected. So trying for employee referral. Please help me if you can. Thanks.

**********************************
1-9 => sleep (8)
9-1 => study
1-5 => work
5-9 => work
9-1 => study
**********************************
sun - tue => thu - sat => (8 + 2*4)
4 hour => 3 hour study + 1 hour revision
Java (4)
DS + programming (3)
Tools
Design
***********************************************************************
**********************************
Java Backend - PayPal => Tue Sep 15, 2020 1pm – 2pm (IST)
Khatabook Round 2  => @ 16th September 2020 3pm - 4pm  (friday)
zalando => ??
Walmart Labs  => ? 
Standard Chartered => 
Google Meet Interview  =>  Tue 6 Oct 2020 7:30pm - 8:15pm (IST)
AUTO1 Group as Expert Java Engineer => Fri Sep 11, 2020 2:30pm – 3:30pm (IST) => 
Engineering Manager - GCX || N26 => Monday, 14 September⋅1:15 – 2:15pm
--------------------------------------------------
Spark Networks => September 4th at 12:00 pm CEST (3.30PM IST)
------------------------------------------------
Rubrik => ??
Netskope => ?? 
Arcesium  =>  
smartnews => ??



standard chattered bank

Welcome e-gift Voucher worth Rs. 3,000 from any of the following brands: Bata/Hush Puppies, Pantaloons, Shoppers Stop and Yatra.com

You will receive an SMS to choose from e-voucher options within 15 days of annual fees payment. The e-voucher will be sent to your registered mobile number/e-mail ID, within 5 days of the request
For Bata/ Hush Puppies, Pantaloons, Shoppers Stop, you can redeem your e-Gift Voucher by showing the code at the respective brand store
For Yatra and Shoppers Stop you can redeem the code online at the time of purchase

***********************************

Pizza Hut e-Voucher will be sent to your registered mobile number within 15 days of achieving spends of Rs. 50,000
For Yatra.com/ Pantaloons, you will receive an SMS to choose the e-Gift Voucher option within 10 days of achieving spends of Rs. 5 Lakhs.

**************************************

Get Pizza Hut e-Voucher worth Rs. 1,000 on achieving spends of Rs. 50,000 in a calendar quarter
Waiver of Renewal Fee on annual spends of Rs. 3 Lakhs
E-Gift Voucher worth Rs. 7,000 from Yatra.com/Pantaloons on achieving annual spends of Rs. 5 Lakhs

--------------------------------
kunnamkulam taluk, kandanisseri village, chovallue desam, near chorakkulam



https://leetcode.com/problems/lru-cache/solution/
public class LRUCache {

  class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;
  }

  private void addNode(DLinkedNode node) {
    /**
     * Always add the new node right after head.
     */
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }

  private void removeNode(DLinkedNode node){
    /**
     * Remove an existing node from the linked list.
     */
    DLinkedNode prev = node.prev;
    DLinkedNode next = node.next;

    prev.next = next;
    next.prev = prev;
  }

  private void moveToHead(DLinkedNode node){
    /**
     * Move certain node in between to the head.
     */
    removeNode(node);
    addNode(node);
  }

  private DLinkedNode popTail() {
    /**
     * Pop the current tail.
     */
    DLinkedNode res = tail.prev;
    removeNode(res);
    return res;
  }

  private Map<Integer, DLinkedNode> cache = new HashMap<>();
  private int size;
  private int capacity;
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    // head.prev = null;
    
    tail = new DLinkedNode();
    // tail.next = null;
    
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DLinkedNode node = cache.get(key);
    if (node == null) return -1;

    // move the accessed node to the head;
    moveToHead(node);
    
    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = cache.get(key);

    if(node == null) {
      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;
    
      cache.put(key, newNode);
      addNode(newNode);
    
      ++size;
    
      if(size > capacity) {
        // pop the tail
        DLinkedNode tail = popTail();
        cache.remove(tail.key);
        --size;
      }
    } else {
      // update the value.
      node.value = value;
      moveToHead(node);
    }
  }
}





preserving longstanding Java principles such as nominal typing and migration compatibility. 

jdk 9 has compact strings

jdk 9 AOT

scavenge cycles

inlining & escape analysis GC







The final round of interviews will be focusing on Codility, System Design and General Tech.
------------------------------------

4th Onsite interview include live coding session (Problem-solving), system design, behavioral, Technical topics.
Real problem solving related to CSV operations.
System design for one of Zalando services.
Behavioural questions about managing a team, motivating them, conflicts, new process introducing  

Real problem solving related to CSV operations. System  design for one of Zalando services. Behavioural questions about managing a team, motivating them, conflicts, new process introducing.
------------------------------------
OOP Principals
Java Stream
---------------------------------
3 separate hour long Skype coding interviews on the same day.
I got to code in two of these interviews, which went great.

Describe your biggest work failure.  
----------------------------------------------------
5) Hiring Manager - technical 6) Coding 7) System Design.
General structure of all mentioned interviews and what to prepare well described in Cracking Code Interview book, as well as similar interview structure have bigger IT companies like Google, Facebook and etc.

Interview Questions
Coding: Leetcode-like Medium and Hard problems can be expected as a challenge.
System Design: add additional components and explain how they will act in existing ecosystem.  

--------------------------------------------------------
Postfix notation evaluation
Decorator question

The task was to design tic tac toe.
I implemented it in pseudocode, everything was ok. No system design questions, no technical questions followed. They just said your knowledge of object oriented design isn't very good. The quality of interview was poor, they just didn't want to bother making good interview.


Be prepared for all the basic questions like, whats your weakness, strength, your last achievement your last fail etc. 

-------------------------------------------------
3. Technical interview about Codility tasks.

 what is the worst complexity of HashMap.  I explained how worst complexity can be achieved and how not to have it. 

 3 rounds of technical interviews/video calls each 45 minutes long on one day.

 Given an array of nodes which represent a tree, find subtree of input node.  

 Create a pascal triangle of size n  

 How to convert an Optional string to integer without throwing exception 

 How to make post idempotent  

 how do you implement security in rest apis  

 how do you implement security on message passed from one micro service to other on a messaging system  

 When you will choose monolith over microservices  

 What are rest apis? how are they documented? Design a rest api 

 How to have a synchronized counter  

 What design patterns are you aware of and use frequently 

 When to use nosql dbs and relational dbs, how does scalability works in cases of nosql and relational dbs, what are ACID properties of transaction  

 HR asked a lot of behavioural questions e.g. why do you want to work for us, how do you keep yourself updated, what do you want to improve in you, in what role do you see yourself in future, how do you provide feedback to team members, how does you team members rate you, biggest achievement etc.  

 

 

 
