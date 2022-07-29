package com.drpicox.game.blog;

import com.drpicox.game.PropertiesLoader;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorsService {

    private final PropertiesLoader propertiesLoader;

    public AuthorsService(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

    private Properties authors;

    private Properties getAuthors() {
        if (authors == null) authors = readAuthorsFile();
        return authors;
    }

    private Properties readAuthorsFile() {
        return propertiesLoader.load("authors.properties");
    }

    public boolean containsGitHubUser(String value) {
        var authors = getAuthors();
        return authors.containsKey(value);
    }

    public Collection<String> getGitHubUsers() {
        return getAuthors().stringPropertyNames();
    }
}
