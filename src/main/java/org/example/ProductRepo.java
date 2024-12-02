package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private List<Product> products; // List to store products


    public ProductRepo() {
        this.products = new ArrayList<>();
    }


    public void addProduct(Product product) {
        products.add(product);
    }


    public void removeProduct(String productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }


    public Product getProduct(String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null; // Return null when product is not found
    }


    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
