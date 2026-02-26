package com.example.app.core.reporting;

import io.qameta.allure.Step;

public final class AllureSteps {

    private AllureSteps() {}

    @Step("{stepName}")
    public static void step(String stepName) {
        // пусто, нужен только Step в Allure
    }
}
