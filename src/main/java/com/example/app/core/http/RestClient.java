package com.example.app.core.http;

import com.example.app.config.AppConfig;
import com.example.app.core.http.filters.NiceLoggingFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public final class RestClient {

    static {
        String base = AppConfig.baseUrl();
        System.out.println("RestAssured.baseURI = " + base);
        RestAssured.baseURI = base;
    }

    private RestClient() {
    }

    public static RequestSpecification given() {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .filter(new NiceLoggingFilter())
                .contentType("application/json");
    }
}