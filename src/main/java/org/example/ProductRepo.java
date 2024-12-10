package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {
    private List<Product> products; // List to store products

    public ProductRepo() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    // Angepasste Methode mit Optional
    public Optional<Product> getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))  // Filtert das Produkt nach ID
                .findFirst();  // Gibt das erste gefundene Produkt als Optional zurück
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
