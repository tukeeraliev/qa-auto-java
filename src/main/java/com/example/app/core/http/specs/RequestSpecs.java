package com.example.app.core.http.specs;

import com.example.app.core.http.RestClient;
import com.example.app.core.http.TokenContext;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecs {

    private RequestSpecs() {}

    public static RequestSpecification base() {
        return RestClient.given();
    }

    public static RequestSpecification json() {
        return base().contentType("application/json");
    }

    public static RequestSpecification auth() {

        String token = TokenContext.get();

        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Token is empty. Call login first.");
        }

        return json().header("Cookie", "token=" + token);
    }
}
