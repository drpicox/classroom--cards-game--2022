package com.drpicox.game.moon.rules;

import com.drpicox.game.cards.CardService;
import com.drpicox.game.moon.EndMoonRule;
import org.springframework.stereotype.Service;

@Service
public class Rule_100_EatersEatFood implements EndMoonRule {

    private final CardService cardService;

    public Rule_100_EatersEatFood(CardService cardService) {
        this.cardService = cardService;
    }

    public void applyRule() {
        var eaters = cardService.findAllByTagName("eats");
        var foods = cardService.findAllByTagName("food");

        var totalEats = eaters.stream().mapToInt(c -> c.getTagValue("eats")).sum();
        var totalFood = foods.stream().mapToInt(c -> c.getTagValue("food")).sum();

        while (totalEats > totalFood) {
            var next = eaters.remove(0);
            totalEats -= next.getTagValue("eats");
            cardService.delete(next);
            cardService.create("Corpse");
        }

        var remainingToEat = totalEats;
        while (remainingToEat > 0) {
            var plated = foods.remove(0);
            remainingToEat -= plated.getTagValue("food");
            cardService.delete(plated);
        }
    }
}
