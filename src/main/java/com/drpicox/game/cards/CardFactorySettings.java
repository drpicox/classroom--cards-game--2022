package com.drpicox.game.cards;

import com.drpicox.game.util.Settings;

public class CardFactorySettings extends Settings {
    public CardFactorySettings(String cardName) {
        super();
        set("cardName", cardName);
    }

    public String getCardName() {
        return get("cardName");
    }
}
