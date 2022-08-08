package com.drpicox.game.cards;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.game.GameBuilderHandler;
import org.springframework.stereotype.Component;

@Component
public class CardsGameBuilderHandler implements GameBuilderHandler {

    private final CardService cardService;

    public CardsGameBuilderHandler(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public String getName() {
        return "cards";
    }

    @Override
    public void build(String key, Constants gameConstants, String... keySegments) {
        var count = gameConstants.getInt(key);
        var cardName = keySegments[2];
        for (var i = 0; i < count; i += 1)
            cardService.create(cardName);
    }
}
