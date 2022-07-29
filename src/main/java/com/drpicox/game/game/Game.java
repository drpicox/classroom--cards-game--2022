package com.drpicox.game.game;

import com.drpicox.game.cards.Card;

import javax.persistence.Entity;
import java.util.List;
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
