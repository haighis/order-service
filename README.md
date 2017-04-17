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
See the wiki for REST operations. What can I do with Order Service? You can use it to Submit a new Order and get a single Order that are accessible via REST POST/GET with JSON content.

## Running

./gradlew bootRun

## Viewing the API

With Netflix Eureka + Swagger a self documenting API will demonstrate API Verbs available

See the wiki for REST operations.

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
![Test Summary](/media/restcodecoverage.jpg?raw=true "Rest API - Order Resource Code Coverage")

Order Service Code Coverage
![Test Summary](/media/orderservicecodecoverage.jpg?raw=true "Order Service Code Coverage")