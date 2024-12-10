package org.example;

import java.util.ArrayList;
import java.util.List;




public class InMemoryOrderRepo implements OrderRepoInterface {
    private List<Order> orders;

    public InMemoryOrderRepo() {
        this.orders = new ArrayList<>();
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrder(String orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null); // Gibt null zurück, wenn keine Bestellung mit dieser ID gefunden wurde
    }
}
