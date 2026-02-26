package com.example.app.ui.saucedemo.pages;

import com.example.app.ui.saucedemo.data.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage<CartPage> {

    private final By cartItemName = By.cssSelector(".inventory_item_name");
    private final By checkoutBtn = By.id("checkout");
    private final By removeBtn = By.id("remove-sauce-labs-backpack");
    private final By cartItems = By.className("cart_item");
    private final By continueShoppingBtn = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String firstItemName() {
        return text(cartItemName);
    }

    public CartPage checkout() {
        return click(checkoutBtn);
    }

    public CartPage remove() {
        return click(removeBtn);
    }

    public boolean isEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }

    public CartPage clickContinueShopping() {
        return click(continueShoppingBtn);
    }

    public boolean isItemPresent(Product product) {
        return driver.findElements(
                By.xpath("//div[@class='inventory_item_name' and text()='" + product.getName() + "']")
        ).size() > 0;
    }
}
