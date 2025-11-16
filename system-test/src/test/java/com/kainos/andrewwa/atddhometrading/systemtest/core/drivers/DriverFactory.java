package com.kainos.andrewwa.atddhometrading.systemtest.core.drivers;

import com.kainos.andrewwa.atddhometrading.systemtest.core.drivers.api.ApiDriver;
import com.kainos.andrewwa.atddhometrading.systemtest.core.drivers.ui.UiDriver;
import com.optivem.atdd.commons.channels.ChannelContext;

import java.util.*;

public class DriverFactory {

    private final String baseUrl;

    public DriverFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Driver createDriver() {
        var channelType = ChannelContext.get();

        if (Objects.equals(channelType, ChannelType.UI)) {
            return new UiDriver(baseUrl);
        } else if (Objects.equals(channelType, ChannelType.API)) {
            return new ApiDriver(baseUrl);
        } else {
            throw new RuntimeException("Unsupported channel: " + channelType);
        }
    }
}
