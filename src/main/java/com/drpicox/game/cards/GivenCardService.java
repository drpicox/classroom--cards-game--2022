package com.drpicox.game.cards;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

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

    public void givenCard(int count, String cardName) {
        givenCard(count, new CardFactorySettings(cardName));
    }

    public void givenCard(int count, CardFactorySettings settings) {
        var cardName = settings.getCardName();
        var predicate =  (Predicate<Card>) (Predicate) byName(cardName);
        if (settings.hasPosition()) predicate = predicate.and(byPosition(settings.getPosition()));
        var cards = cardService.findAllCards(predicate);

        var remaining = count - cards.size();
        while (remaining < 0) {
            var excess = cards.get(cards.size() + remaining);
            cardService.discardCard(excess);
            remaining += 1;
        }

        while (remaining > 0) {
            cardFactory.makeCard(settings);
            remaining -= 1;
        }
    }
}
