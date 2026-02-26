package com.example.app.ui.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage<CheckoutCompletePage> {

    private final By header = By.cssSelector(".complete-header");
    private final By backHomeBtn = By.id("back-to-products");
    private final By completeHeader = By.xpath("//h2[text()='Thank you for your order!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String headerText() {
        return text(header);
    }

    public InventoryPage backHome() {
        click(backHomeBtn);
        return new InventoryPage(driver);
    }

    public String getCompleteMessage() {
        return driver.findElement(completeHeader).getText();
    }
}
