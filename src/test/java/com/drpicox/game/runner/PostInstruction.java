package com.drpicox.game.runner;

import com.drpicox.game.blog.PostLine;

public abstract class PostInstruction {
    private final PostLine line;

    public PostInstruction(PostLine line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return line.toString();
    }

    public String getPassString() {
        return "☑️";
    }

    public void run(Object context) {
    }

    protected PostLine getLine() {
        return line;
    }
}
