package com.drpicox.game.idea;

import com.drpicox.game.card.*;
import com.drpicox.game.moon.EndMoonSettings;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class IdeaEndMoonStepExecutor {

    private final StackService stackService;
    private final IdeaService ideaService;

    public IdeaEndMoonStepExecutor(StackService stackService, IdeaService ideaService) {
        this.stackService = stackService;
        this.ideaService = ideaService;
    }

    public void execute(EndMoonSettings moonSettings, String ideaName, Consumer<IdeaEndMoonSettings> consumer) {
        var idea = ideaService.findByName(ideaName).get();
        var ideaSettings = new IdeaEndMoonSettings(moonSettings, idea);

        var stacks = stackService.findAllStackByBottomCardName(ideaName);
        for (var stack : stacks) {
            ideaSettings.withStack(stack);
            if (validateRequirements(ideaSettings)) consumer.accept(ideaSettings);
        }
    }

    private boolean validateRequirements(IdeaEndMoonSettings settings) {
        var idea = settings.getIdea();
        var summary = settings.getSummary();

        var cardRequirements = idea.getTagRequirements();

        for (var cardRequirement : cardRequirements) {
            var tagValue = cardRequirement.getTagName();
            var value = cardRequirement.getValue();

            var sumValue = summary.sumTagValue(tagValue);
            if (sumValue < value) return false;
        }

        return true;
    }
}
