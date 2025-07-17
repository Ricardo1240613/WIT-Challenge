# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.wit.calculator-api' is invalid and this project uses 'com.wit.calculator_api' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.3/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.3/reference/using/devtools.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## ################################################################################################################################################

# WIT Calculator

## Overview

**WIT Calculator** is a calculator application designed to perform arithmetic operations using Kafka to communicate between the calculator service and rest api.

## Architecture

```
+-------------+          Kafka Topic           +---------------------+
|  REST API   |  -------------------------->   |  Calculator Service |
+-------------+                                +---------------------+
```

- **REST API (rest)**: Receives operation requests and publishes them to Kafka.
- **Calculator Service (calculator)**: Listens to Kafka and performs arithmetic operations.

## Technologies Used

- Java 17
- Spring Boot 3.1+
- Spring Kafka
- Apache Kafka (via Docker)
- JUnit 5
- Maven
- SLF4J (Logging)

## Requirements

- Docker & Docker Compose
- Java 17+
- Maven (included via mvnw wrapper)
- WSL (for Windows, required by Docker)

## Installation & Execution

### Step 1: Clone or Download

Unzip the project.

### Step 2: Start Kafka

```bash
docker-compose up -d
```

### Step 3: Build the Project

```bash
./mvnw clean install
```

### Step 4: Run Services

```bash
# Terminal 1 (REST API)
cd rest
../mvnw spring-boot:run

# Terminal 2 (Calculator Service)
cd calculator
../mvnw spring-boot:run
```

## API Usage

### Endpoint: `GET /calculator`

Query Parameters:

- `operation`: `sum`, `subtract`, `multiply`, `divide`
- `a`: Number A
- `b`: Number B

Example:

```http
http://localhost:8080/calculator/sum?a=10&b=5
```

The result will be logged by the `calculator` service after processing the Kafka message.

## Logging

All logs are handled via **SLF4J + Logback** and printed to the console.

## Tests

Unit tests are located in `calculator/src/test/java`. Run tests via:

```bash
./mvnw test
```

## Automation (Windows)

You can use `run_project.bat` to automate everything:

```bat
@echo off
cd /d "%~dp0"
docker-compose up -d
call mvnw clean install
start cmd /k "cd calculator && ..\mvnw spring-boot:run"
start cmd /k "cd rest && ..\mvnw spring-boot:run"
```


---
Developed by Ricardo Gonçalves - ISEP Artificial Intelligence MSc 1st year
© 2025 WIT Challenge
