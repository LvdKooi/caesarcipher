**Caesar Cipher Web Service**

*Introduction*

A Caesar Cipher is a simple and widely-known encryption technique used in classical cryptography. It works by shifting each letter in the plaintext by a fixed number of positions down or up the alphabet. For example, with a shift of 3, 'A' would be encoded as 'D', 'B' as 'E', and so on. The Caesar Cipher is named after Julius Caesar, who reportedly used it to communicate with his officials.

The idea for this web service came from a Father's Day gift from my son, which included a riddle encoded as a Caesar Cipher. Inspired by this thoughtful gift, I decided to create a web service that could easily encrypt and decrypt messages using this classic technique.

*Application Overview*

This web service implements the Caesar Cipher for encrypting and decrypting messages. The service allows users to input a message and a shift value to either encode or decode the message.

*Running the Application*

When running locally, the application listens on port `8080`. You can access the web service endpoints and test the API using the Swagger UI.

*Swagger UI*

The Swagger UI provides a user-friendly interface for interacting with the API. It is available at the following URL:
```
http://localhost:8080/swagger-ui/index.html#/
```
