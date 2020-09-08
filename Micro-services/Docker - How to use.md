# Docker - How to use

## Installing Docker on Ubuntu

#### Remove old version of Docker

```
$ sudo apt-get remove docker docker-engine docker.io containerd runc
```

#### Installing Docker Engine

```typescript
// Update Software Repositories using the following command on terminal
$ sudo apt-get update
$ sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
$ sudo apt-key fingerprint 0EBFCD88
$ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable nightly test"
$ sudo apt-get update
// Install Docker using the following command
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
$ docker --version
```

 Check if docker is successfully installed in your system

```
$ sudo docker run hello-world
```

#### Create an application in Docker

1. #### Create a folder with 2 files (Dockerfile and main.py file) in it.

2. #### Edit main.py with the below code.

```python
#!/usr/bin/env python3 
print("Docker and GFG rock!")
```

3. #### Edit Dockerfile with the below commands.

```
FROM python:latest
COPY main.py /
CMD [ "python", "./main.py" ]
```

4. #### Create a Docker image.

Once you have created and edited the main.py file and the Dockerfile, **create your image to contain your application**. The ‘-t’ option allows to define the **name of your image**, ‘python-test’ is the image name here.

```
$ docker build -t python-test .
```

5. #### Run the Docker image

Once the image is created, your code is ready to launch.

```
$ docker run python-test
```

### Push an image to Docker Hub

1. Create an Account on Docker Hub.

2. Click on the “Create Repository” button, put the name of the file, and click on “Create”.

3. Now will “tag our image” and “push it to the Docker Hub repository” which we just created. 

   ```typescript
   // Now, run the below command to list docker images:
   $ docker images
   // The above will give us this result
   REPOSITORY TAG IMAGE_ID CREATED SIZE afrozchakure/python-test latest c7857f97ebbd 2 hours ago 933MB.
   // Image ID is used to tag the image. The syntax to tag the image is: 
   // docker tag <image-id> <your dockerhub username>/python-test:latest
   $ docker tag c7857f97ebbd afrozchakure/python-test:latest
   // Push image to Docker Hub repository
   $ docker push afrozchakure/python-test
   ```


### Fetch and run the image from Docker Hub

Remove the image from our local system and pull the image from Docker Hub if it doesn’t exist locally.

```typescript
// remove all versions of a particular image from our local system, using the Image ID.
$ docker rmi -f af939ee31fdc
// Now run the image, it will fetch the image from the docker hub if it doesn’t exist on local machine.
$ docker run afrozchakure/python-test
```

**Note:** If a backend application is running on docker container at port 8000 and you tried to access it from host machine, you will not be able to access as **containers are self isolated** and in that case you have to **explicitly expose your application at a certain port** and connect your machine port to that port . Here application <u>running at port 8080 in container is connected to port 8000</u> at the host machine. Now it can <u>access application using URL localhost:8000</u>

```typescript
docker run --publish 8000:8080 --detach --name alias_name application_name:1.0 
```







