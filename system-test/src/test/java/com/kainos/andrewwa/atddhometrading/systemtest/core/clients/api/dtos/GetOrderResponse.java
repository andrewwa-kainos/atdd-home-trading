package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetOrderResponse {
    private String orderNumber;
    private long productId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String status;
}