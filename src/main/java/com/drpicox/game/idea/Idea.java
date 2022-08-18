package com.drpicox.game.idea;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.Stack;
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
}
