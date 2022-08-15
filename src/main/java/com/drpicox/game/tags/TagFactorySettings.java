package com.drpicox.game.tags;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.util.Settings;

public class TagFactorySettings extends Settings {
    public TagFactorySettings(String cardId) {
        super();
        set("cardId", cardId);
    }

    public TagFactorySettings withCardConstants(Constants cardConstants) {
        set("cardConstants", cardConstants);
        return this;
    }

    public TagFactorySettings withTagName(String tagName) {
        set("tagName", tagName);
        return this;
    }

    public String getCardId() {
        return get("cardId");
    }

    public String getTagName() {
        return get("tagName");
    }

    public Constants getCardConstants() {
        return get("cardConstants");
    }
}
