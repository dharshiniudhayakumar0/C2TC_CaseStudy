package services;

import entities.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    public void createCustomer(int userId, String username, String email, String address) {
        Customer customer = new Customer(userId, username, email, address);
        customers.add(customer);
        System.out.println("Customer created successfully!");
    }

    public Customer getCustomerById(int userId) {
        for (Customer customer : customers) {
            if (customer.getUserId() == userId) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("Customers:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
}