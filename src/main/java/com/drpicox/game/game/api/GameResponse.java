package com.drpicox.game.game.api;

import com.google.gson.Gson;

import java.util.TreeMap;

public class GameResponse extends TreeMap<String, Object> {
    public GameResponse() {} // GSON required constructor

    /**
     * Returns the value of the given field in the game response.
     *
     * It makes sure that the type of the resulting object is the
     * expected one.
     *
     * It is intended to be used primarily in testing, to
     * evaluate the game response for each action.
     *
     * It is how we obtain what we saved with:
     *
     * ```java
     * gameResponse.put("myField", myValue);
     * ```
     *
     * @param fieldName the name of the field to get from the gameResponse (
     * @param clazz the class of type that we want
     * @return the value saved in the response
     */
    public <T> T getField(String fieldName, Class<T> clazz) {
        var gson = new Gson();
        var fieldValue = get(fieldName);
        // Nasty trick to overcome Java typing limitations and convert a collection to a list of objects
        return gson.fromJson(gson.toJsonTree(fieldValue), clazz);
    }
}
