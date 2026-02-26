package com.example.app.api;

import com.example.app.api.models.BookingBuilder;
import com.example.app.api.steps.AuthSteps;
import com.example.app.api.steps.BookingSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class BookingTest extends BaseApiTest {

    private final BookingSteps steps = new BookingSteps();
    private final AuthSteps auth = new AuthSteps();

    @Test(groups = {"api","smoke"})
    public void createBookingShouldReturn200AndIdPresent() {

        steps.createBooking(BookingBuilder.defaultBooking().build())
                .statusIs(200)
                .bookingIdIsPresent();
    }

    @Test(groups = {"api","smoke"})
    public void fullBookingLifecycle() {

        auth.login("admin", "password123");

        int id = steps.createBooking(BookingBuilder.defaultBooking().build())
                .statusIs(200)
                .extractId();

        steps.getBooking(id)
                .statusIs(200)
                .firstnameIs("John");

        var update = BookingBuilder.defaultBooking()
                .firstname("Updated")
                .build();

        steps.updateBooking(id, update)
                .statusIs(200)
                .firstnameIs("Updated");

        steps.deleteBooking(id)
                .deletedSuccessfully();
    }

    @Test(groups = {"api", "smoke"})
    public void createBookingWithEmptyFieldsShouldReturn200() {

        steps.createBooking(BookingBuilder.bookingWithNothing().build())
                .statusIs(200);
    }
}
