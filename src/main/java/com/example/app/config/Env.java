package com.example.app.config;

import java.io.InputStream;
import java.util.Properties;

public class Env {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = Env.class
                .getClassLoader()
                .getResourceAsStream("config/local.properties")) {

            props.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load local.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}