package com.demoblaze.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    private final String loginLink = "#login2";
    private final String usernameInput = "#loginusername";
    private final String passwordInput = "#loginpassword";
    private final String loginButton = "button:has-text('Log in')";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void openLogin() {
        page.locator(loginLink).click();
    }

    public void enterUsername(String username) {
        page.locator(usernameInput).fill(username);
    }

    public void enterPassword(String password) {
        page.locator(passwordInput).fill(password);
    }

    public void clickLogin() {
        page.locator(loginButton).click();
    }

    public void login(String username, String password) {
        openLogin();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
