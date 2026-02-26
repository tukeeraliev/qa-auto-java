package com.example.app.api;

import com.example.app.api.models.BookingData;
import com.example.app.api.steps.BookingSteps;
import com.example.app.base.BaseApiTest;
import org.testng.annotations.Test;

public class GetBookingTest extends BaseApiTest {

    private final BookingSteps steps = new BookingSteps();

    @Test(groups = {"api","smoke"})
    public void shouldReturnFullBookingObject() {

        var data = BookingData.defaultBooking();

        int id = steps.createBooking(data)
                .statusIs(200)
                .extractId();

        steps.getBooking(id)
                .statusIs(200)
                .firstnameGetIs(data.firstname)
                .lastnameIs(data.lastname)
                .priceIs(data.totalprice);
    }
}
