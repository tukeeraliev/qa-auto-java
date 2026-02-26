package com.example.app.ui.saucedemo.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public final class FakerUtils {

    private static final Faker FAKER = new Faker(new Locale("en"));

    private FakerUtils() {}

    public static String username() {
        return FAKER.name().username();
    }

    public static String firstName() {
        return FAKER.name().firstName();
    }

    public static String lastName() {
        return FAKER.name().lastName();
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String password() {
        return FAKER.internet().password(8, 16);
    }

    public static String randomText() {
        return FAKER.lorem().sentence();
    }
}
