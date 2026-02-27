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
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys;

        String envKey = key.toUpperCase().replace('.', '_'); // ui.base.url -> UI_BASE_URL
        String env = System.getenv(envKey);
        if (env != null && !env.isBlank()) return env;

        return PROPERTIES.getProperty(key);
    }
}