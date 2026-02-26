package com.example.app.ui.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage<CheckoutStepTwoPage> {

    private final By finishBtn = By.id("finish");
    private final By cancelBtn = By.id("cancel");

    private final By totalLabel = By.cssSelector(".summary_total_label");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutCompletePage finish() {
        click(finishBtn);
        return new CheckoutCompletePage(driver);
    }

    public InventoryPage cancel() {
        click(cancelBtn);
        return new InventoryPage(driver);
    }

    public boolean isTotalVisible() {
        return isVisible(totalLabel);
    }
}
