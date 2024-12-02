package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("1", "Atlas", 120.0));
        productRepo.addProduct(new Product("2", "Macbook", 1800.0));


        OrderRepoInterface orderRepo = new OrderListRepo();


        ShopService shopService = new ShopService(orderRepo, productRepo);


        shopService.placeOrder("101", List.of("1", "2"));


        shopService.placeOrder("102", List.of("3"));
    }
}
