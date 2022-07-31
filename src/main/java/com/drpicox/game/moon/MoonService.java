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
        var eaters = cardsService.findAllByTagName("eats");
        var foods = cardsService.findAllByTagName("food");

        for (var eater: eaters) {
            if (!foods.isEmpty()) {
                var food = foods.remove(0);
                cardsService.deleteCard(food);
            } else {
                cardsService.deleteCard(eater);
                cardsService.create("corpse");
            }
        }
    }
}
