package com.drpicox.game.game;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Game {
    private List<Card> cards = new LinkedList<>();

    public Game() {
        cards.add(new Card("villager"));
        cards.add(new Card("bush"));
        cards.add(new Card("berry"));
    }

    public Stream<Card> streamCards() {
        return cards.stream();
    }
}
