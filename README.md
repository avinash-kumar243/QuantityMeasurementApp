# 🚀 Quantity Measurement Application  
## 📏 Test-Driven Development (TDD) | OOP | Clean Code | DRY Principle  

---

## 🧠 Project Overview

The **Quantity Measurement Application** is designed to validate equality, conversion, and arithmetic operations between different measurement units such as Feet, Inches, Yards, etc.

This project was implemented incrementally using:

- ✅ Test-Driven Development (TDD)
- ✅ Feature Branch Workflow
- ✅ Clean Code Practices
- ✅ DRY (Don't Repeat Yourself) Principle
- ✅ Proper Unit Conversion Strategy

---

# 🌳 Git Workflow

```
main
 └── dev
      ├── feature/UC1-FeetEquality
      ├── feature/UC2-InchEquality
      ├── feature/UC3-GenericLength
      ├── feature/UC4-YardEquality
      ├── feature/UC5-UnitConversoion
      ├── feature/UC6-UnitAddition
      ├── feature/UC7-TargetUnitAddition
      ├── feature/UC8-StandaloneUnit
      ├── feature/UC9-WeightMeasurement
      ├── feature/UC10-GenericQuantity
      ├── feature/UC11-VolumeMeasurement
      ├── feature/UC12-SubtractionAndDivision
      ├── feature/UC13-CentralizedArithmeticLogic
      ├── feature/UC14-TemperatureMeasurementwithSelectiveArithmetic
      ├── feature/UC15-N-Tier
      ├── feature/UC16-JDBCPersistence
      ├── feature/UC17-SpringBackend
      └── feature/UC18-GoogleAuthUserManagement
```

---

## 📅 17 Feb 2026  
### 🔹 UC1 – Feet Measurement Equality  
**Branch:** `feature/UC1-FeetEquality`

### 🎯 Objective
- Validate equality of two Feet measurements  
- Implement proper `.equals()` method  
- Follow TDD approach  

### ✅ Implementation
- Created Feet class  
- Implemented equality logic  
- Handled null and type safety  
- Wrote JUnit 5 test cases  
- [feature/UC1-FeetEquality](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC1-FeetEquality/src)

---

## 📅 18 Feb 2026  
### 🔹 UC2 – Feet and Inches Measurement Equality  
**Branch:** `feature/UC2-InchEquality`

### 🎯 Objective
- Compare Feet and Inches  
- Ensure 12 inches = 1 foot  

### ✅ Implementation
- Introduced conversion logic  
- Implemented base unit comparison  
- Improved equality handling  
- [feature/UC2-InchEquality](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC2-InchEquality/src)

---
## 📅 19 Feb 2026  
### 🔹 UC3 – Generic Quantity Class (DRY Principle)  
**Branch:** `feature/UC3-GenericLength`

### 🎯 Objective
- Remove duplication  
- Introduce reusable `Quantity` class  
- Apply DRY principle  

### ✅ Implementation
- Centralized conversion logic  
- Removed unit-specific duplication  
- Improved abstraction  
- [feature/UC3-GenericLength](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC3-GenericLength/src)

---

## 📅 20 Feb 2026  
### 🔹 UC4 – Extended Unit Support  
**Branch:** `feature/UC4-YardEquality`

### 🎯 Objective
- Support additional units (Yard, etc.)  
- Make system scalable  

### ✅ Implementation
- Introduced Unit Enum  
- Base unit conversion mapping  
- Easily extensible structure  
- [feature/UC4-YardEquality](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC4-YardEquality/src)

---

## 📅 20 Feb 2026  
### 🔹 UC5 – Unit-to-Unit Conversion  
**Branch:** `feature/UC5-UnitConversoion`

### 🎯 Objective
- Convert one unit into another  

### ✅ Implementation
- Implemented `convertTo()` method  
- Centralized conversion logic  
- Ensured precision-safe calculations  
- [feature/UC5-UnitConversoion](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC5-UnitConversion/src)

---

## 📅 21 Feb 2026  
### 🔹 UC6 – Addition of Two Length Units  
**Branch:** `feature/UC6-UnitAddition`

### 🎯 Objective
- Add two quantities correctly  

### ✅ Implementation
- Converted to base unit before addition  
- Accurate arithmetic operations  
- Clean and reusable method structure  
- [feature/UC6-UnitAddition](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC6-UnitAddition/src)

---

## 📅 22 Feb 2026  
### 🔹 UC7 – Addition with Target Unit Specification  
**Branch:** `feature/UC7-TargetUnitAddition`

### 🎯 Objective
- Add two quantities  
- Return result in specified target unit  

### ✅ Implementation
- Implemented `add(quantity, targetUnit)`  
- Converted result before returning  
- Maintained precision and scalability  
- [feature/UC7-TargetUnitAddition](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition/src)

---

## 📅 23 Feb 2026  
### 🔹 UC8 – Refactoring Unit Enum to Standalone  
**Branch:** `feature/UC8-StandaloneUnit`

### 🎯 Objective
- Separate Unit enum from Quantity class  
- Improve modularity  
- Enable multi-category support  

### ✅ Implementation
- Moved Unit enum to standalone file  
- Improved separation of concerns  
- Increased flexibility for new categories  
- [feature/UC8-StandaloneUnit](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC8-StandaloneUnit/src)

---

## 📅 24 Feb 2026  
### 🔹 UC9 – Weight Measurement  
**Branch:** `feature/UC9-WeightMeasurement`

### 🎯 Objective
- Extend application to support Weight category  
- Maintain clean architecture  

### ✅ Implementation
- Introduced Weight units (Gram, Kilogram, etc.)  
- Implemented base unit conversion  
- Ensured category-safe equality  
- Prevented cross-category comparison (Length ≠ Weight)  
- [feature/UC9-WeightMeasurement](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC9-WeightMeasurement/src)

---

## 📅 24 Feb 2026  
### 🔹 UC10 – Generic Quantity Class with Unit Interface for Multi-Category Support  
**Branch:**  `feature/UC10-GenericQuantity`

### 🎯 Objective
- Create a fully generic Quantity system  
- Support multiple measurement categories  
- Apply interface-based design  

### ✅ Implementation
- Introduced `Unit` interface  
- Implemented category-specific enums (LengthUnit, WeightUnit)  
- Created Generic `Quantity<T extends Unit>` class  
- Ensured:
  - Type-safe unit handling  
  - Category-safe operations  
  - Scalable architecture  
  
- [feature/UC10-GenericQuantity](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC10-GenericQuantity/src)

---

## 📅 25 Feb 2026  
### 🔹 UC11 – Volume Measurement Equality, Conversion & Addition  
**Branch:**  `feature/UC11-VolumeMeasurement`

### 🎯 Objective
- Introduce Volume measurement category  
- Support Litre, Millilitre, Gallon  
- Enable equality, conversion, and addition  

### ✅ Implementation
- Created `VolumeUnit` enum  
- Implemented base unit strategy (Millilitre as base)  
- Enabled cross-unit comparison (1000 ml = 1 Litre)  
- Added addition support within category  
- Prevented cross-category arithmetic  

### 🏗 Result
System now supports:
- Length
- Weight 
- Volume  

- [feature/UC11-VolumeMeasurement](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC11-VolumeMeasurement/src)

---

## 📅 26 Feb 2026  
### 🔹 UC12 – Subtraction & Division Operations  
**Branch:**  `feature/UC12-SubtractionAndDivision`

### 🎯 Objective
- Extend arithmetic support  
- Enable subtraction between quantities  
- Support division operations  

### ✅ Implementation
- Added `subtract()` method  
- Added `divide()` method  
- Ensured same-category enforcement  
- Maintained base unit conversion logic  
- Precision-safe arithmetic  

### ⚙ Design Principle
All arithmetic operations follow:
1. Convert to base unit  
2. Perform operation  
3. Convert to target unit  

- [feature/UC12-SubtractionAndDivision](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC12-SubtractionAndDivision/src)

---

## 📅 27 Feb 2026  
### 🔹 UC13 – Centralized Arithmetic Logic (DRY Enforcement)  
**Branch:**  `feature/UC13-CentralizedArithmeticLogic`

### 🎯 Objective
- Remove duplication across add, subtract, divide  
- Centralize arithmetic handling  

### ✅ Implementation
- Introduced common internal arithmetic handler  
- Removed repeated conversion code  
- Improved maintainability  
- Reduced logic duplication  
- Strengthened DRY compliance  

### 🧠 Engineering Impact
- Cleaner architecture  
- Easier future feature additions  
- Reduced error-prone code blocks  
- [feature/UC13-CentralizedArithmeticLogic](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmeticLogic/src)

---

## 📅 28 Feb 2026  
### 🔹 UC14 – Temperature Measurement with Selective Arithmetic Support & Measurable Refactoring  
**Branch:**  `feature/UC14-TemperatureMeasurementwithSelectiveArithmetic`

### 🎯 Objective
- Add Temperature measurement category  
- Support Celsius & Fahrenheit  
- Restrict invalid arithmetic operations  
- Refactor measurable behavior  

### ⚠ Special Challenge
Temperature is **not purely linear like length or weight**  
Conversion requires formula-based transformation:
- °F = (°C × 9/5) + 32  

### ✅ Implementation
- Created `TemperatureUnit` enum  
- Implemented formula-based conversion logic  
- Allowed equality comparison  
- Restricted unsupported arithmetic (e.g., adding two temperatures directly)  
- Applied measurable abstraction refactoring  

### 🧠 Architectural Enhancement
- Introduced selective arithmetic capability  
- Improved domain modeling  
- Applied behavior-driven restrictions
- [feature/UC14-TemperatureMeasurementwithSelectiveArithmetic](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurementwithSelectiveArithmetic/src)

---

# 🏗 UC15 – N-Tier Architecture Refactoring
📅 **10 March 2026**  
🔖 **Branch:** `feature/UC15-N-Tier`

## 🎯 Objective
Refactor the application into a **clean N-Tier Architecture** to improve:

- Maintainability
- Scalability
- Testability
- Separation of concerns

---

## 🏗 System Architecture

```
Presentation Layer
       │
       ▼
Controller Layer
       │
       ▼
Service Layer
       │
       ▼
Repository Layer
       │
       ▼
Database / Persistence
```

---

## 📦 Project Layer Structure

| Layer | Responsibility |
|------|----------------|
| **Controller** | Handles API requests and responses |
| **Service** | Business logic and validation |
| **DTO** | Data transfer between layers |
| **Entity / Model** | Core domain representation |
| **Repository** | Data persistence abstraction |

---

## 🧠 Concepts Implemented

- N-Tier Architecture Principles
- Data Transfer Objects (DTO)
- Service Oriented Design
- Dependency Injection Pattern
- Error Handling as Data
- Immutable Data Objects
- Layered System Design

---

## ⚙ SOLID Principles Applied

| Principle | Application |
|----------|-------------|
| **SRP** | Each layer has a single responsibility |
| **OCP** | Easily extendable measurement units |
| **LSP** | Unit implementations interchangeable |
| **ISP** | Small measurable interfaces |
| **DIP** | High level modules depend on abstractions |

---

## ✅ Implementation Highlights

- Introduced **DTO layer for API communication**
- Implemented **Service layer for business logic**
- Created **Repository abstraction**
- Applied **Dependency Injection**
- Improved **testability and modularity**

🔗 Repository  
- [feature/UC15-N-Tier](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC15-N-Tier/src/main/java/com/apps/quantitymeasurementapp)

---

# 🗄 UC16 – JDBC Database Integration
📅 **14 March 2026**  
🔖 **Branch:** `feature/UC16-JDBCPersistence`

## 🎯 Objective
Enable **persistent storage of measurement data** using **JDBC and relational database design**.

---

## 🧠 Technologies Used

- Java JDBC
- Maven Dependency Management
- SQL Database
- Connection Pooling
- Prepared Statements

---

## ⚙ Core Concepts Applied

- JDBC Connection Handling
- Connection Pooling
- Parameterized SQL Queries
- Resource Management
- Transaction Handling
- Exception Hierarchy
- Configuration Management

---

## ✅ Implementation

- Integrated **JDBC persistence layer**
- Implemented **Connection Pool**
- Used **Prepared Statements for security**
- Created **Repository layer for database operations**
- Applied **SQL best practices**

🔗 Repository  
- [feature/UC16-JDBCPersistence](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC16-JDBCPersistence/src/main/java/com/app/quantitymeasurementapp)

---

# 🌱 UC17 – Spring Boot Backend
📅 **17 Mar 2026**  
🔖 **Branch:** `feature/UC17-SpringBackend`

## 🎯 Objective
Develop a **Spring Boot backend API** for quantity measurement services.

---

## 🧠 Spring Concepts Used

- Spring Framework
- Spring Boot
- Spring MVC
- REST API Development
- Dependency Injection
- Spring Service Layer
- Logging Framework
- Spring Data JPA

---

## 🏗 Backend Architecture

```
Client Request
      │
      ▼
REST Controller
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
Database
```

---

## 📦 Core Components

| Component | Role |
|----------|------|
| Controller | REST API endpoints |
| Service | Business logic |
| Repository | Data persistence |
| Entity | JPA domain objects |

---

## ✅ Implementation

- Created **Spring Boot application**
- Developed **RESTful APIs**
- Implemented **Service and Repository layers**
- Integrated **Spring Data JPA**
- Added **structured logging**

🔗 Repository  
- [feature/UC17-SpringBackend](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC17-SpringBackend/src/main/java/com/app/quantitymeasurementapp)

---

# 🔐 UC18 – Google Authentication & User Management
📅 **22 Mar 2026**  
🔖 **Branch:** `feature/UC18-GoogleAuthUserManagement`

## 🎯 Objective
Secure the application using **Google OAuth2 authentication and JWT authorization**.

---

## 🔑 Security Technologies

- Spring Security
- OAuth2 Authentication
- Google Sign-In
- JWT (JSON Web Token)
- Secure Session Handling

---

## 🔐 Authentication Flow

```
User Login
   │
   ▼
Google OAuth Authentication
   │
   ▼
Backend Validation
   │
   ▼
JWT Token Generated
   │
   ▼
Secure API Access
```

---

## ✅ Implementation

- Configured **Spring Security**
- Integrated **Google OAuth2 login**
- Implemented **JWT token generation**
- Added **secure API authorization**
- Created **user role management**

🔗 Repository  
- [feature/UC18-GoogleAuthUserManagement](https://github.com/avinash-kumar243/QuantityMeasurementApp/tree/feature/UC18-GoogleAuthUserManagement/src/main/java/com/app/quantitymeasurementapp)

---