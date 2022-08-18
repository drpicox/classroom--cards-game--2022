package com.drpicox.game.cards.api;

import com.drpicox.game.util.HasNames;
import com.drpicox.game.util.HasPosition;

import java.util.List;

public class DerivedStackResponse implements HasPosition, HasNames {

    private int position;
    private List<CardResponse> cards;

    public DerivedStackResponse(int position, List<CardResponse> cards) {
        this.position = position;
        this.cards = cards;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public List<CardResponse> getCards() {
        return cards;
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public String getName(int index) {
        return cards.get(index).getTagName();
    }

    @Override
    public String toString() {
        return "#" + position + "{" + cards + "}";
    }
}
