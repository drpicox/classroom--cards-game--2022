package com.drpicox.game.moon;

import com.drpicox.game.cards.CardsService;
import org.springframework.stereotype.Service;

@Service
public class MoonService {

    private final CardsService cardsService;

    public MoonService(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    public void endMoon() {
        var cards = cardsService.findAll();
        for (var card: cards) {
            if (card.getName().equals("berry")) cardsService.deleteCard(card);
        }

        if (cards.size() == 2) for (var card: cards) {
            if (card.getName().equals("villager")) {
                cardsService.deleteCard(card);
                cardsService.create("corpse");
            }
        }
    }
}
