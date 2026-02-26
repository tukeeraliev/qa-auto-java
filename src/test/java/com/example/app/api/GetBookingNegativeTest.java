package com.example.app.api;

import com.example.app.api.models.BookingData;
import com.example.app.api.steps.BookingSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class GetBookingNegativeTest extends BaseApiTest {

    BookingSteps steps = new BookingSteps();

    @Test(groups = {"api","regression"})
    public void getBookingWithInvalidIdShouldReturn404() {

        var data = BookingData.defaultBooking();

        steps.createBooking(data)
                .statusIs(200);

        steps.getBooking(99999)
                .statusIs(404);
    }

    @Test(groups = {"api","regression"})
    public void getBookingWithStringIdShouldReturn404() {

        var data = BookingData.defaultBooking();

        steps.createBooking(data)
                .statusIs(200);

        String id = "abc";

        steps.getBookingStr(id)
                .statusIs(404);
    }
}
