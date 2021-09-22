package de.neuefische.java214orderdbweb.controller;

import de.neuefische.java214orderdbweb.model.Product;
import de.neuefische.java214orderdbweb.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ShopService shopService;

    public ProductController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return shopService.getProducts();
    }

    @GetMapping
    public List<Product> searchProducts(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return shopService.searchProducts(name.get());
        }
        return shopService.getProducts();
    }

    @GetMapping("{id}")
    public Product getProductBy(@PathVariable String id) {
        return shopService.getProductBy(id);
    }
}
