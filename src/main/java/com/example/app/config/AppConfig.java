package com.example.app.config;

public interface AppConfig {

    static String baseUrl() {
        return ConfigLoader.get(ConfigKeys.BASE_URL.key());
    }

    static String uiBaseUrl() {
        return ConfigLoader.get(ConfigKeys.UI_URL.key());
    }
}
