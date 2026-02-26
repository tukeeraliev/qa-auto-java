package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class CheckoutSmokeTest extends BaseUiTest {

    @Test(groups = {"smoke", "ui"})
    public void shouldOpenCheckoutStepOne() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .startCheckout()
                .checkoutStepOneIsOpened();
    }


}
