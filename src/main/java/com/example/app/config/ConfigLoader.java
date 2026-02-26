package com.example.app.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config/local.properties")) {

            if (is == null) {
                throw new RuntimeException("Config file not found");
        }

            PROPERTIES.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    private ConfigLoader() {}

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
