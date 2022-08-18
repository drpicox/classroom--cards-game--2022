package com.drpicox.game.card.api;

import com.drpicox.game.card.Card;
import com.drpicox.game.tags.api.TagResponse;
import com.drpicox.game.util.HasName;
import com.drpicox.game.util.HasPosition;

import java.util.Map;
import java.util.TreeMap;

public class CardResponse implements HasName, HasPosition {

    private String id;
    private String name;
    private int position;
    private int zindex;
    private Map<String, TagResponse> tags = new TreeMap<>();
    private Map<String, String> description;

    public CardResponse(Card card) {
        this.id = card.getId();
        this.name = card.getTagName();
        this.position = card.getPosition();
        this.zindex = card.getZindex();
        this.description = card.getDescription();

        card.getTags().stream().forEach(tag -> {
            var response = new TagResponse(tag);
            tags.put(tag.getName(), response);
        });
    }

    public String getId() {
        return id;
    }

    public String getTagName() {
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

    public String getDescriptionTerm(String term) {
        return description.get(term);
    }

    public String toString() {
        return "C-" + id + "[#" + position + " z" + zindex + "]";
    }
}
