# E-Commerce System – Internship Task (Fawry)

## Overview

This is a Java-based e-commerce system developed as part of an internship challenge for Fawry Integrated Systems.  
The system simulates core e-commerce functionality with a focus on clean object-oriented design, real-world logic, and SOLID principles.

---

## Key Features

- Define products with name, price, and quantity.
- Support for:
  - Expirable products (e.g., Cheese, Biscuits)
  - Non-expirable products (e.g., TV, Mobile)
  - Shippable products (e.g., Cheese, TV) with weight
  - Non-shippable products (e.g., Mobile cards)
- Customers can:
  - Add products to a cart with limited quantity
  - Perform checkout
- Shipping service handles all shippable products.

---

## Business Rules

- Products cannot be added to the cart if quantity exceeds stock.
- Checkout is blocked if:
  - Cart is empty
  - Product is expired
  - Product is out of stock
  - Customer balance is insufficient
- Shipping fee is calculated based on product weight.
- During checkout, the system displays:
  - Subtotal
  - Shipping fees
  - Total amount paid
  - Customer remaining balance

---

## Interfaces Used

- `Shippable`: For items that can be shipped
  - `String getName()`
  - `double getWeight()`
- `Expirable`: For items with expiry dates
  - `boolean isExpired()`

---

## Assumptions

- Shipping cost is: `5 * productWeight`
- System date is used to evaluate expiration
- No external database or UI – logic is fully in-memory and console-based
- All scenarios are tested inside the `main` method

---

## SOLID Principles in Action

**Single Responsibility Principle**  
Each class is responsible for only one part of the system (e.g., Cart handles cart logic only).

**Open/Closed Principle**  
The system is extendable using interfaces and inheritance without modifying existing code.

**Liskov Substitution Principle**  
All subclasses of `Product` can replace their parent without breaking the program.

**Interface Segregation Principle**  
Separate interfaces are created for different behaviors (e.g., shipping vs. expiration).

**Dependency Inversion Principle**  
High-level modules like `ShippingService` depend on abstractions (`Shippable`), not on concrete classes.

---

## Sample Test Cases

- Successful checkout with valid products (TV, Cheese, Mobile card)
- Failure cases:
  - Product is expired
  - Cart is empty
  - Balance is insufficient
  - Requested quantity exceeds available stock

---

## How to Run

1. Compile the project using any Java IDE or terminal.
2. Run the `main()` method in the main class.
3. Observe the output in the console to verify all behaviors and edge cases.

---

## Author

Mohamed Waheed  
Backend Developer – Internship Applicant  
Fawry Integrated Systems  
