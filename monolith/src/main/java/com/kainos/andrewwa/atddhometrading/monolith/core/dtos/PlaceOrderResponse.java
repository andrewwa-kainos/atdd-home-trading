package com.kainos.andrewwa.atddhometrading.monolith.core.dtos;

import java.math.BigDecimal;

public record PlaceOrderResponse(String orderNumber, BigDecimal totalPrice) {}