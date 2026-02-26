package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.TestCredentials;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class LoginNegativeTest extends BaseUiTest {

    private final Faker faker = new Faker();

    @Test(groups = {"regression", "ui"})
    public void shouldShowErrorWhenPasswordIsWrong() {

        new SauceSteps(driver)
                .openLogin()
                .login(TestCredentials.USER_STANDARD, faker.internet().password())
                .loginErrorContains("do not match");
    }

    @Test(groups = {"regression", "ui"})
    public void shouldShowErrorWhenUserIsLocked() {

        new SauceSteps(driver)
                .openLogin()
                .login(TestCredentials.USER_LOCKED, TestCredentials.PASSWORD)
                .loginErrorContains("user has been locked");
    }

    @Test(groups = {"regression", "ui"})
    public void shouldShowErrorWhenUsernameIsEmpty() {

        new SauceSteps(driver)
                .openLogin()
                .login("", TestCredentials.PASSWORD)
                .loginErrorContains("Username is required");
    }

    @Test(groups = {"regression", "ui"})
    public void shouldShowErrorWhenPasswordIsEmpty() {

        new SauceSteps(driver)
                .openLogin()
                .login(TestCredentials.USER_STANDARD, "")
                .loginErrorContains("Password is required");
    }
}
