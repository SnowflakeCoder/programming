# Spring TDD Project

## Create a Spring project

- Go to https://start.spring.io/
- Give all details and generate War.

![spring-tdd.png](https://github.com/SnowflakeCoder/programming/blob/master/General/images/spring-tdd.png?raw=true)



## AAA pattern

Every structure of a test case follows **AAA pattern**(Arrange, Act, Assert).

**Arrange**: This is the first step of a unit test. Here we will arrange the test, in other words we will do the necessary setup of the test. For example, to perform the test we need to create an object of the targeted class, if necessary, then we need to create mock objects and other variable initialization, something like these.

**Act**: In this step we will execute the test. In other words we will do the actual unit testing and the result will be obtained from the test application. Basically we will call the targeted function in this step using the object that we created in the previous step.

**Assert**: This is the last step of a unit test. In this step we will check and verify the returned result with expected results.





- Prepare a **list of conditions**/use cases.
- Better to **start with a condition which represent the business logic of a method** and then test the corner cases.

------

## GET API

https://medium.com/@sheikarbaz5/spring-boot-with-tdd-test-driven-development-part-i-be1b90da51e

1. Write a *controller* test where we mock the *service*, implement it*.*
2. Write a *service test* where we use the repository instance directly, implement it.

### TDD for Controller

- Let’s start with the first layer i.e., controller. Create a ToDoControllerTest.java inside the test folder.
- We’ve to <u>register this class as a **spring test class**</u> which is tesing a MVC controller. For that, we’ve to **mark this class** with two annotations **@Extendwith** and **@WebMVCTest**.
- In unit testing, we want **each module to be tested independently**. But, our **Controller depends on the Service layer**. Since we are only focused on the Controller code, it is natural to **mock the Service layer code for our unit tests**.
- We need a mockMvc object. 
- **Create a Service** `ServiceOne` and  mark it with **Service annotation**.
- **Create an Entity** `users`, and mark it with the **annotation Entity**.

### TDD for Service

- The next layer we can implement is the service layer. Create a **ToDoServiceTest.java**.
- The **subject under test(SUT)** is ToDoService. We have to **mock all the dependencies** of the SUT. So we’re going to **mock the repository**. Here, **we don’t want to mock MVC** as we’re not dealing with controllers. **Mark the class with SpringBootTest annotation** which is provided by Spring Boot to test it’s components. This annotation **adds ExtendWith annotation implicitly**.

Till now, we’ve implemented **Controller, Service layers** while following TDD. Should we test *ToDoRepository* too? Logically, we should not because **we’re not going to implement anything** inside it. 

While testing *service* layer we’ve mocked ToDoRepository. But, we’re not sure whether the data is getting stored in the DB through *repository* as we’ve not tested it. Now, we’ve two contradictory statements. The solution to this is: We **will not mock *repository* while testing the *service*** layer. This way, we can make sure data is getting stored in the DB and we can escape from testing repository.

Now go to ToDoServiceTest.java and **mark todoRepository with Autowired rather than MockBean**.



## POST API

Now we are going to add an **API to save** a todo.

https://medium.com/@sheikarbaz5/spring-boot-with-tdd-part-ii-88891ba64d9d

**Controller**

- Let’s write the test in ToDoControllerTest.java. Whenever we send an object to the controller, we expect it to return the same object. So, we’ll <u>send an object in the post request and verify the returned object</u>. 
- We have to make a JSON and hit our api with it. We’ll use objectMapper utility to convert our Java Object to JSON.
- We expect a **201 http status** on successful resource creation.











https://mkyong.com/spring-boot/spring-rest-spring-security-example/



## The Test Pyramid

If you want to get serious about automated tests for your software there is one key concept you should know about: the **test pyramid**. Mike Cohn came up with this concept in his book *Succeeding with Agile*. It's a great visual metaphor telling you to think about different layers of testing. It also tells you how much testing to do on each layer.

![img](https://martinfowler.com/articles/practical-test-pyramid/testPyramid.png)