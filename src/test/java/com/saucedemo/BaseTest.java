package com.saucedemo;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    private Playwright playwright;
    private BrowserType browserType;
    private BrowserContext browserContext;
    private Browser browser;
    public Page page;
    protected Properties prop;
    protected HomePage homePage;

    @BeforeClass
    public void launchBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        browserContext = browser.newContext();

    }

    @BeforeMethod
    public void setup(){
        page = browserContext.newPage();
        prop = readProperties();
        page.setViewportSize(1920, 1080);
        page.navigate(prop.getProperty("url"), new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));

        homePage = new HomePage(page);

    }
    @Test
    public void verifyLogin(){
        homePage.login(prop.getProperty("uname"), prop.getProperty("pwd"));
        System.out.println("Logged in...");
    }

    @AfterSuite
    public void tearDown(){
        if (page != null) page.close();
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
    private Properties readProperties(){
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        return prop;
    }
}
