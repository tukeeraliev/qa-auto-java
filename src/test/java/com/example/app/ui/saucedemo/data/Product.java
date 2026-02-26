package com.example.app.ui.saucedemo.data;

public enum Product {

    BACKPACK(
            "Sauce Labs Backpack",
            "add-to-cart-sauce-labs-backpack"
    ),

    BIKE_LIGHT(
            "Sauce Labs Bike Light",
            "add-to-cart-sauce-labs-bike-light"
    ),

    BOLT_TSHIRT(
            "Sauce Labs Bolt T-Shirt",
            "add-to-cart-sauce-labs-bolt-t-shirt"
    ),

    FLEECE_JACKET(
            "Sauce Labs Fleece Jacket",
            "add-to-cart-sauce-labs-fleece-jacket"
    ),

    ONESIE(
            "Sauce Labs Onesie",
            "add-to-cart-sauce-labs-onesie"
    ),

    RED_TSHIRT(
            "Test.allTheThings() T-Shirt (Red)",
            "add-to-cart-test.allthethings()-t-shirt-(red)"
    );

    private final String name;
    private final String addBtnId;

    Product(String name, String addBtnId) {
        this.name = name;
        this.addBtnId = addBtnId;
    }

    public String getName() {
        return name;
    }

    public String getAddBtnId() {
        return addBtnId;
    }
}
