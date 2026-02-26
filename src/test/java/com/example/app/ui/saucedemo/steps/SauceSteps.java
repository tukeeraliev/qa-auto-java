package com.example.app.ui.saucedemo.steps;

import com.example.app.ui.saucedemo.data.Product;
import com.example.app.ui.saucedemo.data.SortOption;
import com.example.app.ui.saucedemo.data.TestCredentials;
import com.example.app.ui.saucedemo.pages.*;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SauceSteps {

    private final Faker faker = new Faker();
    private final WebDriver driver;

    private final SauceLoginPage loginPage;
    private final InventoryPage inventoryPage;
    private final CartPage cartPage;
    private final List<Product> addedProducts = new ArrayList<>();

    private final CheckoutStepOnePage checkoutStepOnePage;
    private final CheckoutStepTwoPage checkoutStepTwoPage;
    private final CheckoutCompletePage checkoutCompletePage;

    public SauceSteps(WebDriver driver) {
        this.driver = driver;

        this.loginPage = new SauceLoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);

        this.checkoutStepOnePage = new CheckoutStepOnePage(driver);
        this.checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        this.checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    @Step("Login as standard user")
    public SauceSteps loginAsStandardUser() {
        loginPage.login(TestCredentials.USER_STANDARD, TestCredentials.PASSWORD);
        return this;
    }

    @Step("Open SauceDemo login page")
    public SauceSteps openLogin() {
        loginPage.open();
        return this;
    }

    @Step("Login with username='{user}'")
    public SauceSteps login(String user, String pass) {
        loginPage.login(user, pass);
        return this;
    }

    @Step("Inventory title should be '{expected}'")
    public SauceSteps inventoryTitleIs(String expected) {
        String actual = inventoryPage.titleText();
        Assert.assertEquals(actual, expected, "Inventory title mismatch");
        return this;
    }

    @Step("Add Backpack to cart")
    public SauceSteps addBackpack() {
        inventoryPage.addBackpackToCart();
        return this;
    }

    @Step("Remove Backpack from cart {Inventory page}")
    public SauceSteps removeBackpack() {
        inventoryPage.removeBackpackFromCart();
        return this;
    }

    @Step("Cart count should be {expected}")
    public SauceSteps cartCountIs(int expected) {
        int actual = inventoryPage.cartCount();
        Assert.assertEquals(actual, expected, "Cart badge count mismatch");
        return this;
    }

    @Step("Open cart")
    public SauceSteps openCart() {
        inventoryPage.openCart();
        return this;
    }

    @Step("Cart first item should be '{expected}'")
    public SauceSteps cartItemIs(String expected) {
        String actual = cartPage.firstItemName();
        Assert.assertEquals(actual, expected, "Cart item name mismatch");
        return this;
    }

    @Step("Start checkout")
    public SauceSteps startCheckout() {
        cartPage.checkout();
        return this;
    }

    @Step("Fill checkout form with random data")
    public SauceSteps fillCheckoutFormWithRandomData() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String zip = faker.address().zipCode();

        checkoutStepOnePage.fillFirstName(firstName);
        checkoutStepOnePage.fillLastName(lastName);
        checkoutStepOnePage.fillPostalCode(zip);

        return this;
    }

    @Step("Fill checkout form: first='{name}', last='{lastName}', zip='{zip}'")
    public SauceSteps fillCheckoutForm(String name, String lastName, String zip) {

        checkoutStepOnePage.fillFirstName(name);
        checkoutStepOnePage.fillLastName(lastName);
        checkoutStepOnePage.fillPostalCode(zip);

        return this;
    }

    @Step("Continue checkout")
    public SauceSteps continueCheckout() {
        checkoutStepOnePage.continueCheckout();
        return this;
    }

    @Step("Finish checkout")
    public SauceSteps finishCheckout() {
        checkoutStepTwoPage.finish();
        return this;
    }

    @Step("Verify order completed")
    public SauceSteps verifyOrderCompleted() {
        String message = checkoutCompletePage.getCompleteMessage();
        Assert.assertTrue(message.contains("Thank you"), "Order not completed");
        return this;
    }

    @Step("Verify error message on step one page contains expected text")
    public SauceSteps checkoutStepOneErrorContains(String expectedPart) {
        String actual = checkoutStepOnePage.errorText();
        Assert.assertTrue(actual.contains(expectedPart),
                "Expected error to contain: " + expectedPart + " but was: " + actual);
        return this;
    }

    @Step("Checkout step one should be opened")
    public SauceSteps checkoutStepOneIsOpened() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("checkout-step-one.html"),
                "Checkout Step One page is not opened");
        return this;
    }

    @Step("Cart page should be opened")
    public SauceSteps cartPageIsOpened() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("cart.html"),
                "Cart page is not opened");
        return this;
    }

    @Step("Checkout Overview page is opened")
    public SauceSteps checkoutOverviewIsOpened() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("checkout-step-two.html"),
                "Checkout Overview page is not opened");
        return this;
    }

    @Step("Login page should be opened")
    public SauceSteps loginPageIsOpened() {
        Assert.assertTrue(new SauceLoginPage(driver).isLoginBtnVisible(),
                "Login page is not opened. Actual URL: " + driver.getCurrentUrl());
        return this;
    }

    @Step("Total is displayed")
    public SauceSteps totalIsDisplayed() {
        Assert.assertTrue(checkoutStepTwoPage.isTotalVisible(),
                "Total label is not visible");
        return this;
    }

    @Step("click cancel from Checkout page two")
    public SauceSteps cancelCheckoutOverview() {
        checkoutStepTwoPage.cancel();
        return this;
    }

    @Step("Open burger menu")
    public SauceSteps openMenu() {
        inventoryPage.openBurgerMenu();
        return this;
    }

    @Step("Logout from application")
    public SauceSteps logout() {
        inventoryPage.logoutFromBurgerMenu();
        return this;
    }

    @Step("Click cancel on checkout page one")
    public SauceSteps cancelCheckoutStepOne() {
        checkoutStepOnePage.cancel();
        return this;
    }

    @Step("Remove product from cart")
    public SauceSteps removeProductFromCart() {
        cartPage.remove();
        return this;
    }

    @Step("Cart should be empty")
    public SauceSteps cartIsEmpty() {
        Assert.assertTrue(cartPage.isEmpty(), "Cart is not empty");
        return this;
    }

    @Step("Click continue shopping from cart")
    public SauceSteps continueShoppingFromCart() {
        cartPage.clickContinueShopping();
        return this;
    }

    @Step("Refresh page")
    public SauceSteps refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Login or password mismatch error is visible")
    public SauceSteps loginErrorContains(String message) {
        String actual = loginPage.errorText();
        Assert.assertTrue(
                actual.contains(message),
                "Expected login error message but was: " + actual);
        return this;
    }

    @Step("Select sort: {option}")
    public SauceSteps selectSort(SortOption option) {
        inventoryPage.selectSortByValue(option.getValue());
        return this;
    }

    @Step("Sort should be selected: {option}")
    public SauceSteps sortIsSelected(SortOption option) {
        Assert.assertEquals(inventoryPage.selectedSortValue(), option.getValue(),
                "Sort option is not selected");
        return this;
    }

    @Step("Sort dropdown should be visible")
    public SauceSteps sortDropdownIsVisible() {
        Assert.assertTrue(inventoryPage.isSortVisible(),
                "Sort dropdown is not visible. URL: " + driver.getCurrentUrl());
        return this;
    }

    @Step("Items should be sorted by price high to low")
    public SauceSteps itemsSortedByPriceDesc() {

        List<Double> actual = inventoryPage.itemPrices();

        List<Double> sorted = new ArrayList<>(actual);
        sorted.sort(Collections.reverseOrder());

        Assert.assertEquals(actual, sorted, "Prices are not sorted high to low");
        System.out.println(actual);
        System.out.println(sorted);

        return this;
    }

    @Step("Items should be sorted by price LOW to HIGH")
    public SauceSteps itemsSortedByPriceAsc() {

        List<Double> actual = inventoryPage.itemPrices();

        List<Double> sorted = new ArrayList<>(actual);
        Collections.sort(sorted);

        Assert.assertEquals(actual, sorted, "Prices are not sorted LOW→HIGH");

        return this;
    }

    @Step("Items should be sorted by name A to Z")
    public SauceSteps itemsSortedByNameAsc() {

        List<String> actual = inventoryPage.itemNames();

        List<String> sorted = new ArrayList<>(actual);
        Collections.sort(sorted);

        Assert.assertEquals(actual, sorted, "Items are not sorted A→Z");
        System.out.println(actual);
        System.out.println(sorted);

        return this;
    }

    @Step("Items should be sorted by name Z to A")
    public SauceSteps itemsSortedByNameDesc() {

        List<String> actual = inventoryPage.itemNames();

        List<String> sorted = new ArrayList<>(actual);
        Collections.sort(sorted);
        Collections.reverse(sorted);

        Assert.assertEquals(actual, sorted, "Items are not sorted Z→A");
        System.out.println(actual);
        System.out.println(sorted);

        return this;
    }

    @Step("Reset App State from burger menu")
    public SauceSteps resetAppState() {
        inventoryPage.resetAppState();
        return this;
    }

    @Step("Add product {product}")
    public SauceSteps addProduct(Product product) {
        inventoryPage.addProduct(product);
        addedProducts.add(product);
        return this;
    }

    @Step("Cart count should match added products")
    public SauceSteps cartCountMatchesAddedProducts() {

        int actual = inventoryPage.cartCount();
        Assert.assertEquals(actual, addedProducts.size(), "Cart count mismatch");

        return this;
    }

    @Step("Cart should contain product {product}")
    public SauceSteps cartContains(Product product) {
        Assert.assertTrue(cartPage.isItemPresent(product),
                "Product not found in cart: " + product.getName());
        return this;
    }
}
