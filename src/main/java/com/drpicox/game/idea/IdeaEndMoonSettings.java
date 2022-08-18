package com.drpicox.game.idea;

import com.drpicox.game.card.CardListSummary;
import com.drpicox.game.card.Stack;
import com.drpicox.game.moon.EndMoonSettings;

public class IdeaEndMoonSettings extends EndMoonSettings {
    public IdeaEndMoonSettings(EndMoonSettings settings, Idea idea) {
        this.extend(settings);
        set("idea", idea);
    }

    public IdeaEndMoonSettings withStack(Stack stack) {
        set("stack", stack);
        set("summary", stack.getSummary());
        return this;
    }

    public Idea getIdea() {
        return get("idea");
    }

    public Stack getStack() {
        return get("stack");
    }

    public CardListSummary getSummary() {
        return get("summary");
    }
}
