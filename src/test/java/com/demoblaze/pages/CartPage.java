package com.demoblaze.pages;

import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    private final String addToCartButton = "a:has-text('Add to cart')";
    private final String cartLink = "#cartur";
    private final String cartRows = "#tbodyid tr";
    private final String deleteButton = "#tbodyid tr td a";

    public CartPage(Page page) {
        this.page = page;
    }

    public void addProductToCart() {
        page.locator(addToCartButton).click();
    }

    public void openCart() {
        page.locator(cartLink).click();
    }

    public int getCartItemCount() {
        page.waitForSelector(
                cartRows,
                new Page.WaitForSelectorOptions().setState(
                        com.microsoft.playwright.options.WaitForSelectorState.VISIBLE
                )
        );

        return page.locator(cartRows).count();
    }

    public void removeProductFromCart() {
        page.locator(deleteButton).click();
    }

    public int getCartItemCountAfterRemove() {
        page.waitForTimeout(1000);
        return page.locator(cartRows).count();
    }
}