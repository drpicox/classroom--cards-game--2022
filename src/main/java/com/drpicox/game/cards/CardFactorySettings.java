package com.drpicox.game.cards;

import com.drpicox.game.util.Settings;

public class CardFactorySettings extends Settings {
    public CardFactorySettings() {}

    public CardFactorySettings(String cardName) {
        set("cardName", cardName);
    }

    public CardFactorySettings withCardName(String cardName) {
        set("cardName", cardName);
        return this;
    }

    public CardFactorySettings withPosition(int position) {
        set("position", position);
        return this;
    }

    public String getCardName() {
        return get("cardName");
    }

    public int getPosition() {
        return getOrDefault("position", 0);
    }

    public boolean hasPosition() {
        return has("position");
    }
}
