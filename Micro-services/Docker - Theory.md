# Docker

Docker is a set of **platforms as a service (PaaS)** products that use the OS-level level visualization to deliver software in packages called **containers**. Containers are <u>isolated from one another and bundle their own software</u>, libraries, and configuration files; they can <u>communicate with each other through well-defined channels</u>. All containers are **run by a single operating system kernel** and therefore use fewer resources than a virtual machine. **Docker is written in Go language**. Docker can be installed in two versions of Docker CE(Community Edition) and Docker EE(Enterprise Edition). For small-scale projects, or for learning, we can use Docker CE.

Docker is the **containerization platform** which is used to package your application and all its dependencies together in the **form of containers** so to make sure that your <u>application works seamlessly in any environment</u> which can be development or test or production. Docker is a tool designed to make it easier to create, deploy, run applications by using containers.

## Containerization

**Containerization is OS-based virtualization** which creates multiple virtual units in the userspace, known as Containers. Containers **share the same host kernel** but are <u>isolated from each other through **private namespaces and resource control mechanisms** at the OS level</u>. 

Container-based Virtualization provides a different level of abstraction in terms of virtualization and isolation when compared with hypervisors. Hypervisors use lot of hardware which results in overhead in terms of <u>virtualizing hardware and virtual device drivers</u>. A full operating-system run on top of this virtualized hardware in each virtual machine instance. But in contrast, containers implement **isolation of processes at the OS level**, thus avoiding such overhead. These containers run on top of the **same shared OS kernel** of the underlying host machine and one or more processes can be run within each container. In containers you don’t have to pre-allocate any RAM, it is **allocated dynamically during creation of containers** while **in VM’s you need to first pre-allocate the memory** and then create the virtual machine. Containerization has better resource utilization compared to VMs and short boot-up process.

## Docker Architecture

Docker architecture consists of Docker client, Docker Daemon running on Docker Host and Docker Hub repository. Docker has client-server architecture in which client communicates with the Docker Daemon running on the Docker Host using combination of REST API’s, Socket IO and TCP. If we have to build the Docker image, then we use the client to execute build command to Docker Daemon then Docker Daemon build an image based on given inputs and save into Docker registry. If you don’t want to create an image then just execute the pull command from the client and then Docker Daemon will pull the image from the Docker Hub and finally if we want to run the image then execute the run command from the client which will create the container.

### Difference between Docker Containers and Virtual Machines

#### Docker Containers

- Docker Containers contain binaries, libraries, and configuration files along with the application itself.
- They <u>don’t contain a guest OS for each container</u> and rely on the underlying OS kernel, which makes the **containers lightweight**.
- Containers <u>**share resources** with other containers in the same host OS</u> and provide **OS-level process isolation**.

#### Virtual Machines

- Virtual Machines (VMs) run on **Hypervisors**, which allow multiple Virtual Machines to run on a single machine along with **its own operating system**.
- Each VM has its own copy of an operating system along with the application and necessary binaries, which makes it significantly larger and it requires more resources.
- They provide **Hardware-level process isolation** and are slow to boot.

### Important Terminologies in Docker

- #### Docker Image

  - It is a file, comprised of multiple layers, used to **execute code in a Docker container**.
  - They are a **set of instructions** used to create docker containers.

- #### Docker Container

  - It is a **runtime instance of an image**. Allows developers to package applications with all parts needed such as libraries and other dependencies.

- #### Docker file

  - It is a text document that contains **necessary commands** which on execution **helps assemble a Docker Image**. **Docker image is created using a Docker file**.

- #### Docker Engine

  - The **software that hosts the containers** is named Docker Engine. Docker Engine is a **client-server based application**. The docker engine has **3 main components**:
    - **Server**: It is responsible for **creating and managing Docker images**, containers, networks, and volumes on the Docker. It is referred to as a **daemon process**.
    - **REST API**: It specifies <u>how the applications can interact with the Server</u> and instructs it what to do.
    - **Client**: The Client is a **docker command-line interface (CLI),** that allows us to interact with Docker using the docker commands.

- #### Docker Hub
  - Docker Hub is the **official online repository** where you can find other Docker Images that are available for use. It makes it easy to find, manage, and **share container images with others**.

### Docker Image

The concept of **Images and Container is like class and object** in which object is instance of class and class is blue print of object. So **Image is Blueprint of the Container**. Images are **different in Virtual Machines and Docker**, in VMs images are just **snapshots of running VM** at different point of times but **Docker images are immutable** that is they can not be changed. In the real-world, a software works on one computer but may not works on others due to different environments, this issue is completely solved by docker images and using this, **application will work same on everyone’s PC** . <u>Development instance is exactly same as testing or production instance</u>. Also Developers around World can **share their Docker Images** on a Platform called **Docker HUB**.

### Docker Container

They are actually **Docker Virtual Machine** but commonly called as Docker Containers . Docker containers are **runnable instance of an image**. You can create, start, stop, move, or delete a container using Docker API or CLI. You can connect a container to one or more networks, attach storage to it, or even create a new image based on its current state. An application runs using a **cluster of containers** which are **self isolated from one another** and also from host machine where they are running. If a backend application is running on docker container at port 8000 and you tried to access it from host machine, you will not be able to access as **containers are self isolated** and in that case you have to **explicitly expose your application at a certain port** and connect your machine port to that port.

# Image vs Container

1.  **Image is Blueprint of the Container**.	Container is instance of the Image.
2. Image is a logical entity.	Container is a real world entity.
3. **Image is created only once**.	Containers are created any number of times using image.
4. **Images are immutable**.	Containers changes only if old image is deleted and new is used to build the container.
5. Images does not require computing resource to work.	<u>Containers requires computing resources to run as they run as Docker Virtual Machine</u>.
6. To make a docker image, you have to **write script in Dockerfile**.	To make container from image, you have to run “**docker build .**” command.
7. Docker Images are used to package up applications and pre-configured server environments.	Containers use server information and file system provided by image in order to operate.
8. There is **no running state** for Docker Image. Containers uses RAM when created and in running state.

# Diff B/W Kubernetes and Docker Swarm

Containers are brilliant at **packaging and holding** all of application codes, dependencies, libraries, and necessary configurations in a way that you can run it anywhere easily. But containers themselves <u>cannot do things like: load balancing, provisioning hosts, distributing containers across multiple servers, scaling up and down of a cluster</u>, etc. So **concept of containerization platforms (container orchestration)** comes into picture.

**Google Kubernetes** : Kubernetes is Google’s open-source, portable, and extensible take on **container orchestration system** for managing and dealing with containerized workloads that **facilitate both declarative configuration and automation**.

**Docker Swarm** : Docker Swarm is a **group of physical/virtual machines** that are meant for running Docker application and have been configured with perspective of **joining together to form a cluster**. Docker Swarm is **native clustering tool for Docker**. It lets you **manage and organize a cluster of Docker nodes**, and allows you to interact with them like a single system. It provides optimality for your IT resources by scheduling containers to run on aptest host, **balancing workloads** to make sure that **containers are loaded/launched where there are enough resources**. All of this is done while maintaining performance standards that you define.

**Differences between Kubernetes and Docker Swarm :**

1.	**Installation:** Installation of Kubernetes requires a series of manual steps and configurations to tie together <u>its components such as etcd, flannel and docker engine</u>.	Docker installation is easy,  a **one-liner command** is available on package manager system on OS you are using.
3.	**API**: It uses its own **unique client, API and YAML** definitions each differing from that of standard Docker equivalents.	Swarm API provides much of familiar/known functionalities from Docker, but functionalities provided are limited and so it does not fully encompass all of it’s constituting commands.
4.	**Deployment Speed** : Its complexity stems offer a unified set of APIs and facilitate **strong guarantees to cluster states** but at **expense of speed**, due to which, **container deployment and scaling is slower**.	On other hand, **Swarm provides faster container deployment** even in very large clusters and due to this high cluster fill stages support faster reaction times to scaling on demand.
5.	**Load balancing**: It enables/facilitates load balancing through manual service configuration, ie. <u>**containers pods** need to be defined as services for load balancing</u>.	It provides **built-in/automated internal load balancing** through connections between any node and container in cluster.
6.	**Updates** : It **handles updates progressively**. Each container is updated one by one(one at a time) to make sure **availability of service is at all times**. If <u>something goes wrong during updation, a working version will be rolled back automatically</u>.	In the case of Docker, it can apply updates to nodes incrementally. If anything goes wrong, you can easily roll back to previous working version of service.
7.	It volumes serves as an abstraction to allow containers (volumes) to be created and removed together with pod they are enclosed in. <u>Containers share data within the same pod</u>.	Its data volumes are basically created separately or together with containers, so that they can be **shared between multiple containers**.
8.	It supports multiple types of logging/monitoring services like Elasticsearch/Kibana (ELK) logs within container, Heapster/Grafana/Influx, Sysdig cloud integration and Node health.	It mainly supports **monitoring services with third-party tools** such as Reimann.
9.	It mainly **uses flannel to setup it’s container networking**. **TLS authentication** requires manual configuration for security purposes.	It facilitates multi-host ingress network overlay for container networking, running on all cluster nodes. **TLS authentication is automatically configured**.
10.	It relies on **etcd** and containers that are manually defined as services for discovery. A DNS server is a strongly recommended add-on to watch Kubernetes API.	It makes service discovery relatively easier. By default, **containers are given their own unique IPs**, which allows them to communicate through virtual private IP addresses in cluster-specific ingress network.
11.	It supports a more **complex, flexible architecture** with **stronger service guarantees** due to which **performance slows down**.	On other hand, supports a simpler architecture, so in terms of **sheer speed**, it always has an added advantage.





# References

https://www.geeksforgeeks.org/introduction-to-docker/

https://www.geeksforgeeks.org/difference-between-docker-image-and-container/?ref=rp

https://www.geeksforgeeks.org/difference-between-kubernetes-and-docker-swarm/



