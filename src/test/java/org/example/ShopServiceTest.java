package org.example;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.InMemoryOrderRepo;



public class ShopServiceTest {

    private ShopService shopService;
    private ProductRepo productRepo;
    private OrderRepoInterface orderRepo;

    @BeforeEach
    public void setUp() {
        // Verwende ProductRepo und OrderRepo als konkrete Implementierungen
        productRepo = new ProductRepo();
        orderRepo = new InMemoryOrderRepo(); // Verwende InMemoryOrderRepo hier
        shopService = new ShopService(orderRepo, productRepo);
    }

    @Test
    public void testPlaceOrder() {
        productRepo.addProduct(new Product("1", "Product A", 10.0));
        productRepo.addProduct(new Product("2", "Product B", 20.0));

        shopService.placeOrder("order1", List.of("1", "2"));

        List<Order> orders = shopService.getOrdersByStatus(OrderStatus.PROCESSING);
        assertEquals(1, orders.size(), "Es sollte genau eine Bestellung geben");
        assertEquals("order1", orders.get(0).getOrderId(), "Bestell-ID stimmt nicht");
        assertEquals(30.0, orders.get(0).getTotalPrice(), "Gesamtpreis stimmt nicht");
    }
}
