# Order Service

Order Service is responsible for:
- Submit a new Order 
- Retrieving a single Order

Order Service is built on Spring.

- Spring Boot
- Spring Cloud
- Liquibase for Database Migrations
- Junit
- Mockito
- Jacoco Code Coverage 

Integration with Travic CI for continuous integration.

[![Build Status](https://travis-ci.org/haighis/order-service.svg?branch=master)](https://travis-ci.org/haighis/order-service)

## Usage
See the [wiki](https://github.com/haighis/order-service/wiki) for REST operations. What can I do with Order Service? You can use it to Submit a new Order and get a single Order that are accessible via REST POST/GET with JSON content.

## Running

./gradlew bootRun

This Microservice can benefit from Netflix Eureka, but does not require Netflix Eureka in order to run. Run each Microservice Product Service, Shopping Cart Service and Order Service. Then run Shop UI and open your browser to 
http://localhost:2005

## Database Setup

Database used is PostgreSQL. Using a default PostgreSQL setup on windows/macosx has been tested. 
1. Create two databases cart and ecommerce
2. Create a user 'jhaigh' and password 'jhaigh' that has priveledges to cart and commerce database in PostgreSql.
3. run ```./gradlew bootRun``` or ```gradlew.bat bootRun``` which will create the database schema on first run.

## Consuming the API

While not required a Netflix Eureka + Swagger combo provides a self documenting API and Netflix Eureka will auto discover Product Service, Order Service and Shopping Cart Services. Running Swagger UI will demonstrate API Verbs available. 

See the [wiki](https://github.com/haighis/order-service/wiki) for REST operations.

## Tests

In memory tests with Mockito for OrderServiceImpl and OrderResource API.  

./gradlew test

To view pass/fail for unit tests there is a report located at 'build/reports/tests/index.html'

Test Summary
![Test Summary](/media/testsummary.jpg?raw=true "Test Summary")

## Test Code Coverage

./gradlew test jacocoTestReport

Jacoco Code Coverage Report is located at 'build/reports/coverage/index.html'

Rest API - Order Resource Code Coverage
![Order Resource](/media/restcodecoverage.jpg?raw=true "Rest API - Order Resource Code Coverage")

Order Service Code Coverage
![Order Service](/media/orderservicecodecoverage.jpg?raw=true "Order Service Code Coverage")