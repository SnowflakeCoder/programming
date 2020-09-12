# Docker Related Components

## Virtualization

Virtualization is the process of creating a **software-based, virtual version of something**(computer storage, servers, application, etc.). These virtual versions or environments are **created from a single physical hardware system**. Virtualization lets you **split one system into many different sections** which <u>act like separate, distinct individual systems</u>. A **software called Hypervisor** makes this kind of splitting possible. The virtual environment created by the hypervisor is called **Virtual Machine**.

## Hypervisor 

A hypervisor is a software that makes virtualization possible. It is also called **Virtual Machine Monitor**. It divides the host system and <u>allocates the resources to each divided VM</u>. You can basically have multiple OS on a single host system. There are **two types of Hypervisors**:

- Type 1: It’s also called **Native Hypervisor or Bare metal Hypervisor**. It runs directly on the underlying host system. It has <u>direct access to your host’s system hardware</u> and hence does **not require a base server operating system**.
- Type 2: This kind of hypervisor makes use of the **underlying host operating system**. It’s also called **Hosted Hypervisor**.

## Containerization

Usually, in the software development process, <u>code developed on one machine might not work perfectly fine on any other machine</u> because of the dependencies. This problem was <u>solved by the containerization</u> concept. So basically, an application that is being developed and deployed is **bundled and wrapped together with all its configuration files and dependencies**. This bundle is called a **container**. Now when you wish to run the application on another system, the container is deployed which will **give a bug-free environment** as all the dependencies and libraries are wrapped together. <u>Most famous containerization environments are Docker and Kubernetes</u>.

**Containerization is OS-based virtualization** which creates multiple virtual units in the user-space, known as Containers. Containers **share the same host kernel** but are <u>isolated from each other through **private namespaces and resource control mechanisms** at the OS level</u>. Containers provide an isolated environment for running the application. The entire <u>user space is explicitly dedicated to the application</u>. Any changes made inside the container is never reflected on the host or even other containers running on the same host.

Hypervisors use lot of hardware which results in overhead in terms of <u>virtualizing hardware and virtual device drivers</u>. A full operating-system run on top of this virtualized hardware in each virtual machine instance. But in contrast, containers implement **isolation of processes at the OS level**, thus avoiding such overhead. These containers run on top of the **same shared OS kernel** of the underlying host machine and one or more processes can be run within each container. In containers you don’t have to pre-allocate any RAM, it is **allocated dynamically during creation of containers** while **in VM’s you need to first pre-allocate the memory** and then create the virtual machine. 

## Virtualization Vs containerization

**Or virtual machines Vs containers**

#### Docker Containers

- Docker Containers contain binaries, libraries, and configuration files along with the application itself.
- They <u>don’t contain a guest OS for each container</u> and rely on the underlying OS kernel, which makes the **containers lightweight**.
- Containers <u>**share resources** with other containers in the same host OS</u> and provide **OS-level process isolation**.
- Containerization has **better resource utilization** compared to VMs and short boot-up process.
- Containers are an **abstraction of the application layer**. Each container is a different application.

#### Virtual Machines

- Virtual Machines (VMs) run on **Hypervisors**, which allow multiple Virtual Machines to run on a single machine along with **its own operating system**.
- Each VM has its own copy of an operating system along with the application and necessary binaries, which makes it significantly larger and it requires more resources.
- They provide **Hardware-level process isolation** and are slow to boot.
- Virtual machines are an **abstraction of the hardware layer**. Each VM is a physical machine.



