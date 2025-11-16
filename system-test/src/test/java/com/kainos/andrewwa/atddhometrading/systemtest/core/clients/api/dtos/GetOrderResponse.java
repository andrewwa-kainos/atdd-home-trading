package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.api.dtos;

import java.math.BigDecimal;

public record GetOrderResponse(
        String orderNumber, long productId, int quantity, BigDecimal unitPrice, BigDecimal totalPrice, String status
) {}