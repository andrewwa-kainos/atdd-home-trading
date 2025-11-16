package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.ui.pages;

import com.kainos.andrewwa.atddhometrading.systemtest.TestConfiguration;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {
    private static final double DEFAULT_TIMEOUT_MILLISECONDS = TestConfiguration.getWaitSeconds() * 1000;
    protected final Page page;
    private final String baseUrl;
    private final double timeoutMilliseconds;

    public BasePage(Page page, String baseUrl) {
        this(page, baseUrl, DEFAULT_TIMEOUT_MILLISECONDS);
    }

    public BasePage(Page page, String baseUrl, double timeOutMilliseconds) {
        this.page = page;
        this.baseUrl = baseUrl;
        this.timeoutMilliseconds = timeOutMilliseconds;
    }

    protected String getBaseUrl() {
        return baseUrl;
    }

    protected String getUrl(String path) {
        return baseUrl + path;
    }

    protected void wait(Locator locator) {
        locator.waitFor(getWaitForOptions());
    }

    private Locator.WaitForOptions getWaitForOptions() {
        return new Locator.WaitForOptions().setTimeout(timeoutMilliseconds);
    }
}
