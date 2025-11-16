package com.kainos.andrewwa.atddhometrading.monolith.core.entities;

import java.math.BigDecimal;

public record Order(
        String orderNumber,
        long productId,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice,
        OrderStatus status
) {

    public Order {
        if (orderNumber == null) {
            throw new IllegalArgumentException("orderNumber cannot be null");
        }
        if (unitPrice == null) {
            throw new IllegalArgumentException("unitPrice cannot be null");
        }
        if (totalPrice == null) {
            throw new IllegalArgumentException("totalPrice cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }
    }

    public Order withStatus(OrderStatus newStatus) {
        return new Order(orderNumber, productId, quantity, unitPrice, totalPrice, newStatus);
    }
}
