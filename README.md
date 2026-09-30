
# Subscription Manager

[![Java](https://img.shields.io/badge/Java-22-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)

A robust and modern backend service for managing personal or business subscriptions. It allows users to track recurring payments, categorize them, and monitor upcoming payment dates and total expenses. The application is built with Java 22 and Spring Boot 3, featuring secure authentication via Keycloak and seamless containerized deployment with Docker.

## 🚀 Features

- **Subscription Management**: Full CRUD operations for subscription records (service name, amount, currency, next payment date).
- **Categorization**: Organize subscriptions into custom categories (e.g., Entertainment, Education, Utilities).
- **Financial Overview**: Dedicated endpoint to retrieve aggregated total amounts spent per category.
- **Secure Authentication**: Role-based access control and protected API endpoints powered by Keycloak (OAuth2 / OpenID Connect).
- **Modern Architecture**: Clean, layered architecture (Controller, DTO, Service, Repository, Mapper) following Spring Boot best practices.
- **Containerized Environment**: Ready-to-run local setup using Docker Compose (includes PostgreSQL and Keycloak with pre-configured realms).

## 🛠️ Tech Stack

- **Language**: Java 22 (with preview features enabled)
- **Framework**: Spring Boot 3.3.0
- **Security**: Spring Security, OAuth2 Resource Server, Keycloak 22.0.1
- **Database**: PostgreSQL (via Spring Data JPA & Hibernate 6.5.2)
- **Build Tool**: Maven
- **Utilities**: Lombok, MapStruct
- **Containerization**: Docker & Docker Compose

## 📋 Prerequisites

Before running the project, ensure you have the following installed on your machine:
- [Java 22 JDK](https://jdk.java.net/22/)
- [Maven 3.8+](https://maven.apache.org/)
- [Docker & Docker Compose](https://www.docker.com/)

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/Tokar08/SubscriptionManager.git
cd SubscriptionManager
```

### 2. Start Infrastructure Services (Docker)
The project includes a `docker-compose.yml` file that spins up the required PostgreSQL databases and a Keycloak instance with pre-configured realms.

```bash
docker-compose up -d
```
*Note: Wait a few moments for Keycloak to initialize and import the realm configuration from the `./realms` directory.*

### 3. Build the Project
Compile the project using Maven. This step is required to generate MapStruct mapper implementations.

```bash
mvn clean install
```

### 4. Run the Application
Start the Spring Boot application:

```bash
mvn spring-boot:run
```
The backend server will start on `http://localhost:7878`.

## 📡 API Endpoints

The API is prefixed with `/api/v1`. Protected routes require a valid Bearer token from Keycloak.

### Categories
- `GET /api/v1/categories` – Retrieve all categories.
- `GET /api/v1/categories/{id}` – Retrieve a specific category by ID.
- `POST /api/v1/categories` – Create a new category *(Requires Authentication)*.
- `PUT /api/v1/categories/{id}` – Update an existing category *(Requires Authentication)*.
- `DELETE /api/v1/categories/{id}` – Delete a category *(Requires Authentication)*.

### Subscriptions
- `GET /api/v1/subscriptions/all` – Retrieve all subscriptions *(Requires Authentication)*.
- `GET /api/v1/subscriptions` – Retrieve subscriptions for the authenticated user *(Requires Authentication)*.
- `GET /api/v1/subscriptions/{id}` – Retrieve a specific subscription by ID.
- `POST /api/v1/subscriptions` – Create a new subscription *(Requires Authentication)*.
- `PUT /api/v1/subscriptions/{id}` – Update an existing subscription *(Requires Authentication)*.
- `DELETE /api/v1/subscriptions/{id}` – Delete a subscription.
- `GET /api/v1/subscriptions/total-amounts` – Get aggregated total amounts for each category.

#### Example Request: Create Subscription
```http
POST http://localhost:7878/api/v1/subscriptions
Authorization: Bearer <your_keycloak_token>
Content-Type: application/json

{
  "serviceName": "Spotify",
  "nextPaymentDate": "2024-07-24T00:00:00",
  "amount": 500,
  "currency": "USD",
  "categoryId": "ad1c8a8e-a415-49a5-b092-fcc6d94338cc"
}
```

## ⚙️ Configuration

Key application properties (like database URLs and Keycloak realm settings) can be adjusted in `src/main/resources/application.properties` (or `.yml`). 

**Default Local Development Credentials:**
- **Keycloak**: `http://localhost:8081` | User: `admin` | Password: `admin`
- **Backend Database**: `localhost:5432` | User: `postgres` | Password: `postgres`
- **Auth Database**: Internal Docker network only (not exposed to host) | User: `norma` | Password: `norma`

## 🧪 Testing

Run the automated test suite using Maven:

```bash
mvn test
```
