package com.kainos.andrewwa.atddhometrading.systemtest.smoketests;

import com.kainos.andrewwa.atddhometrading.systemtest.TestConfiguration;
import com.kainos.andrewwa.atddhometrading.systemtest.core.drivers.ChannelType;
import com.kainos.andrewwa.atddhometrading.systemtest.core.dsl.ShopDsl;
import com.kainos.andrewwa.atddhometrading.systemtest.core.dsl.TradingDsl;
import com.optivem.atdd.commons.channels.Channel;
import com.optivem.atdd.commons.channels.ChannelExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ChannelExtension.class)
public class SmokeTest {
    private ShopDsl shop;
    private TradingDsl trading;

    @BeforeEach
    void setUp() {
        var baseUrl = TestConfiguration.getBaseUrl();
        shop = new ShopDsl(baseUrl);
        trading = new TradingDsl(baseUrl);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (shop != null) {
            shop.close();
        }
    }

    @Channel({ ChannelType.UI, ChannelType.API })
    @TestTemplate
    void shouldBeAbleToGoToShop() {
        shop.goToShop();
    }

    @Channel({ ChannelType.UI, ChannelType.API })
    @TestTemplate
    void shouldBeAbleToTrade() {
        trading.startTrade();
    }
}
