package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "Order Management API is running! Available endpoints: /api/customers, /api/products, /api/orders, /api/order-items";
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint is working! Spring Boot is running correctly.";
    }

    @GetMapping("/health")
    public String health() {
        return "OK - Application is healthy";
    }
}