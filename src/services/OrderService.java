package services;

import entities.Customer;
import entities.Order;
import entities.Product;
import entities.ProductQuantityPair;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Customer customer, List<ProductQuantityPair> productQuantityPairs) {
        Order order = new Order(customer);
        
        // Add products to order and reduce stock
        for (ProductQuantityPair pair : productQuantityPairs) {
            Product product = pair.getProduct();
            int quantity = pair.getQuantity();
            
            if (product.getStockQuantity() >= quantity) {
                order.addProduct(pair);
                product.reduceStock(quantity);
            } else {
                System.out.println("Insufficient stock for product: " + product.getName());
                return;
            }
        }
        
        orders.add(order);
        customer.addOrder(order);
        System.out.println("Order placed successfully!");
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        return customer.getOrders();
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            System.out.println("Order status updated successfully!");
        } else {
            System.out.println("Order not found!");
        }
    }

    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Orders:");
            for (Order order : orders) {
                System.out.print(order);
            }
        }
    }

    public void displayCustomerOrders(Customer customer) {
        List<Order> customerOrders = customer.getOrders();
        if (customerOrders.isEmpty()) {
            System.out.println("No orders found for this customer.");
        } else {
            System.out.println("Orders:");
            for (Order order : customerOrders) {
                System.out.print(order);
            }
        }
    }
}