package com.drpicox.game.card;

import com.drpicox.game.util.HasNames;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class StackService {

    private final CardPositionService cardPositionService;
    private final CardService cardService;
    private final CardRepository cardRepository;

    public StackService(CardPositionService cardPositionService, CardService cardService, CardRepository cardRepository) {
        this.cardPositionService = cardPositionService;
        this.cardService = cardService;
        this.cardRepository = cardRepository;
    }

    public List<Stack> findAllStack() {
        var stacks = new ArrayList<Stack>();
        var maxPosition = cardPositionService.getMaxPosition();
        for (var position = 0; position <= maxPosition; position++) {
            var stack = getStack(position);
            stacks.add(stack);
        }
        return stacks;
    }

    public List<Stack> findAllStack(Predicate<HasNames> names) {
        return findAllStack().stream().filter(names).toList();
    }

    public void stackSlice(Card originCard, Card targetCard) {
        stackSlice(originCard, targetCard.getPosition());
    }

    public void stackSlice(Card originCard, int targetPosition) {
        var originStack = getStack(originCard);
        Stack targetStack = getStack(targetPosition);
        if (originStack.equals(targetStack)) return;

        var cards = originStack.cut(originCard);
        var affectedCards = targetStack.stackOver(cards);

        cardRepository.saveAll(affectedCards);
    }

    public void discardStack(Stack stack) {
        var card = stack.getBottomCard();
        if (card != null) discardSlice(card);
    }

    public void discardStack(int position) {
        var stack = getStack(position);
        discardStack(stack);
    }

    public void discardSlice(Card originCard) {
        var originStack = getStack(originCard);
        var cards = originStack.cut(originCard);

        cardService.discardCards(cards);
    }

    private Stack getStack(int position) {
        var stack = cardPositionService.getStackByPosition(position);
        return stack;
    }

    private Stack getStack(Card stackCard) {
        return getStack(stackCard.getPosition());
    }

    public List<Stack> findAllStackByBottomCardName(String cardName) {
        var bottomCards = cardRepository.findAllByNameAndZindex(cardName, 0);
        var stacks = bottomCards.stream().map(this::getStack).toList();
        return stacks;
    }
}
