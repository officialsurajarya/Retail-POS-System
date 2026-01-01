import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

// ---------------- PRODUCT CLASS ----------------
class Product {

    int id;
    String name;
    double price;

    // Constructor
    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

// ---------------- MAIN CLASS ----------------
public class SimpleBill {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Product> productList = new ArrayList<>();

        int choice;

        System.out.println("===== Retail POS System =====");

        while (true) {

            // MENU
            System.out.println("\n1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Generate Bill (Simple)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Please enter valid number");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    // ADD PRODUCT
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Product Price: ");
                    double price = sc.nextDouble();

                    productList.add(new Product(id, name, price));
                    System.out.println("✅ Product Added Successfully");
                    break;

                case 2:
                    // VIEW PRODUCTS
                    if (productList.isEmpty()) {
                        System.out.println("⚠ No products available");
                    } else {
                        System.out.println("\nID\tName\tPrice");
                        System.out.println("----------------------");
                        for (Product p : productList) {
                            System.out.println(p.id + "\t" + p.name + "\t" + p.price);
                        }
                    }
                    break;

                case 3:
                    // SIMPLE BILLING + FILE SAVE
                    System.out.print("Enter Total Bill Amount: ");
                    double total = sc.nextDouble();

                    System.out.println("💰 Total Amount = " + total);

                    try {
                        FileWriter fw = new FileWriter("bill.txt", true);
                        fw.write("Total Bill Amount = " + total + "\n");
                        fw.close();

                        System.out.println("📁 Bill saved in bill.txt");

                    } catch (IOException e) {
                        System.out.println("❌ Error while saving bill");
                    }
                    break;

                case 4:
                    System.out.println("🙏 Thank you for using POS System");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}

