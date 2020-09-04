# Rest API

## API

An **API** is an **application programming interface**. It is a **set of rules that allow programs to talk to each other**. The developer creates the API on the server and allows the client to talk to it.

## REST

**REST** determines **how the API looks like**. It stands for “**Representational State Transfer**”. It is a set of rules that developers follow when they create their API. One of these rules states that **you should be able to get a piece of data (called a resource)** when you link to a specific URL.

## JSON

JSON (**JavaScript Object Notation**) a common format for sending and requesting data through a REST API. A JSON object looks like a JavaScript Object. In JSON, each property and value must be wrapped with double quotation marks, like this:

```json
{ "property1": "value1",  "property2": "value2",  "property3": "value3"}
```

## The Anatomy Of A Request

A request is made up of four things:

- The endpoint
- The method
- The headers
- The data (or body)

### The Endpoint

The **endpoint** (or route / path) is the url you request for. The **root-endpoint** is the starting point of the API you’re requesting from. The path determines the resource you’re requesting for. 

```bash
root-endpoint/{endpoint}
```

https://www.smashingmagazine.com/tag/javascript/. 

https://www.smashingmagazine.com/ is the root-endpoint and /tag/javascript is the path.

The final part of an endpoint is **query parameters**. Technically, **query parameters are not part of the REST architecture**. Query parameters give you the option to **modify your request with key-value pairs**. They always begin with a question mark (?). Each parameter pair is then separated with an ampersand (&).

```bash
?query1=value1&query2=value2
```

### The Method

The method is the **type of request** you send to the server. These are the five types:

- GET
  - This request is used to **get a resource** from a server. A `GET` request performs a `READ` operation. This is the default request method.
- POST
  - This request is used to **create a new resource** on a server. Server creates a new entry in the database and tells you whether the creation is successful, performs an `CREATE` operation.
- PUT & PATCH
  - These two requests are used to **update a resource** on a server. Server updates an entry in the database and tells you whether the update is successful, performs an `UPDATE` operation.
- DELETE
  - This request is used to **delete a resource** from a server. Server deletes an entry in the database and tells you whether the deletion is successful. A `DELETE` request performs a `DELETE` operation.

These methods **provide meaning for the request** you’re making. They are used to perform four possible actions: Create, Read, Update and Delete (CRUD)

### The Headers

Headers are used to **provide information to both client and server**. It can be used for many purposes, such as **authentication** and providing information about the **body content**. HTTP Headers are **property-value pairs** that are separated by a colon. 

### The Data (Or “Body”)

The data contains information you want to be sent to the server. This option is only used with POST, PUT, PATCH or DELETE requests. If you wish to send JSON data, you’ll need to set the **Content-Type** to **application/json**, and you’ll need to format your data as a JSON object

## Authentication

Developers put measures in place to ensure you perform actions only when you’re authorized to do. This prevents others from impersonating you. Since POST, PUT, PATCH and DELETE requests alter the database, developers almost always put them behind an authentication wall. In some cases, a GET request also requires authentication (like when you access your bank account to check your current balance, for example). On the web, there are **two main ways** to authenticate yourself:

- With a username and password (also called basic authentication)
- With a secret token


The secret token method includes oAuth, which lets you to authenticate yourself with social media networks like Github, Google, Twitter, Facebook, etc.

## HTTP Status Codes And Error Messages

Error messages only appear when something is wrong with your request. HTTP status codes let you tell the status of the response,  whether a specific HTTP request has been successfully completed. HTTP status code is a three-digit code, range from **100+ to 500+**. Responses are grouped in **five classes**:

- 1xxs – Informational responses: The server is thinking through the request.

- 2xxs – Success! The request was successfully completed and the server gave the browser the expected response.
- 3xxs –Redirection: You got redirected somewhere else. The request was received, but there’s a redirect of some kind.
- 4xxs – Client errors: Page not found. The site or page couldn’t be reached. (The request was made, but the page isn’t valid — this is an error on the website’s side of the conversation and often appears when a page doesn’t exist on the site.)
- 5xxs – Server errors: Failure. A valid request was made by the client but the server failed to complete the request.

### Important HTTP status codes

- **Status code 200 – OK**: The HTTP status code 200 shows that the request was successfully carried out. All the requested data was located on the web server and transferred to the client.
- **Status code 201 – Created**: The request has successfully executed and a **new resource has been created in the process**. The response body is either empty or contains a representation containing URIs for the resource created. The Location header in the response should point to the URI as well.
- **Status code 202 –Accepted**: The request was valid and has been accepted but has not yet been processed. The response should **include a URI to poll for status updates** on the request. This allows **asynchronous REST requests**
- **Status code 204 – No Content**: The request was successfully processed but the server did not have any response. The client should not update its display.
- **Status code 301 – Moved Permanently**: The 301 code means that the data requested from the client cannot be found under the given address since it has been moved permanently. Since the current location of the requested content is delivered in the status report, the browser can request the new address straightaway. The user is then forwarded to the new address and the old address is no longer valid. The 301 code also <u>goes unnoticed because the URL in the address bar simply changes</u>.
- **Status code 302 – Moved Temporarily**: Unlike the 301 code, which is a permanent redirection, the 302 informs the user that the requested data has temporarily been moved. With a 302 code the remaining information is specified so that an **automatic redirection** can take place. The old address remains valid.
- **Status code 403 – Forbidden**: The HTTP status code 403 tells the client that the requested data is access-protected and that the request cannot be performed due to the client not having authority. An automatically generated HTML page will let the user know about the access problem.
- **Status code 404 – Not Found**: If the server delivers a 404 message it means that the requested website information was not found on the server. It could be that the address no longer exists or the contents were moved to a new address without notice. Users that receive a 404 message should check whether the address was written correctly in the address bar. Any links to non-existing pages are known as ‘dead links’.
- **Status code 500 – Internal Server Error**: The 500 server response functions as a collection status code for unexpected server errors. If an error occurs on the server’s part, which prevents the requested data from being retrieved, this HTTP status code will automatically be issued. This should be analyzed by the website owner so that repairs can be carried out on the server software.
- **Status code 503 ­– Service Unavailable**: If the user receives a 503 code it means that the relevant web server, which should deliver the requested information, is overloaded. The server response occasionally contains information about **when the request can be processed at the earliest**. Internet users can presume that an administrator is working on the problem and that the server will be available later on.
- HTTP 400 Bad Request
- HTTP 501 Not Implemented
- HTTP 502 Bad Gateway
- HTTP 504 Gateway Timeout



## API Versions

Sometimes, the API can change so much that the developer decides to **upgrade their API to another version**. If this happens, and your application breaks, it’s usually because you’ve written code for an older API, but your request points to the newer API. You can request for a specific API version in two ways. Which way you choose **depends on how the API is written**. These two ways are:

- Directly in the endpoint
- In a request header

## Resource

The **key abstraction of information** in REST is a resource. Any information that can be named can be a resource: a document or image, a temporal service, and so on. REST uses a **resource identifier** to identify the particular resource involved in an interaction between components. 

The **state of the resource** at any particular timestamp is known as **resource representation**. A representation consists of data, metadata describing the data and hypermedia links which can help the clients in transition to the next desired state.

The **data format** of a representation is known as a **media type**. The media type identifies a specification that defines how a representation is to be processed. A truly RESTful API looks like hypertext. Every addressable unit of information carries an address, either explicitly (e.g., link and id attributes) or implicitly (e.g., derived from the media type definition and representation structure). Resource representations shall be **self-descriptive**: the client does not need to know if a resource is employee or device. It should **act on the basis of media-type** associated with the resource. So in practice, you will end up creating lots of custom media-types – normally **one media-type associated with one resource**.

In the REST architectural style, **data and functionality are considered resources** and are accessed using Uniform Resource Identifiers (URIs). The resources are <u>acted upon by using a set of simple, well-defined operations</u>. The **clients and servers exchange representations** of resources by <u>using a standardized interface and protocol</u> – typically HTTP.

**Resources are decoupled from their representation** so that their content can be accessed in a variety of formats, such as HTML, XML, plain text, PDF, JPEG, JSON, and others. Metadata about the resource is available and used, for example, to control caching, detect transmission errors, negotiate the appropriate representation format, and perform authentication or access control. And most importantly, every interaction with a resource is stateless. All these principles help RESTful applications to be simple, lightweight, and fast.

## What is a REST API?

When you’re designing an API, it’s important to know the **type of API you want** for your specific project and what its advantages and disadvantages are. **REST or RESTful APIs** are designed to take **advantage of existing protocols** like HTTP. Therefore, developers **don’t need to install additional software or libraries** when creating a REST API. REST APIs provide a great deal of **flexibility**, **Data is not tied to resources or methods**, so REST can **handle multiple types of calls**, return **different data formats** and even change structurally with the correct implementation of hypermedia.

## 6 Guiding Principles of REST

REST have 6 guiding constraints which must be satisfied **if an interface needs to be referred as RESTful**.

- **Client–server**
  - **client and the server should be separate** from each other and allowed to evolve individually.
  - By <u>separating the UI concerns from the data storage concerns</u>, we improve the portability of the UI across multiple platforms and improve scalability by simplifying the server components.
- **Stateless**
  - REST APIs are stateless, meaning that calls can be made independently of one another. 
  - Each request from client to server must contain **all of the information necessary** to understand the request, and cannot take advantage of any stored context on the server. <u>Session state is therefore kept entirely on the client</u>.
- **Cacheable** – Cache constraints require that the data within a response to a request be **implicitly or explicitly labeled as cacheable or non-cacheable**. If a response is cacheable, then a client cache is given the right to reuse that response data for later, equivalent requests.
- **Uniform interface** – By applying the software engineering principle of generality to the component interface, the overall system architecture is simplified and the visibility of interactions is improved. In order to obtain a uniform interface, multiple architectural constraints are needed to guide the behavior of components. REST is defined by four interface constraints: identification of resources; manipulation of resources through representations; self-descriptive messages; and, hypermedia as the engine of application state.
- **Layered system** – The layered system style allows an architecture to be **composed of hierarchical layers** by constraining component behavior such that each component cannot “see” beyond the immediate layer with which they are interacting.
- **Code on demand** (optional) – REST allows client functionality to be extended by **downloading and executing code in the form of applets or scripts**. This simplifies clients by reducing the number of features required to be pre-implemented.

**Unlike SOAP, REST is not constrained to XML**, but instead can return XML, JSON, YAML or any other format depending on what the client requests. And **unlike RPC**, users aren’t required to know procedure names or specific parameters in a specific order.

The **disadvantage** to RESTful APIs is that you can **lose the ability to maintain state in REST**, such as within sessions.

------

The REST architectural style is a worldview that **elevates information into a first-class element of architectures**. REST allows us to **achieve the architectural properties** of performance, scalability, generality, simplicity, modifiability, and extensibility. 

What does **Representational State Transfer mean?** Transferring, accessing, and manipulating textual data representations in a stateless manner. A RESTful API service is exposed through a Uniform Resource Locator (URL). This logical name separates the identity of the resource from what is accepted or returned. **The URL functions as a handle for the resource**, something that can be requested, updated, or deleted.

A sample RESTful URL : **http://fakelibrary.org/library**

What is actually exposed is not necessarily an arbitrary service, however, but an information resource representing something of value to a consumer. To request the resource, a client would issue a HTTP GET request to retrieve it. This will return the **default representation**. HTTP has a mechanism by which you can ask for information in a different form. By specifying an “Accept” header in the request, if the server supports that representation, it will return it. This is known as **content negotiation**. This ability to ask for information in different forms is possible because of the **separation of the name of the resource from its form**.

```bash
$ curl –H “Accept:application/json” http://fakelibrary.org/library
```

To add a new book, we POST an XML representation to the main **/book** API. At this point, the resource on the server might validate the results, create the data records associated with the book and return a **201 response code** indicating that a new resource has been created. The URL for the new resource can be discovered in the Location header of the response. An important aspect of a RESTful request is that **each request contains enough state to answer the request**. We can also put a **protection model** in place that requires **users to authenticate** and prove that they are allowed to do something before we allow them to. 

# What About SOAP?

REST and SOAP not the same thing. REST is best used to manage systems by **decoupling the information that is produced and consumed** from the technologies that produce and consume it. 
SOAP is best leveraged when the lifecycle of a request cannot be maintained in the scope of a single transaction because of technological, organizational, or procedural complications.



# References

[https://dzone.com/articles/what-is-a-restful-api#:~:text=REST%20APIs%20provide%20a%20great,the%20correct%20implementation%20of%20hypermedia.](https://dzone.com/articles/what-is-a-restful-api#:~:text=REST APIs provide a great,the correct implementation of hypermedia.)

[https://www.mulesoft.com/resources/api/restful-api#:~:text=One%20of%20the%20key%20advantages,the%20correct%20implementation%20of%20hypermedia.](https://www.mulesoft.com/resources/api/restful-api#:~:text=One of the key advantages,the correct implementation of hypermedia.)

https://www.smashingmagazine.com/2018/01/understanding-using-rest-api/

