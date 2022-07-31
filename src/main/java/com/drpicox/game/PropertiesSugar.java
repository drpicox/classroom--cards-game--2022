package com.drpicox.game;

import java.util.Properties;
import java.util.stream.Stream;

public class PropertiesSugar {
    private final Properties properties;

    public PropertiesSugar(Properties properties) {
        this.properties = properties;
    }

    public Stream<String> streamKeysStartWith(String prefix) {
        return properties.stringPropertyNames().stream().filter(k -> k.startsWith(prefix));
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }
}
