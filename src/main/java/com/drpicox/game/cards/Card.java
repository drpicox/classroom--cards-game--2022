package com.drpicox.game.cards;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {
    @Id private String id;
    private String name;

    public Card(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected Card() {}

}
