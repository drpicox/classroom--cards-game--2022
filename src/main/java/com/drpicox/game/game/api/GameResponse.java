package com.drpicox.game.game.api;

import java.util.List;
import java.util.stream.Stream;

public class GameResponse {
    private List<CardResponse> cards;

    public GameResponse(List<CardResponse> cards) {
        this.cards = cards;
    }

    public Stream<CardResponse> streamCards() {
        return cards.stream();
    }

    public Stream<CardResponse> streamCardsByName(String cardName) {
        return streamCards().filter(c -> c.getName().equals(cardName));
    }
}
