# Dev-ops key performance indicator(KPI)

First it was **Waterfall**, then **Agile**, and now it's **DevOps**.  This is how modern developers approach building great products. In the agile age, most companies would deploy/ship software in monthly, quarterly. Now, in the DevOps era, weekly, daily, and even multiple times a day is the norm.  Using SaaS you can easily update applications on the fly without forcing customers to download new components.  Often times, they won’t even realize things are changing. Development teams have adapted to the **shortened delivery cycles** by embracing **automation across their software delivery pipeline**. Most teams have **automated processes** to test the changes, check in code and deploy to new environments.

**Five activities that make devops what it is**: continuous integration, continuous delivery, cloud infrastructure, test automation, and configuration management. 

The benefits of DevOps to enterprises is we focus on **accelerating the speed** and confidence of feedback between customers and developers. The results of this acceleration come in the form of **faster lead time for changes** to production, **increased deployment frequency** to production, **faster time to restore** service to production, and **reduction in the change failure rate** to production.

Dev-ops four key software delivery metrics:

- **Deployment frequency**: The frequency of production deployments tells you **how often you’re delivering** something of value to end users and/or getting feedback from users. 
- **Lead time for changes**: lead time for change is “the time it takes to go from **code committed to code successfully running in production**”. 
- **Time to restore service**: The time to restore service or **mean time to recover (MTTR)** metric calculates the average **time it takes to restore service**.
- **Change failure rate**: The change failure rate is a measure of **how often deployment failures** occur in production that require immediate remedy (particularity, rollbacks).

# CI/CD

Teams struggle to ship software into the customer’s hands due to **lack of consistency** and **excessive manual labor**. CI/CD deliver software to a production environment with speed, safety, and reliability.

## CI/CD pipeline

**Continuous integration (CI) and continuous delivery (CD)** embody a culture, **set of operating principles**, and **collection of practices** that enable application development teams (dev-ops teams) to <u>deliver code changes **more frequently and reliably**</u>. The implementation is also known as the **CI/CD pipeline**. CI/CD enables software development teams to <u>focus on meeting business requirements, code quality, and security because **deployment steps are automated**</u>. The CI/CD pipeline is one of the best practices for devops teams to implement. 

### Continuous integration

Continuous Integration (CI) is a **development practice** that requires developers to <u>integrate code into a shared repository</u> several times a day. Each check-in is then verified by an automated build, allowing teams to **detect problems early**. By integrating regularly, you can **detect errors quickly**, and **locate them more easily**. With CI, a developer practices <u>integrating the code changes continuously with the rest of the team</u>. The integration happens after a “**git push**” to a master branch. Then, in a dedicated server, an automated process builds the application and runs a set of tests to confirm that the newest code integrates with what’s currently in the master branch.

If you’re doing CI and for some reason the integration fails, that means the broken build becomes the **highest priority to fix** before continuing to add more features. CI works in <u>**three simple stages**: push, test, and fix</u>.  If the build can’t be fixed within minutes, the team should decide if they’ll <u>remove the code or turn the feature flag off</u>. 

With continuous integration, developers <u>frequently integrate their code into a main branch</u> of a common repository, rather than building features in isolation and submitting each of them at the end of the cycle. Continuous integration is a **coding philosophy and set of practices** that drive development teams to <u>implement small changes</u> and <u>check in code to version control repositories frequently</u>. 

So the team needs a <u>mechanism to integrate and validate its changes</u>. The technical goal of CI is to establish a <u>consistent and automated way to **build, package, and test** applications</u>. Teams can commit code changes more frequently, which leads to better collaboration and software quality, and the primary purpose is to enable **early detection of integration bugs**. 

In practice, a developer will often **discover boundary conflicts** between new and existing code at the time of integration. If it’s done early and often, then such <u>**conflict resolutions** will be easier and less costly</u> to perform. This will help **reduce integration costs** by having developers do it sooner and more frequently. The goal of CI is to refine integration into a **simple, easily-repeatable everyday development task** that will serve to <u>reduce overall build costs</u> and reveal defects early in the cycle. 

#### Push to master every day

Integrating code daily doesn’t mean that a developer will push the code into a feature branch. The CI practice is about pushing code into the master branch, because that’s the branch that’s going to be used to release software. The “push to master” stage is also known as **trunk-based development**. When you practice CI (integrate code continually),  you amplify feedback then **branches become temporary**. A branch might live only for the day; then it’s integrated into the master branch.

**What about incomplete changes**?  you can integrate incomplete changes by using **feature flags**. Feature flags are an <u>if condition determining whether to run the new code or not</u>. If a change isn’t complete yet, the flag is off by default. That way, when you integrate the code, the rest of the team has a chance to review it. The same technique applies if the new code has bugs.

#### Rely on automated reliable tests

To validate each time a developer integrates new code, CI <u>relies on an automated and reliable suite of tests</u>. If you need to compile the code, the first test is that the code compiles. Then, you can include as many tests as you consider critical, but remember that CI’s purpose is to provide feedback as soon as possible. If the build takes too long, it’ll require the team to coordinate who can push. Keep the CI loop as short as possible. You might have to lean **heavily on unit testing** and **lightly on integration testing**.

The suite of tests should be able to say that something is broken; tests should be reliable. When you spot a bug in production, **create a test case and include it in the CI loop**.

#### Tools for CI

CI is mainly a cultural shift, but some tools could help you to get the job done quickly.

**Jenkins**—a free, open-source, Java-based tool that gives you a lot of flexibility.
**Azure Pipelines**—a Microsoft product free for up to five users and open-source projects.
**Cloud Build**—the managed service offering from Google Cloud Platform.
Travis CI—a popular tool for GitHub open-source projects that offers a hosted or self-hosted solution.
GitLab CI—a free tool from GitLab that can also integrate with other tools via the API.

### Continuous delivery

Continuous delivery picks up where continuous integration ends. CD **automates the delivery of applications** to selected **infrastructure environments**, such as development, testing and production environments, and CD ensures there is an **automated way to push code changes** to them. Continuous Delivery is the ability to get **changes of all types into production** —including new features, configuration changes, bug fixes and experiments,  safely and quickly in a sustainable way.

CI/CD tools help **store the environment-specific parameters** that must be packaged with each delivery. CI/CD automation then performs any necessary service calls to web servers, databases, and other services that may need to be restarted or follow other procedures when applications are deployed.

In CD, the **software delivery process is automated** further to enable easy and confident deployments into production —at any time. A mature continuous delivery process exhibits **a codebase that is always deployable**—on the spot. With CD, software release becomes a routine event without any special testing. A build in CI happens only once and not for each environment. CD will always deploy the same artifact in all environments. Each environment, Development, testing, and staging should be a production-like environment. Use the same tooling, process, and configurations in all the environments. In CD, production is not a special environment; it’s just another stage in the pipeline.

### Continuous Testing

CI and CD **require continuous testing** because the objective is to deliver quality applications and code to users. Continuous testing is often implemented as a <u>set of automated regression, performance, and other tests</u> that are executed in the CI/CD pipeline. In a CI/CD pipeline application changes run through the pipeline and in each segment in the pipeline, the <u>build may fail a critical test and alert the team</u>. Otherwise, it continues on to the next test suite, and successive test passes will result in automatic promotion to the next segment in the pipeline. The last segment in the pipeline will deploy the build to a production-equivalent environment. This is a comprehensive activity, since <u>the build, the deployment, and the environment are all exercised and tested together</u>. The result is a build that is deployable and verifiable in an actual production environment.

### Principles of CD

The following are the vital principles for continuous delivery, they’ll help you to deliver software safely and quickly in a sustainable way.

**Frequent small deployments**
Every time a deployment happens, the application’s stability is at risk. Therefore, we <u>tend to distance deployments</u> from each other. But the problem with that approach is that we **end up accumulating many changes**. Chances are that one of those changes might have problems, forcing us to roll back the other changes that were working. What CD implies is that we should **work in small batches**. When you need to make a significant change in the application, use **feature flags** or apply the **strangler pattern** and split complicated changes into small and simple changes. If you do deployments more often and work in small batches, the risk of doing deployments will be lower.

**Automation with a human touch**
All repeated manual labor that humans or engineers do all the time in every deployment should be automated. Machines are perfect candidates for repetitive tasks. <u>Automation is a critical principle in CD</u> because it helps to **increase the sustainability** of the process. CD will help to produce one-click deployments that can be triggered on demand.

**Always improving**
If you try to implement all together you’ll get overwhelmed and might decide not to do anything at all. If you know there are a lot of improvement opportunities, start with simple things like automate compiling the application or build the application using a dedicated server then start automating the copying/pasting process. There’s always going to be something to improve. CD doesn’t have an end date.

**Shared responsibility model**
Now the deployment pipeline isn’t just an operations problem. Operations will seek ways to help developers build software with quality. Everyone in the team is responsible for creating a safer, quicker, and deterministic delivery pipeline continuously.

#### Tools for CD

**Jenkins**—can also be used for CD with its pipeline as code, Ansible, or Terraform plugins.
**Azure Pipelines**—has a release definition section that you can integrate with a build stage from CI.
Spinnaker— it’s the tool that **Netflix** uses to do releases in a CD way.
**GitLab CI**—lets you configure deployment and release pipelines with GitLab.

**How continuous integration improves collaboration and quality ?**
When practicing CI, developers <u>commit their code into the version control repository frequently, at least daily</u>. It’s <u>easier to identify defects and other software quality issues on **smaller code differentials**</u> rather than larger ones developed over extensive period of times. In addition, when developers work on shorter commit cycles, it is <u>less likely for multiple developers to be editing the same code and requiring a merge</u> when committing.

### Managing Features/Code

Development teams practicing continuous integration use <u>different techniques to control what features and code are ready for production</u>.

#### Feature Flags

**Feature flags**, a configuration mechanism to turn features and code on or off at run time. Features that are still under development are wrapped with feature flags in the code, <u>deployed with the master branch to production, and turned off until they are ready to be used</u>. **Feature flagging tools** such as **CloudBees Rollout**, **Optimizely Rollouts**, and LaunchDarkly integrate with CI/CD tools and enable feature-level configurations.

#### Version Control Branching

Another technique for managing features is **version control branching**. A branching strategy such as **Gitflow** is selected to <u>define protocols over how new code is merged into standard branches</u> for development, testing and production. Additional **feature branches** are created for ones that will take **longer development cycles**. When the feature is complete, the developers can then merge the changes from feature branches into the **primary development branch**. This approach works well, but it can become difficult to manage if there are many features being developed concurrently.

The CI build process itself is then <u>automated by packaging all the software, database, and other components</u>. CI automation process will also <u>execute unit tests and other testing</u>. This testing provides feedback to developers that their code changes <u>didn’t break any existing unit tests</u>. Teams need to decide the **build schedule (eg: daily night)** that works best for the size of the team, the number of daily commits expected, and other application considerations.

### Test automation

**Automated testing frameworks** help quality assurance engineers <u>define, execute, and automate various types of tests</u> that can help development teams know whether a **software build passes or fails**. They include **functionality tests** that are developed at the end of every sprint and aggregated into a **regression test** for the entire application. These regression tests then inform the team whether a code change failed one or more of the tests developed across all functional areas of the application where there is test coverage.

```wiki
What is Regression Testing?
REGRESSION TESTING is defined as a type of software testing to confirm that a recent program or code change has not adversely affected existing features. This testing is done to make sure that new code changes should not have side effects on the existing functionalities. It ensures that the old code still works once the latest code changes are done.
```

A best practice is to enable and require developers to <u>run all or a subset of regressions tests in their local environments</u>. This step ensures that developers only commit code to version control after regression tests pass on the code changes. **Regression testing**, Performance testing, API testing, **static code analysis**, security testing, and other testing forms can also be automated. These tests can be triggered either through command line, webhook, or web service and that they <u>respond with success or fail status codes</u>.

Once testing is automated, continuous testing implies that the automation is integrated into the CI/CD pipeline. Some unit and functionality tests can be integrated into CI that flags issues before or during the integration process. Tests that require a **full delivery environment** such as performance and security testing are often integrated into CD and performed after builds are delivered to target environments.

### CI/CD tool -Jenkins

Continuous delivery is the <u>automation that pushes applications to **delivery environments**</u>. Most development teams typically have <u>one or more development and testing environments</u> where application changes are staged for **testing and review**. A CI/CD tool such as **Jenkins**, **tekton** (GCP), AWS CodeBuild or Azure DevOps is used to **automate the steps and provide reporting**. A typical CD pipeline has <u>build, test, and deploy stages</u>. 

- Pulling code from version control and **executing a build**.
- Executing any **required infrastructure steps** that are automated as code to <u>stand up or tear down cloud infrastructure</u>.
- Moving code to the **target computing environment**.
- Managing the **environment variables** and configuring them for the target environment.
- Pushing application components to their appropriate services, such as web servers, API services, and database services.
- Executing any steps required to <u>restarts services or call service endpoints</u> that are needed for new code pushes.
- Executing <u>continuous tests and rollback environments</u> if tests fail.
- Providing **log data and alerts** on the state of the delivery.

As an example, Jenkins users <u>define their pipelines in a Jenkinsfile</u> that describes different stages such as <u>build, test, and deploy</u>. Environment variables, options, secret keys, certifications, and other parameters are declared in the file and then referenced in stages. The post section handles error conditions and notifications. CI/CD tools typically support a marketplace of plug-ins, that support integration with third-party platforms, user interface, administration, source code management, and build management.

Once a CI/CD tool is selected, development teams must make sure that all <u>environment variables are configured outside the application</u>. CI/CD tools allow setting these variables, masking variables such as passwords and account keys, and configuring them at time of deployment for the target environment. CD tools also provide <u>dashboard and reporting functions</u>. If builds or deliveries fail, they alert developers with information on the failures. They integrate with version control and agile tools, so they can be used to look up what <u>code changes and user stories</u> made up a build.

### Kubernetes and serverless architectures

Many teams operating CI/CD pipelines in cloud environments also use **containers such as Docker** and **orchestration systems such as Kubernetes**. Containers allow for <u>packaging and shipping applications in standard, portable ways</u>. Containers make it easy to **scale up or tear down environments** that have variable workloads.

**Serverless computing architectures** present another avenue for deploying and scaling applications. In a serverless environment, the <u>infrastructure is fully managed by the **cloud service provider**</u> and the application **consumes resources as needed** based on its configuration. On AWS for example, serverless applications run as **Lambda functions** and deployments can be integrated into a <u>Jenkins CI/CD pipeline with a plug-in</u>. 

**CI/CD enables more frequent code deployments**
CI is the automation that **packages and tests software builds** and alerts developers if their changes failed any unit tests. CD is the automation that **delivers changes to infrastructure** and executes additional tests. CI/CD pipelines are designed for businesses that want to <u>improve applications frequently and **require a reliable delivery process**</u>. CI/CD enables teams to <u>focus on the process of enhancing applications</u> and <u>less on the system details of delivering it</u> to computing environments.

CI/CD is a **devops best practice** because it <u>addresses the misalignment between developers who want to push changes frequently, with operations that want stable applications</u>. With automation in place, developers can push changes more frequently. Operations teams see greater stability because environments have standard configurations, there is continuous testing in the delivery process, environment variables are separated from the application, and rollback procedures are automated.

**Devops key performance indicator (KPI)** such as **deployment frequency**, change lead time, and **mean time to recovery (MTTR)** from an incident are often improved when CI/CD with continuous testing is implemented. 

### Common pitfalls of CI/CD

**#1: Automating the wrong processes first**
This trap tends to **strike organizations making the shift** from waterfall development to devops. New organizations have the advantage of implementing CI/CD from scratch. Existing companies have to journey <u>gradually from manual to highly automated development</u> with several iterations. You can prioritize the steps in a CI/CD implementation using below checklist:

- How frequently is the process or scenario repeated?
- How long is the process?
- What people and resource dependencies are involved in the process? Are they causing delays in CI/CD?
- Is the process error-prone if it is not automated?
- What is the urgency in getting the process automated?

That said, **you cannot achieve total test automation**. Sometimes you can break down tasks into smaller segments and automate in patches. Sometimes you should simply document the process in detail and execute it manually.

**#2: Confusing continuous deployment for continuous delivery**
Continuous deployment is the concept that every change made in the code base will be **deployed almost immediately to production** if the results of the pipeline are successful. Continuous deployment unsettles most organizations because <u>rapid product changes can scare away users</u>. Companies believe that if they do not practice continuous deployment, they are not doing CD. They fail to distinguish between continuous deployment and continuous delivery.

Continuous delivery is the concept that every change to the code base goes through the pipeline up to the point of **deploying to nonproduction environments**. The team finds and addresses issues immediately, not later when they plan to release the code base. The code base is always at a quality level that is safe for release. When to release the code base to production is a business decision. Continuous delivery gives them control over product rollout, functionality, and risk factors.

**#3: Lack of coordination between continuous integration and continuous delivery**
CI and CD are two different items. **CI feeds CD**. Implementing a decent CI pipeline and a full CD system takes months and requires collaboration. Quality assurance, the devops team, ops engineers, scrum masters—all must contribute. The toughest aspect of CI/CD is this collaboration and communication between these teams rather than any technical challenge.

**#4: Balancing the frequency of running CI jobs and resource utilization**
CI jobs are supposed to be triggered for every change in the code. Successful jobs allow the changes to go through while failures reject the changes. This encourages developers to trigger more builds in a day for each chunks of code. However, **unnecessary CI jobs consume resources**, which wastes time and money.

------

------

# Server Topology

Geometric representation of **how the computers are connected to each other** is known as topology. There are five types of Computer Network Topology – Mesh, Star, Bus, Ring and Hybrid.

<img src="https://github.com/SnowflakeCoder/programming/blob/master/General/images/server%20topology.png?raw=true" alt="server topology.png" style="zoom:70%;" />

**Mesh Topology**
In mesh topology each device is connected to every other device on the network through a dedicated point-to-point link. When we say dedicated it means that the link only carries data for the two connected devices only. Lets say we have n devices in the network then each device must be connected with (n-1) devices of the network. Number of links in a mesh topology of n devices would be n(n-1)/2.

Advantages of Mesh topology
1. **No data traffic issues** as there is a dedicated link between two devices.
2. Mesh topology is **reliable and robust** as failure of one link doesn’t affect other links and the communication between other devices on the network.
3. Mesh topology is **secure** because there is a point to point link thus unauthorized access is not possible.
4. **Fault detection** is easy.


Disadvantages of Mesh topology
1. Amount of wires required to connected each system is **tedious and headache**.
2. Each device needs to be connected with other devices, so **number of I/O ports** required must be huge.
3. **Scalability issues** because a device cannot be connected with large number of devices with a dedicated point to point link.

**Star Topology**
In star topology each device in the network is connected to a **central device called hub**. Unlike Mesh topology, star topology **doesn’t allow direct communication** between devices, a device must have to communicate through hub. If one device wants to send data to other device, it has to first send the data to hub and then the hub transmit that data to the designated device.

Advantages of Star topology
1. **Less expensive** and Less amount of cables required because each device only need one I/O port and needs to be connected with hub with one link.
2. **Easier to install**
3. Robust, if one link fails, other links will work just fine.
5. Easy fault detection because the link can be easily identified.

Disadvantages of Star topology
1. If **hub goes down** everything goes down, none of the devices can work without hub.
2. **Hub requires more resources** and regular maintenance because it is the central system of star topology.

**Bus Topology**
In bus topology there is a **main cable** and all the devices are connected to this main cable through drop lines. There is a device called tap that connects the drop line to the main cable. Since all the data is transmitted over the main cable, there is a limit of drop lines and the distance a main cable can have.

Advantages of bus topology
1. Easy installation, each cable needs to be connected with backbone cable.
2. Less cables required than Mesh and star topology

Disadvantages of bus topology
1. Difficultly in fault detection.
2. **Not scalable** as there is a <u>limit of how many nodes you can connect</u> with backbone cable.

**Ring Topology**
In ring topology each device is connected with the two devices on either side of it using a dedicated point to point link. If a device wants to send data to another device then it sends the data in one direction, each device in ring topology has a repeater, if the received data is intended for other device then repeater forwards this data until the intended device receives it.

Advantages of Ring Topology
1. Easy to install.
2. Managing is easier as to add or remove a device from the topology only two links are required to be changed.

Disadvantages of Ring Topology
1. A **link failure** can fail the entire network as the signal will not travel forward due to failure.
2. **Data traffic issues**, since all the data is circulating in a ring.

Hybrid topology
A **combination of two or more topology** is known as hybrid topology.

Advantages of Hybrid topology
1. We can <u>choose the topology based on the requirement</u> for example, **scalability** is our concern then we can use star topology instead of bus technology.
2. Scalable as we can further connect other computer networks with the existing networks with different topologies.

Disadvantages of Hybrid topology
1. Fault detection is difficult.
2. Installation is difficult.
3. **Design is complex** so maintenance is high thus expensive.

------

------

# Reference

https://www.infoworld.com/article/3271126/what-is-cicd-continuous-integration-and-continuous-delivery-explained.html

https://dzone.com/articles/what-is-cicd

https://www.infoworld.com/article/3113680/5-common-pitfalls-of-cicd-and-how-to-avoid-them.html

https://beginnersbook.com/2019/03/computer-network-topology-mesh-star-bus-ring-and-hybrid/

https://stackify.com/what-is-cicd-whats-important-and-how-to-get-it-right/







