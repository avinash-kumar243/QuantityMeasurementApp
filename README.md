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

---