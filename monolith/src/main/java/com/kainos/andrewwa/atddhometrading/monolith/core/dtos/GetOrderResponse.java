package com.kainos.andrewwa.atddhometrading.monolith.core.dtos;

import com.kainos.andrewwa.atddhometrading.monolith.core.entities.OrderStatus;

import java.math.BigDecimal;

public record GetOrderResponse(
        String orderNumber,
        long productId,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice,
        OrderStatus status
) {}