package org.example;

import java.util.List;
import java.util.stream.Collectors;


public class ShopService {
    private OrderRepoInterface orderRepo; // To store orders
    private ProductRepo productRepo; // To store products

    // Constructor for orderRepo and productRepo
    public ShopService(OrderRepoInterface orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    // Place an order
    public void placeOrder(String orderId, List<String> productIds) {
        List<Product> orderedProducts = productIds.stream()
                .map(productId -> productRepo.getProductById(productId).orElse(null))
                .filter(product -> product != null)
                .collect(Collectors.toList());

        if (orderedProducts.size() != productIds.size()) {
            System.out.println("Some products do not exist in the inventory.");
            return;
        }

        double totalPrice = orderedProducts.stream().mapToDouble(Product::getPrice).sum();

        Order order = new Order(orderId, orderedProducts, totalPrice, OrderStatus.PROCESSING);
        orderRepo.addOrder(order);
        System.out.println("Order placed successfully!");
    }

    // Method to get orders by status
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.getOrderStatus() == status)
                .collect(Collectors.toList());
    }

    // Method to update the order status
    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        Order order = orderRepo.getOrder(orderId);  // Holen der Bestellung anhand der ID
        if (order != null) {
            Order updatedOrder = order.withStatus(newStatus);  // Status aktualisieren
            orderRepo.removeOrder(orderId);  // Alte Bestellung entfernen
            orderRepo.addOrder(updatedOrder);  // Aktualisierte Bestellung hinzufügen
            System.out.println("Order status updated to " + newStatus);  // Bestätigung ausgeben
        } else {
            System.out.println("Order not found with ID: " + orderId);  // Fehlerfall, falls Bestellung nicht gefunden wird
        }
    }
}
