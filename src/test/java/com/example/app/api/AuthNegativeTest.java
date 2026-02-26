package com.example.app.api;

import com.example.app.api.steps.AuthSteps;
import com.example.app.base.BaseApiTest;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class AuthNegativeTest extends BaseApiTest {

    private final AuthSteps auth = new AuthSteps();
    Faker faker = new Faker();

    @Test(groups = {"api","regression"})
    public void loginWithInvalidUsername() {auth.loginWithInvalidCredentials(faker.name().username(), "password123");}

    @Test(groups = {"api","regression"})
    public void loginWithInvalidPassword() {auth.loginWithInvalidCredentials("admin", faker.internet().password());}

    @Test(groups = {"api", "regression"})
    public void loginWithEmptyBody() {auth.loginWithInvalidCredentials("", "");}
}
