package com.drpicox.game.util;

import java.util.*;

public class RandomList<T> {

    private final List<Item<T>> items = new ArrayList<>();
    private final Map<T, Item<T>> itemsByElement = new HashMap<>();
    private int totalWeight = 0;

    public static RandomList<String> from(DataTable table, String column) {
        var list = new RandomList<String>();
        for (DataTableRow row : table.getRows()) {
            list.add(row.getInt("weight"), row.get(column));
        }
        return list;
    }

    public static <T> RandomList<T> from (T... elements) {
        var list = new RandomList<T>();
        for (T element : elements) {
            list.add(1, element);
        }
        return list;
    }

    public RandomList() {}

    public RandomList(Collection<T> elements) {
        for (T element : elements) add(1, element);
    }

    public RandomList<T> add(int weight, T element) {
        totalWeight += weight;
        if (!itemsByElement.containsKey(element)) {
            var item = new Item<>(element);
            itemsByElement.put(element, item);
            this.items.add(item);
        }

        var item = itemsByElement.get(element);
        item.weight += weight;

        return this;
    }

    public T get(double value) {
        var choosen = (int) Math.floor(value * totalWeight);
        var index = 0;
        var current = 0;
        while (current < choosen) {
            index += 1;
            current += items.get(index).weight;
        }
        return items.get(index).element;
    }

    private static class Item<T> {
        public int weight = 0;
        public T element;

        public Item(T element) {
            this.element = element;
        }
    }
}
