# RETAIL POS SYSTEM - TEAM MEMBER GUIDE

## TEAM DISTRIBUTION & RESPONSIBILITIES

---

## PERSON 1 (Suraj Arya): Main Application & User Module

### **Your Package:** `com.retailpos.main`

### **Your File:** `MainApp.java`

### **Your Responsibilities:**
1. Create the main menu-driven application
2. Implement user authentication system
3. Handle all user input/output
4. Integrate all modules together
5. Create menu systems for all features
6. Handle exceptions at application level

### **Java Concepts You'll Use:**
-  Scanner for input
-  Switch-case statements
-  While loops for menu
-  Exception handling (try-catch)
-  Method calls to service classes
-  String manipulation
-  File reading for authentication

### **Your Tasks Checklist:**
- [ ] Create welcome screen
- [ ] Implement login system with file-based authentication
- [ ] Create main menu with 6 options
- [ ] Create sub-menus for:
  - Product Management
  - Inventory Management
  - Billing System
  - Customer Management
  - Reports
- [ ] Handle all user inputs with validation
- [ ] Call service methods appropriately
- [ ] Display success/error messages
- [ ] Implement exit functionality

### **Sample Code Structure:**
```java
public class MainApp {
    private static Scanner scanner;
    private static InventoryService inventoryService;
    private static BillingService billingService;
    
    public static void main(String[] args) {
        // Initialize
        // Authenticate
        // Main menu loop
    }
    
    private static boolean authenticateUser() { }
    private static void displayMainMenu() { }
    private static void productManagementMenu() { }
    private static void billingMenu() { }
    // ... more methods
}
```

### **Testing Your Module:**
1. Test login with valid/invalid credentials
2. Test all menu navigations
3. Test input validation
4. Test integration with other modules
5. Test exit functionality

---

##  PERSON 2 (Aryan Pandey): Model Classes (OOP Design)

### **Your Package:** `com.retailpos.model`

### **Your Files:**
1. `Product.java`
2. `Customer.java`
3. `Bill.java` (with inner class `BillItem`)

### **Your Responsibilities:**
1. Design data models with proper encapsulation
2. Implement getters and setters
3. Create constructor overloading
4. Implement toString() methods
5. Create methods for file serialization
6. Design inner class for BillItem

### **Java Concepts You'll Use:**
-  Encapsulation (private fields, public methods)
-  Constructor overloading
-  Getters/Setters
-  toString() override
-  Inner classes
-  String parsing
-  ArrayList

### **Your Tasks Checklist:**

#### **Product.java:**
- [ ] Private fields: productId, productName, category, price, stockQuantity, description
- [ ] Multiple constructors
- [ ] Getters and setters for all fields
- [ ] Methods: reduceStock(), addStock(), isInStock(), hasStock()
- [ ] toFileString() - convert object to file format
- [ ] fromFileString() - create object from file line
- [ ] displayProduct() - display formatted output
- [ ] toString() override

#### **Customer.java:**
- [ ] Private fields: customerId, customerName, phoneNumber, email, purchaseHistory, totalPurchaseAmount
- [ ] Multiple constructors
- [ ] Getters and setters
- [ ] Methods: addPurchase(), getPurchaseCount()
- [ ] toFileString() and fromFileString()
- [ ] displayCustomer()
- [ ] toString() override

#### **Bill.java:**
- [ ] Private fields: billId, customerId, customerName, items, subtotal, gstAmount, totalAmount, billDate, cashierName
- [ ] Inner class BillItem with fields: productId, productName, quantity, unitPrice, itemTotal
- [ ] Methods: addItem(), calculateTotals(), getItemCount()
- [ ] toFileString() and fromFileString()
- [ ] displayInvoice() - formatted bill display
- [ ] GST calculation (18%)

### **Sample Code Structure:**
```java
public class Product {
    private String productId;
    private String productName;
    // ... more fields
    
    public Product() { }
    public Product(String id, String name, ...) { }
    
    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String id) { this.productId = id; }
    
    // Business methods
    public void reduceStock(int qty) { }
    public boolean hasStock(int qty) { }
    
    // File operations
    public String toFileString() { }
    public static Product fromFileString(String line) { }
}
```

### **Testing Your Module:**
1. Create objects with different constructors
2. Test getters and setters
3. Test toFileString() and fromFileString()
4. Test business methods (stock operations)
5. Test display methods

---

##  PERSON 3 (Vaibhavi Anand): Business Logic Services

### **Your Package:** `com.retailpos.service`

### **Your Files:**
1. `InventoryService.java`
2. `BillingService.java`

### **Your Responsibilities:**
1. Implement all business logic
2. Perform CRUD operations
3. Manage inventory operations
4. Handle billing transactions
5. Generate reports
6. Use collections (ArrayList, HashMap)

### **Java Concepts You'll Use:**
-  HashMap for fast lookups
-  ArrayList for lists
-  Exception throwing and handling
-  Service layer design
-  Business logic implementation
-  File operations via FileUtil

### **Your Tasks Checklist:**

#### **InventoryService.java:**
- [ ] HashMap<String, Product> inventory
- [ ] loadInventory() from file
- [ ] saveInventory() to file
- [ ] addProduct(Product)
- [ ] getProduct(String productId)
- [ ] updateProduct(String productId, Product)
- [ ] deleteProduct(String productId)
- [ ] viewAllProducts()
- [ ] searchProducts(String searchTerm)
- [ ] checkStock(String productId, int quantity)
- [ ] reduceStock(String productId, int quantity)
- [ ] addStock(String productId, int quantity)
- [ ] getLowStockProducts()
- [ ] displayLowStockAlert()
- [ ] getTotalInventoryValue()
- [ ] generateNextProductId()

#### **BillingService.java:**
- [ ] HashMap<String, Customer> customers
- [ ] ArrayList<Bill> bills
- [ ] Reference to InventoryService
- [ ] loadCustomers() and saveCustomers()
- [ ] loadBills() and saveBill()
- [ ] addCustomer(Customer)
- [ ] getCustomer(String customerId)
- [ ] viewAllCustomers()
- [ ] searchCustomers(String searchTerm)
- [ ] createBill(String customerId, String cashierName)
- [ ] addItemToBill(Bill, String productId, int quantity)
- [ ] completeBilling(Bill)
- [ ] viewAllBills()
- [ ] searchBills(String searchTerm)
- [ ] generateSalesReport()
- [ ] generateNextBillId()
- [ ] generateNextCustomerId()

### **Sample Code Structure:**
```java
public class InventoryService {
    private HashMap<String, Product> inventory;
    
    public InventoryService() {
        inventory = new HashMap<>();
        loadInventory();
    }
    
    private void loadInventory() {
        // Use FileUtil to read products.txt
        // Parse each line
        // Create Product objects
        // Add to HashMap
    }
    
    public void addProduct(Product p) throws InvalidInputException {
        // Check if exists
        // Add to HashMap
        // Save to file
    }
    
    // ... more methods
}
```

### **Testing Your Module:**
1. Test all CRUD operations
2. Test search functionality
3. Test stock management
4. Test billing operations
5. Test report generation
6. Test exception scenarios

---

##  PERSON 4 (Atul Mishra): File Handling & Exception Handling

### **Your Packages:**
1. `com.retailpos.util`
2. `com.retailpos.exception`

### **Your Files:**
1. `FileUtil.java`
2. `ValidationUtil.java`
3. `ProductNotFoundException.java`
4. `InsufficientStockException.java`
5. `InvalidInputException.java`
6. `AuthenticationException.java`

### **Your Responsibilities:**
1. Implement all file I/O operations
2. Create input validation utilities
3. Design custom exception classes
4. Handle file reading/writing
5. Ensure proper resource management

### **Java Concepts You'll Use:**
-  FileReader / FileWriter
-  BufferedReader / BufferedWriter
-  Try-Catch-Finally blocks
-  Custom exceptions (extends Exception)
-  Static utility methods
-  Input validation
-  Regular expressions

### **Your Tasks Checklist:**

#### **FileUtil.java:**
- [ ] Static constants for file paths
- [ ] writeToFile(String filePath, String data, boolean append)
- [ ] readFromFile(String filePath) returns ArrayList<String>
- [ ] writeAllLines(String filePath, ArrayList<String> lines, boolean append)
- [ ] deleteLine(String filePath, String lineToDelete)
- [ ] updateLine(String filePath, String oldLine, String newLine)
- [ ] fileExists(String filePath)
- [ ] createFileIfNotExists(String filePath)
- [ ] initializeFiles() - create default files and users

#### **ValidationUtil.java:**
- [ ] validateNotEmpty(String input, String fieldName)
- [ ] validateInteger(String input, String fieldName)
- [ ] validatePositiveInteger(String input, String fieldName)
- [ ] validateDouble(String input, String fieldName)
- [ ] validatePositiveDouble(String input, String fieldName)
- [ ] validatePhoneNumber(String phone)
- [ ] validateEmail(String email)
- [ ] validateProductId(String productId)
- [ ] validateCustomerId(String customerId)
- [ ] validateChoice(String choice, int min, int max)

#### **Exception Classes:**
Each should have:
- [ ] Default constructor
- [ ] Constructor with message
- [ ] Constructor with message and cause
- [ ] Appropriate fields if needed

### **Sample Code Structure:**
```java
public class FileUtil {
    public static final String PRODUCTS_FILE = "data/products.txt";
    
    public static void writeToFile(String filePath, String data, boolean append) 
            throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath, append));
            writer.write(data);
            writer.newLine();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    // ... more methods
}

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Product not found");
    }
    
    public ProductNotFoundException(String message) {
        super(message);
    }
}
```

### **Testing Your Module:**
1. Test file creation
2. Test reading/writing operations
3. Test validation methods with valid/invalid inputs
4. Test exception throwing
5. Test resource cleanup (file streams)

---

##  INTEGRATION GUIDELINES

### **How Modules Work Together:**

1. **Person 1 (MainApp)** calls:
   - **Person 3 (Services)** for business operations
   - **Person 4 (ValidationUtil)** for input validation

2. **Person 3 (Services)** uses:
   - **Person 2 (Models)** for data objects
   - **Person 4 (FileUtil)** for file operations
   - **Person 4 (Exceptions)** for error handling

3. **Person 2 (Models)**:
   - Used by everyone
   - Provides data structure
   - No dependencies on other modules

4. **Person 4 (Utils & Exceptions)**:
   - Used by everyone
   - Provides utility functions
   - Foundation layer

### **Communication Flow:**
```
User → MainApp → ValidationUtil → Services → Models → FileUtil → Files
                                     ↓
                                 Exceptions
```

---

##  CODING STANDARDS FOR ALL

### **File Header (MANDATORY):**
```java
/*
Developer Name : Person 1 / Person 2 / Person 3 / Person 4
Responsibility: (Module name)
Concepts Used : (Java concepts used)
*/
```

### **Naming Conventions:**
- Classes: PascalCase (ProductService)
- Methods: camelCase (addProduct)
- Variables: camelCase (productName)
- Constants: UPPER_SNAKE_CASE (GST_RATE)

### **Comments:**
- JavaDoc for classes and public methods
- Inline comments for complex logic
- Explain WHY, not WHAT

### **Exception Handling:**
- Always use try-catch for file operations
- Throw specific exceptions
- Display user-friendly error messages

---

##  COMPLETION CHECKLIST

### **Before Submission:**
- [ ] All files have developer name and responsibility
- [ ] All code compiles without errors
- [ ] All methods are properly commented
- [ ] Test data files are created
- [ ] Application runs successfully
- [ ] All features work as expected
- [ ] Exception handling is implemented
- [ ] Input validation is working
- [ ] Files are saved correctly
- [ ] README is complete

---

##  LEARNING OBJECTIVES

By completing this project, each team member will learn:

1. **Collaboration:** Working in a team with clear responsibilities
2. **Modularity:** Building independent yet integrated modules
3. **File Handling:** Reading/writing data without database
4. **OOP:** Practical application of all OOP concepts
5. **Exception Handling:** Creating and handling custom exceptions
6. **Real-world Skills:** Building a complete application from scratch

---

##  TIPS FOR SUCCESS

1. **Start Early:** Don't wait until the last minute
2. **Test Often:** Test your module independently
3. **Communicate:** Discuss interfaces with other team members
4. **Document:** Write clear comments and documentation
5. **Version Control:** Keep backup of your code
6. **Ask Questions:** Clarify doubts early
7. **Review Code:** Check each other's work
8. **Test Integration:** Test how modules work together

---

##  SUPPORT

If you face issues:
1. Check the README and PROJECT_STRUCTURE documents
2. Review sample code provided
3. Test your module independently first
4. Discuss with team members
5. Check file paths and permissions
6. Verify Java version compatibility

---
**Good luck with your project!**