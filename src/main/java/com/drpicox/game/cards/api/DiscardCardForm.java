package com.drpicox.game.cards.api;

public class DiscardCardForm {
    private final String cardId;

    public DiscardCardForm(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
