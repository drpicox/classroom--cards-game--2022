package com.drpicox.game.blog;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorsService {

    private Properties authors;

    private Properties getAuthors() {
        if (authors == null) authors = readAuthorsFile();
        return authors;
    }

    private static Properties readAuthorsFile() {
        try {
            var loader = Thread.currentThread().getContextClassLoader();
            var stream = loader.getResourceAsStream("authors.properties");
            var result = new Properties();
            result.load(stream);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException("Cannot load the \"resources/authors.properties\" file.", e);
        }
    }

    public boolean containsGitHubUser(String value) {
        var authors = getAuthors();
        return authors.containsKey(value);
    }

    public Collection<String> getGitHubUsers() {
        return getAuthors().stringPropertyNames();
    }
}