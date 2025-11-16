package com.kainos.andrewwa.atddhometrading.monolith.core.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PlaceOrderRequest {
    private long productId;

    @Positive(message = "Quantity must be positive")
    private int quantity;
}