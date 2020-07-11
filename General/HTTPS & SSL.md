- [HTTPS](#https)
  
  * [Communication over https and http](#communication-over-https-and-http)
  * [Secure Socket Layer (SSL)](#secure-socket-layer--ssl-)
    + [Advantage of https](#advantage-of-https)
    
    + [How SSL works?](#how-ssl-works-)
      - [Asymmetric Cryptography](#asymmetric-cryptography)
      - [Symmetric Cryptography](#symmetric-cryptography)
      - [SSL Communication](#ssl-communication)
      - [Public Key Infrastructure(PKI)](#public-key-infrastructure-pki-)
      
    + [What is the SSL Certificate?](#what-is-the-ssl-certificate-)
      
      - [X.509](#x509)
      
    + [Types of SSL Certificates](#types-of-ssl-certificates)
      - [Based on Validation Level](#based-on-validation-level)
      - [Based on the Number of Domains they Secure](#based-on-the-number-of-domains-they-secure)
      
    + [How to Get an SSL Certificate?](#how-to-get-an-ssl-certificate-)
      - [Buying an SSL Certificate](#buying-an-ssl-certificate)
      - [Certificate Signing Request (CSR)](#certificate-signing-request--csr-)
        * [Generate a CSR](#generate-a-csr)
        * [Public Key and Private Key](#public-key-and-private-key)
      
    + [SSL Certificate Formats](#ssl-certificate-formats)
    
    + [Install an SSL Certificate](#install-an-ssl-certificate)
    
    + [OpenSSL](#openssl)
    
      

------

------



## HTTPS

HTTPS stands for **Hyper Text Transfer Protocol Secure**. It is a protocol for **securing the communication between two systems**. These can be browser to server, server to server or client to server. Https **uses SSL/TLS technology** to secure the communication between the two systems.

### Communication over https and http

**http** transfers data between the browser and the web server in the **hypertext format**, whereas https transfers data in the **encrypted format**. Thus, https **prevents hackers from reading and modifying the data** during the transfer between the browser and the web server. Even if hackers manage to intercept the communication, they will not be able to use it because the message is encrypted.

HTTPS establish an **encrypted link** between the two systems to secure the communication by transferring encrypted data, using the **Secure Socket Layer (SSL)** or **Transport Layer Security (TLS)** protocols. **TLS is the new version of SSL**.

### Secure Socket Layer (SSL)

SSL is the **standard security technology** for establishing an encrypted link between the two systems. Basically, SSL ensures that the data transfer between the two systems remains **encrypted and private**. The https is essentially **http over SSL**. SSL establishes an encrypted link using an SSL certificate which is also known as a **digital certificate**.

| http                                                 | https                              |
| ---------------------------------------------------- | ---------------------------------- |
| Transfers data in hypertext (structured text) format | Transfers data in encrypted format |
| Uses port **80 by default**                          | Uses port **443 by default**       |
| Not secure                                           | Secured using SSL technology       |
| Starts with `http://`                                | Starts with `https://`             |

#### Advantage of https

**Secure Communication**: https makes a secure connection between two systems.
**Data Integrity**: provides **data integrity by encrypting the data**, so hackers cannot read or modify it.
**Privacy and Security**: https protects the **privacy and security of website users** by preventing hackers to passively listen to communication between the browser and the server.
**Faster Performance**: https increases the speed of data transfer compared to http by **encrypting and reducing the size of the data**.
**SEO Ranking**: Use of https **increases SEO (Search Engine Optimization) ranking**. This ranking refers to your content's position on the search engine results pages (**SERPs**). In Google Chrome, Google shows the **Not Secure label** in the browser if users' data is collected over http.

#### How SSL works?

SSL fundamentally works using  **two concepts**, asymmetric cryptography and symmetric cryptography.

##### Asymmetric Cryptography

Asymmetric cryptography (also known as **Asymmetric Encryption** or **Public Key Cryptography**) uses a **mathematically-related key pair** to encrypt and decrypt data. In a key pair, one key is shared with anyone who is interested in a communication. This is called **Public Key**. The other key in the key pair is kept secret and is called **Private Key**. In the asymmetric cryptography, the **data can be signed with a private key**, which can **only be decrypted using the related public key** in a pair.

Here, the keys referred to a mathematical value and were **created using a mathematical algorithm which encrypts or decrypts the data**. SSL uses asymmetric cryptography to initiate the communication which is known as **SSL handshake**. Most commonly used **asymmetric key encryption algorithms** include EIGamal, RSA, DSA, Elliptic curve techniques and PKCS.

##### Symmetric Cryptography

In the symmetric cryptography, there is **only one key** which encrypts and decrypts the data. **Both sender and receiver should have this key**, which is only known to them. SSL uses symmetric cryptography using the **session key** after the initial handshake is done. The most widely used **symmetric algorithms** are AES-128, AES-192 and AES-256.

##### SSL Communication

SSL protocol uses **asymmetric and symmetric cryptography to transfer data securely**. SSL communication between the two systems is mainly divided into two steps: the **SSL handshake** and the **actual data transfer**.

**SSL Handshake :** The communication over SSL always begins with the SSL handshake. The SSL handshake is an asymmetric cryptography which allows the **browser to verify the web server**, get the public key and **establish a secure connection before the beginning of the actual data transfer**.

Steps involved in the SSL handshake:

1. The client sends a "client hello" message. This includes the client's **SSL version number**, cipher settings, session-specific data and other **information that the server** needs to communicate with the client using SSL.
2. The server responds with a "server hello" message. This includes the server's SSL version number, cipher settings, session-specific data, an **SSL certificate with a public key** and other **information that the client needs** to communicate with the server over SSL.
3. The **client verifies the server's SSL** certificate from CA (**Certificate Authority**) and authenticates the server. If the authentication fails, then the **client refuses the SSL connection** and throws an exception. If the authentication succeeds, then proceed to step 4.
4. The **client creates a session key**, encrypts it with the server's public key and sends it to the server. If the server has requested **client authentication** (mostly in server to server communication), then the client sends his own certificate to the server.
5. The server decrypts the session key with its private key and **sends the acknowledgement to the client encrypted with the session key**.

Thus, at the end of the SSL handshake, both the **client and the server have a valid session key** which they will use to encrypt or decrypt actual data. The **public key and the private key will not be used** any more after this.

**Actual Data Transfer :** The client and the server now **uses the same session key** to encrypt and decrypt actual data and transfer it and so, it is a **symmetric cryptography**. The actual SSL data transfer uses symmetric cryptography because it is **easy and takes less CPU consumption** compared with the asymmetric cryptography.

##### Public Key Infrastructure(PKI)

There are certain infrastructures involved in achieving SSL communication in real life, which are called Public Key Infrastructure. The public key infrastructure (PKI) is a <u>set of roles, policies, and procedures needed to create, manage, distribute, use, store, and revoke digital certificates and manage public key encryption</u>.

PKI includes the following elements:

- **Certificate Authority (CA)**: The authority that authenticates the identity of individuals, computers and other entities.
- **Registration Authority**: A subordinate CA that issues a certificate on the behalf of root CA for specific uses.
- **SSL Certificate**: The **Data file that includes the public key** and other information.
- **Certificate Management System**: The system which stores, validates and revokes certificates.

#### What is the SSL Certificate?

The SSL certificate (also known as **digital certificate**) is a data file issued by the authorized Certificate Authority (CA). The SSL certificate contains the owner's public key and other details. The web server **sends a public key to the browser through an SSL certificate** and the browser verifies it and authenticates the web server using the SSL certificate.

You can **open the certificate of any https website**. Any https website will have a padlock **Secure** in the addressbar. In the **General tab**, the certificate displays Issued to, Issued by and Valid from and to dates. The **Details tab** includes other information. The **Certificate Path tab** includes information about all the intermediate certificates and the root CA certificate.

##### X.509

X.509 is a **standard that defines the format** of the digital certificate. SSL uses the X.509 format. In other words, **SSL certificates are actually X.509 certificates**. X.509 uses a formal language called **Abstract Syntax Notation One (ASN.1)** to express the **certificate's data structure**. The SSL certificate in X.509 format includes the following information:

- **Version**: The version number of the certificate data format as per X.509.
- **Serial number**: Unique identifier of the certificate **assigned by CA**
- **Public Key**: The owner's public key
- **Subject**: Owner's name, address, country and domain name
- **Issuer**: Name of the **CA** who issued the certificate
- **Valid-From**: The date the certificate is valid from
- **Valid-To**: The expiration date
- **Signature Algorithm**: The algorithm used to create the signature
- **Thumbprint**: The **hash** of the certificate
- **Thumbprint Algorithm**: The algorithm used to create a hash of the certificate

#### Types of SSL Certificates

There are different types of SSL certificates based on the **validation level** and the **number of domains** they secure.

##### Based on Validation Level

Websites use SSL certificates to **setup a trust level** with their visitors and customers. Different businesses require to setup different levels of trust. So, CA needs to **validate the website owner's information** based on the trust they want to setup. The following three types of certificates are based on the level of validation.

- **Domain Validated Certificates**
  The Domain Validated (DV) certificate requires the **lowest level validation** because the main purpose of DV certificates is to make the secure communication between the domain's web server and browser. CA only verifies that the **owner has a control over the domain**.

- **Organization Validated Certificates**
  The Organization Validated (OV) certificate requires a medium level validation where CA checks the **rights of an organization** to use the domain and also the **organization's information**. The OV certificate enhances the trust level of the organization and its domain.

- **Extended Validated Certificates**
  The Extended Validated (EV) certificate requires a **high-level validation** where CA conducts rigorous **background checks on the organization** according to guidelines. This includes **verification of the legal, physical and operational existence** of the entity.

##### Based on the Number of Domains they Secure

- **Single Domain Certificate**
  The single domain certificate **secures one fully qualified domain name**. For example, a single domain certificate for www.example.com will not secure the communication for mail.example.com.

- **Wildcard SSL Certificate**
  wildcard SSL certificate **secures an unlimited number of subdomains for a single domain**. Example, a wildcard SSL certificate for example.com will also secure mail.example.com, blog.example.com etc.

- **Unified SSL Certificate /Multi-Domain SSL Certificate/SAN Certificate**
  The unified SSL certificate **secures up to 100 domains** using the same certificate with the help of the **SAN extension**. It is especially designed to secure Microsoft Exchange and Office Communication environments.

#### How to Get an SSL Certificate?

Once you choose what type of SSL certificate you require, you need to **procure it from the reputed CA**. There are two ways to get an SSL certificate:

- Buy a certificate from CA
- Get a free certificate from a non-profit open CA

If you have an e-commerce web site, you gather user's information or you perform monetary transactions, then it is recommended to buy EV or OV certificates from a reputed CA. If you have a static website or you are **not doing any monetary transactions**, then you may use a free DV certificate, but most of them are **valid for three months** (renew every three months).

##### Buying an SSL Certificate

You can buy an SSL certificate from CA. The prices vary depending on the CA and type of the SSL certificate. The following are overall steps for buying SSL certificates from a CA:

1. **Choose a Certificate Authority (CA)**: You can choose your CA from where you want to buy an SSL certificate. There are many CAs such as Comodo, DigiCert, RapidSSL, GeoTrust, thawte, Certum etc. You may choose the CA **based on your budget and the features** you need to manage the certificate. There are resellers who provides cheap SSL certificates from these CAs.
2. **Select the certificate** you need: Choose the certificate you require for your website based on the **validation method** and the **number of web sites** you want to secure.
3. **Purchase the certificate**: Once you choose the certificate you require, make the payment to proceed. For some CA, this step comes after submitting a CSR.
4. Generate and submit a CSR (**Certificate Signing Request**) to the CA: You need to generate a CSR from your web server and submit it to the CA. 
5. **Download the SSL certificate** (after successful validation): After submitting a CSR, the CA will take some time for validating your information. This may **vary based on the validation certificate** you purchase. Once the CA successfully validates your information, you will get an email containing the certificate or you can download it from your account on the CA's website.
6. **Install** an SSL certificate on your web server: Once you get SSL certificate, you need to install it on the web server from where you generated your CSR. The installation process depends on the server OS.

##### Certificate Signing Request (CSR)

In order to get an SSL certificate for your website, you need to generate and submit a CSR to the CA (Certificate Authority). A CSR is an **encoded message** submitted by an applicant to a CA to get an SSL certificate. A CSR contains a **public key** and the applicant's information such as **FQDN (Fully Qualified Domain Name)**, organization name and address. The CA validates the applicant's information and **issues an SSL certificate with the public key included in the CSR**.

###### Generate a CSR

Generally, a CSR is generated using the web server where the SSL certificate is going to be installed. However, it can also be generated using SSL tools or a modern browser such as Chrome or Firefox. The most common format for CSRs is the **PKCS #10 specification**. A CSR is a **Base64 ASCII encoding message** starting with "-----BEGIN NEW CERTIFICATE REQUEST-----" and ending with "-----END NEW CERTIFICATE REQUEST-----".

A CSR can be generated using any web server. We can **generate a CSR using IIS**. In the **Internet Information Services (IIS)** Manager window, select your server and create the Certificate Request.

###### Public Key and Private Key

A CSR includes a public key generated by the web server where you are going to install an SSL certificate. The **web server generates a key pair**, a public key and a private key when the CSR is generated. It includes a public key in the CSR and also stores a private key secretly in the file system. This **private key will be used when installing a certificate** on the web server.

#### SSL Certificate Formats

An SSL Certificate is essentially an **X.509 certificate**. X.509 is a standard that **defines the structure of the certificate**. It **defines the data fields** that should be included in the SSL certificate. X.509 uses a formal language called **Abstract Syntax Notation One (ASN.1)** to express the certificate's data structure.

There are different formats of X.509 certificates such as PEM, DER, PKCS#7 and PKCS#12. **PEM and PKCS#7** formats use **Base64 ASCII** encoding while **DER and PKCS#12** use **binary encoding**. The certificate files have **different extensions** based on the format and encoding they use. Most CAs (Certificate Authority) provide certificates in **PEM format** in Base64 ASCII encoded files. 

#### Install an SSL Certificate

The following are the steps to install an SSL certificate and bind it to your domain:

1. Gather the necessary files: SSL Certificate Files should have the following three files:

   SSL Certificate for your domain ( .crt file)
   Intermediate certificates or CA bundle (optional,  .crt file)
   **Private key** (.key file)

   You may have the private key stored in a .key file type. However, it depends on where you generated the CSR and where you want to install it. If you generated the CSR from the same web server where you want to install an SSL certificate, then you will not have a private key file because it is **secretly stored by the web server**. If you generated the CSR using a browser or SSL tools, then you should have a saved private key in a separate .key file. If the web server where you generate the CSR is different from the web server where you are going to install the certificate, then you need to **export the private key** from where you generated the CSR because the private key is generated at the same time when the CSR is generated.

2. Find out the certificate file type and the format your web server requires.

3. Convert the certificate file into the **web server compatible file**

   If the certificate files and format you got from the CA are not supported on your web server or hosting provider then you have to convert the certificates to **web server supported format using OpenSSL**.

4. **Install the certificates on your web server** : depends on the web server or hosting provider.

5. **Bind the installed SSL certificate to your web site** : depends on the web server or hosting provider.

6. **Test the https website** : Test your SSL certificate with the **SSL tools** and check it is working fine or not.

#### OpenSSL

OpenSSL is a robust, commercial-grade, and **full-featured toolkit** for the Transport Layer Security (TLS) and Secure Sockets Layer (SSL) protocols. It is also a **general-purpose cryptography library**. If you have got certificate files from the CA which are **not supported on your web server**, then you can **convert your certificate files into the format** your web server requires using **OpenSSL commands**.

