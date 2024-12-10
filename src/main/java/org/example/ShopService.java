package org.example;
import java.util.List;
import java.util.stream.Collectors;
// Import für Collectors

public class ShopService {
    private OrderRepoInterface orderRepo; // To store orders
    private ProductRepo productRepo; // To store products

    // Constructor  orderRepo and productRepo
    public ShopService(OrderRepoInterface orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public void placeOrder(String orderId, List<String> productIds) {
        // Create a list to store the ordered products
        List<Product> orderedProducts = productIds.stream()
                .map(productId -> productRepo.getProductById(productId).orElseThrow(() -> new ProductNotFoundException(productId))) // Verwende getProductById mit Optional
                .filter(product -> product != null)  // Nur Produkte, die existieren
                .toList();

        // Check if all products were found
        if (orderedProducts.size() != productIds.size()) {
            System.out.println("Some products do not exist in the inventory.");
            return;
        }

        // Calculate total price
        double totalPrice = orderedProducts.stream().mapToDouble(Product::getPrice).sum();

        // Create  order and add it to orderRepo
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
