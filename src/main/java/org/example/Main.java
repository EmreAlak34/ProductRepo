package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Verwende InMemoryProductRepo für ProductRepo
        ProductRepo productRepo = new InMemoryProductRepo();
        productRepo.addProduct(new Product("1", "Atlas", 120.0));
        productRepo.addProduct(new Product("2", "Macbook", 1800.0));

        // Verwende InMemoryOrderRepo für OrderRepoInterface
        OrderRepoInterface orderRepo = new InMemoryOrderRepo();

        // Erstelle ShopService mit den Repositories
        ShopService shopService = new ShopService(orderRepo, productRepo);

        // Platziere Bestellungen
        shopService.placeOrder("101", List.of("1", "2"));
        shopService.placeOrder("102", List.of("3"));
    }
}
