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
        return gameResponse.deserializeField("cards", CardResponseList.class);
    }

    public static List<CardResponse> findAllCards(GameResponse gameResponse, Predicate<? super CardResponse> predicate) {
        return findAllCards(gameResponse).stream().filter(predicate).toList();
    }

    public static Optional<CardResponse> findCard(GameResponse gameResponse, Predicate<? super CardResponse> predicate) {
        // TODO: JPA throws an exception if there is more than one result
        return findAllCards(gameResponse, predicate).stream().findAny();
    }

    private CardResponseList() {} // GSON required constructor

}
