package com.kainos.andrewwa.atddhometrading.systemtest.e2etests;

import com.kainos.andrewwa.atddhometrading.systemtest.TestConfiguration;
import com.kainos.andrewwa.atddhometrading.systemtest.core.drivers.ChannelType;
import com.kainos.andrewwa.atddhometrading.systemtest.core.dsl.ShopDsl;
import com.optivem.atdd.commons.channels.Channel;
import com.optivem.atdd.commons.channels.ChannelExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ChannelExtension.class)
public class E2eTest {
    private ShopDsl shop;

    @BeforeEach
    void setUp() {
        var baseUrl = TestConfiguration.getBaseUrl();
        shop = new ShopDsl(baseUrl);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (shop != null) {
            shop.close();
        }
    }

    @Channel({ChannelType.UI, ChannelType.API})
    @TestTemplate
    void shouldGenerateOrderNumberWithPrefix() {
        shop.placeOrder("order: order1");
        shop.confirmOrderDetails("order: order1", "orderNumberPrefix: ORD-");
    }

    @Channel({ChannelType.UI, ChannelType.API})
    @TestTemplate
    void shouldRetainOrderDetailsAfterPlacement() {
        shop.placeOrder("order: order1", "productId: 11", "quantity: 3");
        shop.confirmOrderDetails("order: order1", "productId: 11", "quantity: 3");
    }

    @Channel({ChannelType.UI, ChannelType.API})
    @TestTemplate
    void shouldHaveInitialStatusPlaced() {
        shop.placeOrder("order: order1");
        shop.confirmOrderDetails("order: order1", "status: PLACED");
    }

    @Channel({ChannelType.UI, ChannelType.API})
    @TestTemplate
    void shouldAllowCancellingPlacedOrder() {
        shop.placeOrder("order: order1");
        shop.cancelOrder("order: order1");
        shop.confirmOrderDetails("order: order1", "status: CANCELLED");
    }

    @Channel({ChannelType.UI, ChannelType.API})
    @TestTemplate
    void shouldRejectOrderWithNegativeQuantity() {
        shop.placeOrder("order: order1", "productId: 10", "quantity: -5");
        shop.confirmOrderPlacementFailed("order: order1", "errorMessage: Quantity must be positive");
    }
}
