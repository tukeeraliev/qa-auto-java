package com.example.app.api;

import com.example.app.api.steps.HealthSteps;
import com.example.app.assertions.api_assertions.HealthAssertions;
import com.example.app.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class HealthTest extends BaseApiTest {

    private final HealthSteps steps = new HealthSteps();

    @Test(groups = {"api","smoke"})
    public void pingShouldReturn201() {

        Response response = steps.ping();

        HealthAssertions.assertThat(response)
                .statusIs(201)
                .bodyIs("Created");
    }
}
