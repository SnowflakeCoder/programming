# Content delivery network (CDN)

A content delivery network, or content distribution network (CDN), is a **geographically distributed network of proxy servers and their data centers**. The goal is to provide **high availability** and **performance** by distributing the service closer to end users. CDNs serve a large portion of the Internet content today. CDNs are a layer in the internet ecosystem. Content owners such as media companies and e-commerce vendors <u>pay CDN operators to deliver their content</u> to their end users. In turn, a <u>CDN pays Internet service providers (ISPs), carriers, and network operators for hosting its servers</u> in their data centers.

CDN is an **umbrella term** spanning **different types of content delivery services**: video streaming, software downloads, web and mobile content acceleration, licensed/managed CDN, transparent caching, and services to measure CDN performance, load balancing, Multi CDN switching and analytics and cloud intelligence.

# Domain Name System (*DNS*)

The Domain Name System (*DNS*) is the phonebook of the Internet. **Humans access information online through domain names**, like espn.com. **Web browsers interact through IP addresses**. *DNS* **translates domain names to IP addresses** so browsers can load Internet resources. DNS servers eliminate the need for humans to memorize IP addresses. The process of **DNS resolution** involves converting a hostname into an IP address.

## How does DNS work?

There are <u>4 DNS servers involved in loading a webpage</u>:

- **DNS recursor** - The DNS recursor is a server **designed to receive queries from client machines** through applications such as web browsers. Typically the recursor is then **responsible for making additional requests** in order to satisfy the client’s DNS query.
- **Root nameserver** - The root server is the **first step in translating** (resolving) host names into IP addresses. It can be thought of like an **index in a library** that points to different racks of books - typically it **serves as a reference to other more specific locations**.
- **TLD nameserver** - The **top level domain server (TLD)** can be thought of as a **specific rack of books in a library**. This nameserver is the next step in the search for a specific IP address, and it **hosts the last portion of a hostname** (In example.com, the TLD server is “com”).
- **Authoritative nameserver** - This final nameserver can be thought of as a **dictionary on a rack of books**, in which a specific name can be translated into its definition. The authoritative nameserver is the **last stop in the nameserver query**. If the authoritative name server has access to the requested record, it will return the IP address for the requested hostname back to the DNS Recursor (the librarian) that made the initial request.



# Code Quality

5 Key Code Quality Aspects to Measure

- **Reliability**: Reliability measures the **probability that a system will run without failure** over a specific period of operation. It relates to the number of defects and availability of the software. Number of defects can be measured by running a **static analysis tool**. Software availability can be measured using the **mean time between failures (MTBF)**. 
- **Maintainability**: Maintainability measures how easily software can be maintained. It relates to the size, consistency, structure, and complexity of the codebase. And ensuring maintainable source code relies on a number of factors, such as **testability and understandability**.
- **Testability**: Testability measures **how well the software supports testing efforts**. It relies on how well you can control, observe, isolate, and automate testing, among other factors. Testability can be measured based on **how many test cases you need to find potential faults in the system**. **Size and complexity of the software can impact testability**.
- **Portability**: Portability measures how usable the cyclomatic complexity. It relates to platform independency.
- **Reusability**: Reusability measures whether existing assets — such as code — can be used again. Assets are more easily reused if they have characteristics such as **modularity or loose coupling**. Reusability can be measured by the number of **interdependencies**. Running a **static analyzer** can help you identify these interdependencies.

## SonarQube

SonarQube is an open-source platform developed for **continuous inspection of code quality** to perform automatic reviews with static analysis of code to detect bugs, code smells, and security vulnerabilities. SonarQube offers <u>reports on duplicated code, coding standards, unit tests, code coverage, code complexity, comments, bugs, and security vulnerabilities</u>. SonarQube can record metrics history and provides evolution graphs. SonarQube provides fully automated analysis and integration with Maven, Ant, Gradle, MSBuild and continuous integration tools (Atlassian Bamboo, Jenkins, Hudson, etc.)

# Version Management

**Version Management** also called **Version Control** or Revision **Control**, is a means to effectively **track and control changes to a collection of related entities**. Version control systems are a category of software tools that help a software team **manage changes to source code over time**. Version control software keeps track of every modification to the code in a special kind of database. If a mistake is made, developers can **turn back the clock** and **compare earlier versions of the code** to help fix the mistake.

**Version management tools** : #1) Git · #2) CVS · #3) SVN 





**5 Software Development Best Practices**

1. **CODE SIMPLICITY**. Strive to keep your code simple. ...
2. **TESTING**. Continuously test from end to end. ...
3. **CODE COHERENCE**. Keep it consistent across your team. ...
4. **CODE REVIEWS**. Don't be shy, allow someone to check your code! ...
5. **ESTIMATION**. Set your time and budget estimates realistically.



# Test Pyramid

A testing pyramid is a pyramid of where all the different types of tests fits. The *test pyramid* is a way of thinking about **how different kinds of automated tests should be used** to create a balanced portfolio.  Its essential point is that you should have many more low-level UnitTests than high level BroadStackTests running through a GUI.



![Image for post](https://miro.medium.com/max/1200/1*S-WQ9KwM7kkmwKWy41SPYw.png)



