package com.drpicox.game.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Names implements Predicate<List<HasName>> {

    public static Predicate<HasName> byName(String cardName) {
        return (hasName) -> hasName.getName().equals(cardName);
    }

    public static Names byNames(String... cardNames) {
        return new Names().and(cardNames);
    }

    public static Names byNames(int count, String cardName) {
        return new Names().and(count, cardName);
    }

    private List<String> names = new ArrayList<>();

    private Names() {}

    public Names and(String ... and) {
        for (var cardName: and) names.add(cardName);
        return this;
    }

    public Names and(int count, String cardName) {
        for (var i = 0; i < count; i += 1) names.add(cardName);
        return this;
    }

    public String[] get() {
        return names.toArray(String[]::new);
    }

    @Override
    public boolean test(List<HasName> hasNames) {
        if (names.size() != hasNames.size()) return false;
        for (var i = 0; i < names.size(); i += 1) {
            String expectedName = names.get(i);
            String actualName = hasNames.get(i).getName();
            if (!expectedName.equals(actualName)) return false;
        }

        return true;
    }
}
