package com.example.app.api.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

public class CreateBookingResponse {

    @JsonProperty("bookingid")
    public int bookingid;
    public CreateBookingRequest booking;
}
