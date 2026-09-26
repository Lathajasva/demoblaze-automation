package com.demoblaze.tests;

import com.demoblaze.pages.LoginPage;
import com.demoblaze.pages.LogoutPage;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTest {

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
    public void logoutTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("Lathajasva", "Latha123");

        page.locator("#nameofuser").waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        String welcomeText = page.locator("#nameofuser").innerText();

        System.out.println("WELCOME TEXT = " + welcomeText);

        Assert.assertTrue(
                welcomeText.contains("Welcome"),
                "Login was not successful"
        );

        LogoutPage logoutPage = new LogoutPage(page);

        logoutPage.logout();

        page.locator("#login2").waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        boolean loginVisible = logoutPage.isLoginLinkVisible();

        System.out.println("LOGIN LINK VISIBLE AFTER LOGOUT = " + loginVisible);

        Assert.assertTrue(
                loginVisible,
                "Login link is not visible after logout"
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
