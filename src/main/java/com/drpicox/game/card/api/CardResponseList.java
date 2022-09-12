package com.drpicox.game.card.api;

import com.drpicox.game.card.Card;
import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.util.OneCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.drpicox.game.util.OneCollector.toOne;

public class CardResponseList extends ArrayList<CardResponse> {

    public CardResponseList(List<Card> cards) {
        cards.forEach(card -> add(new CardResponse(card)));
        sort((c1, c2) -> c1.getZindex() - c2.getZindex());
    }

    public static List<CardResponse> findAllCard(GameResponse gameResponse) {
        var result = gameResponse.getField("cards", CardResponseList.class);
        return result;
    }

    public static List<CardResponse> findAllCard(GameResponse gameResponse, Predicate<? super CardResponse> predicate) {
        var result = findAllCard(gameResponse).stream().filter(predicate).toList();
        return result;
    }

    public static CardResponse getCard(GameResponse gameResponse, Predicate<? super CardResponse> predicate) {
        return findAllCard(gameResponse, predicate).stream().collect(toOne());
    }

    private CardResponseList() {} // GSON required constructor

}
