# Behavioural Questions









## Common Ideas

- Take a **data driven approach to engineering** where all initiatives have **metrics to help drive progress** and **determine success**.
- challenging and pushing the boundaries of **innovation and technology**.
- **Anticipate and react to major technology changes** to ensure that Tesco is regarded as a leader and innovator in its field.
- Establish **technical standards** and ensure adherence to them for **product development and company operations**. 
- **Drive innovation and continuous improvement in technology** and methodologies to continuously increase productivity and ensure that **technology standards** and **best practices are maintained** in every area.
- How to develop, implement and maintain a **disaster recovery plan**.



## Organization

- why do you want to work for this organization ?
   - Very healthy **Work atmosphere**. Good **work life balance**.
   - Very good example of a **multidisciplinary team**.
   - A core part of the success is built on its **innovative use of technology**.
   - Good Exposure to new technologies and very strict to design and architecture principles.
   - Provides a comprehensive **training on many technologies** including several programming languages, databases and OS platforms.
   
   I'm really **driven by results**. I like it when I have a **concrete goal to meet** and enough time to **figure out a strong strategy for accomplishing it**. 
   I was responsible for several projects where I directed development teams and implemented repeatable processes. The teams achieved 100% on-time delivery of software products. I was motivated both by the challenge of finishing the projects ahead of schedule and by managing the teams that achieved our goals.



## Personal

 - Tell me a time when you had a conflict in the team.
    - A person **not interested in writing unit test cases** and don't care about code coverage.
    - I explained him the importance of writing unit test and code coverage, how its important in **CICD pipeline** and how it can actually save time in future.
    - I gave him more time to finish the unit test cases before giving next task.
 - Tell me a time when you had to take responsibility of someone else mistake.
    - MDM migration one of my junior member <u>accidently modified a table by using wrong environmental variable</u>.
    - I have explained the situation to my higher manager and I took <u>full responsibility in restoring the DB</u>.
 - What sources do you read? Tell me two books you recently read?  
    - stackoverflow.com, dzone.com, Java World, highscalability, mechanical-sympathy
    - **Youtube channels** - **ProgrammingKnowledge**, **Coding Tech**, **GoogleTechTalks**
    - geeksforgeeks/leet code for programming
    - Gang of Four Design Pattern (PDF), clean code [**uncle bob**], refactoring [**martin fowler**]
 - Tell me about most challenging project worked on?
    - Refactored MDM & Source implementation (entity support) and NBE level
    - Adapter design pattern which handles different interfaces. 
    - performance issues due to high payload(similar values)
 - Tell me what was the **biggest bug** you shipped and what it meant for the user and the company. What were the learning you took away from the situation?  
    - similarity news issue due to **training model hashcode value**.
    - Automating the services. code review and evaluating results with current results.
 - Describe an interesting/difficult problem you worked on  
    - performance problem - **Number of go routines** increased.
    - Using **Refresh-Ahead Caching** / **asynchronous cache refresh**.
    - Using **Prometheus - grafana dashboard** for storing and visualizing time series data.
    - First we split the channel based on API - caused more db queries.
    - We have to split the channel based on RedisKey and  give different workers.
 - What do you **want to improve** in you, in what role do you see yourself in future ?
     - More exposure to coud computing.
         - **SaaS** - Software as a service.
         - **IaaS** - Infrastructure as a service.
         - **PaaS** - Platform as a service.
     - Adopt **best practices in software engineering**: 
         - How CICD pipeline works here
             - A CI/CD pipeline **automates your software delivery process**. The pipeline builds code, runs tests (CI), and safely deploys a new version of the application (CD). Automated pipelines **remove manual errors** and **enable fast product iterations**.
         - SOLID principles / Clean code principles.
         - **design**, **testing**, version control, documentation, **build**, **deployment**, monitoring and operations. 
         - Better understanding in **Building flexible systems** choosing simple, straightforward solutions over more complex ones.
     - More exposure to other **innovative technologies**.
 - how do you provide feedback to team members ?
     - Regularly give **actionable feedback** that helps them improve the performance.
         - help them understand the progress.
     - Make sure your team member understands that feedback in a positive manner.
         - Always give specific details that help then understand why feedback is given.
     - Never delay feedback
         - avoidance just defers problems and **make them grow**, not go away.
     - Always give both negative and positive feedbacks.
     - **Successful conflict management** results in greater productivity and positive working relationships.
 - how does you team members rate you.
     - Team members always rate me as a **good leader** and some one who is **easy to talk to**.
         - Regularly give **actionable feedback** that helps them improve the performance.
         - keeps the team **focused on our priority results/deliverables**.
         - Regularly shares **relevant information** from manager and senior leaders.
         - Define and maintain **clear goals** for the team.
         - Share my **learnings and best practices** and discuss with in the team.
     - kind of  **go to guy** role  - whose knowledge of something is considerable so everyone wants to go to him or her for information or results. - because of my experience. 
 - What's your biggest achievement / Tell me a time when you did something that had a big impact.
     - Developed MDM with new programming language. (GO)
     - Writing test cases and following strict guide lines like **writing unit test cases**.
     - Added metrics and error handling systems.
     - Improved the latency more than 10 times. 





# Multidisciplinary team

 Three key elements to working in a multidisciplinary team:

1. Share a common goal.
2. Commit to that goal **as a team**
3. Be clear on what you expect from each other





# Tools Used

- Combination of **Prometheus and Grafana**
  - monitoring tool used by DevOps teams for <u>storing and visualizing time series data</u>. 
  - Prometheus acts as the **storage backend** and Grafana as the <u>interface for analysis and visualization</u>.
- JMeter
- VisualVM & jvisualvm 
- Mockito mock objects 
- SonarQube to ascertain code coverage & code quality metrics & Code duplication 
- importing the thread dumps into “Samurai”, which is a visual tool.



## Open Source Metrics and error monitoring software

- **Jaeger**: end-to-end **distributed tracing**. **Monitor and troubleshoot** transactions/communications in complex distributed systems
- **Sentry**: For **error tracking**, so we don't have to manually check any logs.
- **Prometheus**: Used to **track internal metrics** such as what failed/successed at where  and how many times.
- **Grafana**: Graphs for internal metrics and auto altering through mail and google chat.



# Reference

[tech-interview-handbook/behavioral-questions](https://yangshun.github.io/tech-interview-handbook/behavioral-questions)





## Behavioural Questions

 - Tell me a time when you had a conflict in the team.
 - Tell me a time when you did something that had a big impact.
 - Tell me a time when you had to take responsability of someone else mistake  
 - What sources do you read? Tell me two books you recently read?  
 - Tell me about most challenging project worked on?
 - Tell me what was the biggest bug you shipped and what it meant for the user and the company. What were the learning you took away from the situation?  
 - Tell about a time you had to deal with a conflict in your team  
 - Describe an interesting/difficult problem you worked on  

## SAR (Situation-Action-Result) technique examples

### Example 1: Tuning performance

**Situation**: Performance problem where the application server had to be restarted every second day.

**Action**:

- Used JMeter to simulate the load conditions.
- Identified the cause of the problem to be leaking database connections due to not properly closing the connections under an exceptional scenario.
- Used the profiling tool “VisualVM” & identified a memory leak where long living objects were holding on to short lived objects. jvisualvm to detect memory leak.
- Fixed the database connection leaks by closing the connections in a finally block.
- Fixed the code to release memory consumed by the short lived objects.
- Tuned the JVM & GC settings. 
- Load and endurance tested the fixed code with the load testing tool JMeter to confirm that the issue has been fixed.


### Example 2: Code quality

**Situation**: Java code that is hard to maintain and reuse. Changes to one module may break another module.

**Action**:

- Wrote unit tests with proper Mockito mock objects for the existing un-maintainable code.
- Introduced SonarQube to ascertain code coverage & code quality metrics. Fixed the blocker, critical, and major severity items.
- Re-factored the code with OO concepts and design patterns in a test driven manner to improve maintainability.
- Large procedural style if/else statements were replaced with objects adhering to the Open-Closed design principle.
- Code duplication was eliminated with the help of SonarQube tool & refactoring.
- Reran the unit tests to ensure that the functionality is not broken due to refactoring.

**Result**: The application became much easier to maintain, extend, and reuse. The test coverage was increased from 27% to 76%.

### Example 3: Concurrency Management

**Situation**: The production ready application consumed very less CPU and response times were very poor due to heavy I/O operations like database read/write operations.

**Action**:

- Monitored the CPU usage with Visual VM tool.
- Got a series of thread dumps, say 7 to 10 at a particular interval, say 5 to 8 seconds and analysed those thread dumps by importing the thread dumps into “Samurai”, which is a visual tool.
- Paid attention to the blocked threads in red. Alternatively, VisualVM is handy for debugging deadlocks & analyzing thread dumps.
- Fixed the concurrency issue by reducing the synchronization granularity in the code.
- The offending SQL statement was identified with a SQL query planner and tuned.

**Result**: The response times were halved and the average CPU usage increased from 45% to 98%.