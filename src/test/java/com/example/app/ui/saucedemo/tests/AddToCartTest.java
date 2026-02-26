package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.Product;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseUiTest {

    @Test(groups = {"smoke", "ui"})
    public void addBackpackShouldAppearInCart() {
        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addProduct(Product.BACKPACK)
                .cartCountMatchesAddedProducts()
                .openCart()
                .cartItemIs(Product.BACKPACK.getName());
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldAddMultipleProducts() {
        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addProduct(Product.BACKPACK)
                .cartCountMatchesAddedProducts()
                .addProduct(Product.BIKE_LIGHT)
                .cartCountMatchesAddedProducts()
                .openCart()
                .cartContains(Product.BIKE_LIGHT)
                .cartContains(Product.BACKPACK);
    }
}
