package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.Product;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class CheckoutStepOneValidationTest extends BaseUiTest {

    @Test(groups = {"regression", "ui"})
    public void shouldShowErrorWhenFirstNameIsEmpty() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .startCheckout()
                .fillCheckoutForm("", "Tom", "12345")
                .continueCheckout()
                .checkoutStepOneErrorContains("First Name is required");
    }

    @Test(groups = {"regression", "ui"})
    public void shouldShowErrorWhenLastNameIsEmpty() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .startCheckout()
                .fillCheckoutForm("Tom", "", "12345")
                .continueCheckout()
                .checkoutStepOneErrorContains("Last Name is required");
    }

    @Test(groups = {"regression", "ui"})
    public void shouldShowErrorWhenZipIsEmpty() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .startCheckout()
                .fillCheckoutForm("Bom", "Tom", "")
                .continueCheckout()
                .checkoutStepOneErrorContains("Postal Code is required");
    }

    @Test(groups = {"regression", "ui"})
    public void shouldReturnToCartWhenCancelClicked() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .openCart()
                .startCheckout()
                .cancelCheckoutStepOne()
                .cartPageIsOpened()
                .cartItemIs(Product.BACKPACK.getName());
    }
}
