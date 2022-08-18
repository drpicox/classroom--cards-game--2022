package com.drpicox.game.idea;

import com.drpicox.game.cards.Card;
import com.drpicox.game.cards.Stack;
import com.drpicox.game.util.HasName;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Idea implements HasName {
    @Id private String name;
    @ElementCollection private List<IdeaTagRequirement> requirements;

    public Idea(String name, List<IdeaTagRequirement> requirements) {
        this.name = name;
        this.requirements = requirements;
    }

    @Override
    public String getTagName() {
        return name;
    }

    public List<IdeaTagRequirement> getTagRequirements() {
        return requirements;
    }

    private Idea() {} // JPA required constructor

    public Map<String, Card> getRequiredCards(Stack stack) {
        var result = new HashMap<>();
        var requiredCardCount = requirements.stream().mapToInt(r -> r.getValue()).sum();
        var range = stack.subCardList(1, 1 + requiredCardCount);

        return null;
    }
}
