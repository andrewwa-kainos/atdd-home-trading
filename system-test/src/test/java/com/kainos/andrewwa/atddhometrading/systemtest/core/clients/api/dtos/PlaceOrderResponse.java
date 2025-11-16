package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceOrderResponse {
    private String orderNumber;
    private BigDecimal totalPrice;
}
