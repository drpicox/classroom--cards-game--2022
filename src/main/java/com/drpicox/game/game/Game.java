package com.drpicox.game.game;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Game {
    private List<Card> cards;

    public Game(List<Card> cards) {
        this.cards = cards;
    }

    public Stream<Card> streamCards() {
        return cards.stream();
    }
}
