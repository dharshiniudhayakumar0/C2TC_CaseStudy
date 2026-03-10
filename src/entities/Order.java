package entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status;

    public Order(Customer customer) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }

    // Getters and Setters
    public int getOrderId() { //read only accesss
        return orderId;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductQuantityPair> getProducts() {
        return products;
    }

    public void addProduct(ProductQuantityPair productQuantityPair) {
        this.products.add(productQuantityPair);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId)
           .append(", Customer: ").append(customer.getUsername())
          .append(", Status: ").append(status).append("\n");
        for (ProductQuantityPair pair : products) {
            sb.append(pair.toString()).append("\n");
        }
        return sb.toString();
    }
}

