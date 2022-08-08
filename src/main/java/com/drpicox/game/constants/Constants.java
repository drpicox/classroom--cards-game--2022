package com.drpicox.game.constants;

import java.util.Collection;
import java.util.Properties;
import java.util.stream.Stream;

public class Constants {
    private final Properties properties;

    public Constants(Properties properties) {
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

    public boolean containsKey(String value) {
        return properties.containsKey(value);
    }

    public Collection<String> keySet() {
        return properties.stringPropertyNames();
    }
}
