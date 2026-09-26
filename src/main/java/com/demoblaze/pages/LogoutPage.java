package com.demoblaze.pages;

import com.microsoft.playwright.Page;

public class LogoutPage {

    private final Page page;

    private final String logoutLink = "#logout2";
    private final String loginLink = "#login2";

    public LogoutPage(Page page) {
        this.page = page;
    }

    public void logout() {
        page.locator(logoutLink).click();
    }

    public boolean isLoginLinkVisible() {
        return page.locator(loginLink).isVisible();
    }
}
