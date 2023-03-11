# Book Store API For Existing User
___
### Spring Boot Application

---

### Summary
Users can see details of books which was saved to app. In order to create a new user profile and add books, registration is required.

___
The application has 5 apis

* AuthAPI
* BookAPI
* CategoryAPI
* UserAPI
* ImageUploadAPI

### Tech Stack

---
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Restful API
- PostgreSql database  
- Docker
- Docker compose
- JUnit 5

### Prerequisites

---
- Maven
- Docker

### Run & Build

---
There are 2 ways of run & build the application.

#### Docker Compose

For docker compose usage, docker images already push to docker.io

You just need to run `docker-compose up` command
___
*$PORT: 8080*
```ssh
$ cd book-store
$ docker-compose up
```

#### Maven

___
*$PORT: 8080*
```ssh
$ cd book-store\book-store
$ mvn clean install
$ mvn spring-boot:run
```
