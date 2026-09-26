package com.demoblaze.pages;

import com.microsoft.playwright.Page;

public class ProductSearchPage {

    private final Page page;

    private final String laptopsCategory = "a:has-text('Laptops')";

    public ProductSearchPage(Page page) {
        this.page = page;
    }

    public void selectLaptopsCategory() {
        page.locator(laptopsCategory).click();
    }

    public int getProductCount() {
        return page.locator(".card-title").count();
    }
}
