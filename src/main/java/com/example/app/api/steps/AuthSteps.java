package com.example.app.api.steps;

import com.example.app.api.models.AuthRequest;
import com.example.app.api.models.AuthResponse;
import com.example.app.api.services.AuthService;
import com.example.app.config.Env;
import com.example.app.core.http.TokenContext;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

public class AuthSteps {

    private final AuthService service = new AuthService();

    @Step("Login as {username}")
    public String login(String username, String password) {

        Response response = service.login(new AuthRequest(username, password));

        Assert.assertEquals(response.statusCode(), 200);

        String token = response.as(AuthResponse.class).token;

        Assert.assertNotNull(token);

        TokenContext.set(token);

        return token;
    }

    @Step("Login with invalid credentials")
    public void loginWithInvalidCredentials(String username, String password) {

        Response response = service.login(new AuthRequest(username, password));

        Assert.assertEquals(response.statusCode(), 200);

        String reason = response.jsonPath().getString("reason");

        Assert.assertEquals(reason, "Bad credentials",
                "Reason should be Bad credentials");
    }

    @Step()
    public String login() {
        return login(
                Env.get("auth.username"),
                Env.get("auth.password")
        );
    }
}
