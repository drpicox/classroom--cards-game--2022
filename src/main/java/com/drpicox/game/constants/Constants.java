package com.drpicox.game.constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
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

    public List<String> findAllKeysStartingBy(String prefix) {
        return properties.keySet().stream().filter(k -> k.startsWith(prefix)).toList();
    }

    private List<String> findAllNumberedKeys(String key) {
        return properties.keySet().stream()
            .filter(k -> k.startsWith(key + "."))
            .filter(k -> {
                var tail = k.substring(key.length() + 1);
                return tail.matches("\\s*\\d+");
            })
            .toList();
    }

    public CsvRow getCsv(String key) {
        return getCsv(key, null);
    }

    public CsvRow getCsv(String key, Map<String, Integer> headings) {
        var rowValue = getString(key);
        if (rowValue == null) return null;

        var fields = rowValue.split(",");
        for (var i = 0; i < fields.length; i+=1)
            fields[i] = fields[i].trim();

        return new CsvRow(fields, headings);
    }

    public CsvTable getCsvTable(String key) {
        var headings = getCsvTableHeading(key);
        var rows = getCsvTableRows(key, headings);

        if (rows == null) return CsvTable.EMPTY;
        return new CsvTable(rows);
    }

    private Map<String, Integer> getCsvTableHeading(String key) {
        var headings = new HashMap<String, Integer>();
        var csvRow = getCsv(key);
        if (csvRow != null) {
            var headingFields = csvRow.getFields();
            for (var i = 0; i < headingFields.length; i += 1) {
                headings.put(headingFields[i], i);
            }
        }
        return headings;
    }

    private List<CsvRow> getCsvTableRows(String key, Map<String, Integer> headings) {
        var rowKeys = findAllNumberedKeys(key);
        var rows = new ArrayList<CsvRow>(rowKeys.size());
        for (var rowKey : rowKeys) {
            var row = getCsv(rowKey, headings);
            rows.add(row);
        }

        if (rows.size() == 0) return null;

        return rows;
    }
}
