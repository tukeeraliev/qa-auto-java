package com.example.app.assertions.api_assertions;

import io.restassured.response.Response;

public final class HealthAssertions {

    private HealthAssertions() {}

    public static ResponseAssertions assertThat(Response response) {
        return new ResponseAssertions(response);
    }
}
