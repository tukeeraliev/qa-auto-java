package com.example.app.db;

import com.example.app.core.db.DbClient;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class DbSmokeTest {

    Faker faker = new Faker();

    @Test
    public void shouldConnectAndCountBookings() throws Exception {
        int count = DbClient.countBookings();
        System.out.println("Bookings in DB: " + count);
        assertTrue(count >= 0);
    }

    @Test
    public void shouldInsertAndCountBooking() throws Exception {

        DbClient.initSchema();
        DbClient.clearBookings();

        int before = DbClient.countBookings();
        Assert.assertEquals(before, 0);

        int id = DbClient.insertBooking(faker.name().firstName(), faker.name().lastName(), 100);
        Assert.assertTrue(id > 0);

        int after = DbClient.countBookings();
        Assert.assertEquals(after, 1);
    }

    @Test
    public void shouldInsertAndFindByApiId() throws Exception {
        DbClient.initSchema();
        DbClient.clearBookings();

        int apiId = 10001; // любой уникальный int
        String fn = faker.name().firstName();
        String ln = faker.name().lastName();

        int dbId = DbClient.insertBookingWithApiId(apiId, fn, ln, 100);
        Assert.assertTrue(dbId > 0);

        Assert.assertTrue(DbClient.existsByApiId(apiId), "Row with api_id=" + apiId + " must exist");
    }
}