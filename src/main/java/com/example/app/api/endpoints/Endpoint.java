package com.example.app.api.endpoints;

public enum Endpoint {

    PING("/ping"),
    AUTH("/auth"),
    BOOKING("/booking");

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }

    public String withId(Object id) {
        return path + "/" + id;
    }
}
