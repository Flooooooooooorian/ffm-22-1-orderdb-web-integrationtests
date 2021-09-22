package de.neuefische.java214orderdbweb.controller;

import de.neuefische.java214orderdbweb.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.neuefische.java214orderdbweb.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private final ShopService shopService;

    @Autowired
    public OrderController(ShopService orderService) {
        this.shopService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return shopService.listOrders();
    }

    @GetMapping("{id}")
    public Order getOrderBy(@PathVariable String id) {
        return shopService.getOrderBy(id);
    }

    @PostMapping
    public Order makeOrder(@RequestBody List<String> productIds) {
        return shopService.orderProducts(productIds);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable String id) {
        shopService.deleteOrder(id);
    }
}
