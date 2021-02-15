# RBS Primes Challenge
Write a RESTful service which calculates and returns all the prime numbers up to and including a number provided.
 
## Example
The REST call would look something like http://your.host.com/primes/10 and should return JSON content:

 ``` 
{
  "Initial":  10,
  "Primes": [2,3,5,7]
}
 ``` 
 
## Requirements

The project must be written in Java 7 / 8.
The project must use Maven OR Gradle to build, test and run.
The project must have unit and integration tests.
The project must be runnable in that the service should be hosted in a container e.g. Tomcat, Jetty, Spring Boot etc.
You may use any frameworks or libraries for support e.g. Spring MVC, Apache CXF etc.
The project must be accessible from Github.
 
## Optional Extensions

Deploy the solution to a chosen platform that we can access.
Consider supporting varying return content types such as XML based, that should be configurable using the requested media type.

## See it in action
Although you can clone this repo then run locally it is also running on my own AWS account.

## Running the app locally

### Prerequisites
1. Install JDK8 or above
2. Install Maven

### Getting started
Fork this repository then `git clone` to start working locally.

To build the project, execute `./mvn clean install` from the project root

To run the project, execute `./mvn spring-boot:run` then access the service in your browser at [http://localhost:1080/primes/10](http://localhost:1080/primes/10).

## App Features

* Multi Module Project with public interface defined in RAML modelling language
* raml-maven-plugin used to generate API model and Spring Rest Controllers
* Layers presented in the application to split API model and internal model
* Unit tests with example of Mokito and my testing favorite jAssert usage
* Rest Assured used with SpringIntegrationTest for integation tests with given, when, then
* Standardised Error Json and Api exception hierarchy : ApiServerException and ApiClientException
* ExceptionHandler using ControllerAdvice
* Lombok used to simplify code. Builder patterns used for fluent reading.
* Streams used for Prime Calculation