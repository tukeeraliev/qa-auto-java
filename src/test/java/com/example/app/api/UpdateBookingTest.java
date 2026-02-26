package com.example.app.api;

import com.example.app.api.models.BookingData;
import com.example.app.api.steps.AuthSteps;
import com.example.app.api.steps.BookingSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class UpdateBookingTest extends BaseApiTest {

    private final AuthSteps auth = new AuthSteps();
    private final BookingSteps steps = new BookingSteps();

    @Test(groups = {"api","smoke"})
    public void shouldUpdateBooking() {

        // login
        auth.login("admin", "password123");

        // create booking
        int id = steps.createBooking(BookingData.defaultBooking())
                .statusIs(200)
                .extractId();   // сейчас добавим

        // update data
        var update = BookingData.defaultBooking();
        update.firstname = "Updated";

        steps.updateBooking(id, update)
                .statusIs(200)
                .firstnameIs("Updated");
    }
}
