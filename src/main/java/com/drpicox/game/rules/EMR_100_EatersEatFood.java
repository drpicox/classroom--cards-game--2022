package com.drpicox.game.rules;

import com.drpicox.game.cards.CardsService;
import com.drpicox.game.moon.EndMoonRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMR_100_EatersEatFood implements EndMoonRule {

    private final CardsService cardsService;

    public EMR_100_EatersEatFood(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    public void applyRule() {
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
