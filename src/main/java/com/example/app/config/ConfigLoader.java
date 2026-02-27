package com.example.app.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

    private static final Properties PROPERTIES = new Properties();

    static {
        String profile = System.getProperty("profile", "local"); // local по умолчанию
        String path = "config/" + profile + ".properties";

        try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Config file not found: " + path);
            }
            PROPERTIES.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + path, e);
        }
    }

    private ConfigLoader() {}

    public static String get(String key) {
        // приоритет: -Dkey=... > properties
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }
}