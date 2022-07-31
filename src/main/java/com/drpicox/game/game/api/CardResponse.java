package com.drpicox.game.game.api;

import com.drpicox.game.cards.Card;

public class CardResponse {

    private String id;
    private String name;

    public CardResponse(Card card) {
        this.id = card.getId();
        this.name = card.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTag(String tag) {
        return 1;
    }
}
