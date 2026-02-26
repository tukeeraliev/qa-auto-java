package com.example.app.ui.saucedemo.e2e;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.Product;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutE2ETest extends BaseUiTest {

    private SauceSteps steps;

    @BeforeMethod(alwaysRun = true)
    public void initSteps() {
        steps = new SauceSteps(driver);
    }

    @Test(groups = {"smoke", "ui"})
    public void shouldCompleteCheckoutFlow() {

        steps.openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .cartItemIs(Product.BACKPACK.getName())
                .startCheckout()
                .checkoutStepOneIsOpened()
                .fillCheckoutFormWithRandomData()
                .continueCheckout()
                .checkoutOverviewIsOpened()
                .finishCheckout()
                .verifyOrderCompleted();
    }
}
