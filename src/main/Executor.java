package main;

import entities.*;
import services.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Executor {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductService productService = new ProductService();
    private static AdminService adminService = new AdminService();
    private static CustomerService customerService = new CustomerService();
    private static OrderService orderService = new OrderService();

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
// displaMainMenu oru helper method 
    private static void displayMainMenu() {
        System.out.println("\n1. Seller Menu");
        System.out.println("2. Buyer Menu");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nSeller Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create seller profile");
            System.out.println("5. View seller");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    productService.displayProducts();
                    break;
                case 4:
                    createAdmin();
                    break;
                case 5:
                    adminService.displayAdmins();
                    break;
                case 6:
                    updateOrderStatus();
                    break;
                case 7:
                    orderService.displayAllOrders();
                    break;
                case 8:
                    System.out.println("Exiting Admin...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void customerMenu() {
        while (true) {
            System.out.println("\nBuyer Menu:");
            System.out.println("1. Create Customer profile");
            System.out.println("2. View Customers");	  
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    customerService.displayCustomers();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    viewCustomerOrders();
                    break;
                case 5:
                    productService.displayProducts();
                    break;
                case 6:
                    System.out.println("Exiting Customer Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Stock Quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(productId, name, price, stock);
        productService.addProduct(product);
    }

    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        productService.removeProduct(productId);
    }

    private static void createAdmin() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        adminService.createAdmin(userId, username, email);
    }

    private static void createCustomer() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        customerService.createCustomer(userId, username, email, address);
    }

    private static void placeOrder() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        List<ProductQuantityPair> orderItems = new ArrayList<>();

        while (true) {
            System.out.print("Enter Product ID to add to order (or -1 to complete): ");
            int productId = scanner.nextInt();

            if (productId == -1) {
                break;
            }

            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("Product not found!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            orderItems.add(new ProductQuantityPair(product, quantity));
        }

        if (!orderItems.isEmpty()) {
            orderService.placeOrder(customer, orderItems);
        } else {
            System.out.println("No items added to order.");
        }
    }

    private static void viewCustomerOrders() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        orderService.displayCustomerOrders(customer);
    }

    private static void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
        String status = scanner.nextLine();

        orderService.updateOrderStatus(orderId, status);
    }
}