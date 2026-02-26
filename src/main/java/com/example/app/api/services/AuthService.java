package com.example.app.api.services;

import com.example.app.api.endpoints.Endpoint;
import com.example.app.api.models.AuthRequest;
import com.example.app.core.http.specs.RequestSpecs;
import io.restassured.response.Response;

public class AuthService {

    public Response login(AuthRequest request) {
        return RequestSpecs.json()
                .body(request)
                .post(Endpoint.AUTH.path());
    }
}
