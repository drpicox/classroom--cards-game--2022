package com.drpicox.game.cards;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

import static com.drpicox.game.util.GivenAlgorithm.given;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Positions.byPosition;

@Service
public class GivenCardService {

    private final CardService cardService;
    private final CardFactory cardFactory;

    public GivenCardService(CardService cardService, CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory = cardFactory;
    }

    public List<Card> givenCard(int count, String cardName) {
        return givenCard(count, new CardFactorySettings(cardName));
    }

    public List<Card> givenCard(int count, CardFactorySettings settings) {
        var cardName = settings.getCardName();
        var hasPosition = settings.hasPosition();
        var position = settings.getPosition();

        return given(count,
            () -> cardService.findAllCards(byName(cardName)).stream()
                .filter(c -> !hasPosition || c.getPosition() == position)
                .toList(),
            card -> cardService.discardCard(card),
            () -> cardFactory.makeCard(settings)
        );
    }
}
