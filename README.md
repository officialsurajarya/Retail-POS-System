# Retail POS System (Console-Based)

A **Console-Based Retail Point of Sale (POS) System** developed using **Core Java**, designed as a **team project of 4 members**.
The project demonstrates **maximum Core Java concepts**, **file handling using `.txt` files**, and **real-world POS workflow**.

---

## Project Overview

This Retail POS System allows:

- User authentication (Admin / Cashier)
- Product inventory management
- Billing and invoice generation
- Persistent data storage using text files
- Modular, package-based team development

**Technology Used:** Core Java (No GUI, No Database)

---

## Team Structure

| Person   | Responsibility                       |
| -------- | ------------------------------------ |
| Person 1 | Main Application, Menu, User Login   |
| Person 2 | Model Classes (OOP Design)           |
| Person 3 | Inventory & Billing Logic            |
| Person 4 | File Handling, Utilities, Exceptions |

Each file contains a **header comment** showing:

- Developer name
- Module responsibility
- Java concepts used

---

## Project Structure

```
RetailPOSSystem/
│
├── users.txt
├── products.txt
├── customers.txt
├── bills.txt
│
└── src/
    └── com/
        └── retailpos/
            ├── main/
            │   └── MainApp.java
            │
            ├── model/
            │   ├── Product.java
            │   ├── Customer.java
            │   └── Bill.java
            │
            ├── service/
            │   ├── UserService.java
            │   ├── InventoryService.java
            │   └── BillingService.java
            │
            ├── util/
            │   ├── FileUtil.java
            │   └── ValidationUtil.java
            │
            └── exception/
                ├── AuthenticationException.java
                ├── ProductNotFoundException.java
                ├── InsufficientStockException.java
                └── InvalidInputException.java
```

---

## File-Based Data Storage

All data is stored using **text files (`.txt`)**.

### `users.txt`

```
admin,admin123,ADMIN
cashier,cash123,CASHIER
```

### `products.txt`

```
101,Soap,40,50
102,Shampoo,120,30
103,Rice,60,100
```

### `customers.txt`

```
1,Ramesh,9876543210
2,Suresh,9123456789
```

### `bills.txt`

```
(Generated automatically after billing)
```

---

## Login Details (For Testing)

### Admin Login

```
Username: admin
Password: admin123
```

### Cashier Login

```
Username: cashier
Password: cash123
```

---

## Core Java Concepts Used

- Object-Oriented Programming (OOP)

  - Encapsulation
  - Inheritance
  - Polymorphism
  - Abstraction

- Packages & Modular Design
- File Handling

  - FileReader / FileWriter
  - BufferedReader / BufferedWriter

- Collections Framework

  - ArrayList
  - HashMap

- Custom Exceptions
- Wrapper Classes
- String Handling
- Menu-Driven Console Application

---

## How to Run the Project

### Step 1

Open **IntelliJ IDEA / Eclipse / VS Code**

### Step 2

Create a Java project named:

```
RetailPOSSystem
```

### Step 3

Create packages exactly as shown in the structure

### Step 4

Place all `.txt` files in the **project root directory**

> (same level as `src` folder)

### Step 5

Run:

```
MainApp.java
```

---

## Sample Flow

1. User logs in using credentials from `users.txt`
2. Products are loaded from `products.txt`
3. User views products
4. Creates bill by entering product ID & quantity
5. Stock is reduced automatically
6. Invoice is displayed on console
7. Bill is saved to `bills.txt`

---

## Key Highlights

✔ Team-based development
✔ Real-world POS workflow
✔ Persistent storage without database
✔ Clean, readable code
✔ Interview & academic ready

---

## Viva / Interview Explanation (Short)

> “This is a console-based Retail POS System built using Core Java. It uses file handling for persistent storage, follows OOP principles, and is developed in a team-based modular architecture.”

---

## License

This project is developed for **educational purposes**.
