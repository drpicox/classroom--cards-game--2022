package com.drpicox.game.tags.api;

import com.drpicox.game.tags.Tag;

public class TagDTO {
    private final int value;

    public TagDTO(Tag tag) {
        this.value = tag.getValue();
    }

    public int getValue() {
        return value;
    }
}
