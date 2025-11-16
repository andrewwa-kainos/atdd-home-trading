package com.kainos.andrewwa.atddhometrading.monolith.core.dtos;

import jakarta.validation.constraints.Positive;

public record PlaceOrderRequest(long productId, @Positive(message = "Quantity must be positive") int quantity) {}