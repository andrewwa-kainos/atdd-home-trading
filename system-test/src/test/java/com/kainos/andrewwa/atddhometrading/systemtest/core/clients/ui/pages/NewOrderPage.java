package com.kainos.andrewwa.atddhometrading.systemtest.core.clients.ui.pages;

import com.microsoft.playwright.Page;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewOrderPage extends BasePage {
    public NewOrderPage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public void inputProductId(String productId) {
        var productIdInput = page.locator("[aria-label='Product ID']");
        productIdInput.fill(productId);
    }

    public void inputQuantity(String quantity) {
        var quantityInput = page.locator("[aria-label='Quantity']");
        quantityInput.fill(quantity);
    }

    public void clickPlaceOrder() {
        var placeOrderButton = page.locator("[aria-label='Place Order']");
        placeOrderButton.click();
    }

    public void confirmConfirmationMessageShown() {
        var confirmationMessageText = readConfirmationMessageText();
        var matcher = getConfirmationMessageTextMatcher(confirmationMessageText);
        assertTrue(
                matcher.find(),
                "Confirmation message should match expected pattern. Actual: " + confirmationMessageText
        );
    }

    public String readConfirmationMessageText() {
        var confirmationMessage = page.locator("[role='alert']");
        wait(confirmationMessage);
        return confirmationMessage.textContent();
    }

    private Matcher getConfirmationMessageTextMatcher(String confirmationMessageText) {
        var pattern = Pattern.compile(
                "Success! Order has been created with Order Number ([\\w-]+) and Total Price \\$(\\d+(?:\\.\\d{2})?)");
        return pattern.matcher(confirmationMessageText);
    }

    public Optional<String> getOrderNumber() {
        var confirmationMessageText = readConfirmationMessageText();
        var matcher = getConfirmationMessageTextMatcher(confirmationMessageText);

        if (!matcher.find()) {
            return Optional.empty();
        }

        var orderNumber = matcher.group(1);
        return Optional.of(orderNumber);
    }

    public Optional<BigDecimal> getTotalPrice() {
        var confirmationMessageText = readConfirmationMessageText();
        var matcher = getConfirmationMessageTextMatcher(confirmationMessageText);

        if (!matcher.find()) {
            return Optional.empty();
        }

        var totalPriceString = matcher.group(2);
        var totalPrice = new BigDecimal(totalPriceString);
        return Optional.of(totalPrice);
    }
}
