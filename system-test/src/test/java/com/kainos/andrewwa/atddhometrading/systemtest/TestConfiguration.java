package com.kainos.andrewwa.atddhometrading.systemtest;

import org.yaml.snakeyaml.Yaml;

import java.util.*;

public class TestConfiguration {

    private static final Map<String, Object> config;

    static {
        var yaml = new Yaml();
        var inputStream = TestConfiguration.class.getClassLoader().getResourceAsStream("application.yml");
        config = yaml.load(inputStream);
    }

    public static String getBaseUrl() {
        return getNestedValue("test", "eshop", "baseUrl");
    }

    @SuppressWarnings("unchecked")
    private static <T> T getNestedValue(String... keys) {
        var current = config;
        for (int i = 0; i < keys.length - 1; i++) {
            current = (Map<String, Object>) current.get(keys[i]);
        }
        return (T) current.get(keys[keys.length - 1]);
    }

    public static int getWaitSeconds() {
        return getNestedValue("test", "wait", "seconds");
    }
}
