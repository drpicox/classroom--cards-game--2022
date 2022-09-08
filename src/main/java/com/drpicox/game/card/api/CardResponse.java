package com.drpicox.game.card.api;

import com.drpicox.game.card.Card;
import com.drpicox.game.tags.api.TagResponse;
import com.drpicox.game.util.HasName;
import com.drpicox.game.util.HasPosition;

import java.util.Map;
import java.util.TreeMap;

public class CardResponse implements HasName, HasPosition {

    public CardResponse(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.position = card.getPosition();
        this.zindex = card.getZindex();
        this.description = card.getDescription();
        this.maxProgress = card.getMaxProgress();
        this.progress = card.getProgress();
        this.looksLike = card.getLooksLike();

        card.getTags().stream().forEach(tag -> {
            var response = new TagResponse(tag);
            tags.put(tag.getName(), response);
        });
    }

    private String id;
    private String name;
    private int position;
    private int zindex;
    private int maxProgress;
    private int progress;
    private String looksLike;
    private Map<String, TagResponse> tags = new TreeMap<>();
    private Map<String, String> description;

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

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getProgress() {
        return progress;
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
