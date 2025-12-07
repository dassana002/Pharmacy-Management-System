# Agent Guidelines - Pharmacy Management System

## Build & Test Commands
- **Build**: `mvn clean compile`
- **Run Application**: `mvn clean javafx:run`
- **Run Tests**: `mvn test`
- **Run Single Test**: `mvn test -Dtest=TestClassName#testMethodName`
- **Package**: `mvn clean package`

## Project Overview
JavaFX desktop application (Java 25) with MySQL database backend. Stack: Maven, JUnit 5, JavaFX 21.0.6, MySQL Connector 9.5.0, BCrypt 0.4 for password hashing. Main entry point: `lk.ijse.pharmacymanagementsystem.Launcher`.

## Code Style Guidelines
**Package Structure**: `lk.ijse.pharmacymanagementsystem.*` with subpackages: Controller, Model, Dto, DBconnection

**Imports**: Order as JavaFX → java.* → third-party (jbcrypt, mysql). Use explicit imports, no wildcards.

**Naming**: PascalCase for classes (e.g., `EmployeeModel`), camelCase for methods/variables. Avoid abbreviations.

**Formatting**: 4 spaces indentation, braces on same line, max 120 chars per line.

**Null Checks**: Use explicit null checks (`if (value != null)`), never rely on implicit boolean conversion or elvis operators.

**Error Handling**: Declare `throws SQLException` for database methods. Use try-catch in Controllers, never in Models. Log exceptions properly instead of silent failures.

**DTO/Model Separation**: DTOs for data transfer (e.g., `EmployeeDTO`), Models for DB operations (e.g., `EmployeeModel`). Keep business logic in Models, validation in Controllers.

**Database**: Use `PreparedStatements` to prevent SQL injection. Singleton pattern for DB connections: `DBConnection.getInstance().getConnection()`. Store credentials externally (never hardcoded).

**Security**: Hash passwords with BCrypt (`BCrypt.hashpw()` for hashing, `BCrypt.checkpw()` for verification). Validate all user input in Controllers before passing to Models. Never use plaintext passwords.
