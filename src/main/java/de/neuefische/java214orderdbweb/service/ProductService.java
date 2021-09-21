package de.neuefische.java214orderdbweb.service;

import de.neuefische.java214orderdbweb.model.Product;
import de.neuefische.java214orderdbweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.listProducts();
    }

    public Product getProductBy(String id){
        Optional<Product> optionalProduct = productRepository.getProduct(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }
}
