package com.drpicox.game.game.api;

import com.drpicox.game.cards.CardsService;
import com.drpicox.game.game.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    private final GameService gameService;
    private final CardsService cardsService;

    public GameController(GameService gameService, CardsService cardsService) {
        this.gameService = gameService;
        this.cardsService = cardsService;
    }

    @GetMapping
    public GameResponse getGame() {
        gameService.create();
        var cards = cardsService.findAll().stream()
            .map(c -> new CardResponse(c))
            .collect(Collectors.toList());
        return new GameResponse(cards);
    }
}
