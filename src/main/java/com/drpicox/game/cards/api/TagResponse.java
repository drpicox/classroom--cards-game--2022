package com.drpicox.game.cards.api;

import com.drpicox.game.tags.Tag;

public class TagResponse {
    private final int value;

    public TagResponse(Tag tag) {
        this.value = tag.getValue();
    }

    public int getValue() {
        return value;
    }
}
