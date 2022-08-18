package com.drpicox.game.cards.api;

import com.drpicox.game.cards.Card;
import com.drpicox.game.game.api.GameResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CardResponseList extends ArrayList<CardResponse> {

    public CardResponseList(List<Card> cards) {
        cards.forEach(card -> add(new CardResponse(card)));
    }

    public static List<CardResponse> findAllCards(GameResponse gameResponse) {
        var result = gameResponse.deserializeField("cards", CardResponseList.class);
        return result;
    }

    public static List<CardResponse> findAllCards(GameResponse gameResponse, Predicate<? super CardResponse> predicate) {
        var result = findAllCards(gameResponse).stream().filter(predicate).toList();
        return result;
    }

    public static Optional<CardResponse> findCard(GameResponse gameResponse, Predicate<? super CardResponse> predicate) {
        // TODO: Card singular and use predicate on the first directly to avoid too many array conversions
        return findAllCards(gameResponse, predicate).stream().findAny();
    }

    private CardResponseList() {} // GSON required constructor

}
