package com.drpicox.game;

import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class PropertiesLoader {

    private static Map<String, Properties> cache = new HashMap<>();

    public Properties load(String first, String ...more) {
        String fileName = Paths.get(first, more).toFile().getPath();
        if (cache.containsKey(fileName)) return cache.get(fileName);

        try {
            var loader = Thread.currentThread().getContextClassLoader();
            var stream = loader.getResourceAsStream(fileName);
            var result = new Properties();
            result.load(stream);

            cache.put(fileName, result);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException("Cannot load the \"resources/" + fileName + "\" file.", e);
        }
    }
}
