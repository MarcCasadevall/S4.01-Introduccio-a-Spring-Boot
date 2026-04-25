# S4.01 – UserApi

**Description**: First contact with Spring Boot and REST API development. A fully layered user management API built from scratch, applying SOLID principles, the Repository and Service Layer patterns, and tested with MockMvc and Mockito.

---

## 📌 Exercise Statement

Build a minimal but functional REST API that receives and returns JSON data using HTTP methods, following clean architecture practices from the ground up.

Key concepts covered:
- REST API design and HTTP methods (GET, POST)
- Spring Boot controllers with `@RestController`
- Receiving data via `@PathVariable`, `@RequestParam` and `@RequestBody`
- Inversion of Control (IoC), Dependency Injection and Spring Beans
- Layered architecture: Controller → Service → Repository
- Repository pattern with in-memory implementation
- Service layer with business logic and email uniqueness validation
- Unit testing with Mockito and TDD
- Integration testing with `@SpringBootTest` and `MockMvc`

---

## ✨ Features

- `GET /health` — Health check endpoint returning `{ "status": "OK" }`
- `GET /users` — List all users (supports optional `?name=` filter)
- `GET /users/{id}` — Get a user by UUID (returns 404 if not found)
- `POST /users` — Create a new user with auto-generated UUID and unique email validation

---

## 🔧 Technologies

- **Backend**: Java 21, Spring Boot 3.2.5
- **Build tool**: Maven
- **Testing**: JUnit 5, MockMvc, Mockito
- **Server**: Apache Tomcat (embedded)
- **Manual testing**: Postman

---

## 🚀 Installation & Execution

1. Clone the repository:
```bash
   git clone <repository-url>
   cd userapi
```

2. Run the application:
```bash
   mvn spring-boot:run
```
   The API will start at `http://localhost:9000`

3. Or build and run as a standalone JAR:
```bash
   mvn clean package
   java -jar target/userapi-0.0.1-SNAPSHOT.jar
```

4. Run all tests:
```bash
   mvn test
```

---

## 📸 Demo

![JAR execution evidence](evidenciaJar.png)

---

## 🧩 Architecture & Technical Decisions

### Layered Architecture
