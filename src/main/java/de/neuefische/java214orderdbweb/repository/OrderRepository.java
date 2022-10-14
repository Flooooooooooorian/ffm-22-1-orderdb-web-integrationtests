package de.neuefische.java214orderdbweb.repository;

import de.neuefische.java214orderdbweb.model.Order;
import de.neuefische.java214orderdbweb.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public List<Order> listOrders(){
        return orders;
    }

    public Order getOrderBy(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public Order addOrder(Order newOrder){
        orders.add(newOrder);
        return newOrder;
    }

    public void deleteOrder(Order order) {
        orders.remove(order);
    }
}
