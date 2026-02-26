package com.example.app.api.steps;

import com.example.app.api.services.HealthService;
import com.example.app.core.reporting.AllureSteps;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class HealthSteps {

    private final HealthService healthService = new HealthService();

    @Step("Ping health endpoint")
    public Response ping() {
        return healthService.ping();
    }
}
