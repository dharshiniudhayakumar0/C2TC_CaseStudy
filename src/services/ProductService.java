package services;

import entities.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully!");
    }

    public void removeProduct(int productId) {
        products.removeIf(p -> p.getProductId() == productId);
        System.out.println("Product removed successfully!");
    }

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}