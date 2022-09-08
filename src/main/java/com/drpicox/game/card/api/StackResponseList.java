package com.drpicox.game.card.api;

import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.util.OneCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.drpicox.game.util.OneCollector.toOne;

public class StackResponseList {
    public static List<DerivedStackResponse> findAllStack(GameResponse game) {
        var cards = CardResponseList.findAllCard(game);
        var maxPosition = cards.stream().mapToInt(CardResponse::getPosition).max().orElse(-1);

        var stacks = new ArrayList<DerivedStackResponse>();
        for (int position = 0; position <= maxPosition; position++) {
            stacks.add(getStackByPosition(cards, position));
        }
        return stacks;
    }

    public static List<DerivedStackResponse> findAllStack(GameResponse gameResponse, Predicate<? super DerivedStackResponse> predicate) {
        var allStacks = findAllStack(gameResponse);
        var result = allStacks.stream().filter(predicate).toList();
        return result;
    }

    public static DerivedStackResponse getStack(GameResponse gameResponse, Predicate<? super DerivedStackResponse> predicate) {
        return findAllStack(gameResponse).stream().filter(predicate).collect(toOne());
    }

    private static DerivedStackResponse getStackByPosition(List<CardResponse> cards, int position) {
        var stackCards = cards.stream()
            .filter(card -> card.getPosition() == position)
            .toList();

        var stack = new DerivedStackResponse(position, stackCards);
        return stack;
    }

    public static int getFreePosition(GameResponse game) {
        var occupiedPositions = findAllStack(game).stream().map(DerivedStackResponse::getPosition).collect(Collectors.toSet());
        var position = 0;
        while (occupiedPositions.contains(position)) {
            position++;
        }
        return position;
    }
}
