package com.drpicox.game.constants;

import java.util.List;

public class CsvTable {

    public static CsvTable EMPTY = new CsvTable(List.of());

    public CsvTable(List<CsvRow> rows) {
        this.rows = rows;
    }

    private List<CsvRow> rows;

    public List<CsvRow> getRows() {
        return rows;
    }

    public List<String> getColumn(String name) {
        return rows.stream().map(row -> row.get(name)).toList();
    }
}
