package com.drpicox.game.game.api;

import com.google.gson.Gson;

import java.util.TreeMap;

public class GameResponse extends TreeMap<String, Object> {
    public GameResponse() {} // GSON required constructor

    public <T> T deserializeField(String collectionName, Class<T> clazz) {
        var gson = new Gson();
        var collection = get(collectionName);
        return gson.fromJson(gson.toJsonTree(collection), clazz);
    }
}
