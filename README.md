# Overview
The **Lease-a-Car API** is a microservices-based REST API designed to maintain vehicle versions, customer data, and other necessary data for leasing brokers and companies. 

The API allows brokers to calculate lease rates for customers and maintain customer data, while leasing companies can manage the necessary car information to ensure.

# To build the entire project
./mvnw clean install

# To run customer-service (by default on port 8080)
cd customer-service
./mvnw spring-boot:run

# To run car-service (by default on port 8081)
cd car-service
./mvnw spring-boot:run