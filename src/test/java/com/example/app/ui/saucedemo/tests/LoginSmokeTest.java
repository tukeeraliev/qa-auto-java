package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class LoginSmokeTest extends BaseUiTest {

    @Test(groups = {"smoke", "ui"})
    public void loginShouldOpenInventory() {
        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .inventoryTitleIs("Products");
    }
}
