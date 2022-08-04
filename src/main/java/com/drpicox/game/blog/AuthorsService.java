package com.drpicox.game.blog;

import com.drpicox.game.PropertiesSyrup;
import com.drpicox.game.PropertiesSyrupLoader;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorsService {

    private final PropertiesSyrupLoader propertiesSyrupLoader;

    public AuthorsService(PropertiesSyrupLoader propertiesSyrupLoader) {
        this.propertiesSyrupLoader = propertiesSyrupLoader;
    }

    private PropertiesSyrup authors;

    private PropertiesSyrup getAuthors() {
        if (authors == null) authors = readAuthorsFile();
        return authors;
    }

    private PropertiesSyrup readAuthorsFile() {
        return propertiesSyrupLoader.load(".", "authors");
    }

    public boolean containsGitHubUser(String value) {
        var authors = getAuthors();
        return authors.containsKey(value);
    }

    public Collection<String> getGitHubUsers() {
        return getAuthors().keySet();
    }
}
