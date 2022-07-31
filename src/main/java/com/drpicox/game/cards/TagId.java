package com.drpicox.game.cards;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TagId implements Serializable {
    private String cardId;
    private String tagName;

    public TagId(String cardId, String tagName) {
        this.cardId = cardId;
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagId tagId = (TagId) o;
        return Objects.equals(cardId, tagId.cardId) && Objects.equals(tagName, tagId.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, tagName);
    }

    protected TagId() {}

    @Override
    public String toString() {
        return "TagId{" +
            "cardId='" + cardId + '\'' +
            ", tagName='" + tagName + '\'' +
            '}';
    }
}
