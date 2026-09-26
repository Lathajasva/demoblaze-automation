package com.demoblaze.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CheckoutPage {

    private final Page page;

    private final String placeOrderButton = "button:has-text('Place Order')";
    private final String orderModal = "#orderModal";
    private final String nameInput = "#name";
    private final String cityInput = "#city";
    private final String countryInput = "#country";
    private final String cardInput = "#card";
    private final String monthInput = "#month";
    private final String yearInput = "#year";
    private final String purchaseButton = "#orderModal button:has-text('Purchase')";

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void openOrderModal() {
        page.locator(placeOrderButton).click();

        page.locator(orderModal).waitFor(
                new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        page.locator(nameInput).waitFor(
                new com.microsoft.playwright.Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );
    }

    public void enterName(String name) {
        page.locator(nameInput).fill(name);
    }

    public void enterCity(String city) {
        page.locator(cityInput).fill(city);
    }

    public void enterCountry(String country) {
        page.locator(countryInput).fill(country);
    }

    public void enterCard(String card) {
        page.locator(cardInput).fill(card);
    }

    public void enterMonth(String month) {
        page.locator(monthInput).fill(month);
    }

    public void enterYear(String year) {
        page.locator(yearInput).fill(year);
    }

    public void submitOrder() {
        page.locator(purchaseButton).click();
    }
}
