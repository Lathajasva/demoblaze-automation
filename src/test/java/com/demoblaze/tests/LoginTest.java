package com.demoblaze.tests;

import com.demoblaze.pages.LoginPage;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

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
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("Lathajasva", "Latha123");

        page.waitForTimeout(5000);

        String welcomeText = page.locator("#nameofuser").innerText();

        System.out.println("WELCOME TEXT = [" + welcomeText + "]");

        Assert.assertTrue(
                welcomeText.contains("Welcome"),
                "Login was not successful"
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
