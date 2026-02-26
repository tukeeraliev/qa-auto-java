package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.Product;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class ResetAppStateTest extends BaseUiTest {

    @Test(groups = {"smoke", "ui"})
    public void shouldResetAppStateFromBurgerMenu() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addProduct(Product.BACKPACK)
                .cartCountMatchesAddedProducts()
                .openMenu()
                .resetAppState()
                .cartIsEmpty();
    }
}
