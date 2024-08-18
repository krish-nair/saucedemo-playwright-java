package com.saucedemo;

import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyLogin(){
        homePage.login(prop.getProperty("uname"), prop.getProperty("pwd"));
        System.out.println("Logged in...");
    }
}
