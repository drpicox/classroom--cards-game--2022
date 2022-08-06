package com.drpicox.game.hints.api;

import com.drpicox.game.hints.Post;

public class PostResponse {
    private final String id;
    private final String body;
    private final String title;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
