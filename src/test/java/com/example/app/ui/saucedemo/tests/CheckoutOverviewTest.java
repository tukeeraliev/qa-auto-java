package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.Product;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class CheckoutOverviewTest extends BaseUiTest {

    @Test(groups = {"regression", "ui"})
    public void shouldDisplayTotalOnCheckoutOverview() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .startCheckout()
                .fillCheckoutFormWithRandomData()
                .continueCheckout()
                .checkoutOverviewIsOpened()
                .totalIsDisplayed();
    }

    @Test(groups = {"regression", "ui"})
    public void shouldReturnToInventoryWhenCancelFromOverview() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .startCheckout()
                .fillCheckoutFormWithRandomData()
                .continueCheckout()
                .checkoutOverviewIsOpened()
                .cancelCheckoutOverview()
                .inventoryTitleIs("Products")
                .cartCountIs(1)
                .openCart()
                .cartItemIs(Product.BACKPACK.getName());
    }
}
