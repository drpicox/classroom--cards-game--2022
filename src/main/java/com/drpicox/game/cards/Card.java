package com.drpicox.game.cards;

import com.drpicox.game.tags.Tag;
import com.drpicox.game.util.HasId;
import com.drpicox.game.util.HasName;
import com.drpicox.game.util.HasPosition;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Card implements HasId, HasName, HasPosition {
    @Id private String id;
    private String name;
    private int position;
    private int zindex;

    @OneToMany
    private List<Tag> tags;

    public Card(String id, String name, List<Tag> tags, int position, int zindex) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.position = position;
        this.zindex = zindex;
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

    public int getPosition() {
        return position;
    }

    public int getZindex() {
        return zindex;
    }

    public void placeAt(int position, int zindex) {
        this.position = position;
        this.zindex = zindex;
    }

    protected Card() {}

    public int getTagValue(String tagName) {
        return tags.stream()
            .filter(tag -> tag.getName().equals(tagName))
            .mapToInt(tag -> tag.getValue())
            .findAny()
            .orElse(0);
    }

    @Override
    public String toString() {
        return "C-" + id + "[#" + position + " z" + zindex + "]";
    }
}
