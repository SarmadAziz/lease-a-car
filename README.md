# Overview
The **Lease-a-Car API** is a microservices-based REST API designed to maintain vehicle versions, customer data, and other necessary data for leasing brokers and companies. 

The API allows brokers to calculate lease rates for customers and maintain customer data, while leasing companies can manage the necessary car information to ensure.

## Run services with maven locally

### To run car-service (by default on port 8081)
cd car-service
./mvnw clean install
./mvnw spring-boot:run

### To run customer-service (by default on port 8082)
cd customer-service
./mvnw clean install
./mvnw spring-boot:run

## run services with docker

### go to car-service folder
cd car-service
### build the docker image
docker build . -t car-service:1.0
### run the docker image
docker run --name car-service-instance -d -it -p 8081:8081 car-service:1.0

### go to customer-service folder
cd customer-service
### build the docker image
docker build . -t customer-service:1.0
### run the docker image
docker run --name customer-service-instance -d -it -p 8082:8082 customer-service:1.0

## stopping a container
When you use the above 'docker run xxx' command, a processId is printed to your terminal. The processId looks something like: ***41b441614ef2dd68a708719792703d40d44fcbbd2f9dcc6cbba6ddf8815ef7cd***
### command to stop
docker stop <TheProcessId>

## run services with docker compose
From the root directory simply use:

docker compose -f docker-compose.yml up

