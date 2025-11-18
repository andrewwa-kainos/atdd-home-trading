package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.ui.pages;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {
    public HomePage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public NewOrderPage clickNewOrder() {
        page.click("a[href='/shop.html']");
        return new NewOrderPage(page, getBaseUrl());
    }

    public OrderHistoryPage clickOrderHistory() {
        page.click("a[href='/order-history.html']");
        return new OrderHistoryPage(page, getBaseUrl());
    }

    public DashboardPage clickDashboard() {
        page.click("a[href='/dashboard.html']");
        return new DashboardPage(page, getBaseUrl());
    }
}
