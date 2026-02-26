package com.example.app.ui.saucedemo.tests;

import com.example.app.base.BaseUiTest;
import com.example.app.ui.saucedemo.data.SortOption;
import com.example.app.ui.saucedemo.steps.SauceSteps;
import org.testng.annotations.Test;

public class InventorySortingTest extends BaseUiTest {

    @Test(groups = {"regression", "ui"})
    public void shouldSortItemsByNameAscending() {
        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .inventoryTitleIs("Products")
                .sortDropdownIsVisible()
                .selectSort(SortOption.NAME_AZ)
                .itemsSortedByNameAsc();
    }

    @Test(groups = {"regression", "ui"})
    public void shouldSortItemsByNameDescending() {
        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .inventoryTitleIs("Products")
                .sortDropdownIsVisible()
                .selectSort(SortOption.NAME_ZA)
                .itemsSortedByNameDesc();
    }

    @Test(groups = {"regression", "ui"})
    public void shouldSortItemsByPriceAscending() {
        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .inventoryTitleIs("Products")
                .sortDropdownIsVisible()
                .selectSort(SortOption.PRICE_LOW_HIGH)
                .itemsSortedByPriceAsc();
    }

    @Test(groups = {"regression", "ui"})
    public void shouldSortItemsByPriceDescending() {
        new SauceSteps(driver)
                .openLogin()
                .loginAsStandardUser()
                .inventoryTitleIs("Products")
                .sortDropdownIsVisible()
                .selectSort(SortOption.PRICE_HIGH_LOW)
                .itemsSortedByPriceDesc();
    }
}
