package com.drpicox.game.moon.steps;

import com.drpicox.game.cards.CardFactory;
import com.drpicox.game.cards.CardFactorySettings;
import com.drpicox.game.cards.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import com.drpicox.game.util.Settings;
import org.springframework.stereotype.Service;

@Service
public class EndMoonStep_100_EatersEatFood implements EndMoonStep {

    private final CardService cardService;
    private final CardFactory cardFactory;

    public EndMoonStep_100_EatersEatFood(CardService cardService, CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory = cardFactory;
    }

    public void execute(EndMoonSettings settings) {
        var eaters = cardService.findAllByTagName("eats");
        var foods = cardService.findAllByTagName("food");

        var totalEats = eaters.stream().mapToInt(c -> c.getTagValue("eats")).sum();
        var totalFood = foods.stream().mapToInt(c -> c.getTagValue("food")).sum();

        while (totalEats > totalFood) {
            var next = eaters.remove(0);
            totalEats -= next.getTagValue("eats");
            cardService.delete(next);
            cardFactory.makeCard(new CardFactorySettings("Corpse"));
        }

        var remainingToEat = totalEats;
        while (remainingToEat > 0) {
            var plated = foods.remove(0);
            remainingToEat -= plated.getTagValue("food");
            cardService.delete(plated);
        }
    }
}
