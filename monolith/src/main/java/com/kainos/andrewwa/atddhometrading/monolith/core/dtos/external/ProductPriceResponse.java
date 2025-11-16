package com.kainos.andrewwa.atddhometrading.monolith.core.dtos.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductPriceResponse(long id, BigDecimal price) {}
