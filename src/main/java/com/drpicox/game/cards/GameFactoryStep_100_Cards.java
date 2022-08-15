package com.drpicox.game.cards;

import com.drpicox.game.game.GameFactorySettings;
import com.drpicox.game.game.GameFactoryStep;
import org.springframework.stereotype.Component;

@Component
public class GameFactoryStep_100_Cards implements GameFactoryStep {

    private final CardFactory cardFactory;

    public GameFactoryStep_100_Cards(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    @Override
    public void execute(GameFactorySettings settings) {
        var gameConstants = settings.getGameConstants();
        var initialCountsKeys = gameConstants.findAllKeysStartingBy("cards.initialCount.");

        for (var initialCountKey : initialCountsKeys) {
            var csv = gameConstants.getCsv(initialCountKey);
            var cardName = csv[0];
            var count = Integer.parseInt(csv[1]);
            for (var i = 0; i < count; i += 1)
                cardFactory.makeCard(new CardFactorySettings(cardName));
        }
    }
}
