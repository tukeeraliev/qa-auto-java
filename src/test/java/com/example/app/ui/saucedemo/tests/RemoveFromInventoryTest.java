package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class RemoveFromInventoryTest extends BaseUiTest {

    @Test
    public void RemoveFromInventoryTest() {

        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .addBackpack()
                .cartCountIs(1)
                .removeBackpack()
                .cartCountIs(0);
    }
}
