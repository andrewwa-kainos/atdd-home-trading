package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.api.dtos;

import java.math.BigDecimal;

public record PlaceOrderResponse(String orderNumber, BigDecimal totalPrice) {}
