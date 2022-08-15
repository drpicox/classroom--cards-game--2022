package com.drpicox.game.game;

import com.drpicox.game.constants.ConstantsLoader;
import com.drpicox.game.util.Settings;
import com.drpicox.game.util.Steps;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class GameFactory {

    private final ConstantsLoader constantsLoader;
    private final Steps<GameFactorySettings> gameBuilderSteps;

    public GameFactory(ConstantsLoader constantsLoader, List<GameFactoryStep> gameFactorySteps) {
        this.constantsLoader = constantsLoader;
        this.gameBuilderSteps = Steps.from(gameFactorySteps);
    }

    public void makeGame(String name) throws IOException, URISyntaxException {
        var gameConstants = constantsLoader.load("games/" + name + ".properties");
        var settings = new GameFactorySettings(gameConstants);
        gameBuilderSteps.execute(settings);
    }

}
