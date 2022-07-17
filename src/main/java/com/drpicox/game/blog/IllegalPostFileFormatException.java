package com.drpicox.game.blog;

public class IllegalPostFileFormatException extends IllegalArgumentException {
    public IllegalPostFileFormatException(String postId, int line, String message) {
        super("Error in the format of post while parsing the " + postId + ".md:" + line + ": " + message);
    }
}