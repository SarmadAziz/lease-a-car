# todo
- make a service to service call
- use regular database instead of in-memory
- add integration test with rest assured
- add logging
- add profiles for DTAP street
- use JWT + oauth
- use eureka for service discovery
- setup api gateway

# remarks sarmad:
- Car class should also have validation, but I've already demonstrated this with the customers-service
- I should have also made a CarDto, but I've already demonstrated this with the customers-service
- CarController could be expanded with other CRUD functionality, but I've already demonstrated this with the customers-service
- There is no integration between Customers and cars service. I should have added a **carId** and **LeaseRateCalculationInput** to **Customer** and **CustomerDto**. 
  - A broker using the frontend would then:
    - get the list of cars, 
    - select a certain car, 
    - calculate the lease price for a potential customer
    - submit customer info + LeaseRateCalculationInput