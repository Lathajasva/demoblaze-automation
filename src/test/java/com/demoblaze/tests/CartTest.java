package com.demoblaze.tests;

import com.demoblaze.pages.CartPage;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
        );

        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://www.demoblaze.com/");
    }

    @Test
    public void removeProductFromCartTest() {

        page.onDialog(dialog -> {
            System.out.println("DIALOG MESSAGE = " + dialog.message());
            dialog.accept();
        });

        page.locator("a:has-text('Samsung galaxy s6')").click();

        page.waitForTimeout(2000);

        CartPage cartPage = new CartPage(page);

        cartPage.addProductToCart();

        page.waitForTimeout(2000);

        cartPage.openCart();

        page.waitForTimeout(2000);

        int beforeRemove = cartPage.getCartItemCount();

        System.out.println("CART ITEM COUNT BEFORE REMOVE = " + beforeRemove);

        Assert.assertTrue(
                beforeRemove > 0,
                "Product was not added to cart"
        );

        cartPage.removeProductFromCart();

        page.waitForTimeout(2000);

        int afterRemove = cartPage.getCartItemCountAfterRemove();

        System.out.println("CART ITEM COUNT AFTER REMOVE = " + afterRemove);

        Assert.assertEquals(
                afterRemove,
                0,
                "Product was not removed from cart"
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
