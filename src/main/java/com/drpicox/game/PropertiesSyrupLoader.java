package com.drpicox.game;

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

    public PropertiesSyrup load(String category, String baseName) {
        var fileName = baseName.toLowerCase().replaceAll(" ", "-") + ".properties";

        try {
            var syrupName = category + "/" + fileName;
            if (!syrupsCache.containsKey(syrupName)) {
                var props = searchAndLoad(category, fileName);
                syrupsCache.put(syrupName, props);
            }

            return syrupsCache.get(syrupName);
        } catch (Throwable e) {
            throw new RuntimeException("Could not load " + category + "/" + fileName, e);
        }
    }

    private PropertiesSyrup searchAndLoad(String category, String fileName) throws URISyntaxException, IOException {
        var file = searchFile(category, fileName);
        return loadFile(file);
    }

    private File searchFile(String category, String fileName) throws URISyntaxException {
        if (category.equals(".")) return new File(getUri(fileName));

        loadCategory(category);
        var syrupName = category + "/" + fileName;
        return filePaths.get(syrupName);
    }

    private static PropertiesSyrup loadFile(File file) throws IOException {
        try (var reader = new FileReader(file)) {
            var props = new Properties();
            props.load(reader);
            return new PropertiesSyrup(props);
        }
    }

    private void loadCategory(String category) throws URISyntaxException {
        if (categoriesLoaded.contains(category)) return;
        categoriesLoaded.add(category);

        var categoryUri = getUri(category);
        loadDirectory(category, categoryUri);
    }

    private static URI getUri(String resourceName) throws URISyntaxException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        var uri = loader.getResource(resourceName).toURI();
        return uri;
    }

    private void loadDirectory(String category, URI uri) {
        var files = new File(uri).listFiles();
        for (var file: files) {
            if (file.isDirectory()) loadDirectory(category, file.toURI());
            else loadFile(category, file);
        }
    }

    private void loadFile(String category, File file) {
        var syrupName = category + "/" + file.getName();
        filePaths.put(syrupName, file);
    }
}
