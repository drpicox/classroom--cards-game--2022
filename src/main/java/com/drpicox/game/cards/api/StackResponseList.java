package com.drpicox.game.cards.api;

import com.drpicox.game.game.api.GameResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class StackResponseList {
    public static List<DerivedStackResponse> findAllStacks(GameResponse game) {
        var cards = CardResponseList.findAllCards(game);
        var maxPosition = cards.stream().mapToInt(CardResponse::getPosition).max().orElse(-1);

        var stacks = new ArrayList<DerivedStackResponse>();
        for (int position = 0; position <= maxPosition; position++) {
            stacks.add(getStackByPosition(cards, position));
        }
        return stacks;
    }

    public static List<DerivedStackResponse> findAllStacks(GameResponse gameResponse, Predicate<? super DerivedStackResponse> predicate) {
        return findAllStacks(gameResponse).stream().filter(predicate).toList();
    }

    public static Optional<DerivedStackResponse> findStack(GameResponse gameResponse, Predicate<? super DerivedStackResponse> predicate) {
        return findAllStacks(gameResponse).stream().filter(predicate).findAny();
    }

    private static DerivedStackResponse getStackByPosition(List<CardResponse> cards, int position) {
        var stackCards = cards.stream()
            .filter(card -> card.getPosition() == position)
            .sorted((c1, c2) -> c1.getZindex() - c2.getZindex())
            .toList();

        var stack = new DerivedStackResponse(position, stackCards);
        return stack;
    }
}
