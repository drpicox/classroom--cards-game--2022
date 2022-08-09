package com.drpicox.game.cards;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.game.GameBuilderHandler;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

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
        var csv = gameConstants.getCsv(key);
        var cardName = csv[0];
        var count = Integer.parseInt(csv[1]);
        for (var i = 0; i < count; i += 1)
            cardService.create(cardName);
    }
}
