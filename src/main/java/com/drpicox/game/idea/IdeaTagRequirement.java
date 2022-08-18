package com.drpicox.game.idea;

import com.drpicox.game.util.HasName;

import java.io.Serializable;

public class IdeaTagRequirement implements HasName, Serializable {
    public IdeaTagRequirement(int value, String tagName) {
        this.value = value;
        this.tagName = tagName;
    }

    private int value;
    private String tagName;

    public int getValue() {
        return value;
    }

    public String getTagName() {
        return tagName;
    }
}
