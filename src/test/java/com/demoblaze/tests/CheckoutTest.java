package com.demoblaze.tests;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.CheckoutPage;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://www.demoblaze.com/");
    }

    @Test
    public void placeOrderTest() {

        page.onDialog(dialog -> {
            System.out.println("DIALOG MESSAGE = " + dialog.message());
            dialog.accept();
        });

        page.locator("a:has-text('Samsung galaxy s6')")
                .click(new Locator.ClickOptions().setNoWaitAfter(true));

        page.waitForTimeout(2000);

        CartPage cartPage = new CartPage(page);

        cartPage.addProductToCart();

        page.waitForTimeout(2000);

        cartPage.openCart();

        page.waitForTimeout(2000);

        CheckoutPage checkoutPage = new CheckoutPage(page);

        checkoutPage.openOrderModal();

        checkoutPage.enterName("Latha");
        checkoutPage.enterCity("Chennai");
        checkoutPage.enterCountry("India");
        checkoutPage.enterCard("4111111111111111");
        checkoutPage.enterMonth("09");
        checkoutPage.enterYear("2026");

        checkoutPage.submitOrder();

        page.waitForTimeout(2000);

        String confirmation = page.locator(".sweet-alert").innerText();

        System.out.println("ORDER CONFIRMATION = " + confirmation);

        Assert.assertTrue(
                confirmation.contains("Thank you for your purchase"),
                "Order confirmation was not displayed"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}
