# Order Service

Ordere Service is built on Spring. Order Service is responsible for Submit a new Order and for retrieving a single Order.

- Spring Boot
- Spring Cloud
- Liquibase for Database Migrations
- Junit
- Mockito
- Jacoco Code Coverage 

See the wiki for REST operations.

## Running

./gradlew bootRun

## Viewing the API for Order Service

With Netflix Eureka + Swagger a self documenting API will demonstrate API Verbs available for Order Service 

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


