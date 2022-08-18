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
        var ideaConstants = cardConstantsCollection.getByName(ideaName);
        var requirements = getCardRequirements(ideaConstants);
        var idea = new Idea(ideaName, requirements);
        ideaRepository.save(idea);
    }

    private List<IdeaTagRequirement> getCardRequirements(Constants ideaConstants) {
        var requirements = new ArrayList<IdeaTagRequirement>();

        var requirementsTable = ideaConstants.getCsvTable("idea.requirement.tags");
        for (var requirementRow: requirementsTable.getRows()) {
            var tagName = requirementRow.get("tagName");
            var tagValue = requirementRow.getInt("tagValue");

            var requirement = new IdeaTagRequirement(tagValue, tagName);
            requirements.add(requirement);
        }

        return requirements;
    }
}
