package com.drpicox.game.idea.api;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.game.api.GameResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game/ideas")
public class IdeaController {

    private final CardFactory cardFactory;
    private final GameResponseFactory gameResponseFactory;

    public IdeaController(CardFactory cardFactory, GameResponseFactory gameResponseFactory) {
        this.cardFactory = cardFactory;
        this.gameResponseFactory = gameResponseFactory;
    }

    @PostMapping("/draw")
    public GameResponse drawIdea(@RequestBody DrawIdeaForm drawIdeaForm) {
        var ideaName = drawIdeaForm.getIdeaName();
        cardFactory.makeCard(new CardFactorySettings(ideaName));
        return gameResponseFactory.makeGameResponse();
    }
}
