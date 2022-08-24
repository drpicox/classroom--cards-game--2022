package com.drpicox.game.idea;

import com.drpicox.game.card.CardConstantsCollection;
import com.drpicox.game.constants.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaFactory {

    private final IdeaRepository ideaRepository;
    private final CardConstantsCollection cardConstantsCollection;

    public IdeaFactory(IdeaRepository ideaRepository, CardConstantsCollection cardConstantsCollection) {
        this.ideaRepository = ideaRepository;
        this.cardConstantsCollection = cardConstantsCollection;
    }

    public void makeIdea(IdeaFactorySettings ideaFactorySettings) {
        var ideaName = ideaFactorySettings.getIdeaName();
        if (ideaRepository.existsById(ideaName)) return;

        var ideaConstants = cardConstantsCollection.getByName(ideaName);
        var requirements = getCardRequirements(ideaConstants);
        var cardRewards = getCardRewards(ideaConstants);
        var level = ideaFactorySettings.getLevel();
        var xp = ideaFactorySettings.getXp();
        var idea = new Idea(ideaName, level, xp, requirements, cardRewards);
        ideaRepository.save(idea);
    }

    private List<IdeaTagRequirement> getCardRequirements(Constants ideaConstants) {
        var requirements = new ArrayList<IdeaTagRequirement>();

        var requirementsTable = ideaConstants.getCsvTable("idea.requirement.tags");
        for (var requirementRow: requirementsTable.getRows()) {
            var cardCount = requirementRow.getInt("cardCount");
            var tagValue = requirementRow.getInt("tagValue");
            var tagName = requirementRow.get("tagName");

            var requirement = new IdeaTagRequirement(cardCount, tagValue, tagName);
            requirements.add(requirement);
        }

        return requirements;
    }

    private List<String> getCardRewards(Constants ideaConstants) {

        var cardRewardsTable = ideaConstants.getCsvTable("idea.rewards.cards");
        var cardRewards = cardRewardsTable.getColumn("cardName");

        return cardRewards;
    }
}
