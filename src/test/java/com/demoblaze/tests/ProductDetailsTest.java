package com.demoblaze.tests;

import com.demoblaze.pages.ProductDetailsPage;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProductDetailsTest {

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
    public void productDetailsTest() {

        ProductDetailsPage productDetailsPage =
                new ProductDetailsPage(page);

        productDetailsPage.selectSamsungGalaxyS6();

        page.waitForTimeout(2000);

        String title = productDetailsPage.getProductTitle();
        String price = productDetailsPage.getProductPrice();
        String description = productDetailsPage.getProductDescription();

        System.out.println("PRODUCT TITLE = " + title);
        System.out.println("PRODUCT PRICE = " + price);
        System.out.println("PRODUCT DESCRIPTION PRESENT = " +
                !description.isEmpty());

        Assert.assertTrue(
                title.contains("Samsung galaxy s6"),
                "Product title is not displayed correctly"
        );

        Assert.assertTrue(
                !price.isEmpty(),
                "Product price is not displayed"
        );

        Assert.assertTrue(
                !description.isEmpty(),
                "Product description is not displayed"
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
