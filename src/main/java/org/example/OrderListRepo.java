package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepoInterface {
    private List<Order> orders; 


    public OrderListRepo() {
        this.orders = new ArrayList<>();
    }


    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }


    @Override
    public void removeOrder(String orderId) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
    }


    @Override
    public Order getOrder(String orderId) {
        return orders.stream().filter(order -> order.getOrderId().equals(orderId)).findFirst().orElse(null);
    }


    @Override
    public List<Order> getAllOrders() {
        return orders;
    }
}
