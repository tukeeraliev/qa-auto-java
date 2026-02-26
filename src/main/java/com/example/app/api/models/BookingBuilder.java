package com.example.app.api.models;

public class BookingBuilder {

    private final CreateBookingRequest request = new CreateBookingRequest();

    private BookingBuilder() {}

    // ⭐ entry point
    public static BookingBuilder booking() {
        return new BookingBuilder();
    }

    // ⭐ готовый дефолт
    public static BookingBuilder defaultBooking() {
        return booking()
                .firstname("John")
                .lastname("Smith")
                .totalPrice(123)
                .depositPaid(true)
                .checkin("2025-01-01")
                .checkout("2025-01-05")
                .additionalNeeds("Breakfast");
    }

    public static BookingBuilder bookingWithNothing() {
        return booking()
                .firstname("")
                .lastname("")
                .totalPrice(-100)
                .depositPaid(false)
                .checkin("2025-01-01")
                .checkout("2025-01-05")
                .additionalNeeds("");
    }

    public BookingBuilder firstname(String value) {
        request.firstname = value;
        return this;
    }

    public BookingBuilder lastname(String value) {
        request.lastname = value;
        return this;
    }

    public BookingBuilder totalPrice(int value) {
        request.totalprice = value;
        return this;
    }

    public BookingBuilder depositPaid(boolean value) {
        request.depositpaid = value;
        return this;
    }

    public BookingBuilder checkin(String value) {
        ensureDates();
        request.bookingdates.checkin = value;
        return this;
    }

    public BookingBuilder checkout(String value) {
        ensureDates();
        request.bookingdates.checkout = value;
        return this;
    }

    public BookingBuilder additionalNeeds(String value) {
        request.additionalneeds = value;
        return this;
    }

    public CreateBookingRequest build() {
        return request;
    }

    private void ensureDates() {
        if (request.bookingdates == null) {
            request.bookingdates = new CreateBookingRequest.BookingDates();
        }
    }
}
