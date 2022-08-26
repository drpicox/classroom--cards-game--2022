package com.drpicox.game.idea.api;

import com.drpicox.game.card.CardFactory;
import com.drpicox.game.card.CardFactorySettings;
import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.game.api.GameResponseFactory;
import com.drpicox.game.idea.IdeaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final CardFactory cardFactory;
    private final GameResponseFactory gameResponseFactory;

    public IdeaController(IdeaService ideaService, CardFactory cardFactory, GameResponseFactory gameResponseFactory) {
        this.ideaService = ideaService;
        this.cardFactory = cardFactory;
        this.gameResponseFactory = gameResponseFactory;
    }

    @PostMapping("/{ideaId}/draw")
    public GameResponse drawIdea(@PathVariable String ideaId) {
        var idea = ideaService.findIdeaById(ideaId).get();
        cardFactory.makeCard(new CardFactorySettings(idea.getName()));
        return gameResponseFactory.makeGameResponse();
    }
}
