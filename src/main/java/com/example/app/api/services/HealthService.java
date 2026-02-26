package com.example.app.api.services;

import com.example.app.api.endpoints.Endpoint;
import com.example.app.core.http.RestClient;
import com.example.app.core.http.specs.RequestSpecs;
import io.restassured.response.Response;

public class HealthService {

    public Response ping() {
        return RestClient.given()
                .get(Endpoint.PING.path());
    }
}
