package com.example.app.api;

import com.example.app.api.models.BookingData;
import com.example.app.api.steps.AuthSteps;
import com.example.app.api.steps.BookingSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseApiTest {

    private final AuthSteps auth = new AuthSteps();
    private final BookingSteps steps = new BookingSteps();

    @Test(groups = {"api","smoke"})
    public void shouldDeleteBooking() {

        auth.login("admin", "password123");

        int id = steps.createBooking(BookingData.defaultBooking())
                .statusIs(200)
                .extractId();

        steps.deleteBooking(id)
                .deletedSuccessfully();

        steps.getBooking(id)
                .notFound();
    }
}
