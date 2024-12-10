package org.example;

import java.util.List;


public class Order {
    private String orderId;
    private List<Product> products;
    private double totalPrice;
    private OrderStatus orderStatus; // Neues Attribut für den Status

    // Constructor
    public Order(String orderId, List<Product> products, double totalPrice, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.products = products;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus; // Status initialisieren
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

    public OrderStatus getOrderStatus() {
        return orderStatus; // Methode zum Abrufen des Status
    }

    // Setter (falls du den Status später ändern möchtest)
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
