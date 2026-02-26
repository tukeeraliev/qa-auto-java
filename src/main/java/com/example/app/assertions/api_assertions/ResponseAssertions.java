package com.example.app.assertions.api_assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseAssertions <T extends ResponseAssertions<T>> {

    protected final Response response;

    protected ResponseAssertions(Response response) {
        this.response = response;
    }

    public static ResponseAssertions<?> of(Response response) {
        return new ResponseAssertions<>(response);
    }

    @Step("Status code should be {expected}")
    public T statusIs(int expected) {
        Assert.assertEquals(response.statusCode(), expected);
        return (T) this;
    }

    @Step("Body should be '{expected}'")
    public ResponseAssertions bodyIs(String expected) {
        Assert.assertEquals(response.getBody().asString(), expected);
        return this;
    }
}
