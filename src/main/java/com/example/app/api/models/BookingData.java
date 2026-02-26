package com.example.app.api.models;

public class BookingData {

    public static CreateBookingRequest defaultBooking() {

        CreateBookingRequest req = new CreateBookingRequest();

        req.firstname = "John";
        req.lastname = "Smith";
        req.totalprice = 123;
        req.depositpaid = true;

        CreateBookingRequest.BookingDates dates = new CreateBookingRequest.BookingDates();
        dates.checkin = "2025-01-01";
        dates.checkout = "2025-01-05";

        req.bookingdates = dates;
        req.additionalneeds = "Breakfast";

        return req;
    }
}
