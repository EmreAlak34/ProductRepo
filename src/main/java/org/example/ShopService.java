package org.example;

import java.util.List;
import java.util.stream.Collectors; // Import für Collectors

public class ShopService {
    private OrderRepoInterface orderRepo; // To store orders
    private ProductRepo productRepo; // To store products

    // Constructor  orderRepo and productRepo
    public ShopService(OrderRepoInterface orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    // Method to place an order
    public void placeOrder(String orderId, List<String> productIds) {
        // Create a list to store the ordered products
        List<Product> orderedProducts = productIds.stream()
                .map(productRepo::getProduct)  // Look up each product by ID
                .filter(product -> product != null)  // Only include products that exist
                .toList();

        // Check if all products were found
        if (orderedProducts.size() != productIds.size()) {
            System.out.println("Some products do not exist in the inventory.");
            return;
        }

        // Calculate total price
        double totalPrice = orderedProducts.stream().mapToDouble(Product::getPrice).sum();

        // Create order and add it to orderRepo with a default status
        Order order = new Order(orderId, orderedProducts, totalPrice, OrderStatus.PROCESSING);
        orderRepo.addOrder(order);

        // Print message
        System.out.println("Order placed successfully!");
    }

    // Method to get orders by status
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.getOrderStatus() == status) // Use the correct method to get the status
                .collect(Collectors.toList());
    }
}
