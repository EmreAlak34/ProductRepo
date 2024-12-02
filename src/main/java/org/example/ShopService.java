package org.example;

import java.util.List;

public class ShopService {
    private OrderRepoInterface orderRepo; // To store orders
    private ProductRepo productRepo; // To store products

    // Constructor  orderRepo and productRepo
    public ShopService(OrderRepoInterface orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    // Method to place  order
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

        // Create  order and add it to orderRepo
        Order order = new Order(orderId, orderedProducts, totalPrice);
        orderRepo.addOrder(order);

        // Print message
        System.out.println("Order placed successfully!");
    }
}
