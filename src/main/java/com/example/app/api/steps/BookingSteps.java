package com.example.app.api.steps;

import com.example.app.api.models.BookingData;
import com.example.app.api.models.CreateBookingRequest;
import com.example.app.api.services.BookingService;
import com.example.app.assertions.api_assertions.BookingAssertions;
import com.example.app.assertions.api_assertions.ResponseAssertions;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class BookingSteps {

    private final BookingService bookingService = new BookingService();

    @Step("Get booking with id = {id}")
    public BookingAssertions getBooking(int id) {
        Response response = bookingService.getBooking(id);
        return BookingAssertions.assertThat(response);
    }

    @Step("Get booking with id = {id}")
    public BookingAssertions getBookingStr(String id) {
        Response response = bookingService.getBooking(id);
        return BookingAssertions.assertThat(response);
    }

    @Step("Create booking")
    public BookingAssertions createBooking(CreateBookingRequest request) {
        Response response = bookingService.createBooking(request);
        return BookingAssertions.assertThat(response);
    }

    @Step("Update booking {id}")
    public BookingAssertions updateBooking(int id, CreateBookingRequest request) {

        Response response = bookingService.updateBooking(id, request);

        return BookingAssertions.assertThat(response);
    }

    @Step("Delete booking {id}")
    public BookingAssertions deleteBooking(int id) {

        Response response = bookingService.deleteBooking(id);

        return BookingAssertions.assertThat(response);
    }

    @Step("Delete Booking no auth {id}")
    public BookingAssertions deleteBookingNoAuth(int id) {

        Response response = bookingService.deleteBookingNoAuth(id);

        return BookingAssertions.assertThat(response);
    }

    @Step("Delete booking with invalid token")
    public ResponseAssertions deleteBookingInvalidToken(int id) {

        Response response = bookingService.deleteBookingWithToken(id, "Invalid_token_123");

        return ResponseAssertions.of(response);
    }

    @Step("Update booking without token (should fail)")
    public ResponseAssertions updateBookingNoAuth(int id, CreateBookingRequest body) {
        Response response = bookingService.updateBookingNoAuth(id, body);
        return ResponseAssertions.of(response);
    }
}
