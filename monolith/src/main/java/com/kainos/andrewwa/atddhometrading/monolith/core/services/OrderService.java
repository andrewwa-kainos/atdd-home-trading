package com.kainos.andrewwa.atddhometrading.monolith.core.services;

import com.kainos.andrewwa.atddhometrading.monolith.core.dtos.GetOrderResponse;
import com.kainos.andrewwa.atddhometrading.monolith.core.dtos.PlaceOrderRequest;
import com.kainos.andrewwa.atddhometrading.monolith.core.dtos.PlaceOrderResponse;
import com.kainos.andrewwa.atddhometrading.monolith.core.entities.Order;
import com.kainos.andrewwa.atddhometrading.monolith.core.entities.OrderStatus;
import com.kainos.andrewwa.atddhometrading.monolith.core.exceptions.NotExistValidationException;
import com.kainos.andrewwa.atddhometrading.monolith.core.exceptions.ValidationException;
import com.kainos.andrewwa.atddhometrading.monolith.core.repositories.OrderRepository;
import com.kainos.andrewwa.atddhometrading.monolith.core.services.external.ErpGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;

@Service
public class OrderService {

    public static final MonthDay DECEMBER_31 = MonthDay.of(12, 31);
    private static final LocalTime CANCELLATION_BLOCK_START = LocalTime.of(22, 0);
    private static final LocalTime CANCELLATION_BLOCK_END = LocalTime.of(23, 0);

    private final OrderRepository orderRepository;
    private final ErpGateway erpGateway;

    public OrderService(OrderRepository orderRepository, ErpGateway erpGateway) {
        this.orderRepository = orderRepository;
        this.erpGateway = erpGateway;
    }

    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        var productId = request.productId();
        var quantity = request.quantity();

        if (productId <= 0) {
            throw new ValidationException("Product ID must be greater than 0, received: " + productId);
        }

        var orderNumber = orderRepository.nextOrderNumber();
        var unitPrice = erpGateway.getUnitPrice(productId);
        var totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        var order = new Order(orderNumber, productId, quantity, unitPrice, totalPrice, OrderStatus.PLACED);

        orderRepository.addOrder(order);

        return new PlaceOrderResponse(orderNumber, totalPrice);
    }

    public GetOrderResponse getOrder(String orderNumber) {
        var optionalOrder = orderRepository.getOrder(orderNumber);

        if (optionalOrder.isEmpty()) {
            throw new NotExistValidationException("Order " + orderNumber + " does not exist.");
        }

        var order = optionalOrder.get();

        var response = new GetOrderResponse(
                orderNumber,
                order.productId(),
                order.quantity(),
                order.unitPrice(),
                order.totalPrice(),
                order.status()
        );

        return response;
    }

    public void cancelOrder(String orderNumber) {
        var optionalOrder = orderRepository.getOrder(orderNumber);

        if (optionalOrder.isEmpty()) {
            throw new NotExistValidationException("Order " + orderNumber + " does not exist.");
        }

        var order = optionalOrder.get();

        var now = LocalDateTime.now();
        var currentDate = MonthDay.from(now);
        var currentTime = now.toLocalTime();

        if (currentDate.equals(DECEMBER_31) && currentTime.isAfter(CANCELLATION_BLOCK_START) && currentTime.isBefore(
                CANCELLATION_BLOCK_END)) {
            throw new ValidationException("Order cancellation is not allowed on December 31st between 22:00 and 23:00");
        }

        orderRepository.updateOrder(order.withStatus(OrderStatus.CANCELLED));
    }
}
