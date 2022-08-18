package com.drpicox.game.cards;

import java.util.List;

public class CardListSummary {
    public CardListSummary(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> cards;

    public int sumTagValue(String tagValue) {
        return cards.stream().mapToInt(card -> card.getTagValue(tagValue)).sum();
    }

    public String getDescriptionTerm(String term) {
        return cards.stream()
            .map(card -> card.getDescriptionTerm(term))
            .filter(value -> value != null)
            .findFirst()
            .get();
    }

    public List<Card> findAllCardByDescriptionTermAndTagName(String term, String tagName) {
        return cards.stream()
            .filter(card -> card.getDescriptionTerm(term) != null)
            .filter(card -> card.getTagValue(tagName) > 0)
            .toList();
    }
}
