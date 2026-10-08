# Twitter API (Backend)

A RESTful API clone of Twitter (X) built with Spring Boot and PostgreSQL, featuring JWT-based authentication.

## Project Status

**Work in progress.** The project is currently focused on backend development, with ongoing improvements and refinements.

A simple frontend is planned to let users interact with the API through a web interface.

---

## Tech Stack

- **Java 25**
- **Spring Boot 4.x** (Web, Data JPA, Security, Validation)
- **PostgreSQL**
- **JWT (jjwt 0.12.3)**
- **Lombok**
- **Maven**

---

## Features

- **Authentication & Authorization:**
  - User registration and login
  - Stateless JWT architecture with BCrypt password hashing
  - Automated auditing with JPA Auditing (`AuditorAware`)
- **Tweet Management:**
  - Create, update, and delete tweets (author-only modifications)
  - Retrieve single tweet details or all tweets by user ID
- **Interactions:**
  - Like and unlike tweets
  - Add, update, and delete comments on tweets
  - Retweet and undo retweet
- **Error Handling & Response Format:**
  - Centralized exception handling via `GlobalExceptionHandler`
  - Standardized API response wrapper (`ApiResponse<T>`)

---

## Getting Started

### Prerequisites

- JDK 25 or compatible version
- PostgreSQL
- Maven (or the bundled `mvnw`)

### Configuration

Copy `src/main/resources/application.properties.example` to `application.properties` and configure your database and JWT credentials:

```properties
spring.application.name=twitter
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/twitter_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

jwt.secret=your-256-bit-minimum-secret-key
jwt.expiration=86400000
```

### Build and Run

Run the application using the Maven wrapper:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080` by default.

---

## API Endpoints

All protected endpoints require an `Authorization: Bearer <TOKEN>` header.

### Authentication (`/auth`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/auth/register` | Register a new user | Public |
| `POST` | `/auth/login` | Authenticate and obtain JWT | Public |

### Tweets (`/tweet`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/tweet/findById?id={id}` | Get tweet details by ID | Bearer Token |
| `GET` | `/tweet/findByUserId?userId={userId}` | List all tweets by user ID | Bearer Token |
| `POST` | `/tweet` | Create a new tweet | Bearer Token |
| `PUT` | `/tweet/{id}` | Update tweet content | Bearer Token (Author) |
| `DELETE`| `/tweet/{id}` | Delete a tweet | Bearer Token (Author) |

### Comments (`/comment`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/comment` | Add a comment to a tweet | Bearer Token |
| `PUT` | `/comment/{id}` | Update comment content | Bearer Token (Author) |
| `DELETE`| `/comment/{id}` | Delete a comment | Bearer Token (Author) |

### Likes (`/like`, `/dislike`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/like` | Like a tweet | Bearer Token |
| `POST` | `/dislike` | Remove like from a tweet | Bearer Token |

### Retweets (`/retweet`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/retweet` | Retweet a tweet | Bearer Token |
| `DELETE`| `/retweet/{id}` | Remove a retweet | Bearer Token (Author) |

---

## Project Structure

```text
src/main/java/com/wtech/twitter/
├── config/         # App and JPA auditing configurations
├── controller/     # REST API controllers
├── dto/            # Request and response data transfer objects
├── entity/         # JPA database entities
├── exceptions/     # Custom exceptions and GlobalExceptionHandler
├── repository/     # Spring Data JPA repositories
├── security/       # JWT filters and Spring Security setup
├── service/        # Business logic services
└── validation/     # Validation annotations and logic
```
