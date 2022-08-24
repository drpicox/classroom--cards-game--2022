package com.drpicox.game.idea.api;

import com.drpicox.game.idea.Idea;
import com.drpicox.game.util.HasName;

import java.util.List;
import java.util.Optional;

import static com.drpicox.game.util.Names.byName;

public class IdeaResponse implements HasName {

    public IdeaResponse(Idea idea) {
        this.name = idea.getName();
        this.level = idea.getLevel();
        this.xp = idea.getXp();
        this.cardRewards = idea.getCardRewards();
        this.tagRequirements = idea.getTagRequirements().stream().map(IdeaTagRequirementResponse::new).toList();
    }

    private String name;
    private List<IdeaTagRequirementResponse> tagRequirements;
    private int level;
    private int xp;
    private List<String> cardRewards;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public List<String> getCardRewards() {
        return cardRewards;
    }

    public Optional<IdeaTagRequirementResponse> findTagRequirement(String cardName) {
        var requirement = tagRequirements.stream().filter(byName(cardName)).findAny();
        return requirement;
    }

}
