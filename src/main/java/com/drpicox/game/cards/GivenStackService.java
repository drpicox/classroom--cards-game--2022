package com.drpicox.game.cards;

import com.drpicox.game.util.Names;
import org.springframework.stereotype.Service;

@Service
public class GivenStackService {

    private final CardPositionService cardPositionService;
    private final StackService stackService;
    private final CardFactory cardFactory;

    public GivenStackService(CardPositionService cardPositionService, StackService stackService, CardFactory cardFactory) {
        this.cardPositionService = cardPositionService;
        this.stackService = stackService;
        this.cardFactory = cardFactory;
    }


    public void givenStack(int count, Names names) {
        var stacks = stackService.findAllStack(names);

        var remaining = count - stacks.size();

        while (remaining < 0) {
            var excess = stacks.get(stacks.size() + remaining);
            stackService.discardStack(excess);
            remaining += 1;
        }

        while (remaining > 0) {
            createStack(names);
            remaining -= 1;
        }
    }

    public void givenStackAt(int position, Names names) {
        stackService.discardStack(position);
        createStack(names, new CardFactorySettings().withPosition(position));
    }

    private void createStack(Names names) {
        var freePosition = cardPositionService.getCardFreePosition();
        var settings = new CardFactorySettings().withPosition(freePosition);
        createStack(names, settings);
    }

    private void createStack(Names names, CardFactorySettings settings) {
        for (var cardName: names) {
            cardFactory.makeCard(settings.withCardName(cardName));
        }
    }
}
