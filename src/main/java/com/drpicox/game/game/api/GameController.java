package com.drpicox.game.game.api;

import com.drpicox.game.cards.CardService;
import com.drpicox.game.cards.api.CardResponse;
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
    private final MoonService moonService;
    private final GameResponseFactory gameResponseFactory;

    public GameController(GameService gameService, MoonService moonService, GameResponseFactory gameResponseFactory) {
        this.gameService = gameService;
        this.moonService = moonService;
        this.gameResponseFactory = gameResponseFactory;
    }

    @GetMapping
    public GameResponse getGame() throws IOException, URISyntaxException {
        gameService.createIfDoesNotExist();
        return gameResponseFactory.makeGameResponse();
    }

    @PostMapping("/moon")
    public GameResponse endMoon() {
        moonService.endMoon();
        return gameResponseFactory.makeGameResponse();
    }
}
