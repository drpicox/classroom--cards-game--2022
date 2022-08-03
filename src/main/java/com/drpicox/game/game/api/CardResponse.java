package com.drpicox.game.game.api;

import com.drpicox.game.cards.Card;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CardResponse {

    private String id;
    private String name;
    private Map<String, TagResponse> tags = new TreeMap<>();

    public CardResponse(Card card) {
        this.id = card.getId();
        this.name = card.getName();

        card.getTags().stream().forEach(tag -> {
            var response = new TagResponse(tag);
            tags.put(tag.getName(), response);
        });
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTag(String tagName) {
        var tag = tags.get(tagName);

        if (tag == null) return 0;
        return tag.getValue();
    }
}
