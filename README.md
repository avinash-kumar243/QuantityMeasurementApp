# 🚀 Quantity Measurement Application  
## 📏 Test-Driven Development (TDD) | OOPS | Clean Code | DRY Principle  

---

## 🧠 Project Overview

The **Quantity Measurement Application** is designed to validate equality, conversion, and arithmetic operations between different measurement units such as LengthUnits, WeightUnits, VolumnUnits and TemperatureUnits.
This project was implemented incrementally using:

- ✅ Test-Driven Development (TDD)
- ✅ Feature Branch Workflow
- ✅ Clean Code Practices
- ✅ DRY (Don't Repeat Yourself) Principle
- ✅ Proper Unit Conversion Strategy

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