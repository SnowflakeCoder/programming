# Software Testing

Software testing can be stated as the **process of verifying and validating that a software is bug free**, meets the technical requirements and the user requirements effectively and efficiently with handling all the exceptional and boundary cases. The process of software testing aims to <u>improve the software in terms of efficiency, accuracy and usability</u>. It mainly aims at measuring specification, functionality and performance of a software program or application.

**Software testing can be divided into two steps:**
**Verification:** it refers to the set of tasks that ensure that software correctly implements a specific function. **Verification:** “Are we building the product right?”

**Validation:** it refers to a different set of tasks that ensure that the software that has been built is traceable to customer requirements. **Validation:** “Are we building the right product?”

## Different types

**Manual Testing:** Manual testing includes testing a software manually, i.e., without using any automated tool or any script. In this type, the tester takes over the role of an end-user and tests the software to identify any unexpected behavior or bug. There are different stages for manual testing such as <u>unit testing, integration testing, system testing, and user acceptance testing</u>.

Testers use test plans, test cases, or test scenarios to test a software to ensure the completeness of testing. Manual testing also includes **exploratory testing**, as testers explore the software to identify errors in it.

**Automation Testing:** Automation testing, which is also known as **Test Automation**, is when the tester writes scripts and uses another software to test the product. This process involves **automation of a manual process**. Automation Testing is used to re-run the test scenarios that were performed manually, quickly, and repeatedly.

Apart from regression testing, automation testing is also used to test the application from load, performance, and stress point of view. It increases the test coverage, improves accuracy, and saves time and money in comparison to manual testing.

## 4 levels of software testing

- **Unit Testing:** A testing process where **individual units/components of a software are tested**. The purpose is to validate that each unit of the software performs as designed. Here it focuses on the **smallest unit of software design**, often done by programmer.
- **Integration Testing:** A testing process where **individual units are combined and tested as a group**. The purpose is to **expose faults in the interaction** between integrated units. Integration testing is of **four types**: <u>(i) Top down (ii) Bottom up (iii) Sandwich (iv) Big-Bang</u>. Example are Black Box testing and White Box testing.
- **System Testing:** A testing process where a complete, integrated system/software is tested. The purpose is to **evaluate the system’s compliance** with the specified requirements. In this software is tested such that it **works fine for different operating system**.It is covered under the **black box testing** technique. In this we just focus on required input and output without focusing on internal working. In this we have <u>security testing, recovery testing , stress testing and performance testing</u>. 
- **Acceptance Testing:** A testing process where a system is **tested for acceptability**. The purpose of this test is to evaluate the system’s compliance with the business requirements and **assess whether it is acceptable for delivery**.
- **Regression Testing**: Every time new module is added leads to changes in program. This type of testing make sure that **whole component works properly even after adding components** to the complete program. 

**Smoke Testing**: This test is done to make sure that software under testing is ready or stable for further testing. It is called smoke test as **testing initial pass is done** to check if it did not catch the fire or smoked in the initial switch on.

**Alpha Testing**: This is a type of validation testing.It is a type of *acceptance testing* which is <u>done before the product is released to customers</u>. It is typically done by QA people. 

**Beta Testing**: The beta test is **conducted at customer sites** by the end-user of the software. This version is **released for the limited number of users** for testing in real time environment.

**Stress Testing**: In this we **gives unfavorable conditions** to the system and check how they perform in those condition. Example: Test cases that require maximum memory or other resources, may cause thrashing in a virtual OS, may cause excessive disk usage.

**Performance Testing**: It is designed to test the **run-time performance of software** within the context of an integrated system.It is used to test **speed and effectiveness of program**, what is the performance of the system in the given load. It is also called **load testing**. 

## Black Box vs White Box Testing

**Black Box Testing:** The technique of testing in which the tester doesn’t have access to the source code of the software and is conducted at the software interface (**functional test** of the software) **without concerning with the internal logical structure** of the software is known as black box testing. Also known as **closed box /data driven testing**. This can only be done by trial and error method. Mostly done by testers.

**White-Box Testing:** The technique of testing in which the **tester is aware of the internal workings** of the product (**structural test** of the software), have access to it’s source code and is conducted by making sure that all internal operations are performed according to the specifications is known as white box testing. Also knwon as **clear box/structural testing**. Data domains and internal boundaries can be better tested. Mostly done by developers.

























# Software Testing Dictionary

## Acceptance Testing

Acceptance testing, a testing technique performed to **determine whether or not the software system has met the requirement specifications**. The main purpose of this test is to <u>evaluate the system's compliance with the business requirements</u> and verify if it is has met the required criteria for delivery to end users. There are **various forms of acceptance testing**:

- User acceptance Testing
- Business acceptance Testing
- Alpha Testing
- Beta Testing

**Acceptance Criteria**

Acceptance criteria are defined on the basis of the following attributes : 

Functional Correctness and Completeness, Data Integrity,  Data Conversion, Usability, Performance, Timeliness, Confidentiality and Availability, Installability and Upgradability, Scalability, Documentation

The acceptance test cases are executed against the test data or using an acceptance test script and then the results are compared with the expected ones. The **acceptance test activities are carried out in phases**. Firstly, the basic tests are executed, and if the test results are satisfactory then the execution of more complex scenarios are carried out. The acceptance test activities are designed to **reach at one of the conclusions**:

1. Accept the system as delivered.
2. Accept the system after the requested modifications have been made.
3. Do not accept the system.

## Accessibility Testing

Accessibility testing is a **subset of usability testing** where in the users under consideration are **people with all abilities and disabilities**. The significance of this testing is to **verify both usability and accessibility**. Accessibility aims to cater people of different abilities such as: **Visual Impairments**, **Physical Impairment**, **Hearing Impairment**, Cognitive Impairment, Learning Impairment

A good web application should cater to all sets of people and NOT just limited to disabled people. These include:

1. Users with **poor communications infrastructure**
2. **Older people** and new users, who are often computer illiterate
3. Users using old system (NOT capable of running the latest software)
4. Users, who are using NON-Standard Equipment
5. Users, who are having restricted access

## Active Testing

Active testing, a testing technique, where the **user introduces test data and analyses the result**. During Active testing, a tester builds a mental model of the software under test which **continues to grow and refine as your interaction with the software continues**.

**How we do Active Testing ?**

1. At the end of each and every action performed on the application under test, we need to **check if the model/application seems to fulfill client's needs**.
2. If not, the **application needs to be adapted**, or that we got a problem in the application. We continuously engage in the testing process and help us to **come up with new ideas, test cases, test data to fulfill**.
3. At the same time, we need to **note down things**, which we might want to turn to later or we follow up with the concerned team on them, **eventually finding and pinpointing problems in the software**.
4. Hence, any application under test needs active testing which **involves testers who spot the defects**.

## Actual Outcome

Actual Outcome/ actual result, which a tester gets after performing the test. Actual Outcome is **always documented along with the test case** during the test execution phase. After performing the tests, the **actual outcome is compared with the expected outcome** and the deviations are noted. The deviation, if any, is known as **defect.** So after getting the actual outcome, we can mark whether the scenario is pass or fail. While developing the test cases, we usually have the following fields:

1. Test Scenario	2. Test Steps	3. Parameters	4. Expected Result	5. Actual Result

## Adhoc Testing

When a software testing performed **without proper planning and documentation**, it is said to be Adhoc Testing. Such kind of tests are **executed only once unless** we uncover the defects. Adhoc Tests are done **after formal testing** is performed on the application. Adhoc methods are the least formal type of testing as it is **NOT a structured approach**. Hence, defects found using this method are **hard to replicate as there are no test cases** aligned for those scenarios.

Testing is carried out with the **knowledge of the tester** about the application and the **tester tests randomly** without following the specifications/requirements. Hence the success of Adhoc testing depends upon the **capability of the tester**, who carries out the test. The tester has to find defects without any proper planning and documentation, solely **based on tester's intuition**.

**When to Execute Adhoc Testing ?**

Adhoc testing can be performed when there is **limited time to do exhaustive testing** and usually **performed after the formal test execution**. Adhoc testing will be effective only if the tester has in-depth understanding about the System Under Test.

**Forms of Adhoc Testing :**

1. **Buddy Testing:** **Two buddies**, <u>one from development team and one from test team</u> mutually work on identifying defects in the same module. Buddy testing **helps the testers develop better test cases** while development team can also make design changes early. This kind of testing happens **usually after completing the unit testing**.
2. **Pair Testing:** **Two testers** are assigned the same modules and they **share ideas** and work on the same systems to find defects. <u>One tester executes the tests while another tester records the notes on their findings</u>.
3. **Monkey Testing:** Testing is **performed randomly without any test cases** in order to break the system.

**Various ways to make Adhoc Testing More Effective**

1. **Preparation:** By getting the <u>defect details of a similar application</u>, the probability of finding defects in the application is more.
2. **Creating a Rough Idea:** By creating a rough idea in place the tester will have a focused approach.
3. **Divide and Rule:** By testing the application part by part, we will have a <u>better focus and better understanding</u> of the problems if any.
4. **Targeting Critical Functionalities:** target those <u>areas that are NOT covered</u> while designing test cases.
5. **Using Tools:** Using profilers, debuggers and even task monitors.
6. **Documenting the findings:** Though testing is performed randomly, it is better to document the tests if time permits and note down the deviations if any. If defects are found, corresponding test cases are created so that it **helps the testers to retest the scenario**.

## Age Testing

It is a testing technique that evaluates a **system's ability to perform in the future** and usually carried out by test teams. As the <u>system gets older, how significantly the performance might drop</u> is what is being measured in Age Testing. **Defect Age** is measured in terms of **two parameters: Phases and Time** 

**Defect age in Phases** is defined as the <u>difference between defect injection phase and defect detection phase</u>.

- **defect injection phase** is the phase of the SDLC when the defect was introduced.

- **defect detection phase** is the phase of the SDLC when the defect was pinpointed.

- ```
  Defect Age in Phase = Defect Detection Phase - Defect Injection Phase
  ```

**Defect age in Time** is defined as the time difference between defect detected date and the current date, provided the defect is still said to be open.

- ```
  Defect Age in Time = Defect Fix Date (OR) Current Date - Defect Detection Date
  ```

## Agile Testing

A software testing practice that **follows the principles of agile software development** is called Agile Testing. Agile is an **iterative development methodology**, where <u>requirements evolve through collaboration between the customer and self-organizing teams</u> and agile aligns development with customer needs.

**Advantages of Agile Testing**

Agile Testing **Saves Time and Money**		Less Documentation		**Regular feedback from the end user**

Daily meetings can help to **determine the issues well in advance**

**Principles of Agile Testing**

- **Testing is NOT a Phase:** Agile team tests continuously and continuous testing is the only way to ensure continuous progress.
- **Testing Moves the project Forward:** When following conventional methods, testing is considered as quality gate but agile testing provide feedback on an ongoing basis and the product meets the business demands.
- **Everyone Tests:** In conventional SDLC, only test team tests while in agile including developers and BA's test the application.
- **Shortening Feedback Response Time:** In conventional SDLC, only during the acceptance testing, the Business team will get to know the product development, while in agile for each and every iteration, they are involved and continuous feedback shortens the feedback response time and cost involved in fixing is also less.
- **Clean Code:** Raised defects are fixed within the same iteration and thereby keeping the code clean.
- **Reduce Test Documentation:** Instead of very lengthy documentation, agile testers use reusable checklist, focus on the essence of the test rather than the incidental details.
- **Test Driven:** In conventional methods, testing is performed after implementation while in agile testing, testing is done while implementation.

**Best Practices**

1. Automated Unit Tests	2. TDD	3. Automated Regression Tests	4. Exploratory Testing

## All Pair Testing

**All-pairs** / **pairwise testing** is a testing approach taken for testing the software using **combinatorial method**. It's a method to **test all the possible discrete combinations** of the parameters involved. The idea is to **bring down the number of test cases** by combining multiple test cases..

## Alpha Testing

Alpha testing **takes place at the developer's site** by the internal teams, **before release to external customers**. This testing is performed without the involvement of the development teams. In the **first phase** of alpha testing, the **software is tested by in-house developers** during which the goal is to catch bugs quickly. In the **second phase** of alpha testing, the **software is given to the software QA team** for additional testing. Alpha testing is often performed as a form of **internal acceptance testing**, **before the beta testing is performed**.

## API Testing

The API Testing is performed for the system, which has a **collection of API** that ought to be tested.

- **Exploring boundary conditions** and ensuring that the test harness varies parameters of the API calls in ways that **verify functionality and expose failures**.
- Generating more **value added parameter combinations** to verify the calls with two or more parameters.
- Verifying the behaviour of the API which is considering the **external environment conditions** such as files, peripheral devices, and so forth.
- Verifying the Sequence of API calls and check if the API's produce useful results from successive calls.

**Common Tests performed on API's**

- Return Value based on input condition - The return value fare checked based on the input condition.
- Verify if the API's does not return anything.
- Verify if the API triggers some other event or calls another API. The Events output should be tracked and verified.
- Verify if the API is updating any data structure.

## Arc Testing

Arc Testing is nothing but **branch testing**. A Branch is the **outcome of a decision**. So branch coverage is a measure of outcomes of a branch. Determining the number of branches in a method is easy as a the output of a **branch has usually two outcomes** (True of False). Branch testing focusses on **executing each condition in a decision** in order to test all possible outcomes at least once. Every branch (decision) taken each way, true and false. It helps in validating all the branches in the code making sure that **no branch leads to abnormal behaviour** of the application.

## Anomaly

In Software testing, Anomaly refers to **a result that is different from the expected one.** This behaviour can result from a document or also from a testers notion and experiences. An Anomaly can also refer to a **usability problem** as the testware may behave as per the specification, but it can still improve on usability. <u>Sometimes, the anomaly can also referred as a defect / Bug</u>.

## Assertion Testing

**An assertion is a boolean expression** at a specific point in a program which will be true unless there is a bug in the program. A test assertion is defined as an expression, which encapsulates some testable logic specified about a target under test. The main advantage of having assertions is to **identify defects in a program**. The usefulness of assertions include:

- It is used to detect subtle errors which might go unnoticed.
- It is used to detect errors sooner after they occur.
- Make a statement about the effects of the code that is guaranteed to be true.

**Limitations of Assertion**

Like any other piece of code, assertions may themselves contain errors and lead to below problems:

- Failing to report a bug that exists.
- Reporting an error when it does not exist.
- Can lead to other side effects
- Can Take time to execute if it contains errors and occupies memory as well.

## Backward Compatibility Testing

**Validation:** it refers to a different set of tasks that ensure that the software that has been built is traceable to customer requirements.

**Verification:** “Are we building the product right?”
**Validation:** “Are we building the right product?”

























# Software Testing Dictionary

## Acceptance Testing

Acceptance testing, a testing technique performed to **determine whether or not the software system has met the requirement specifications**. The main purpose of this test is to <u>evaluate the system's compliance with the business requirements</u> and verify if it is has met the required criteria for delivery to end users. There are **various forms of acceptance testing**:

- User acceptance Testing
- Business acceptance Testing
- Alpha Testing
- Beta Testing

**Acceptance Criteria**

Acceptance criteria are defined on the basis of the following attributes : 

Functional Correctness and Completeness, Data Integrity,  Data Conversion, Usability, Performance, Timeliness, Confidentiality and Availability, Installability and Upgradability, Scalability, Documentation

The acceptance test cases are executed against the test data or using an acceptance test script and then the results are compared with the expected ones. The **acceptance test activities are carried out in phases**. Firstly, the basic tests are executed, and if the test results are satisfactory then the execution of more complex scenarios are carried out. The acceptance test activities are designed to **reach at one of the conclusions**:

1. Accept the system as delivered.
2. Accept the system after the requested modifications have been made.
3. Do not accept the system.

## Accessibility Testing

Accessibility testing is a **subset of usability testing** where in the users under consideration are **people with all abilities and disabilities**. The significance of this testing is to **verify both usability and accessibility**. Accessibility aims to cater people of different abilities such as: **Visual Impairments**, **Physical Impairment**, **Hearing Impairment**, Cognitive Impairment, Learning Impairment

A good web application should cater to all sets of people and NOT just limited to disabled people. These include:

1. Users with **poor communications infrastructure**
2. **Older people** and new users, who are often computer illiterate
3. Users using old system (NOT capable of running the latest software)
4. Users, who are using NON-Standard Equipment
5. Users, who are having restricted access

## Active Testing

Active testing, a testing technique, where the **user introduces test data and analyses the result**. During Active testing, a tester builds a mental model of the software under test which **continues to grow and refine as your interaction with the software continues**.

**How we do Active Testing ?**

1. At the end of each and every action performed on the application under test, we need to **check if the model/application seems to fulfill client's needs**.
2. If not, the **application needs to be adapted**, or that we got a problem in the application. We continuously engage in the testing process and help us to **come up with new ideas, test cases, test data to fulfill**.
3. At the same time, we need to **note down things**, which we might want to turn to later or we follow up with the concerned team on them, **eventually finding and pinpointing problems in the software**.
4. Hence, any application under test needs active testing which **involves testers who spot the defects**.

## Actual Outcome

Actual Outcome/ actual result, which a tester gets after performing the test. Actual Outcome is **always documented along with the test case** during the test execution phase. After performing the tests, the **actual outcome is compared with the expected outcome** and the deviations are noted. The deviation, if any, is known as **defect.** So after getting the actual outcome, we can mark whether the scenario is pass or fail. While developing the test cases, we usually have the following fields:

1. Test Scenario	2. Test Steps	3. Parameters	4. Expected Result	5. Actual Result

## Adhoc Testing

When a software testing performed **without proper planning and documentation**, it is said to be Adhoc Testing. Such kind of tests are **executed only once unless** we uncover the defects. Adhoc Tests are done **after formal testing** is performed on the application. Adhoc methods are the least formal type of testing as it is **NOT a structured approach**. Hence, defects found using this method are **hard to replicate as there are no test cases** aligned for those scenarios.

Testing is carried out with the **knowledge of the tester** about the application and the **tester tests randomly** without following the specifications/requirements. Hence the success of Adhoc testing depends upon the **capability of the tester**, who carries out the test. The tester has to find defects without any proper planning and documentation, solely **based on tester's intuition**.

**When to Execute Adhoc Testing ?**

Adhoc testing can be performed when there is **limited time to do exhaustive testing** and usually **performed after the formal test execution**. Adhoc testing will be effective only if the tester has in-depth understanding about the System Under Test.

**Forms of Adhoc Testing :**

1. **Buddy Testing:** **Two buddies**, <u>one from development team and one from test team</u> mutually work on identifying defects in the same module. Buddy testing **helps the testers develop better test cases** while development team can also make design changes early. This kind of testing happens **usually after completing the unit testing**.
2. **Pair Testing:** **Two testers** are assigned the same modules and they **share ideas** and work on the same systems to find defects. <u>One tester executes the tests while another tester records the notes on their findings</u>.
3. **Monkey Testing:** Testing is **performed randomly without any test cases** in order to break the system.

**Various ways to make Adhoc Testing More Effective**

1. **Preparation:** By getting the <u>defect details of a similar application</u>, the probability of finding defects in the application is more.
2. **Creating a Rough Idea:** By creating a rough idea in place the tester will have a focused approach.
3. **Divide and Rule:** By testing the application part by part, we will have a <u>better focus and better understanding</u> of the problems if any.
4. **Targeting Critical Functionalities:** target those <u>areas that are NOT covered</u> while designing test cases.
5. **Using Tools:** Using profilers, debuggers and even task monitors.
6. **Documenting the findings:** Though testing is performed randomly, it is better to document the tests if time permits and note down the deviations if any. If defects are found, corresponding test cases are created so that it **helps the testers to retest the scenario**.

## Age Testing

It is a testing technique that evaluates a **system's ability to perform in the future** and usually carried out by test teams. As the <u>system gets older, how significantly the performance might drop</u> is what is being measured in Age Testing. **Defect Age** is measured in terms of **two parameters: Phases and Time** 

**Defect age in Phases** is defined as the <u>difference between defect injection phase and defect detection phase</u>.

- **defect injection phase** is the phase of the SDLC when the defect was introduced.

- **defect detection phase** is the phase of the SDLC when the defect was pinpointed.

- ```
  Defect Age in Phase = Defect Detection Phase - Defect Injection Phase
  ```

**Defect age in Time** is defined as the time difference between defect detected date and the current date, provided the defect is still said to be open.

- ```
  Defect Age in Time = Defect Fix Date (OR) Current Date - Defect Detection Date
  ```

## Agile Testing

A software testing practice that **follows the principles of agile software development** is called Agile Testing. Agile is an **iterative development methodology**, where <u>requirements evolve through collaboration between the customer and self-organizing teams</u> and agile aligns development with customer needs.

**Advantages of Agile Testing**

Agile Testing **Saves Time and Money**		Less Documentation		**Regular feedback from the end user**

Daily meetings can help to **determine the issues well in advance**

**Principles of Agile Testing**

- **Testing is NOT a Phase:** Agile team tests continuously and continuous testing is the only way to ensure continuous progress.
- **Testing Moves the project Forward:** When following conventional methods, testing is considered as quality gate but agile testing provide feedback on an ongoing basis and the product meets the business demands.
- **Everyone Tests:** In conventional SDLC, only test team tests while in agile including developers and BA's test the application.
- **Shortening Feedback Response Time:** In conventional SDLC, only during the acceptance testing, the Business team will get to know the product development, while in agile for each and every iteration, they are involved and continuous feedback shortens the feedback response time and cost involved in fixing is also less.
- **Clean Code:** Raised defects are fixed within the same iteration and thereby keeping the code clean.
- **Reduce Test Documentation:** Instead of very lengthy documentation, agile testers use reusable checklist, focus on the essence of the test rather than the incidental details.
- **Test Driven:** In conventional methods, testing is performed after implementation while in agile testing, testing is done while implementation.

**Best Practices**

1. Automated Unit Tests	2. TDD	3. Automated Regression Tests	4. Exploratory Testing

## All Pair Testing

**All-pairs** / **pairwise testing** is a testing approach taken for testing the software using **combinatorial method**. It's a method to **test all the possible discrete combinations** of the parameters involved. The idea is to **bring down the number of test cases** by combining multiple test cases..

## Alpha Testing

Alpha testing **takes place at the developer's site** by the internal teams, **before release to external customers**. This testing is performed without the involvement of the development teams. In the **first phase** of alpha testing, the **software is tested by in-house developers** during which the goal is to catch bugs quickly. In the **second phase** of alpha testing, the **software is given to the software QA team** for additional testing. Alpha testing is often performed as a form of **internal acceptance testing**, **before the beta testing is performed**.

## API Testing

The API Testing is performed for the system, which has a **collection of API** that ought to be tested.

- **Exploring boundary conditions** and ensuring that the test harness varies parameters of the API calls in ways that **verify functionality and expose failures**.
- Generating more **value added parameter combinations** to verify the calls with two or more parameters.
- Verifying the behaviour of the API which is considering the **external environment conditions** such as files, peripheral devices, and so forth.
- Verifying the Sequence of API calls and check if the API's produce useful results from successive calls.

**Common Tests performed on API's**

- Return Value based on input condition - The return value fare checked based on the input condition.
- Verify if the API's does not return anything.
- Verify if the API triggers some other event or calls another API. The Events output should be tracked and verified.
- Verify if the API is updating any data structure.

## Arc Testing

Arc Testing is nothing but **branch testing**. A Branch is the **outcome of a decision**. So branch coverage is a measure of outcomes of a branch. Determining the number of branches in a method is easy as a the output of a **branch has usually two outcomes** (True of False). Branch testing focusses on **executing each condition in a decision** in order to test all possible outcomes at least once. Every branch (decision) taken each way, true and false. It helps in validating all the branches in the code making sure that **no branch leads to abnormal behaviour** of the application.

## Anomaly

In Software testing, Anomaly refers to **a result that is different from the expected one.** This behaviour can result from a document or also from a testers notion and experiences. An Anomaly can also refer to a **usability problem** as the testware may behave as per the specification, but it can still improve on usability. <u>Sometimes, the anomaly can also referred as a defect / Bug</u>.

## Assertion Testing

**An assertion is a boolean expression** at a specific point in a program which will be true unless there is a bug in the program. A test assertion is defined as an expression, which encapsulates some testable logic specified about a target under test. The main advantage of having assertions is to **identify defects in a program**. The usefulness of assertions include:

- It is used to detect subtle errors which might go unnoticed.
- It is used to detect errors sooner after they occur.
- Make a statement about the effects of the code that is guaranteed to be true.

**Limitations of Assertion**

Like any other piece of code, assertions may themselves contain errors and lead to below problems:

- Failing to report a bug that exists.
- Reporting an error when it does not exist.
- Can lead to other side effects
- Can Take time to execute if it contains errors and occupies memory as well.

## Backward Compatibility Testing