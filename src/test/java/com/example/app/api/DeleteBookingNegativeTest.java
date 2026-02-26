package com.example.app.api;

import com.example.app.api.models.BookingData;
import com.example.app.api.steps.AuthSteps;
import com.example.app.api.steps.BookingSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class DeleteBookingNegativeTest extends BaseApiTest {

    private final BookingSteps steps = new BookingSteps();
    private final AuthSteps auth = new AuthSteps();

    @Test(groups = {"api", "regression"})
    public void deleteWithNoTokenShouldReturn403() {

        int id = steps.createBooking(BookingData.defaultBooking())
                .statusIs(200)
                .extractId();

        steps.deleteBookingNoAuth(id)
                .statusIs(403);

        steps.getBooking(id)
                .statusIs(200);
    }

    @Test(groups = {"api", "regression"})
    public void deleteInvalidIdShouldReturn405() {

        auth.login();

        steps.deleteBooking(99999)
                .statusIs(405);
    }

    @Test(groups = {"api", "regression"})
    public void deleteWithInvalidTokenShouldReturn403() {

        int id = steps.createBooking(BookingData.defaultBooking())
                .statusIs(200)
                .extractId();

        steps.deleteBookingInvalidToken(id)
                .statusIs(403);

        steps.getBooking(id)
                .statusIs(200);
    }
}
