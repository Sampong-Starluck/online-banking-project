# 🏦 Online Banking Application

A simple **Online Banking System** built with **Spring Boot** and **Gradle**,
supporting core banking features such as **Account Management**, **Customer
Management**, **Fund Transfers**, and **User Authentication**.

This project demonstrates clean backend architecture with modular packages,
service layers, and repository pattern.

---

## 📌 Features

- 👤 User Registration & Authentication
- 🏛️ Account Management (create, update, delete, view)
- 👥 Customer Management
- 💸 Fund Transfers between accounts
- 🔐 Security layer with authentication & authorization
- 🛠️ Common utilities for configuration, exception handling, and validation

---

## 🔧 Tech Stack

- **Java 21+**
- **Spring Boot 3+**
- **Spring Data JPA (Hibernate)**
- **Spring Security**
- **Gradle**
- **PostgreSQL** (configurable in `application.yml`)
- **Lombok**
- **JUnit & Spring Test** for testing
- **Swagger / OpenAPI** for API documentation

---

## 📂 Project Structure

```text
src/main/java/org/sampong/onlinebanking
 ├── _common/           # common utilities (config, exceptions, enums, etc.)
 ├── account/           # account domain (controller, service, repository, model)
 ├── customer/          # customer domain
 ├── transfer/          # money transfer domain
 ├── user/              # authentication & user management
 └── OnlineBankingApplication.java

src/main/resources
 ├── application.yml          # default config
 ├── application-dev.yml      # dev environment config
 ├── application-local.yml    # local environment config
 ├── sql/                     # database SQL scripts
 └── templates/               # templates (if using Thymeleaf)
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Gradle
- PostgreSQL

### Build & Run

```bash
    # Clone the repo
    git clone https://github.com/<your-username>/online-banking.git
    cd online-banking
    
    # Build project
    ./gradlew build
    
    # Run Spring Boot application
    ./gradlew bootRun
```

App runs at: **http://localhost:8080**

---

## ⚙️ Configuration

You can configure profiles in `application.yml`:

- `application.yml` → Base config
- `application-dev.yml` → Development profile
- `application-local.yml` → Local profile

Switch profiles via:

```bash
  ./gradlew bootRun --args='--spring.profiles.active=dev'
```

---

## 📖 API Endpoints

### 👤 User API (`/api/users`)
| Method | Endpoint              | Description                | Body / Params                 |
|--------|-----------------------|----------------------------|-------------------------------|
| `POST` | `/api/users/register` | Register a new user        | `{username, password, email}` |
| `POST` | `/api/users/login`    | Authenticate user (JWT)    | `{username, password}`        |
| `GET`  | `/api/users/profile`  | Get logged-in user profile | Auth Header                   |

---

### 🏦 Account API (`/api/accounts`)
| Method   | Endpoint                  | Description             | Body / Params             |
|----------|---------------------------|-------------------------|---------------------------|
| `POST`   | `/api/accounts`           | Create a new account    | `{userId, type, balance}` |
| `GET`    | `/api/accounts/{id}`      | Get account by ID       | Path ID                   |
| `GET`    | `/api/accounts/user/{id}` | Get accounts by User ID | Path User ID              |
| `PUT`    | `/api/accounts/{id}`      | Update account          | `{type, balance}`         |
| `DELETE` | `/api/accounts/{id}`      | Delete account          | Path ID                   |

---

### 👥 Customer API (`/api/customers`)
| Method   | Endpoint              | Description                | Body / Params        |
|----------|-----------------------|----------------------------|----------------------|
| `POST`   | `/api/customers`      | Create a customer profile  | `{name, dob, email}` |
| `GET`    | `/api/customers/{id}` | Get customer details by ID | Path ID              |
| `PUT`    | `/api/customers/{id}` | Update customer            | `{name, dob, email}` |
| `DELETE` | `/api/customers/{id}` | Delete customer            | Path ID              |

---

### 💸 Transfer API (`/api/transfers`)
| Method | Endpoint                   | Description                     | Body / Params                          |
|--------|----------------------------|---------------------------------|----------------------------------------|
| `POST` | `/api/transfers`           | Transfer funds between accounts | `{fromAccountId, toAccountId, amount}` |
| `GET`  | `/api/transfers/{id}`      | Get transfer details by ID      | Path ID                                |
| `GET`  | `/api/transfers/user/{id}` | Get transfers by User           | Path User ID                           |

---

## 📑 Swagger / OpenAPI Setup

This project includes **Swagger UI** for interactive API documentation.

### Add Dependency (`build.gradle.kts`)

```kotlin
dependencies {
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
}
```

### Access Swagger UI
Once the app is running:

👉 Go to: **http://localhost:8080/v3/swagger-ui/index.html**  
👉 Open raw OpenAPI docs: **http://localhost:8080/v3/api-docs**

This gives you an **interactive API explorer** for testing endpoints without Postman.

---

## 🧪 Testing

Run tests with:

```bash
./gradlew test
```

---

## 📌 Future Improvements

- 🧑‍💼 Admin panel for managing users and accounts
- 📱 REST API documentation with Swagger / OpenAPI
- 🏦 Support for multiple currencies
- 📊 Transaction history & reporting
- 🛡 Enhanced security with JWT tokens & role-based access
- ☁️ Docker & CI/CD setup

---

## 👨‍💻 Author

Built with ❤️ by **Starluck**
- Backend Engineer