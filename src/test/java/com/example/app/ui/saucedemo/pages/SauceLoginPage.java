package com.example.app.ui.saucedemo.pages;

import com.example.app.config.AppConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceLoginPage extends BasePage<SauceLoginPage> {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public SauceLoginPage(WebDriver driver) {
        super(driver);
    }

    public SauceLoginPage open() {
        driver.get(AppConfig.uiBaseUrl());
        return this;
    }

    public SauceLoginPage login(String user, String pass) {
        return type(username, user)
                .type(password, pass)
                .click(loginBtn);
    }

    public String errorText() {
        return text(error);
    }

    public boolean isLoginBtnVisible() {
        return isVisible(loginBtn);
    }
}
