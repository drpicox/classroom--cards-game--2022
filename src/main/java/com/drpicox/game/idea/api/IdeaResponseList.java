package com.drpicox.game.idea.api;

import com.drpicox.game.game.api.GameResponse;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class IdeaResponseList extends ArrayList<IdeaResponse> {

    private static List<IdeaResponse> findAllIdea(GameResponse game) {
        var result = game.deserializeField("ideas", IdeaResponseList.class);
        return result;
    }

    public static Optional<IdeaResponse> findIdea(GameResponse game, Predicate<? super IdeaResponse> predicate) {
        var result = findAllIdea(game).stream().filter(predicate).findAny();
        return result;
    }

    private IdeaResponseList() {} // GSON required constructor
}
