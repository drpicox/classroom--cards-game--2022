package com.drpicox.game.idea.api;

import com.drpicox.game.idea.IdeaTagRequirement;
import com.drpicox.game.util.HasName;

public class IdeaTagRequirementResponse implements HasName {
    private int value;
    private String tagName;

    public IdeaTagRequirementResponse(IdeaTagRequirement requirement) {
        this.value = requirement.getValue();
        this.tagName = requirement.getTagName();
    }

    public int getValue() {
        return value;
    }

    public String getTagName() {
        return tagName;
    }
}
