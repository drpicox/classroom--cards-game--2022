package com.drpicox.game.util;

import com.drpicox.game.cards.api.DerivedStackResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Names implements Predicate<HasNames>, Iterable<String>, HasNames {

    public static Predicate<? super HasName> byName(String cardName) {
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
    public Iterator<String> iterator() {
        return names.iterator();
    }

    @Override
    public int size() {
        return names.size();
    }

    @Override
    public String getName(int index) {
        return names.get(index);
    }

    @Override
    public boolean test(HasNames hasNames) {
        if (size() != hasNames.size()) return false;
        for (var i = 0; i < size(); i += 1) {
            String expectedName = getName(i);
            String actualName = hasNames.getName(i);
            if (!expectedName.equals(actualName)) return false;
        }

        return true;
    }
}
