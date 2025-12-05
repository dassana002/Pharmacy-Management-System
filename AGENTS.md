# Agent Guidelines - Pharmacy Management System

## Build & Test Commands
- **Build**: `mvn clean compile`
- **Run Application**: `mvn clean javafx:run`
- **Run Tests**: `mvn test`
- **Run Single Test**: `mvn test -Dtest=TestClassName#testMethodName`
- **Package**: `mvn clean package`

## Project Overview
JavaFX desktop application (Java 25) with MySQL database backend. Stack: Maven, JUnit 5, JavaFX 21.0.6, MySQL Connector 9.5.0, BCrypt 0.4 for password hashing.

## Code Style Guidelines
**Package Structure**: `lk.ijse.pharmacymanagementsystem.*` with subpackages: Controller, Model, Dto, DBconnection

**Imports**: Order as JavaFX → java.* → third-party (jbcrypt, mysql). Use explicit imports, no wildcards.

**Naming**: PascalCase for classes (e.g., `EmployeeModel`), camelCase for methods/variables. Avoid abbreviations.

**Formatting**: 4 spaces indentation, braces on same line, max 120 chars per line.

**Error Handling**: Declare `throws SQLException` for database methods. Check null explicitly using explicit null checks (not elvis operators). Use BCrypt for passwords (never plaintext).

**DTO/Model Separation**: DTOs for data transfer, Models for DB operations. Keep business logic in Models.

**Database**: Use PreparedStatements to prevent SQL injection. Implement Singleton for DB connections: `DBConnection.getInstance()`. External storage for DB credentials (never hardcoded).

**Security**: Hash passwords with BCrypt (e.g., `BCrypt.hashpw()` and `BCrypt.checkpw()`). Validate all user input in Controllers before passing to Models. Never store plaintext credentials.
