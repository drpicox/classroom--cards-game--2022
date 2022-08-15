package com.drpicox.game.game.api;

import com.drpicox.game.cards.api.CardResponse;
import com.drpicox.game.cards.api.CardResponseList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

public class GameResponse extends TreeMap<String, Object> {
    public GameResponse() {} // GSON required constructor

    public <T> T deserializeField(String collectionName, Class<T> clazz) {
        var gson = new Gson();
        var collection = get(collectionName);
        return gson.fromJson(gson.toJsonTree(collection), clazz);
    }
}
