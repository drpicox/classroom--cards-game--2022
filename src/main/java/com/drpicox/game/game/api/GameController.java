package com.drpicox.game.game.api;

import com.drpicox.game.cards.CardsService;
import com.drpicox.game.game.GameService;
import com.drpicox.game.moon.MoonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    private final GameService gameService;
    private final CardsService cardsService;
    private final MoonService moonService;

    public GameController(GameService gameService, CardsService cardsService, MoonService moonService) {
        this.gameService = gameService;
        this.cardsService = cardsService;
        this.moonService = moonService;
    }

    @GetMapping
    public GameResponse getGame() throws IOException, URISyntaxException {
        gameService.createIfDoesNotExist();
        return getGameResponse();
    }

    @PostMapping("/moon")
    public GameResponse endMoon() {
        moonService.endMoon();
        return getGameResponse();
    }

    private GameResponse getGameResponse() {
        var cards = cardsService.findAll().stream()
            .map(c -> new CardResponse(c))
            .collect(Collectors.toList());
        return new GameResponse(cards);
    }
}
