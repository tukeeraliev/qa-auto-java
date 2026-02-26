package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.Product;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class CartRegressionTest extends BaseUiTest {

    @Test(groups={"regression","ui"})
    public void shouldRemoveItemFromCart() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .cartItemIs(Product.BACKPACK.getName())
                .removeProductFromCart()
                .cartIsEmpty();
    }

    @Test(groups={"regression","ui"})
    public void shouldContinueShoppingAndKeepCartItems() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addProduct(Product.BACKPACK)
                .openCart()
                .cartItemIs(Product.BACKPACK.getName())
                .continueShoppingFromCart()
                .inventoryTitleIs("Products")
                .cartCountMatchesAddedProducts()
                .openCart()
                .cartItemIs(Product.BACKPACK.getName());
    }

    @Test(groups={"regression","ui"})
    public void shouldKeepCartAfterInventoryRefresh() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addProduct(Product.BACKPACK)
                .cartCountMatchesAddedProducts()
                .refreshPage()
                .cartCountMatchesAddedProducts()
                .openCart()
                .cartItemIs(Product.BACKPACK.getName());
    }
}
