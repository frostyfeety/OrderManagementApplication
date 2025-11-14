package org.example.service;

import org.example.model.Order;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public Order createOrder(Order order) {
        // Проверить, что customer существует
        if (!customerRepository.existsById(order.getCustomerId())) {
            throw new RuntimeException("Customer not found with id: " + order.getCustomerId());
        }
        return orderRepository.save(order);
    }

    public Order updateOrder(int id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        // Проверить, что новый customer существует (если меняется)
        if (orderDetails.getCustomerId() != order.getCustomerId()) {
            if (!customerRepository.existsById(orderDetails.getCustomerId())) {
                throw new RuntimeException("Customer not found with id: " + orderDetails.getCustomerId());
            }
            order.setCustomerId(orderDetails.getCustomerId());
        }

        order.setOrderDate(orderDetails.getOrderDate());
        order.setStatus(orderDetails.getStatus());

        return orderRepository.save(order);
    }

    public void deleteOrder(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        orderRepository.delete(order);
    }
}