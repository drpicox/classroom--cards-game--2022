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

    public static List<CardResponse> findAllCards(GameResponse gameResponse, Predicate<? super CardResponse> names) {
        return findAllCards(gameResponse).stream().filter(names).toList();
    }

    public static Optional<CardResponse> findCard(GameResponse gameResponse, Predicate<? super CardResponse> names) {
        // TODO: JPA throws an exception if there is more than one result
        return findAllCards(gameResponse, names).stream().findAny();
    }

    private CardResponseList() {} // GSON required constructor

}
