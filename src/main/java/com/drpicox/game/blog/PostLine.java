package com.drpicox.game.blog;

public class PostLine {
    private final String postId;
    private final int lineNumber;
    private final String lineText;

    public PostLine(String postId, int lineNumber, String lineText) {
        this.postId = postId;
        this.lineNumber = lineNumber;
        this.lineText = lineText;
    }

    public String getLineText() {
        return lineText;
    }

    @Override
    public String toString() {
        var lineNumberAsText = "" + lineNumber;
        while (lineNumberAsText.length() < 3) lineNumberAsText += " ";

        return postId + ":" + lineNumberAsText + " | " + lineText;
    }
}
