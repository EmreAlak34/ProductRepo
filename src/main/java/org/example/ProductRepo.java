package org.example;

import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    void addProduct(Product product);
    void removeProduct(String productId);
    Optional<Product> getProductById(String productId);
    List<Product> getAllProducts();
}
