package com.drpicox.game.constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Constants {
    private final Map<String, String> properties;

    public Constants(Map<String, String> properties) {
        this.properties = properties;
    }

    public static Constants load(File file) throws IOException {
        var properties = new LinkedHashMap<String, String>();

        var lines = Files.readAllLines(file.toPath());
        for (var line: lines) {
            var index = line.indexOf('=');
            if (index < 0) continue;

            var key = line.substring(0, index).trim();
            var value = line.substring(index + 1).trim();
            properties.put(key, value);
        }

        return new Constants(properties);
    }

    public Stream<String> streamKeysStartWith(String prefix) {
        return properties.keySet().stream().filter(k -> k.startsWith(prefix));
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.get(key));
    }

    public String getString(String key) {
        return properties.get(key);
    }

    public boolean containsKey(String value) {
        return properties.containsKey(value);
    }

    public Collection<String> keySet() {
        return properties.keySet();
    }

    public String[] getCsv(String key) {
        var fields = getString(key).split(",");
        for (var i = 0; i < fields.length; i+=1)
            fields[i] = fields[i].trim();

        return fields;
    }
}
