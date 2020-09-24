## Spring Boot Internals

A typical Spring MVC Application basically need **3 types of jars**.

- Spring Core jars
- View Resolvers
- Tomcat Server Jars

You need to put all these jars and create the instances and put that into **spring context**, so that when container starts it can take those into account. These are the things that you **need to do manually**. Apart from this you also need to **take care of the configurations** like port, properties, profiling etc.

### Spring boot concept

- **Starters and AutoConfigration** are the two important components.
- <u>**Spring boot starter** reduces build's dependencies and **AutoConfigurator** reduces the spring configuration</u>.
- **Spring Boot Starters** are a set of **convenient dependency descriptors** that you can include in your application. put all these libraries into a single jar (**starter**) and add that single jar, all of these gets downloaded automatically. If you want to add DB specific libraries you can use **spring JDBC starter jar**. When you add that jar all **related dependencies get downloaded automatically** because those are configured into that jar.
- do above configurations automatically (**AutoConfigration**) by putting some default configurations. Spring boot starter will contain spring boot **autoconfigure dependency.**

### condition annotations

Categories of condition annotations are 

Class conditions

Bean Conditions

Property Conditions

Resource Conditions

Web application conditions

SpEL (Spring Expression Language) expression conditions

## TDD

- why must you write your tests first => "So **you know when you're done**"
- purpose of our suite of tests => so that we can refactor
- afraid of your code => what will happen to you if you touch it
- The reason we **write our tests first**, is so that we KNOW that every line of code we wrote, was written **because of a failing test**. We know that **every single decision we made is tested**. So now we can pull up that code on our screen and **CLEAN IT Without any fear**.
- `Not allowed to write any production code` unless it is to make a fairly unit test pass.
- `Not allowed to write any more of a unit test` than is sufficient to fail; and compilation failures are failures.
- not allowed to write **any more production code** than is sufficient to pass the one failing unit test.

### Spring TDD

- Every structure of a test case follows **AAA pattern**(Arrange, Act, Assert).
- **Arrange**: arrange the test, do the necessary setup of the test. eg: create object of the targeted class, create mock objects and other variable initialization etc.
- **Act**: execute the test and get the result.
- **Assert**: check and verify the returned result with expected results.

#### TDD for Controller

- <u>register this class as a **spring test class**</u> which is tesing a MVC controller=> **mark this class** with two annotations **@Extendwith** and **@WebMVCTest**.
- **mock the Service and repository layer** code for controller unit tests => **@MockBean**
- @Autowired **MockMvc** ;

## Classify programming languages

- `Compiled vs Interpreted` => “When source code is translated”
  - **Interpreted**: Code **translated on the fly**, during execution.
- `Statically typed vs Dynamically typed` => “When types are checked” 
  - **Type checking** - looking at variables and their types and then saying does this expression make sense.
  - **statically typed** : **types are static**, meaning once you set a variable to a type, you cannot change it. Because <u>typing is associated with the variable rather than the value it refers to</u>.
  - **dynamically typed**: types are ***dynamic***, meaning **after you set a variable to a type, you CAN change it**. Because <u>typing is associated with the value it assumes</u> rather than the variable itself. So a language is dynamically typed **if the type is associated with run-time values**.
  - *Explicit type conversion* (**Type casting**): uses an operation to trigger a type conversion.
  - ***Type coercion*** is *implicit type conversion*: automatically converts to the types it needs..
    - (e.g. merging two types using `+`)
- `Strongly typed vs Weakly typed` programming languages.
  - **strongly-typed**: <u>variables are bound to specific data types</u>. don't allow "**type coercion**" will raise a type error if types do not match.
  - A **weakly-typed**: variables are <u>not bound to a specific data type</u>; they still have a type, but **type safety constraints are lower**

Statically typed languages are <u>generally compiled languages</u>. + **give more control**

Dynamically typed languages are <u>generally interpreted</u>. + give more **flexibility** 

![img](https://miro.medium.com/max/1648/1*BddwVWW6hFU0miT9DCbUWQ.png)

## Test Pyramid

tells you **how much testing to do on each layer**.

<img src="https://martinfowler.com/articles/practical-test-pyramid/testPyramid.png" alt="img" style="zoom:50%;" />

## user authentication and authorization

**Authentication** is the process of **verifying who a user is**, while **authorization** is the process of verifying **what they have access to**. Authorization usually `done after successful authentication`. <u>Access to a resource is protected by both authentication and authorization</u>.

## OAUTH2

- Used for Authenticate an application. stands for **open authorization**.
- Applications can use **OAuth2 to authorize users** of his application by using a **external authorization server** (delegate the responsibilities of user authorization).
- The **3 high-level roles** that exist within an OAuth 2 are **user, the application and the API**.
  - With in the API there is an **authentication server** and a **resource server**.
- 

Steps

1. first client need to **create the client ID and the client secret** and store both information.
   1. client makes a request to the server.
   2. server just sends back the **authorization code**.
2. request the **access token** from the application
   1. client uses that authorization code and sends that code to the server
   2. you pass the API key, client ID and the certain kind of **scopes that you want** in your application.
   3. a **prompt will tell you what permissions** an application is requesting.
3. after the successful sign-in the server returns the **access token** to client.
4. with the help of this **access token**, client requests access to use information from the resource API.

<img src="https://www.javainuse.com/boot-39_3.jpg" alt="boot-39_3" style="zoom:50%;" />

## JSON Web Token (JWT)

- defines a compact and self-contained way for <u>securely transmitting information between parties as a **JSON object**</u>.
- can be verified and trusted because it is **digitally signed**. signed using a **secret** (with the **HMAC** algorithm) or a **public/private key** pair (**RSA** or **ECDSA**).
- Because JWTs can be signed, you can be sure the senders are who they say they are. Additionally, as the <u>signature is calculated using the header and the payload</u>, you can also verify that the content hasn't been tampered with
- JWT Structure => Header, Payload and Signature.

Flow

- **Generating JWT** - Expose a POST API with mapping **/authenticate**. On passing correct username and password it will generate a JSON Web Token(JWT)
- If user tries to access GET API with mapping **/hello**. It will allow access only if request has a valid JSON Web Token(JWT)

### Generating JWT

<img src="https://www.javainuse.com/62-2-min.JPG" alt="Spring Boot JWT Generate Token" style="zoom:67%;" />

### Validating JWT

<img src="https://www.javainuse.com/62-3-min.JPG" alt="Spring Boot JWT Validate Token" style="zoom:67%;" />

