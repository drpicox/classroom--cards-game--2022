package com.drpicox.game.blog;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class BlogService {

    private List<Post> posts;

    public List<Post> findAll() {
        if (posts == null) {
            try {
                posts = loadAll();
            } catch (Exception e) {
                throw new RuntimeException("An exception found while loading all posts", e);
            }
        }

        return posts;
    }

    public Optional<Post> findPost(String postId) {
        return findAll().stream().filter(p -> p.getId().equals(postId)).findFirst();
    }

    private List<Post> loadAll() throws Exception {
        var result = new LinkedList<Post>();

        var files = getResourceBlogFiles();
        for (File f : files) {
            var id = f.getName().split("\\.")[0];
            result.add(loadPost(id, f));
        }
        Collections.sort(result);
        return result;
    }

    private Post loadPost(String postId, File file) throws Exception {
        try (
            var fr = new FileReader(file);
            var br = new BufferedReader(fr);
        ) {
            var path = Paths.get(file.toURI());
            var lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            var rawContent = Files.readAllBytes(path);

            return new PostParser(postId, lines, rawContent).parse();
        }
    }

    private File[] getResourceBlogFiles() throws URISyntaxException {
        var uri = getBlogUri();
        return new File(uri).listFiles();
    }

    private URI getBlogUri() throws URISyntaxException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader.getResource("blog").toURI();
    }
}
