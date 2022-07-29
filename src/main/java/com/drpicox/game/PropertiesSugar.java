package com.drpicox.game;

import java.util.Properties;
import java.util.stream.Stream;

public class PropertiesSugar {
    private final Properties gameProperties;

    public PropertiesSugar(Properties gameProperties) {
        this.gameProperties = gameProperties;
    }

    public Stream<String> streamKeysStartWith(String prefix) {
        return gameProperties.stringPropertyNames().stream().filter(k -> k.startsWith(prefix));
    }

    public int getInt(String initialCardKey) {
        return Integer.parseInt(gameProperties.getProperty(initialCardKey));
    }
}
