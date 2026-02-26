package com.example.app.api;

import com.example.app.api.models.BookingBuilder;
import com.example.app.api.models.BookingData;
import com.example.app.api.steps.AuthSteps;
import com.example.app.api.steps.BookingSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class UpdateBookingNegativeTest extends BaseApiTest {

    private final BookingSteps steps = new BookingSteps();
    private final AuthSteps auth = new AuthSteps();

    @Test(groups = {"api", "regression"})
    public void updateWithoutTokenShouldReturn403() {

        int id = steps.createBooking(BookingData.defaultBooking())
                .statusIs(200)
                .extractId();

        var update = BookingBuilder.defaultBooking()
                .firstname("Updated")
                .build();

        steps.updateBookingNoAuth(id, update)
                .statusIs(403);

        steps.getBooking(id)
                .statusIs(200)
                .firstnameIs("John");
    }
}
