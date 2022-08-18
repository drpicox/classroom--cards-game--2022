package com.drpicox.game.card.api;

import com.drpicox.game.card.CardService;
import com.drpicox.game.game.api.GameResponseFactorySettings;
import com.drpicox.game.game.api.GameResponseFactoryStep;
import org.springframework.stereotype.Component;

@Component
public class GameResponseFactoryStep_100_Cards implements GameResponseFactoryStep {

    private final CardService cardService;

    public GameResponseFactoryStep_100_Cards(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void execute(GameResponseFactorySettings settings) {
        var cards = cardService.findAll().stream().map(c -> new CardResponse(c)).toList();

        var gameResponse = settings.getGameResponse();
        gameResponse.put("cards", cards);
    }
}
