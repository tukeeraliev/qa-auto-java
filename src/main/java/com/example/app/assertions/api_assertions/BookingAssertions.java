package com.example.app.assertions.api_assertions;

import com.example.app.api.models.CreateBookingResponse;
import com.example.app.api.models.BookingResponse;
import com.example.app.api.models.UpdateBookingResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BookingAssertions extends ResponseAssertions<BookingAssertions>{

    private CreateBookingResponse parsed;

    protected BookingAssertions(Response response) {
        super(response);
    }

    public static BookingAssertions assertThat(Response response) {
        return new BookingAssertions(response);
    }

    private CreateBookingResponse parsed() {
        if (parsed == null) {
            parsed = response.as(CreateBookingResponse.class);
        }
        return parsed;
    }

    private UpdateBookingResponse parsedUpdate() {
        return response.as(UpdateBookingResponse.class);
    }

    @Step("Booking id should be {expected}")
    public BookingAssertions bookingIdIs(int expected) {
        int actual = response.jsonPath().getInt("bookingid");
        Assert.assertEquals(actual, expected);
        return this;
    }

    @Step("Booking id should be present")
    public BookingAssertions bookingIdIsPresent() {
        int id = parsed().bookingid;
        Assert.assertTrue(id > 0);
        return this;
    }

    @Step("Firstname should be {expected}")
    public BookingAssertions firstnameIs(String expected) {
        Assert.assertEquals(parsedUpdate().firstname, expected);
        return this;
    }

    public int extractId() {
        return parsed().bookingid;
    }

    @Step("Booking should be deleted")
    public BookingAssertions deletedSuccessfully() {
        statusIs(201); // restful-booker delete возвращает 201
        return this;
    }

    @Step("Booking should not exist")
    public BookingAssertions notFound() {
        statusIs(404);
        return this;
    }

    private BookingResponse parsedGet() {
        return response.as(BookingResponse.class);
    }

    @Step("Firstname should be {expected}")
    public BookingAssertions firstnameGetIs(String expected) {
        Assert.assertEquals(parsedGet().firstname, expected);
        return this;
    }

    @Step("Lastname should be {expected}")
    public BookingAssertions lastnameIs(String expected) {
        Assert.assertEquals(parsedGet().lastname, expected);
        return this;
    }

    @Step("Total price should be {expected}")
    public BookingAssertions priceIs(int expected) {
        Assert.assertEquals(parsedGet().totalprice, expected);
        return this;
    }

    @Step("Response should match booking schema")
    public BookingAssertions matchesSchema(String schemaName) {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schema/" + schemaName));
        return this;
    }
}
