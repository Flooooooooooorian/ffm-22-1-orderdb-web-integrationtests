package de.neuefische.java214orderdbweb.controller;

import de.neuefische.java214orderdbweb.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.neuefische.java214orderdbweb.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.listOrders();
    }

    @GetMapping("{id}")
    public Order getOrderBy(@PathVariable String id) {
        return orderService.getOrderBy(id);
    }

    @PostMapping
    public Order makeOder(@RequestBody List<String> productIds) {
        return orderService.orderProducts(productIds);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
    }
}
