package com.drpicox.game.tags.food;

import com.drpicox.game.cards.CardFactory;
import com.drpicox.game.cards.CardFactorySettings;
import com.drpicox.game.cards.CardService;
import com.drpicox.game.moon.EndMoonSettings;
import com.drpicox.game.moon.EndMoonStep;
import org.springframework.stereotype.Service;

@Service
public class EndMoonStep_200_EatersEatFood implements EndMoonStep {

    private final CardService cardService;
    private final CardFactory cardFactory;

    public EndMoonStep_200_EatersEatFood(CardService cardService, CardFactory cardFactory) {
        this.cardService = cardService;
        this.cardFactory = cardFactory;
    }

    public void execute(EndMoonSettings settings) {
        var eaters = cardService.findAllByTagName("Eats");
        var foods = cardService.findAllByTagName("Food");

        var totalEats = eaters.stream().mapToInt(c -> c.getTagValue("Eats")).sum();
        var totalFood = foods.stream().mapToInt(c -> c.getTagValue("Food")).sum();

        while (totalEats > totalFood) {
            var next = eaters.remove(0);
            totalEats -= next.getTagValue("Eats");
            cardService.discardCard(next);
            cardFactory.makeCard(new CardFactorySettings("Corpse"));
        }

        var remainingToEat = totalEats;
        while (remainingToEat > 0) {
            var plated = foods.remove(0);
            remainingToEat -= plated.getTagValue("Food");
            cardService.discardCard(plated);
        }
    }
}
