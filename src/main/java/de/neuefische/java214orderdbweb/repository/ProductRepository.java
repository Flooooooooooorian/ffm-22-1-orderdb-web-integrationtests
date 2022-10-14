package de.neuefische.java214orderdbweb.repository;

import de.neuefische.java214orderdbweb.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.products.add(new Product("123", "Apple"));
        this.products.add(new Product("124", "Banana"));
        this.products.add(new Product("125", "Car"));
        this.products.add(new Product("126", "Bike"));
    }

    public List<Product> listProducts() {
        return products;
    }

    public Product getProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
