package com.drpicox.game.idea.harvest;

import com.drpicox.game.card.*;
import com.drpicox.game.idea.IdeaEndMoonSettings;
import com.drpicox.game.idea.IdeaEndMoonStepExecutor;
import com.drpicox.game.idea.IdeaService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Service;

@Service
public class EndMoonStep_900_HarvestIdea implements EndMoonStep {

    public static final String IDEA_NAME = "Harvest Idea";
    private final StackService stackService;
    private final CardFactory cardFactory;
    private final IdeaService ideaService;
    private final IdeaEndMoonStepExecutor ideaEndMoonStepExecutor;

    public EndMoonStep_900_HarvestIdea(StackService stackService, CardFactory cardFactory, IdeaService ideaService, IdeaEndMoonStepExecutor ideaEndMoonStepExecutor) {
        this.stackService = stackService;
        this.cardFactory = cardFactory;
        this.ideaService = ideaService;
        this.ideaEndMoonStepExecutor = ideaEndMoonStepExecutor;
    }

    public void execute(EndMoonSettings settings) {
        ideaEndMoonStepExecutor.execute(settings, "Harvest Idea", this::executeIdea);
    }

    private void executeIdea(IdeaEndMoonSettings settings) {
        var summary = settings.getSummary();
        var position = settings.getPosition();

        var plant = summary.getCardByDescriptionTermAndTagName("Fruit", "Fruit Plant");
        var fruit = plant.getDescriptionTerm("Fruit");
        cardFactory.makeCards(2, new CardFactorySettings(fruit).withPosition(position));
    }
}
