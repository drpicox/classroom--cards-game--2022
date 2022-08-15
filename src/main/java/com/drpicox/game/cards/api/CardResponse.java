package com.drpicox.game.cards.api;

import com.drpicox.game.cards.Card;
import com.drpicox.game.util.HasId;
import com.drpicox.game.util.HasName;
import com.drpicox.game.util.HasPosition;

import java.util.Map;
import java.util.TreeMap;

public class CardResponse implements HasId, HasName, HasPosition {

    private String id;
    private String name;
    private int position;
    private int zindex;
    private Map<String, TagResponse> tags = new TreeMap<>();

    public CardResponse(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.position = card.getPosition();
        this.zindex = card.getZindex();

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

    public int getPosition() {
        return position;
    }

    public int getZindex() {
        return zindex;
    }

    public int getTag(String tagName) {
        var tag = tags.get(tagName);

        if (tag == null) return 0;
        return tag.getValue();
    }

    public String toString() {
        return "C-" + id + "[#" + position + " z" + zindex + "]";
    }
}
