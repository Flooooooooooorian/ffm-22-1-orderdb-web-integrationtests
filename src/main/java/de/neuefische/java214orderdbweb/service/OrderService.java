package de.neuefische.java214orderdbweb.service;

import de.neuefische.java214orderdbweb.model.Order;
import de.neuefische.java214orderdbweb.model.Product;
import de.neuefische.java214orderdbweb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepo, ProductService productService) {
        this.productService = productService;
        this.orderRepo = orderRepo;
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
            Product productToAdd = productService.getProductBy(productId);
            productsToOrder.add(productToAdd);
        }
        return orderRepo.addOrder(new Order(generateId(), productsToOrder));
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Order changeOrder(String id, List<String> productIds) {
        List<Product> productsToOrder = new ArrayList<>();
        for (String productId : productIds) {
            Product productToAdd = productService.getProductBy(productId);
            productsToOrder.add(productToAdd);
        }
        Optional<Order> optionalOrder = orderRepo.getOrderBy(id);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setProductList(productsToOrder);
        }
        return orderRepo.addOrder(new Order(id, productsToOrder));
    }

    public void deleteOrder(String id) {
        Order order = getOrderBy(id);
        orderRepo.deleteOrder(order);
    }
}