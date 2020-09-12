# JSON Web Token (JWT)

A **JSON Web Token (JWT)** is an open standard ([RFC 7519](https://tools.ietf.org/html/rfc7519)) that defines a compact and self-contained way for <u>securely transmitting information between parties as a **JSON object**</u>. This information can be verified and trusted because it is **digitally signed**. <u>JWTs can be signed using a **secret** (with the **HMAC** algorithm) or a **public/private key** pair (**RSA** or **ECDSA**)</u>.

Although JWTs can be encrypted to also provide secrecy between parties, we will focus on *signed* tokens. Signed tokens can verify the **integrity of the claims** contained within it, while encrypted tokens *hide* those claims from other parties. When tokens are signed using public/private key pairs, the <u>signature also certifies that only the party holding the private key is the one that signed it</u>.

## When should you use JWT?

- **Authorization**: This is the most common scenario for using JWT. Once the user is logged in, each subsequent request will include the JWT, allowing the user to access routes, services, and resources that are permitted with that token. <u>**Single Sign On** is a feature that widely uses JWT</u>.
- **Information Exchange**: JSON Web Tokens are a good way of securely transmitting information between parties. Because JWTs can be signed, you can be sure the senders are who they say they are. Additionally, as the signature is calculated using the header and the payload, you can also verify that the content hasn't been tampered with.

## JWT Structure

- ### Header

  - The header *typically* consists of **two parts**: the **type of token**, which is JWT, and the **signing algorithm** that is used, such as HMAC SHA256 or RSA.

- ### Payload

  - payload contains the claims. **Claims are statements about an entity**.
    - **Registered claims**: These are a set of **predefined claims** which are not mandatory but recommended, to provide a set of useful, interoperable claims.
    - **Public claims**: These can be defined at will by those using JWTs. 
    - **Private claims**: These are the <u>**custom claims** created to share information between parties</u> that agree on using them and are neither *registered* or *public* claims.

- ### Signature

  - To create the signature part, you have to <u>take the encoded header, the encoded payload, a secre</u>t, the algorithm specified in the header, and sign that.

## How do JWT work?

In authentication, when the user successfully logs in using their credentials, a JSON Web Token will be returned. Since **tokens are credentials**, great care must be taken to **prevent security issues**. In general, you should not keep tokens longer than required. Whenever the <u>user wants to access a protected route or resource</u>, the user agent should send the JWT, typically in the Authorization header using the Bearer schema. The <u>server's protected routes will check for a valid JWT</u> in the `Authorization` header, and if it's present, the user will be allowed to access protected resources.

## Why should we use JWT?

Let's talk about the <u>benefits of JSON Web Tokens (JWT)</u> when compared to <u>Simple Web Tokens (SWT)</u> and <u>Security Assertion Markup Language Tokens (SAML)</u>.

As JSON is less verbose than XML, when it is encoded its size is also smaller, making **JWT more compact than SAML**. Security-wise, SWT can only be symmetrically signed by a shared secret using the HMAC algorithm. However, JWT and SAML tokens can use a public/private key pair in the form of a X.509 certificate for signing. Signing XML with XML Digital Signature without introducing obscure security holes is very difficult when compared to the simplicity of signing JSON.

<u>JSON parsers are common in most programming languages</u> because they map directly to objects. Conversely, XML doesn't have a natural document-to-object mapping. This makes it easier to work with JWT than SAML assertions.

## Reference

https://jwt.io/introduction/



# Spring OAuth2 With JWT Sample

Let's explore a use case of <u>setting up **OAuth 2 authentication** for a Spring Boot application</u>. In this example, we will use **JSON Web Token (JWT) as the format** of the Oauth2 token.

You may want to adopt **OAuth** if you need to <u>allow other people to build a front end app for your services</u>. We focus on OAuth 2 and JWT because they are the <u>most popular authentication framework and protocol</u> in the market.

## Spring Security OAuth 2

<u>**Spring Security** is an extensible framework for authentication</u>, and **Spring Security OAuth 2** is an <u>implementation of OAuth 2 that is built on top of Spring Security</u>.

 Spring Security includes **2 basic steps**: <u>creating an authentication object for each request</u>, and <u>applying the check depending on the configured authentication</u>. The first step is done in a multi-layer Security Filter. Depending on the configuration, each layer can help to create authentication, including basic authentication, digest authentication, form authentication, or any custom authentication that we choose to implement ourselves. Spring Security OAuth 2 is a custom authentication.





# Spring Boot Security - Introduction to OAuth2

OAuth (Open Authorization) is a simple way to **publish and interact with protected data**.
It is an open standard for **token-based authentication and authorization** on the Internet. It allows an end user's account information to be used by third-party services, such as Facebook, without exposing the user's password.
The OAuth specification describes five grants for acquiring an access token:

- Authorization code grant
- Implicit grant
- Resource owner credentials grant
- Client credentials grant
- Refresh token grant





## References

https://dzone.com/articles/spring-oauth2-with-jwt-sample

http://self-issued.info/docs/draft-jones-json-web-token-01.html#anchor3

https://www.javainuse.com/spring/jwt

https://www.javainuse.com/spring/spring-boot-oauth-introduction