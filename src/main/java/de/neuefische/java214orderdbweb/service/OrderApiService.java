package de.neuefische.java214orderdbweb.service;

import de.neuefische.java214orderdbweb.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderApiService {

    private final WebClient webClient = WebClient.create();

    @Value("${orderapi.url}")
    private String url;

    public Order getOrderById(String id) {
        return webClient.get()
                .uri(url + id)
                .retrieve()
                .toEntity(Order.class)
                .block()
                .getBody();
    }
}
