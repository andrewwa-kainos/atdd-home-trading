package com.kainos.andrewwa.atddhometrading.systemtest.core.dsl;

import com.kainos.andrewwa.atddhometrading.systemtest.commons.dsl.DslContext;
import com.kainos.andrewwa.atddhometrading.systemtest.commons.dsl.DslParamsFactory;
import com.kainos.andrewwa.atddhometrading.systemtest.core.drivers.SystemDriver;

public class TradingDsl implements AutoCloseable {
    private final DslParamsFactory paramsFactory;
    private final SystemDriver driver;

    public TradingDsl(String baseUrl) {
        this.driver = new SystemDriver(baseUrl);
        var context = new DslContext();
        this.paramsFactory = new DslParamsFactory(context);
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void startTrade() {
        driver.goToTrading();
    }
}
