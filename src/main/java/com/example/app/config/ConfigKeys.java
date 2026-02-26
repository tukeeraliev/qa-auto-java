package com.example.app.config;

public enum ConfigKeys {


    BASE_URL("base.url"),
    UI_URL("ui.base.url");

    private final String key;

    ConfigKeys(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
