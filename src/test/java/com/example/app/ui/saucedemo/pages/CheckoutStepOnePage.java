package com.example.app.ui.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage<CheckoutStepOnePage> {

    private final By firstName = By.id("first-name");
    private final By lastName  = By.id("last-name");
    private final By postal    = By.id("postal-code");

    private final By continueBtn = By.id("continue");
    private final By cancelBtn   = By.id("cancel");
    private final By error       = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public CheckoutStepOnePage fillFirstName(String value) {
        type(firstName, value);
        return this;
    }

    public CheckoutStepOnePage fillLastName(String value) {
        type(lastName, value);
        return this;
    }

    public CheckoutStepOnePage fillPostalCode(String value) {
        type(postal, value);
        return this;
    }

    public CheckoutStepOnePage fillForm(String fn, String ln, String zip) {
        return fillFirstName(fn)
                .fillLastName(ln)
                .fillPostalCode(zip);
    }

    public CheckoutStepTwoPage continueCheckout() {
        click(continueBtn);
        return new CheckoutStepTwoPage(driver);
    }

    public CartPage cancel() {
        click(cancelBtn);
        return new CartPage(driver);
    }

    public String errorText() {
        return text(error);
    }
}
