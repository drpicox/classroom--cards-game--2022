package com.drpicox.game.idea.api;

import com.drpicox.game.card.Card;
import com.drpicox.game.card.api.CardResponse;
import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.idea.Idea;
import com.drpicox.game.util.OneCollector;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.drpicox.game.util.OneCollector.toOne;

public class IdeaResponseList extends ArrayList<IdeaResponse> {

    public IdeaResponseList(List<Idea> ideas) {
        ideas.forEach(idea -> add(new IdeaResponse(idea)));
    }

    private static List<IdeaResponse> findAllIdea(GameResponse game) {
        var result = game.deserializeField("ideas", IdeaResponseList.class);
        return result;
    }

    public static IdeaResponse getIdea(GameResponse game, Predicate<? super IdeaResponse> predicate) {
        var result = findAllIdea(game).stream().filter(predicate).collect(toOne());
        return result;
    }

    private IdeaResponseList() {} // GSON required constructor
}
