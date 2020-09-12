# Kubernetes (K8S)

Kubernetes is an open-source **Container Management tool** which <u>**automates** container deployment, container scaling, and descaling and container load balancing</u> (also called as **container orchestration tool**). It is written in Golang. Kubernetes can **group ‘n’ number of containers into one logical unit** for <u>managing and deploying them easily</u>. It works brilliantly with all **cloud vendors i.e. public, hybrid and on-premises**.

Kubernetes is an open-source platform that manages Docker containers in the form of a cluster. Along with the automated deployment and scaling of containers, it provides healing by automatically restarting failed containers and rescheduling them when their hosts die. This capability improves the application’s availability.

## Features of Kubernetes –

1. **Automated Scheduling**– Kubernetes provides advanced scheduler to launch container on cluster nodes. It performs resource optimization.
2. **Self-Healing Capabilities**– It provides rescheduling, replacing and restarting the containers which are died.
3. **Automated Rollouts and Rollbacks**– It supports rollouts and rollbacks for the desired state of the containerized application.
4. **Horizontal Scaling and Load Balancing**– Kubernetes can scale up and scale down the application as per the requirements.

## Kubernetes Vs Docker Swarm

DOCKER SWARM	KUBERNETES
No Auto-Scaling	Auto-Scaling
Does Auto Load-Balancing	Manually configure your Load-Balancing settings
It performs rolling updates to containers straightaway	K8S performs rolling updates to Pods as a whole
Share storage volumes with any other containers	Share storage volumes between multiple containers inside the same pods
Its uses 3rd party tools like ELK	K8S provides in-built tools for logging and monitoring

## Architecture of Kubernetes

Kubernetes follows the client-server architecture where we have master installed on one machine and the node on separate Linux machines. It follows the masterslave model, which uses a master to manage Docker containers across multiple Kubernetes nodes. A master and its controlled nodes(worker nodes) constitute a **“Kubernetes cluster”**. A developer can deploy an application in the docker containers via the assistance of the Kubernetes master.

### 1. Kubernetes-Master Node Components

Kubernetes master is responsible for managing the entire cluster, coordinates all activities inside the cluster and communicates with the worker nodes to keep the Kubernetes and your application running. This is the entry point of all administrative tasks. When we install Kubernetes on our system we have four primary components of Kubernetes Master that will get installed. The components of Kubernetes Master node are:

**a.) API Server**– The API server is the entry point for all the REST commands used to control the cluster. All the administrative tasks are done by API server within the master node. If we want to create, delete, update or display in Kubernetes object it has to go through this API server.API server validates and configures the API objects such as ports, services, replication, controllers and deployments and it is responsible for exposing API’s for every operation. We can interact with this API’s using a tool called **kubcetl**.
*‘kubcetl’ is a very tiny go language binary which basically talks to the API server to perform any operations that we issue from the command line. It is a command-line interface for running commands against Kubernetes clusters*

**b.) Scheduler**– It is a service in master responsible for distributing the workload. It is responsible for tracking the utilization of working load of each worker nodes and then placing the workload on which resources are available and can accept the workload. The scheduler is responsible for scheduling pods across available nodes depending on the constraints you mention in the configuration file it schedule these pods accordingly. The scheduler is responsible for workload utilization and allocating pod to new node.

**c.) Controller Manager**– Also known as controllers.It is a daemon which runs in nonterminating loop and is responsible for collecting and sending information to API server. It regulates the kubernetes cluster by performing lifestyle function such as namespace creation and lifecycle event garbage collections, terminated pod garbage collection, cascading deleted garbage collection, node garbage collection and many more. Basically controller watches the desired state of cluster if the current state of the cluster does not meet the desired state then the control loop takes the corrective steps to make sure that the current state is same as that of the desired state. The key controllers are replication controller, endpoint controller, namespace controller, and service account controller. So in this way controllers are responsible for overall health of the entire cluster by ensuring that nodes are up and running all the time and correct pods are running as mentioned in the specs file.

**d.) etcd**– It is a distributed key-value lightweight database. In Kubernetes, it is a central database for storing the current cluster state at any point of time and also used to store the configuration details such as subnets, config maps etc. It is written in Go programming language.

### 2. Kubernetes-Worker Node Components

Kubernetes Worker node contains all the necessary services to manage the networking between the containers, communication with the master node and assign resources to the containers scheduled. The components of Kubernetes Worker node are:

**a.) Kubelet**– It is a primary node agent which communicates with the master node and executes on each worker node inside the cluster. It gets the pod specifications through the API server and execute the container associated with the pods and ensures that the containers described in the pods are running and healthy. If kubelet notices any issues with the pods running on the worker nodes then it tries to restart the pod on the same node and if the issue is with the worker node itself then the Kubernetes master node detects the node failure and decides to recreate the pods on the other healthy node.

**b.) Kube-Proxy**– It is the core networking component inside the kubernetes cluster. It is responsible for maintaining the entire network configuration. Kube-Proxy maintains the distributed network across all the nodes, all the pods and across all the containers and also exposes the services across the outside world. It acts as a network proxy and load balancer for a service on a single worker node and manages the network routing for TCP and UDP packets. It listens to the API server for each service endpoint creation and deletion so for each service endpoint it sets up the route so that you can reach to it.

**c.) Pods**– Pod is a group of containers that are deployed together on the same host. With the help of pods we can deploy multiple dependent containers together so it acts as a wrapper around these containers so we can interact and manage these containers primarily through pods.

**d.) Docker**– Docker is the containerization platform which is used to package your application and all its dependencies together in the form of containers so to make sure that your application works seamlessly in any environment which can be development or test or production. Docker is a tool designed to make it easier to create, deploy, run applications by using containers. Docker is the world’s leading software container platform. It was launched in 2013 by a company called Dotcloud. Its is written in Go language. It has been just six years since Docker was launched yet communities have already shifted to it from VM’s. Docker is designed to benefit both developers and system administrators making it a part of many Devops toolchains. Developers can write code without worrying about the testing and production environment. Sysadmins need not worry about infrastructure as Docker can easily scale up and scale down the number of systems. Docker comes into play at the deployment stage of software development cycle.





# Reference

https://www.geeksforgeeks.org/introduction-to-kubernetes-k8s/