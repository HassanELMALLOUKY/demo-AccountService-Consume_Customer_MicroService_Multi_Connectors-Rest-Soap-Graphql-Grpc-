# Account Service
**Project Architecture**
<p></p>
<img src="src/main/resources/architecture.png">
<p>First MicroService: Customer-Service <a href="https://github.com/HassanELMALLOUKY/demo-MicroService-MultiConnectors-REST-SOAP-GraphQL-GRPC-">Click Here</a></p>
<p>Second MicroService: Account-Service <a href="">Click Here</a></p>
**Project Description:**

This project is a consumer of a customer microservice that interacts with the service using multiple connectors, including REST, SOAP, GraphQL, and gRPC.

**Connectors Implemented:**

1. **REST Clients:**

    - **RestTemplate:** Implemented a REST client using RestTemplate to communicate with the customer microservice over RESTful API.

    - **Web Client:** Implemented a REST client using WebClient to perform asynchronous requests to the customer microservice.

    - **OpenFeign:** Implemented a REST client using OpenFeign, a declarative web service client, to simplify RESTful API calls.

2. **SOAP Client:**

   Implemented a SOAP client to interact with the customer microservice using SOAP-based web services.

3. **GraphQL Client:**

   Implemented a GraphQL client to perform GraphQL queries and mutations against the customer microservice.

4. **gRPC Client:**

   Implemented a gRPC client to communicate with the customer microservice using gRPC, a high-performance, language-agnostic RPC framework.

**Usage:**

1. **REST Clients:**

    - **RestTemplate:** The RestTemplate client can be used by calling appropriate methods to perform RESTful API requests.

    - **Web Client:** The WebClient client can be used to make asynchronous RESTful API requests.

    - **OpenFeign:** The OpenFeign client provides a declarative way to interact with RESTful APIs. Use the annotated interfaces to perform API calls.

2. **SOAP Client:**

    - The SOAP client provides methods to perform SOAP-based web service requests. Initialize the client and use the methods accordingly.

3. **GraphQL Client:**

    - The GraphQL client allows executing GraphQL queries and mutations against the customer microservice. Initialize the client and use it to send queries.

4. **gRPC Client:**

    - The gRPC client provides methods to interact with the gRPC services of the customer microservice. Initialize the client and use it to make gRPC calls.

**Requirements:**

- Java JDK 17 or higher
- Spring Boot 3.0.1
- Dependencies for RestTemplate, WebClient, OpenFeign, GraphQL, and gRPC communication
- Maven build tool

**Installation:**

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/your-project.git
   cd your-project
   ```

2. Build the project:

    - If using Maven:

      ```bash
      mvn clean install
      ```

3. Run the application:

   ```bash
   # If using Maven
   mvn spring-boot:run


**Contributing:**

Contributions are welcome! If you find any issues or want to enhance the project, feel free to submit a pull request or open an issue.



![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) **Next Challenge:**


My next challenge is to deploy these microservices using Docker to enable seamless and scalable containerization of the application.

For Docker deployment, I will:

Create Dockerfiles for each microservice to define the container image.
Use Docker Compose to manage the multi-container application setup.
Configure the necessary environment variables and network settings.
Test the application in the Docker environment to ensure it works seamlessly in containers.
By deploying the microservices with Docker, I aim to enhance the application's portability, consistency, and isolation while simplifying the deployment process. Docker will enable easier collaboration and ensure a consistent runtime environment across different platforms.
