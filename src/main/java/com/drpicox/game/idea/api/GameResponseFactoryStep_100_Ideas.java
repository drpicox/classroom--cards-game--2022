package com.drpicox.game.idea.api;

import com.drpicox.game.game.api.GameResponseFactorySettings;
import com.drpicox.game.game.api.GameResponseFactoryStep;
import com.drpicox.game.idea.IdeaService;
import org.springframework.stereotype.Component;

@Component
public class GameResponseFactoryStep_100_Ideas implements GameResponseFactoryStep {

    private final IdeaService ideaService;

    public GameResponseFactoryStep_100_Ideas(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @Override
    public void execute(GameResponseFactorySettings settings) {
        var ideas = new IdeaResponseList(ideaService.findAll());

        var gameResponse = settings.getGameResponse();
        gameResponse.put("ideas", ideas);
    }
}
