package com.saucedemo;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;
    private final Locator username;
    private final Locator password;
    private final Locator loginButton;

    public HomePage(Page page) {
        this.page = page;
        this.username = page.getByPlaceholder("Username");
        this.password = page.getByPlaceholder("Password");
        this.loginButton = page.locator("#login-button");
    }

    public void login(String uname, String pwd){
        username.fill(uname);
        password.fill(pwd);
        loginButton.click();
    }
}
