# RETAIL POS SYSTEM - PROJECT STRUCTURE

## Complete Directory Structure

```
RetailPOSSystem/
│
├── src/                                   Source code directory
│   └── com/
│       └── retailpos/
│           ├── main/
│           │   └── MainApp.java           [Suraj Arya] Menu-driven application
│           │
│           ├── model/
│           │   ├── Product.java           [Aryan Pandey] Product entity
│           │   ├── Customer.java          [Aryan Pandey] Customer entity
│           │   └── Bill.java              [Aryan Pandey] Bill entity with BillItem inner class
│           │
│           ├── service/
│           │   ├── InventoryService.java  [Vaibhavi Anand] Inventory management logic
│           │   └── BillingService.java    [Vaibhavi Anand] Billing & customer logic
│           │
│           ├── util/
│           │   ├── FileUtil.java          [Atul Mishra] File I/O operations
│           │   └── ValidationUtil.java    [Atul Mishra] Input validation
│           │
│           └── exception/
│               ├── ProductNotFoundException.java        [Atul Mishra]
│               ├── InsufficientStockException.java      [Atul Mishra]
│               ├── InvalidInputException.java           [Atul Mishra]
│               └── AuthenticationException.java         [Atul Mishra]
│
├── data/                                  # Data storage directory
│   ├── products.txt                       # Product inventory data
│   ├── customers.txt                      # Customer information
│   ├── bills.txt                          # Generated bills
│   └── users.txt                          # Login credentials
│
├── bin/                                   # Compiled .class files (auto-generated)
│
└── README.md                               # Project documentation
```

---

## File Count Summary

### Java Source Files: 13 files
- Main: 1 file
- Model: 3 files
- Service: 2 files
- Util: 2 files
- Exception: 4 files

### Data Files: 4 files
- products.txt
- customers.txt
- bills.txt
- users.txt

---

## Lines of Code (Approximate)

| Package      | Files | Total Lines |
|--------------|-------|-------------|
| main         | 1     | 650+        |
| model        | 3     | 700+        |
| service      | 2     | 800+        |
| util         | 2     | 350+        |
| exception    | 4     | 200+        |
| **TOTAL**    | **13**| **2700+**   |

---

## Team Responsibility Breakdown

### Suraj Arya: Main Application & User Module (650+ lines)
**Package:** com.retailpos.main
**Files:**
- MainApp.java
**Concepts:**
- Menu-driven programming
- Scanner input handling
- User authentication
- Switch-case statements
- Exception handling
- Module integration

---

### Aryan Pandey: Model Classes (700+ lines)
**Package:** com.retailpos.model
**Files:**
- Product.java
- Customer.java
- Bill.java (with BillItem inner class)
**Concepts:**
- Encapsulation
- Constructor overloading
- Getters/Setters
- toString() override
- Inner classes
- Data modeling
- Object serialization to strings

---

### Vaibhavi Anand: Business Logic Services (800+ lines)
**Package:** com.retailpos.service
**Files:**
- InventoryService.java
- BillingService.java
**Concepts:**
- Business logic implementation
- CRUD operations
- HashMap usage
- ArrayList operations
- Service layer design
- Transaction management
- Report generation

---

### Atul Mishra: File Handling & Exception Handling (550+ lines)
**Packages:** com.retailpos.util, com.retailpos.exception
**Files:**
- FileUtil.java
- ValidationUtil.java
- ProductNotFoundException.java
- InsufficientStockException.java
- InvalidInputException.java
- AuthenticationException.java
**Concepts:**
- File I/O operations
- BufferedReader/Writer
- Custom exceptions
- Input validation
- Static utility methods
- Try-catch-finally blocks

---

## Data Flow Diagram

```
User Input (Scanner)
        ↓
MainApp (Menu System)
        ↓
ValidationUtil (Input Validation)
        ↓
Service Layer (Business Logic)
        ↓
Model Classes (Data Objects)
        ↓
FileUtil (File Operations)
        ↓
Text Files (.txt)
```

---

## Method Call Flow Example (Creating a Bill)

```
1. MainApp.createBill()
   ↓
2. ValidationUtil.validateCustomerId()
   ↓
3. BillingService.createBill()
   ↓
4. BillingService.getCustomer()
   ↓
5. FileUtil.readFromFile(customers.txt)
   ↓
6. Customer.fromFileString()
   ↓
7. Bill constructor
   ↓
8. MainApp.addItemToBill()
   ↓
9. InventoryService.checkStock()
   ↓
10. InventoryService.getProduct()
    ↓
11. Bill.addItem()
    ↓
12. BillingService.completeBilling()
    ↓
13. InventoryService.reduceStock()
    ↓
14. FileUtil.updateLine(products.txt)
    ↓
15. FileUtil.writeToFile(bills.txt)
    ↓
16. Bill.displayInvoice()
```

---

## Exception Handling Flow

```
User Input Error
        ↓
InvalidInputException → Caught in MainApp
        ↓
Display error message
        ↓
Re-prompt user

Product Not Found
        ↓
ProductNotFoundException → Caught in Service Layer
        ↓
Return to menu

Insufficient Stock
        ↓
InsufficientStockException → Caught in Billing
        ↓
Cancel transaction / Show available stock

File I/O Error
        ↓
IOException → Caught in FileUtil
        ↓
Error logged / Graceful degradation
```

---

## Key Design Patterns Used

1. **Layered Architecture**
   - Presentation Layer (MainApp)
   - Business Logic Layer (Services)
   - Data Access Layer (FileUtil)
   - Data Layer (Model classes)

2. **Singleton Pattern** (Implicit)
   - Single InventoryService instance
   - Single BillingService instance

3. **Factory Pattern** (Partial)
   - fromFileString() methods in models
   - Auto-ID generation

4. **Repository Pattern**
   - FileUtil as data repository
   - CRUD operations abstracted

---

## File Format Specifications

### products.txt Format:
```
Field1|Field2|Field3|Field4|Field5|Field6
ProductID|ProductName|Category|Price|StockQuantity|Description
```

### customers.txt Format:
```
Field1|Field2|Field3|Field4|Field5
CustomerID|CustomerName|PhoneNumber|Email|TotalPurchaseAmount
```

### bills.txt Format:
```
Field1|Field2|Field3|Field4|Field5|Field6|Field7|Field8|Field9
BillID|CustomerID|CustomerName|CashierName|BillDate|Subtotal|GST|TotalAmount|Items(ProductID:Name:Qty:Price;...)
```

### users.txt Format:
```
Field1|Field2|Field3
Username|Password|Role
```

---

## Testing Scenarios

### Test Case 1: Add Product
1. Login as admin
2. Navigate to Product Management
3. Add product with valid data
4. Verify in products.txt
5. View product list

### Test Case 2: Create Bill
1. Login as cashier
2. Navigate to Billing
3. Select customer
4. Add multiple products
5. Complete billing
6. Verify stock reduction
7. Check bill in bills.txt

### Test Case 3: Low Stock Alert
1. Reduce product stock below 10
2. Navigate to Inventory Management
3. Check low stock alert
4. Add stock
5. Verify update

### Test Case 4: Exception Handling
1. Try to add duplicate product ID
2. Try to bill non-existent product
3. Try to bill with insufficient stock
4. Try invalid login credentials
5. Try invalid input formats

---

## Performance Considerations

1. **File Operations**
   - Files loaded at service initialization
   - In-memory operations (HashMap/ArrayList)
   - File written only on changes

2. **Search Operations**
   - Linear search for small datasets
   - Case-insensitive search
   - Multiple field matching

3. **Memory Usage**
   - All data loaded in memory
   - Suitable for small to medium datasets
   - Consider pagination for large datasets

---

## Future Enhancements (Optional)

1. Database integration (MySQL/PostgreSQL)
2. GUI using Swing/JavaFX
3. Barcode scanning integration
4. Email invoice generation
5. Multi-currency support
6. Employee management
7. Discount and promotion system
8. Backup and restore functionality
9. Advanced reporting (charts, graphs)
10. Cloud storage integration

---

## Conclusion

This project provides a comprehensive learning experience in:
- Core Java programming
- File handling without database
- Object-oriented design
- Team collaboration
- Real-world application development
- Problem-solving skills

The modular structure allows easy maintenance, testing, and future enhancements.