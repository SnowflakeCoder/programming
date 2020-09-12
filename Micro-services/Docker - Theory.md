# Docker

Docker is a set of **platforms as a service (PaaS)** products that use the OS-level level virtualization to deliver software in packages called **containers**. Containers are <u>isolated from one another and bundle their own software</u>, libraries, and configuration files; they can <u>communicate with each other through well-defined channels</u>. All containers are **run by a single operating system kernel** and therefore use fewer resources than a virtual machine. **Docker is written in Go language**. Docker can be installed in two versions of Docker CE(Community Edition) and Docker EE(Enterprise Edition). For small-scale projects, or for learning, we can use Docker CE.

Docker is the **containerization platform** which is used to package your application and all its dependencies together in the **form of containers** so to make sure that your <u>application works seamlessly in any environment</u> which can be development or test or production. Docker is a tool designed to make it easier to create, deploy, run applications by using containers.

## Docker Architecture

Docker architecture consists of **Docker client**, **Docker Daemon** running on Docker Host and **Docker Hub** repository. Docker has client-server architecture in which <u>client communicates with the Docker Daemon running on the Docker Host using **combination of REST API’s**, Socket IO and TCP</u>. If we have to <u>build the Docker image, then we use the client to execute build command to Docker Daemon</u> then Docker Daemon build an image based on given inputs and **save into Docker registry**. If you don’t want to create an image then just execute the **pull command** from the client and then Docker Daemon will pull the image from the Docker Hub and finally if we want to run the image then execute the **run command** from the client which will create the container.



## Components of Docker

The main components of Docker include – Docker clients and servers, Docker images, Dockerfile, Docker Registries and Docker containers. These components are explained in details in the below section :

1. **Docker Clients and Servers**– Docker has a client-server architecture. The Docker Daemon/Server consists of all containers. The Docker Daemon/Server receives the request from the Docker client through CLI or REST API’s and thus process the request accordingly. Docker client and Daemon can be present on the same host or different host.

1. **Docker Images**– Docker images are used to build docker containers by using a read-only template. The foundation of every image is a base image for eg. base images such as- ubuntu14.04 LTS, Fedora 20. Base images can also be created from scratch and then required applications can be added to the base image by modifying it thus this process of creating a new image is called “committing the change”.
2. **Docker File**– Dockerfile is a text file that contains a series of instructions on how to build your Docker image. This image contains all the project code and its dependencies. The same Docker image can be used to spin ‘n’ number of containers each with modification to the underlying image. The final image can be uploaded to Docker Hub and share among various collaborators for testing and deployment. The set of commands that you need to use in your Docker File are FROM, CMD, ENTRYPOINT, VOLUME, ENV and many more.
3. **Docker Registries**– Docker Registry is a storage component for Docker images. We can store the images in either public/private repositories so that multiple users can collaborate in building the application. Docker Hub is Docker’s own cloud repository. Docker Hub is called a public registry where everyone can pull available images and push their own images without creating an image from scratch.
4. **Docker Containers**– Docker Containers are runtime instance of Docker image. Containers contain the whole kit required for an application, so the application can be run in an isolated way. For eg.- Suppose there is an image of Ubuntu OS with NGINX SERVER when this image is run with docker run command, then a container will be created and NGINX SERVER will be running on Ubuntu OS.



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

# Docker Compose

Docker Compose is a tool with which we can create a **multi-container application**. It makes <u>easier to configure and run applications made up of multiple containers</u>. For example, if your application required WordPress and MySQL, you could <u>create one file which would start both the containers</u> as a service without the need to start each one separately. We define a **multi-container application in a YAML file**. With the **docker-compose up command**, we can start the application in the foreground. Docker-compose will look for the `docker-compose.yaml` file in the current folder to start the application. By adding the -d option to the docker-compose up command, we can start the application in the background. Creating a docker-compose.yaml file for WordPress application :

```
#cat docker-compose.yaml
version: ’2’
services:
db:
image: mysql:5.7
volumes:db_data:/var/lib/mysql
restart: always
environment:
MYSQL_ROOT_PASSWORD: wordpress
MYSQL_DATABASE: wordpress
MYSQL_USER: wordpress
MYSQL_PASSWORD: wordpress
wordpress:
depends_on:
- db
image: wordpress:latest
ports:
- "8000:80"
restart: always
environment:
WORDPRESS_DB_HOST: db:3306

WORDPRESS_DB_PASSWORD: wordpress
volumes:
db_data:
```

In this docker-compose.yaml file, ports section for the WordPress container means that we are going to map the **host’s 8000 port with the container’s 80 port**. So that host can access the application with its IP and port no.

# Docker Networks

When we create and run a container, **Docker by itself assigns IP address to it**, by default. Most of the time, it is required to create and deploy Docker networks as per our needs. So, <u>Docker let us design the network as per our requirements.</u> There are **three types of Docker networks**- default networks, user-defined networks and overlay networks.

There are three types of networks in Docker –

1. **Bridged network**: When a new Docker container is created **without the –network argument**, <u>Docker by default connects the container with the bridge network</u>. In bridged networks, all the containers in a single host can connect to each other through their IP addresses. **Bridge network is created when the span of Docker hosts is one** i.e. when all containers run on a single host. We <u>need an **overlay network** to create a network that has a span of more than one Docker host</u>.
2. **Host network**: When a new Docker container is created with the **–network=host** argument it pushes the container into the host network stack where the Docker daemon is running. All interfaces of the host are accessible from the container which is assigned the host network.
3. **None network**: When a new Docker container is created with the **–network=none** argument it puts the Docker container in **its own network stack**. So, in this none network, **no IP addresses** are assigned to the container, because of which <u>they cannot communicate with each other</u>.

To get list of all the default networks that Docker creates, we run the command shown below –

```typescript
$ docker network ls

//We can assign any one of the networks to the Docker containers. The --network option of the ‘docker run’ command is used to assign a specific network to the container.
$docker run --network ="network name"

//To get detailed information about a particular network we use the command-
$docker network inspect "network name"
```

## Advantages of Docker

1. **Speed** – The speed of Docker containers **compared to VM is very fast**. The time required to build a container is very fast because they are really **small and lightweight**. Development, testing, and deployment can be done faster as containers are small.
2. **Portability** – The applications that are built inside docker containers are **extremely portable**. These portable applications can **easily be moved anywhere** as a single element and their performance also remains the same.
3. **Scalability** – Docker has the ability that it can be <u>deployed in several physical servers</u>, data servers, and cloud platforms. It can also be run on every Linux machine.
4. **Density** – Docker uses the resources that are available more efficiently because it **does not use a hypervisor**. This is the reason that **more containers can be run on a single host** as compared to virtual machines. Docker Containers have higher performance because of their **high density and no overhead wastage of resources**.







# References

https://www.geeksforgeeks.org/introduction-to-docker/

https://www.geeksforgeeks.org/difference-between-docker-image-and-container/?ref=rp

https://www.geeksforgeeks.org/difference-between-kubernetes-and-docker-swarm/

https://www.geeksforgeeks.org/containerization-using-docker/

https://www.geeksforgeeks.org/virtualisation-with-docker-containers/