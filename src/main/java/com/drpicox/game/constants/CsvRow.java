package com.drpicox.game.constants;

import java.util.Map;

public class CsvRow {
    public CsvRow(String[] fields, Map<String, Integer> headings) {
        this.fields = fields;
        this.headings = headings;
    }

    private String[] fields;
    private Map<String, Integer> headings;

    public String[] getFields() {
        return fields;
    }

    public String get(int index) {
        return fields[index];
    }
    public String get(String name) {
        return get(headings.get(name));
    }

    public int getInt(int index) {
        return Integer.parseInt(fields[index]);
    }
    public int getInt(String name) {
        return getInt(headings.get(name));
    }



}
