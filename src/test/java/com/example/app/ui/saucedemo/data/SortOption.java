package com.example.app.ui.saucedemo.data;

public enum SortOption {
    NAME_AZ("az"),
    NAME_ZA("za"),
    PRICE_LOW_HIGH("lohi"),
    PRICE_HIGH_LOW("hilo");

    private final String value;

    SortOption(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
