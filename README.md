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
      └── feature/UC14-TemperatureMeasurementwithSelectiveArithmetic
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
