package com.drpicox.game.blog.api;

import com.drpicox.game.blog.Post;

public class PostDTO {
    private final String id;
    private final String body;
    private final String title;

    public PostDTO(Post post) {
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
