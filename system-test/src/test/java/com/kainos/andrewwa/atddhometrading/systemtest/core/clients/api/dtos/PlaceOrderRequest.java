package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.api.dtos;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private String productId;
    private String quantity;
}
