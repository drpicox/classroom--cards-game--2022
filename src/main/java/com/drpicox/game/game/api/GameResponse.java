package com.drpicox.game.game.api;

import com.drpicox.game.cards.Card;

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
}
