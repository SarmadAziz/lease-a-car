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

### car-service 
cd car-service

docker build . -t car-service:1.0

docker run --name car-service-instance -d -it -p 8081:8081 car-service:1.0

### customer-service
cd customer-service

docker build . -t customer-service:1.0

docker run --name customer-service-instance -d -it -p 8082:8082 customer-service:1.0

## stopping a container
When you use the above 'docker run xxx' command, a processId is printed to your terminal. The processId looks something like: ***41b441614ef2dd68a708719792703d40d44fcbbd2f9dcc6cbba6ddf8815ef7cd***
### command to stop:
docker stop **processId**

## run services with docker compose
From the root directory simply use:

docker compose -f docker-compose.yml up --build

## swagger docs
customer-service: http://localhost:8082/swagger-ui/index.html#/

car-service: http://localhost:8081/swagger-ui/index.html#/

## Testing the services
Boot up the api via maven or docker.

You can use Postman to test the endpoints. 

If you are using intelij, you can make use of **generated-requests.http** to perform http calls



