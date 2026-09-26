package com.demoblaze.pages;

import com.microsoft.playwright.Page;

public class ProductDetailsPage {

    private final Page page;

    private final String productLink = "a:has-text('Samsung galaxy s6')";
    private final String productTitle = "h2.name";
    private final String productPrice = "h3.price-container";
    private final String productDescription = "#more-information";

    public ProductDetailsPage(Page page) {
        this.page = page;
    }

    public void selectSamsungGalaxyS6() {
        page.locator(productLink).click();
    }

    public String getProductTitle() {
        return page.locator(productTitle).innerText();
    }

    public String getProductPrice() {
        return page.locator(productPrice).innerText();
    }

    public String getProductDescription() {
        return page.locator(productDescription).innerText();
    }
}
