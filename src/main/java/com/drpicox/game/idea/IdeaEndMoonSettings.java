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
        var count = getIdea().countRequiredCards();

        set("stack", stack);
        set("summary", getStack().getSummary(1, count + 1));
        set("position", getStack().getPosition());
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

    public int getPosition() {
        return get("position");
    }
}
