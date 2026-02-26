package com.example.app.ui.saucedemo.pages;

import com.example.app.ui.saucedemo.data.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static groovyjarjarpicocli.CommandLine.Help.Ansi.Style.reset;

public class InventoryPage extends BasePage<InventoryPage> {

    private final By title = By.cssSelector(".title");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");
    private final By backpackName = By.id("item_4_title_link");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logOutBtnOnBurgerMenu = By.id("logout_sidebar_link");
    private final By resetAppStateLink = By.id("reset_sidebar_link");
    private final By closeBtn = By.id("react-burger-cross-btn");

    private final By sortSelect = By.cssSelector(".product_sort_container");
    private final By itemPrices = By.className("inventory_item_price");
    private final By itemNames = By.className("inventory_item_name");


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSortVisible() {
        return isVisible(sortSelect);
    }

    public String titleText() {
        return text(title);
    }

    public String backpackTitle() {
        return text(backpackName);
    }

    public void addProduct(Product product) {
        click(By.id(product.getAddBtnId()));
    }

    public InventoryPage addBackpackToCart() {
        return click(addBackpack);
    }

    public InventoryPage removeBackpackFromCart() {
        return click(removeBackpack);
    }

    public int cartCount() {
        if (!isVisible(cartBadge)) return 0;
        return Integer.parseInt(text(cartBadge));
    }

    public InventoryPage openCart() {
        return click(cartLink);
    }

    public InventoryPage openBurgerMenu() {
        return click(burgerMenu);
    }

    public SauceLoginPage logoutFromBurgerMenu() {
        click(logOutBtnOnBurgerMenu);
        return new SauceLoginPage(driver);
    }

    public InventoryPage selectSortByValue(String value) {
        WebElement el = visible(sortSelect);      // важно: visible() с wait
        new Select(el).selectByValue(value);
        return this;
    }

    public String selectedSortValue() {
        return new org.openqa.selenium.support.ui.Select(visible(sortSelect))
                .getFirstSelectedOption()
                .getAttribute("value");
    }

    public List<Double> itemPrices() {
        return driver.findElements(itemPrices)
                .stream()
                .map(e -> e.getText().replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public List<String> itemNames() {
        return driver.findElements(itemNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public void resetAppState() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetAppStateLink)).click();
        click(closeBtn);
    }
}
