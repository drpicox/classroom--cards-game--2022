package com.drpicox.game.rules;

import com.drpicox.game.cards.CardsService;
import com.drpicox.game.moon.EndMoonRule;
import org.springframework.stereotype.Service;

@Service
public class Rule_100_EatersEatFood implements EndMoonRule {

    private final CardsService cardsService;

    public Rule_100_EatersEatFood(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    public void applyRule() {
        var eaters = cardsService.findAllByTagName("eats");
        var foods = cardsService.findAllByTagName("food");

        var totalEats = eaters.stream().mapToInt(c -> c.getTagValue("eats")).sum();
        var totalFood = foods.stream().mapToInt(c -> c.getTagValue("food")).sum();

        while (totalEats > totalFood) {
            var next = eaters.remove(0);
            totalEats -= next.getTagValue("eats");
            cardsService.delete(next);
            cardsService.create("Corpse");
        }

        var remainingToEat = totalEats;
        while (remainingToEat > 0) {
            var plated = foods.remove(0);
            remainingToEat -= plated.getTagValue("food");
            cardsService.delete(plated);
        }
    }
}
