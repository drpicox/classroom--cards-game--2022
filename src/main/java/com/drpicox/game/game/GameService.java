package com.drpicox.game.game;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class GameService {

    static final String GAME_ID = "TheGameId";

    private final GameFactory gameFactory;
    private final GameRepository gameRepository;

    public GameService(GameFactory gameFactory, GameRepository gameRepository) {
        this.gameFactory = gameFactory;
        this.gameRepository = gameRepository;
    }

    public boolean existsGame() {
        return gameRepository.existsById(GAME_ID);
    }
}
