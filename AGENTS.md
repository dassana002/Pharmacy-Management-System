# Agent Guidelines - Pharmacy Management System

## Build & Test Commands
- **Build**: `mvn clean compile`
- **Run Application**: `mvn clean javafx:run`
- **Run Tests**: `mvn test`
- **Run Single Test**: `mvn test -Dtest=TestClassName#testMethodName`
- **Package**: `mvn clean package`

## Project Overview
JavaFX desktop application (Java 25) with MySQL database backend. Stack: Maven, JUnit 5, JavaFX 21.0.6, MySQL Connector 9.5.0, BCrypt 0.4. Main entry point: `lk.ijse.pharmacymanagementsystem.Launcher` (extends `javafx.application.Application`).

## Code Style Guidelines
**Package Structure**: `lk.ijse.pharmacymanagementsystem.*` with subpackages: `Controller`, `Model`, `Dto`, `DBconnection`

**Imports**: Order as JavaFX → java.* → third-party (jbcrypt, mysql). Use explicit imports, no wildcards. Never use `import java.util.*`.

**Naming**: PascalCase for classes (e.g., `EmployeeModel`), camelCase for methods/variables. Avoid abbreviations.

**Formatting**: 4 spaces indentation, opening braces on same line, max 120 chars per line.

**Types & Null Checks**: Use explicit `if (value != null)` checks. Never use implicit boolean conversion or elvis operators.

**Error Handling**: Database methods declare `throws SQLException`. Use try-catch only in Controllers, never in Models/Dto. Log exceptions properly; never fail silently.

**Architecture**: DTO classes (`EmployeeDTO`) for data transfer only. Model classes (`EmployeeModel`) contain DB logic via `PreparedStatement` queries. Controllers validate user input before calling Models. Singleton `DBConnection.getInstance().getConnection()` for database access.

**Security**: Hash passwords with BCrypt (`BCrypt.hashpw()` for hashing, `BCrypt.checkpw()` for verification). Validate all user input in Controllers. Never hardcode credentials or use plaintext passwords. Use `PreparedStatements` to prevent SQL injection.
