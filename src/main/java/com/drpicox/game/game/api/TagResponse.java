package com.drpicox.game.game.api;

import com.drpicox.game.cards.Tag;

public class TagResponse {
    private final int value;

    public TagResponse(Tag tag) {
        this.value = tag.getValue();
    }

    public int getValue() {
        return value;
    }
}
