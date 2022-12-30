
# project-management-springmvc-ui

Based on a Microservice Architecture, this project has been designed and developed as a solution for "Problem Statement #1: Project Mgmt Micro App" of "Weekly Status Reporting Project". This a simple Front-End UI application developed with JAVA, Spring MVC, and Thymeleaf to display and handle Project related CRUD operations.


## Features

This Front-End application provides Thymeleaf based UI for the following operations:

- Create and manage company/organization level Projects.
- Search and find individual Project by id and name.
- Update and Delete Project related details.
- Create and manage mailing list for individual Project.
- Search and find individual Email by id and name.
- Update and Delete Email related details.

## Requirements

- Java 1.8+
- Maven 3.0+
- Docker Engine
- Latest MySQL

## Tech Stack

- Java 1.8+
- Maven 3.0+
- Spring Boot 2.7.0+
- JUnit 5
- Docker Engine
- Latest MySQL 8.0+

## Run Locally

Clone the project

```bash
  git clone https://github.com/VipulJadhav12/project-management-springmvc-ui.git
```

Go to the project directory

```bash
  cd project-management-springmvc-ui
```

Open and edit the src/main/resources/application.properties file

```bash
  # Port no. at which this application will listen on
  server.port=8181
  server.error.include-message=always

  # Disabling the JDBC Datasource auto-config
  spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

  project.api.url=http://localhost:9191/api/v1/projects
  mailing-list.api.url=http://localhost:9191/api/v1/emails  
```

Run the above source code through mvn command line

```bash
  mvn spring-boot:run
```

Compile and Package the above source code as a JAR

```bash
  mvn clean package
```
or
```bash
  mvn clean package -Dmaven.test.skip=true
```

Run the above packaged source code through java command line. For that, go to the target directory

```bash
  java -jar project-management-springmvc-thymeleaf-ui-0.0.1-SNAPSHOT.jar
```

Main webpages to start to start with

```bash
  http://localhost:<PORT_NO>/displayAllProjects
```
and
```bash
  http://localhost:<PORT_NO>/displayAllMailingList
```

## Running Tests

To run tests, run the following commands

Go to the project directory

```bash
  cd project-management-springmvc-ui
```

Open and edit the src/test/resources/application.properties file

```bash
  server.port=8181
  server.error.include-message=always

  # Disabling the JDBC Datasource auto-config
  spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

  project.api.url=http://localhost:9191/api/v1/projects
  mailing-list.api.url=http://localhost:9191/api/v1/emails
```

Run the unit test-cases through mvn command line

```bash
  mvn test
```

## Authors

- [@krashnat922](https://github.com/krashnat922)
- [@sachin396](https://github.com/sachin396)
- [@VipulJadhav12](https://github.com/VipulJadhav12)

