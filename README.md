# RETAIL POS SYSTEM - CONSOLE APPLICATION
## Complete Java Project with File Handling (No GUI, No Database)

---

##  PROJECT OVERVIEW

This is a **Console-Based Retail Point of Sale (POS) System** developed using **Core Java** with **Text File Storage** for data persistence. The project demonstrates comprehensive use of Object-Oriented Programming concepts, File Handling, Exception Handling, and modular design.

---

##  TEAM STRUCTURE

This project is designed for a **team of 4 members**, with each member responsible for specific packages and modules:

### **Person 1 - Main Application & User Module**
- **Package:** `com.retailpos.main`
- **Files:** `MainApp.java`
- **Responsibilities:**
  - Menu-driven application interface
  - User authentication system
  - Main program flow control
  - Integration of all modules

### **Person 2 - Model Classes (OOP Design)**
- **Package:** `com.retailpos.model`
- **Files:** `Product.java`, `Customer.java`, `Bill.java`
- **Responsibilities:**
  - Entity class design
  - Encapsulation implementation
  - Data modeling
  - Inner class implementation (BillItem)

### **Person 3 - Business Logic Services**
- **Package:** `com.retailpos.service`
- **Files:** `InventoryService.java`, `BillingService.java`
- **Responsibilities:**
  - Inventory management logic
  - Billing operations
  - Customer management
  - CRUD operations
  - Report generation

### **Person 4 - File Handling & Exception Handling**
- **Package:** `com.retailpos.util` & `com.retailpos.exception`
- **Files:** 
  - `FileUtil.java`, `ValidationUtil.java`
  - `ProductNotFoundException.java`, `InsufficientStockException.java`, 
  - `InvalidInputException.java`, `AuthenticationException.java`
- **Responsibilities:**
  - File I/O operations
  - Data validation
  - Custom exception classes
  - Error handling utilities

---

##  PACKAGE STRUCTURE

```
com.retailpos
├── main
│   └── MainApp.java                    (Suraj Arya)
├── model
│   ├── Product.java                    (Aryan Pandey)
│   ├── Customer.java                   (Aryan Pandey)
│   └── Bill.java                       (Aryan Pandey)
├── service
│   ├── InventoryService.java           (Vaibhavi Anand)
│   └── BillingService.java             (Vaibhavi Anand)
├── util
│   ├── FileUtil.java                   (Atul Mishra)
│   └── ValidationUtil.java             (Atul Mishra)
└── exception
    ├── ProductNotFoundException.java   (Atul Mishra)
    ├── InsufficientStockException.java (Atul Mishra)
    ├── InvalidInputException.java      (Atul Mishra)
    └── AuthenticationException.java    (Atul Mishra)
```

---

##  FILE STORAGE STRUCTURE

```
data/
├── products.txt      - Product inventory data
├── customers.txt     - Customer information
├── bills.txt         - Generated bills/invoices
└── users.txt         - Login credentials
```

### File Formats:

**products.txt:**
```
ProductID|ProductName|Category|Price|StockQuantity|Description
P001|Laptop|Electronics|45000.00|15|Dell Inspiron 15 3000 Series
```

**customers.txt:**
```
CustomerID|CustomerName|PhoneNumber|Email|TotalPurchaseAmount
C001|Rahul Sharma|9876543210|rahul.sharma@email.com|0.0
```

**users.txt:**
```
Username|Password|Role
admin|admin123|ADMIN
cashier|cash123|CASHIER
```

**bills.txt:**
```
BillID|CustomerID|CustomerName|CashierName|BillDate|Subtotal|GST|TotalAmount|Items
B0001|C001|Rahul Sharma|admin|01-01-2026 10:30:00|45000.00|8100.00|53100.00|P001:Laptop:1:45000.00
```

---

##  JAVA CONCEPTS IMPLEMENTED

### 1. **Object-Oriented Programming (OOP)**
- **Encapsulation:** All model classes with private fields and public getters/setters
- **Inheritance:** Exception classes extending base Exception class
- **Polymorphism:** Method overloading in constructors and utilities
- **Abstraction:** Service layer abstracting business logic

### 2. **File Handling**
- FileReader / FileWriter
- BufferedReader / BufferedWriter
- Try-Catch-Finally blocks
- File existence checking
- Directory creation

### 3. **Collections Framework**
- ArrayList for dynamic lists
- HashMap for key-value storage
- Iterating collections

### 4. **Exception Handling**
- Custom exception classes
- Try-Catch blocks
- Exception propagation
- Meaningful error messages

### 5. **Advanced Concepts**
- Inner classes (BillItem inside Bill)
- Static methods and constants
- String parsing and tokenization
- Date/Time handling
- Wrapper classes (Integer, Double)
- Input validation

---

##  SYSTEM FEATURES

### 1. **User Authentication**
- Login system with username and password
- Role-based access (Admin/Cashier)
- Maximum 3 login attempts
- Credentials stored in users.txt

### 2. **Product Management**
- Add new products
- View all products
- Search products by ID/Name/Category
- Update product details
- Delete products
- Auto-generated Product IDs

### 3. **Inventory Management**
- Check stock availability
- Add stock to products
- Low stock alerts (stock < 10)
- Calculate total inventory value
- Stock reduction after billing

### 4. **Billing System**
- Create new bills
- Add multiple products to bill
- Calculate subtotal, GST (18%), and total
- Generate and display invoice
- Save bills to file
- Search bills

### 5. **Customer Management**
- Add new customers
- View all customers
- Search customers
- Track purchase history
- Auto-generated Customer IDs

### 6. **Reports**
- Sales report (total sales, GST, items sold)
- Inventory report
- Customer report

---

##  HOW TO RUN

### **Method 1: Using Scripts (Linux/Mac)**

1. **Compile the project:**
   ```bash
   chmod +x compile.sh
   ./compile.sh
   ```

2. **Run the application:**
   ```bash
   chmod +x run.sh
   ./run.sh
   ```

### **Method 2: Manual Compilation**

1. **Compile all files:**
   ```bash
   javac -d bin -sourcepath src src/com/retailpos/exception/*.java
   javac -d bin -sourcepath src src/com/retailpos/util/*.java
   javac -d bin -sourcepath src src/com/retailpos/model/*.java
   javac -d bin -sourcepath src src/com/retailpos/service/*.java
   javac -d bin -sourcepath src src/com/retailpos/main/*.java
   ```

2. **Run the application:**
   ```bash
   cd bin
   java com.retailpos.main.MainApp
   ```

### **Method 3: Using IDE**

1. Import the project into Eclipse/IntelliJ/NetBeans
2. Set `src` as source folder
3. Run `MainApp.java`

---

##  DEFAULT LOGIN CREDENTIALS

| Username | Password   | Role    |
|----------|------------|---------|
| admin    | admin123   | ADMIN   |
| cashier  | cash123    | CASHIER |
| manager  | manager123 | MANAGER |

---

##  SAMPLE WORKFLOW

### **Adding a Product:**
1. Login as admin
2. Select Product Management → Add New Product
3. Enter product details
4. Product saved to products.txt

### **Creating a Bill:**
1. Login as cashier
2. Select Billing System → Create New Bill
3. Enter Customer ID
4. Add products with quantities
5. Confirm billing
6. Invoice generated and saved
7. Stock automatically reduced

### **Checking Low Stock:**
1. Select Inventory Management → Low Stock Alert
2. View products with stock < 10
3. Add stock as needed

---

##  CODE QUALITY FEATURES

- **Proper Comments:** Every class and method documented
- **Developer Attribution:** Each file has developer name and responsibility
- **Modular Design:** Clear separation of concerns
- **Error Handling:** Comprehensive exception handling
- **Input Validation:** All user inputs validated
- **Clean Code:** Proper naming conventions and formatting
- **Resource Management:** Proper closing of file streams

---

##  LEARNING OUTCOMES

Students working on this project will learn:

1. **Core Java Concepts:**
   - OOP principles
   - File handling with text files
   - Exception handling
   - Collections framework

2. **Software Design:**
   - Package organization
   - Layered architecture
   - Separation of concerns
   - Modular programming

3. **Real-World Skills:**
   - Team collaboration
   - Code documentation
   - Error handling strategies
   - Data validation

4. **Problem Solving:**
   - Inventory management logic
   - Billing calculations
   - Search and filter operations
   - Report generation

---

##  TROUBLESHOOTING

### **Issue: Files not found**
- Ensure `data` directory exists in project root
- Check file paths in FileUtil.java

### **Issue: Compilation errors**
- Verify Java version (Java 8 or higher)
- Check CLASSPATH settings
- Ensure all files are in correct packages

### **Issue: Login failed**
- Check users.txt file exists
- Verify credentials format in file

---

##  IMPORTANT NOTES

1. **No GUI:** This is a console-based application
2. **No Database:** All data stored in text files
3. **Text Files Only:** Uses .txt files for storage
4. **File Handling:** Proper exception handling for file operations
5. **Data Persistence:** Data saved immediately after operations
6. **GST Calculation:** Fixed at 18% for all products

---

##  PROJECT SUBMISSION CHECKLIST

- All Java files with proper package structure
- Developer name mentioned in each file
- Sample data files (products.txt, customers.txt, users.txt)
- Compilation scripts
- README documentation
- Code compiles without errors
- Application runs successfully
- All features working as expected

---

##  SUPPORT

For issues or questions:
1. Check the README carefully
2. Review code comments and JavaDoc
3. Verify file paths and permissions
4. Check Java version compatibility

---

##  LICENSE

This project is created for educational purposes as part of a Core Java course assignment.

---

**Developed by Team of 4 Members (Suraj Arya, Aryan Pandey, Vabhavi Anand, Atul Mishra)**
**Technology Stack:** Core Java, File Handling, Console I/O
**Version:** 1.0
**Date:** January 2026