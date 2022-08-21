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
    public Idea(String name, int level, int xp, List<IdeaTagRequirement> requirements) {
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.requirements = requirements;
    }

    @Id private String name;
    private int level;
    private int xp;
    @ElementCollection private List<IdeaTagRequirement> requirements;

    @Override
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public List<IdeaTagRequirement> getTagRequirements() {
        return requirements;
    }

    public int countRequiredCards() {
        return requirements.stream().mapToInt(r -> r.getCardCount()).sum();
    }

    public void increaseXp() {
        this.xp += 1;
        if (xp >= level * 10) {
            level += 1;
            xp = 0;
        }
    }

    private Idea() {} // JPA required constructor
}
