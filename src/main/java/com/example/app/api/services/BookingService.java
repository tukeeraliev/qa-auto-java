package com.example.app.api.services;

import com.example.app.api.endpoints.Endpoint;
import com.example.app.api.models.BookingData;
import com.example.app.api.models.CreateBookingRequest;
import com.example.app.core.http.specs.RequestSpecs;
import io.restassured.response.Response;

public class BookingService {

    public Response getBooking(int id) {
        return RequestSpecs.base()
                .get(Endpoint.BOOKING.path() + "/" + id);
    }

    public Response getBooking(String id) {
        return RequestSpecs.base()
                .get(Endpoint.BOOKING.path() + "/" + id);
    }

    public Response createBooking(Object body) {
        return RequestSpecs.json()
                .body(body)
                .post(Endpoint.BOOKING.path());
    }

    public Response updateBooking(int id, CreateBookingRequest body) {

        return RequestSpecs.auth()
                .body(body)
                .put(Endpoint.BOOKING.withId(id));
    }

    public Response deleteBooking(int id) {

        return RequestSpecs.auth()
                .delete(Endpoint.BOOKING.withId(id));
    }

    public Response deleteBookingNoAuth(int id) {

        return RequestSpecs.base()
                .delete(Endpoint.BOOKING.withId(id));
    }

    public Response deleteBookingWithToken(int id, String token) {
        return RequestSpecs.base()
                .cookie("token", token)
                .delete(Endpoint.BOOKING.withId(id));
    }

    public Response updateBookingNoAuth(int id, CreateBookingRequest body) {
        return RequestSpecs.base()
                .body(body)
                .put(Endpoint.BOOKING.withId(id));
    }
}
