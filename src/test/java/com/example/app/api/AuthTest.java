package com.example.app.api;

import com.example.app.api.steps.AuthSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class AuthTest extends BaseApiTest {

    private final AuthSteps auth = new AuthSteps();

    @Test(groups = {"api","smoke"})
    public void shouldLogin() {
        auth.login("admin", "password123");
    }
}
