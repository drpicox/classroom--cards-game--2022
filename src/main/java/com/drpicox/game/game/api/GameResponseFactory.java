package com.drpicox.game.game.api;

import com.drpicox.game.util.Steps;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameResponseFactory {

    private final Steps<GameResponseFactorySettings> gameResponseFactorySteps;

    public GameResponseFactory(List<GameResponseFactoryStep> steps) {
        this.gameResponseFactorySteps = Steps.from(steps);
    }

    public GameResponse makeGameResponse() {
        var gameResponse = new GameResponse();
        var settings = new GameResponseFactorySettings(gameResponse);
        gameResponseFactorySteps.execute(settings);

        return gameResponse;
    }
}
