package org.example;

import java.util.List;

public class Order {
    private String orderId;
    private List<Product> products;
    private double totalPrice;

    // Constructor
    public Order(String orderId, List<Product> products, double totalPrice) {
        this.orderId = orderId;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
