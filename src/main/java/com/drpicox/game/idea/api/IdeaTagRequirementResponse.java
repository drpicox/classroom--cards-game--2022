package com.drpicox.game.idea.api;

import com.drpicox.game.idea.IdeaTagRequirement;
import com.drpicox.game.util.HasName;

public class IdeaTagRequirementResponse implements HasName {
    private int cardCount;
    private int tagValue;
    private String tagName;

    public IdeaTagRequirementResponse(IdeaTagRequirement requirement) {
        this.cardCount = requirement.getCardCount();
        this.tagValue = requirement.getTagValue();
        this.tagName = requirement.getTagName();
    }

    public int getTagValue() {
        return tagValue;
    }

    public String getTagName() {
        return tagName;
    }

    public int getCardCount() {
        return cardCount;
    }
}
