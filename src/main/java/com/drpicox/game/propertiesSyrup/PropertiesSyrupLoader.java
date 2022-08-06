package com.drpicox.game.propertiesSyrup;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class PropertiesSyrupLoader {

    private final Map<String, PropertiesSyrup> syrupsCache = new HashMap<>();
    private final Map<String, File> filePaths = new HashMap<>();
    private final Set<String> categoriesLoaded = new HashSet<>();

    PropertiesSyrupLoader(List<PropertiesSyrupCollection> collections) throws URISyntaxException, IOException {
        for (var collection: collections) {
            load(collection);
        }
    }

    private void load(PropertiesSyrupCollection collection) throws URISyntaxException, IOException {
        var directoryUri = getUri(collection.getCategory());
        var directory = new File(directoryUri);

        loadDirectory(collection, directory);
    }

    private void loadDirectory(PropertiesSyrupCollection collection, File directory) throws IOException {
        var files = directory.listFiles();
        for (var file: files) {
            if (file.isDirectory()) loadDirectory(collection, file);
            else loadFile(collection, file);
        }
    }

    private void loadFile(PropertiesSyrupCollection collection, File file) throws IOException {
        var props = loadFile(file);
        collection.add(props);
    }

    public PropertiesSyrup load(String resourceName) throws IOException, URISyntaxException {
        var uri = getUri(resourceName);
        var file = new File(uri);
        return loadFile(file);
    }

    private static PropertiesSyrup loadFile(File file) throws IOException {
        try (var reader = new FileReader(file)) {
            var props = new Properties();
            props.load(reader);
            return new PropertiesSyrup(props);
        }
    }

    private static URI getUri(String resourceName) throws URISyntaxException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        var uri = loader.getResource(resourceName).toURI();
        return uri;
    }
}
