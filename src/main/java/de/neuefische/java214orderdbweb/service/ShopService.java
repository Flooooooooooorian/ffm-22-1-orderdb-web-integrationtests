package de.neuefische.java214orderdbweb.service;

import de.neuefische.java214orderdbweb.model.Order;
import de.neuefische.java214orderdbweb.model.Product;
import de.neuefische.java214orderdbweb.repository.OrderRepository;
import de.neuefische.java214orderdbweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepository;

    @Autowired
    public ShopService(OrderRepository orderRepo, ProductRepository productRepository) {
        this.orderRepo = orderRepo;
        this.productRepository = productRepository;
    }

    public List<Order> listOrders(){
        return orderRepo.listOrders();
    }

    public Order getOrderBy(String id) {
        Order order = orderRepo.getOrderBy(id);
        if (order != null) {
            return order;
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }

    public Order orderProducts(List<String> productIds) {
        List<Product> productsToOrder = new ArrayList<>();
        for (String productId : productIds) {
            Product productToAdd = getProductBy(productId);
            productsToOrder.add(productToAdd);
        }
        return orderRepo.addOrder(new Order(generateId(), productsToOrder));
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public void deleteOrder(String id) {
        Order order = getOrderBy(id);
        orderRepo.deleteOrder(order);
    }

    public List<Product> getProducts(){
        return productRepository.listProducts();
    }

    public List<Product> searchProducts(String name){
        List<Product> products = new ArrayList<>();
        for (Product product : productRepository.listProducts()) {
            if (product.getName().contains(name)) {
                products.add(product);
            }
        }
        return products;
    }

    public Product getProductBy(String id){
        Product product = productRepository.getProduct(id);
        if (product != null) {
            return product;
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }
}
