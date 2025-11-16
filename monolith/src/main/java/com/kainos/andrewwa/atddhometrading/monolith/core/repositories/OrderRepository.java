package com.kainos.andrewwa.atddhometrading.monolith.core.repositories;

import com.kainos.andrewwa.atddhometrading.monolith.core.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private static final Map<String, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        if (orders.containsKey(order.orderNumber())) {
            throw new IllegalArgumentException("Order with order number " + order.orderNumber() + " already exists.");
        }

        orders.put(order.orderNumber(), order);
    }

    public void updateOrder(Order order) {
        if (!orders.containsKey(order.orderNumber())) {
            throw new IllegalArgumentException("Order with order number " + order.orderNumber() + " does not exist.");
        }

        orders.put(order.orderNumber(), order);
    }

    public Optional<Order> getOrder(String orderNumber) {
        if (!orders.containsKey(orderNumber)) {
            return Optional.empty();
        }

        var order = orders.get(orderNumber);
        return Optional.of(order);
    }

    public String nextOrderNumber() {
        return "ORD-" + UUID.randomUUID();
    }
}
