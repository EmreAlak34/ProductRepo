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
    package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

    public class ShopServiceTest {

        private ShopService shopService;
        private OrderRepoInterface orderRepo;
        private ProductRepo productRepo;

        @BeforeEach
        public void setUp() {
            // Mock oder einfache Implementierungen für Repositories erstellen
            orderRepo = new InMemoryOrderRepo();  // Beispielhafte InMemory-Implementierung
            productRepo = new InMemoryProductRepo(); // Beispielhafte InMemory-Implementierung

            shopService = new ShopService(orderRepo, productRepo);
        }

        @Test
        public void testPlaceOrder() {
            // Test für das Platzieren einer Bestellung
            productRepo.addProduct(new Product("1", "Product A", 10.0));
            productRepo.addProduct(new Product("2", "Product B", 20.0));

            shopService.placeOrder("order1", List.of("1", "2"));

            List<Order> orders = shopService.getOrdersByStatus(OrderStatus.PROCESSING);
            assertEquals(1, orders.size(), "Es sollte genau eine Bestellung geben");
            assertEquals("order1", orders.get(0).getOrderId(), "Bestell-ID stimmt nicht");
            assertEquals(30.0, orders.get(0).getTotalPrice(), "Gesamtpreis stimmt nicht");
        }

        @Test
        public void testPlaceOrderWithNonExistentProduct() {
            // Test für das Bestellen eines nicht existierenden Produkts
            productRepo.addProduct(new Product("1", "Product A", 10.0));

            // Test, ob die Ausnahme geworfen wird, wenn ein Produkt nicht gefunden wird
            Exception exception = assertThrows(ProductNotFoundException.class, () -> {
                shopService.placeOrder("order1", List.of("1", "3"));  // Produkt mit ID "3" existiert nicht
            });

            String expectedMessage = "Product with ID 3 not found.";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage), "Die Fehlermeldung stimmt nicht überein.");
        }
    }

}
