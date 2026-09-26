package com.demoblaze.tests;

import com.demoblaze.pages.ProductSearchPage;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProductSearchTest {

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
    public void productCategoryTest() {

        ProductSearchPage productSearchPage =
                new ProductSearchPage(page);

        productSearchPage.selectLaptopsCategory();

        page.waitForTimeout(2000);

        int productCount = productSearchPage.getProductCount();

        System.out.println("LAPTOP PRODUCT COUNT = " + productCount);

        Assert.assertTrue(
                productCount > 0,
                "No laptop products displayed"
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
