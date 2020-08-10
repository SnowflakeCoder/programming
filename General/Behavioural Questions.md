
# Behavioural Questions

 - Tell me a time when you had a conflict in the team.
    - A person **not interested in writing unit test cases** and don't care about code coverage.
    - I explained him the importance of writing unit test and code coverage, how its important in **CICD pipeline** and how it can actually save time in future.
    - I gave him more time to finish the unit test cases before giving next task.
 - Tell me a time when you did something that had a big impact.
    - MDM design and implementation.
    - Improved the latency more than 10 times. 
 - Tell me a time when you had to take responsibility of someone else mistake.
    - MDM migration one of my junior member <u>accidently modified a table by using wrong environmental variable</u>.
    - I have explained the situation to my higher manager and I took <u>full responsibility in restoring the DB</u>.
 - What sources do you read? Tell me two books you recently read?  
    - stackoverflow.com, dzone.com, Java World, highscalability, mechanical-sympathy
    - Youtube channels - **ProgrammingKnowledge**, **Coding Tech**, **GoogleTechTalks**
    - geeksforgeeks/leet code for programming
    - Gang of Four Design Pattern (PDF), clean code [uncle bob], refactoring [martin fowler]

 - Tell me about most challenging project worked on?
    - Refactored MDM & Source implementation (entity support) and NBE level
    - Adapter design pattern which handles different interfaces. 
    - performance issues due to high payload(similar values)
 - Tell me what was the biggest bug you shipped and what it meant for the user and the company. What were the learning you took away from the situation?  
    - similarity news issue due to training model hashcode value.
    - Automating the services. code review and evaluating results with current results.
 - Describe an interesting/difficult problem you worked on  
    - performance problem - Number of go routines increased.
    - Using **Prometheus - grafana dashboard** for storing and visualizing time series data.
    - First we split the channel based on API - caused more db queries.
    - We have to split the channel based on RedisKey and  give different workers.
 - why do you want to work for us ? 
 - What do you want to improve in you, in what role do you see yourself in future ?
 - how do you provide feedback to team members ? 
 - how does you team members rate you, biggest achievement etc. 



# Tools Used

- Combination of **Prometheus and Grafana**
  - monitoring tool used by DevOps teams for <u>storing and visualizing time series data</u>. 
  - Prometheus acts as the **storage backend** and Grafana as the <u>interface for analysis and visualization</u>.
- JMeter
- VisualVM & jvisualvm 
- Mockito mock objects 
- SonarQube to ascertain code coverage & code quality metrics & Code duplication 
- importing the thread dumps into “Samurai”, which is a visual tool.



Reference

[tech-interview-handbook/behavioral-questions](https://yangshun.github.io/tech-interview-handbook/behavioral-questions)



## SAR (Situation-Action-Result) technique examples





 