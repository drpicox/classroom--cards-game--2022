package com.drpicox.game.blog.api;

import com.drpicox.game.blog.Post;

import java.util.Map;

public class ListPostsResponseEntry {
    private final String id;
    private final String title;
    private final Map<String,String> frontMatter;

    public ListPostsResponseEntry(Post post) {
        id = post.getId();
        title = post.getTitle();
        frontMatter = post.getFrontMatter();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getProperty(String key) {
        return frontMatter.get(key);
    }

    public Map<String, String> getFrontMatter() {
        return frontMatter;
    }
}
