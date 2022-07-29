package com.drpicox.game;

import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class PropertiesLoader {

    public Properties load(String fileName) {
        try {
            var loader = Thread.currentThread().getContextClassLoader();
            var stream = loader.getResourceAsStream(fileName);
            var result = new Properties();
            result.load(stream);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException("Cannot load the \"resources/" + fileName + "\" file.", e);
        }
    }
}
