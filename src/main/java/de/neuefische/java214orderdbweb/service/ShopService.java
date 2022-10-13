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
    private final OrderApiService orderApiService;

    @Autowired
    public ShopService(OrderRepository orderRepo, ProductRepository productRepository, OrderApiService orderApiService) {
        this.orderRepo = orderRepo;
        this.productRepository = productRepository;
        this.orderApiService = orderApiService;
    }

    public List<Order> listOrders(){
        return orderRepo.listOrders();
    }

    public Order getOrderBy(String id) {
        Optional<Order> optionalOrder = orderRepo.getOrderBy(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
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
        Optional<Product> optionalProduct = productRepository.getProduct(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }

    public Order getApiOder(String id) {
        Order apiOrder = orderApiService.getOrderById(id);
        return orderRepo.addOrder(apiOrder);
    }
}
