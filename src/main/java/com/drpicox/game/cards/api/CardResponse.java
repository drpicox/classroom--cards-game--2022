package com.drpicox.game.cards.api;

import com.drpicox.game.cards.Card;
import com.drpicox.game.util.HasId;
import com.drpicox.game.util.HasName;

import java.util.Map;
import java.util.TreeMap;

public class CardResponse implements HasId, HasName {

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

    @Override
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
