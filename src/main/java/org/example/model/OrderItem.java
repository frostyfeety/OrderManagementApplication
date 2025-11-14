package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Order ID is mandatory")
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @NotNull(message = "Product ID is mandatory")
    @Column(name = "product_id", nullable = false)
    private int productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private int quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public OrderItem() {
        this.createdAt = LocalDateTime.now();
    }

    public OrderItem(int orderId, int productId, int quantity) {
        this();
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return String.format("OrderItem[ID: %d, OrderID: %d, ProductID: %d, Quantity: %d]",
                id, orderId, productId, quantity);
    }
}