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
- Hibernate
- Lombok
- PostgreSql  
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

You just need to run `docker-compose up` command
___
*$PORT: 8080*
```ssh
$ cd book-store/book-store
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
### Screenshots
- login
> Required fields for user signup are `username` `password` and `email`

![signup](https://user-images.githubusercontent.com/83432342/224550331-27111446-42cc-4dfb-880c-04d13099fba2.png)

- signup
> User's token returns after login `username` and `password`
   
![login](https://user-images.githubusercontent.com/83432342/224550333-6d4ddd12-6aeb-41eb-8693-2b967d23fa29.png)


- user
> The user is reached by token

![user](https://user-images.githubusercontent.com/83432342/224550340-3768d944-1369-4971-9b4f-46da7604ceef.png)

-post
> Must have authorization for post methods

![post](https://user-images.githubusercontent.com/83432342/224550342-d409226a-39e7-4f27-ba2d-ded68df1717a.png)

-get
> Get methods do not require authorization

![get](https://user-images.githubusercontent.com/83432342/224550347-004c08ed-ba3f-4ef5-b07e-acc134f13648.png)


-pagination
> Pagination is used when listing all books

![pagination](https://user-images.githubusercontent.com/83432342/224550349-c498c987-c63d-4cb8-b242-ad2e3c5f11d4.png)
