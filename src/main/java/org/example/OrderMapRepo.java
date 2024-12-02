package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.OrderRepoInterface;


public class OrderMapRepo implements OrderRepoInterface {
    private Map<String, Order> orders; // Map to store orders with orderId as the key

    // Constructor for map
    public OrderMapRepo() {
        this.orders = new HashMap<>();
    }

    // Add new order
    @Override
    public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    // Remove order by ID
    @Override
    public void removeOrder(String orderId) {
        orders.remove(orderId);
    }

    // Get order by ID
    @Override
    public Order getOrder(String orderId) {
        return orders.get(orderId); // Return the order directly from the map
    }

    // Get all orders
    @Override
    public List<Order> getAllOrders() {
        return orders.values().stream().collect(Collectors.toList());
    }
}
