package org.example;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ShopService {
    private OrderRepoInterface orderRepo; // To store orders
    private ProductRepo productRepo; // To store products


    public ShopService(OrderRepoInterface orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }


    public void placeOrder(String orderId, List<String> productIds) {
        // Create a list to store the ordered products
        List<Product> orderedProducts = productIds.stream()
                .map(productId -> productRepo.getProductById(productId).orElse(null))
                .filter(product -> product != null) // Only include products that exist
                .collect(Collectors.toList());


        if (orderedProducts.size() != productIds.size()) {
            System.out.println("Some products do not exist in the inventory.");
            return;
        }


        double totalPrice = orderedProducts.stream().mapToDouble(Product::getPrice).sum();

        // Create a new order with the current timestamp
        Order order = new Order(orderId, orderedProducts, totalPrice, OrderStatus.PROCESSING, Instant.now());


        orderRepo.addOrder(order);
        System.out.println("Order placed successfully!");
    }


    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.orderStatus() == status)  // Use the correct method to get the status
                .collect(Collectors.toList());
    }


    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        Order order = orderRepo.getOrder(orderId);  // Get the order by ID
        if (order != null) {
            // Create an updated order with the new status and the same timestamp
            Order updatedOrder = new Order(order.orderId(), order.products(), order.totalPrice(), newStatus, order.orderTimestamp());
            orderRepo.removeOrder(orderId);  // Remove the old order
            orderRepo.addOrder(updatedOrder);  // Add the updated order
            System.out.println("Order status updated to " + newStatus);  // Confirmation message
        } else {
            System.out.println("Order not found with ID: " + orderId);  // Error message if order not found
        }
    }
}
