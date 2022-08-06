package com.drpicox.game.hints;

import com.drpicox.game.propertiesSyrup.PropertiesSyrup;
import com.drpicox.game.propertiesSyrup.PropertiesSyrupLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class AuthorsService {

    private final PropertiesSyrupLoader propertiesSyrupLoader;

    public AuthorsService(PropertiesSyrupLoader propertiesSyrupLoader) {
        this.propertiesSyrupLoader = propertiesSyrupLoader;
    }

    private PropertiesSyrup authors;

    private PropertiesSyrup getAuthors() throws IOException, URISyntaxException {
        if (authors == null) authors = readAuthorsFile();
        return authors;
    }

    private PropertiesSyrup readAuthorsFile() throws IOException, URISyntaxException {
        return propertiesSyrupLoader.load( "authors.properties");
    }

    public boolean containsGitHubUser(String value) throws IOException, URISyntaxException {
        var authors = getAuthors();
        return authors.containsKey(value);
    }

    public Collection<String> getGitHubUsers() {
        return authors.keySet();
    }
}
