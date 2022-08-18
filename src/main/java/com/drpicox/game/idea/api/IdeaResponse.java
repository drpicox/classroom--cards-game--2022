package com.drpicox.game.idea.api;

import com.drpicox.game.idea.Idea;
import com.drpicox.game.util.HasName;

import java.util.List;
import java.util.Optional;

import static com.drpicox.game.util.Names.byName;

public class IdeaResponse implements HasName {

    private String name;
    private List<IdeaTagRequirementResponse> tagRequirements;

    public IdeaResponse(Idea idea) {
        this.name = idea.getTagName();
        this.tagRequirements = idea.getTagRequirements().stream().map(IdeaTagRequirementResponse::new).toList();
    }

    public String getTagName() {
        return name;
    }

    public Optional<IdeaTagRequirementResponse> findTagRequirement(String cardName) {
        var requirement = tagRequirements.stream().filter(byName(cardName)).findAny();
        return requirement;
    }
}
