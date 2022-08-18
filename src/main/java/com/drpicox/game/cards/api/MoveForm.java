package com.drpicox.game.cards.api;

public class MoveForm {
    public MoveForm(int position, int zindex) {
        this.position = position;
        this.zindex = zindex;
    }

    public int position;
    public int zindex;

    public int getPosition() {
        return position;
    }

    public int getZindex() {
        return zindex;
    }
}
