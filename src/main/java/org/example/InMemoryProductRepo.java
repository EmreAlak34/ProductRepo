package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepo implements ProductRepo {

    private List<Product> products;

    public InMemoryProductRepo() {
        this.products = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void removeProduct(String productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    @Override
    public Optional<Product> getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
