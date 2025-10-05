# Online Banking System

## Table of contents

1. [Overview](#overview)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [Project Structure](#project-structure)
5. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Steps to run](#steps-to-run)
6. [Configuration](#configuration)
7. [API Overview](#api-overview)
    - [User API](#user-api-apiusers)
    - [Account API](#account-api-apiaccounts)
    - [Customer API](#customer-api-apicustomers)
    - [Transfer API](#transfer-api-apitransfers)
8. [API Documentation](#-api-documents)
9. [Testing](#testing)
10. [Future Improvements](#future-improvements)
11. [Frontend Side](#frontend-part)
12. [Author](#author)

## Overview
A simple Online Banking API built with Spring Boot and Gradle.
It provides core banking features such as user authentication, customer and account management, and fund transfers.

## Features
- User registration and login (with authentication & authorization)
- Account creation, update, and deletion
- Customer profile management
- Fund transfer between accounts
- Centralized error handling and validation
- Swagger documentation for APIs

## Tech Stack
- Java 21+
- Spring Boot 3+
- Spring Data JPA (Hibernate)
- Spring Security
- Gradle
- PostgreSQL
- Lombok
- JUnit & Spring Test
- Swagger / OpenAPI

## Project Structure

```text
src/main/java/org/sampong/onlinebanking
 ├── _common/       (shared configs, exceptions, enums)
 ├── account/       (account features)
 ├── customer/      (customer features)
 ├── transfer/      (fund transfer features)
 ├── user/          (authentication & user management)
 └── OnlineBankingApplication.java

src/main/resources
 ├── application.yml          (main configuration)
 ├── application-dev.yml      (development environment)
 ├── application-local.yml    (local environment)
 └── sql/                     (database SQL scripts)
```

## Getting Started

### Prerequisites:
- Java 17 or higher
  - Gradle
  - PostgreSQL running locally

### Steps to run

1. Clone the repository:
    ```shell
      git clone https://github.com/<your-username>/online-banking.git
      cd online-banking
    ```
2. Build the application:
    ```shell
      ./gradlew build
    ```
3. Run the application:
    ```shell
      ./gradlew bootRun
    ```

Application runs at: `http://localhost:8080`

## Configuration

Configuration files:
- application.yml          (default)
- application-dev.yml      (development)
- application-local.yml    (local testing)

Run with a specific profile:
```shell
  ./gradlew bootRun --args='--spring.profiles.active=dev'
```

## API Overview

### User API (`/api/users`)

| Method | Endpoint    | Description           |
|--------|-------------|-----------------------|
| POST   | `/register` | Register a new user   |
| POST   | `/login`    | Authenticate user     |
| GET    | `/profile`  | Get current user info |

---

### Account API (`/api/accounts`)

| Method | Endpoint     | Description          |
|--------|--------------|----------------------|
| POST   | `/`          | Create account       |
| GET    | `/{id}`      | Get account by ID    |
| GET    | `/user/{id}` | Get accounts by user |
| PUT    | `/{id}`      | Update account       |
| DELETE | `/{id}`      | Delete account       |

---

### Customer API (`/api/customers`)

| Method | Endpoint | Description        |
|--------|----------|--------------------|
| POST   | `/`      | Create customer    |
| GET    | `/{id}`  | Get customer by ID |
| PUT    | `/{id}`  | Update customer    |
| DELETE | `/{id}`  | Delete customer    |

---

### Transfer API (`/api/transfers`)

| Method | Endpoint     | Description                     |
|--------|--------------|---------------------------------|
| POST   | `/`          | Transfer funds between accounts |
| GET    | `/{id}`      | Get transfer info               |
| GET    | `/user/{id}` | Get transfers for a user        |

## API Documents
------------------------------------------------------------
Swagger UI and OpenAPI are enabled for exploring and testing APIs.

- Swagger UI:   `http://localhost:8080/v3/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Testing
Run all tests:
```shell
  ./gradlew test
```

## Future improvements
- Admin dashboard for user and account management
- Role-based access control (RBAC)
- Multiple currency support
- Transaction history and analytics
- Docker container setup and CI/CD integration

## Frontend part
- Frontend: [banking-client](https://gitlab.com/online-banking2/banking-client)

## Author
- Developed by: Starluck
- Role: Backend Engineer (learning full-stack)