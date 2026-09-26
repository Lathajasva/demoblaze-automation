package com.demoblaze.pages;

import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    private final String addToCartButton = "a:has-text('Add to cart')";
    private final String cartLink = "#cartur";
    private final String cartTable = "#tbodyid";

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
        return page.locator(cartTable + " tr").count();
    }
}
