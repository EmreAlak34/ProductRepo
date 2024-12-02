package org.example;



import java.util.List;

public interface OrderRepoInterface {
    void addOrder(Order order); // Add a new order
    void removeOrder(String orderId); // Remove an order by ID
    Order getOrder(String orderId); // Get a single order by ID
    List<Order> getAllOrders(); // Get all orders
}
