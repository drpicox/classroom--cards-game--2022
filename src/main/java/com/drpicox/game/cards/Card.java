package com.drpicox.game.cards;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Card {
    @Id private String id;
    private String name;

    @OneToMany
    private List<Tag> tags;

    public Card(String id, String name, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    protected Card() {}

    public int getTagValue(String tagName) {
        return tags.stream()
            .filter(tag -> tag.getName().equals(tagName))
            .mapToInt(tag -> tag.getValue())
            .findAny()
            .orElse(0);
    }
}
