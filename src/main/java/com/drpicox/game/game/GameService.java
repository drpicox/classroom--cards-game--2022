package com.drpicox.game.game;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class GameService {

    private static final String GAME_ID = "TheGameId";

    private final GameFactory gameFactory;
    private final GameRepository gameRepository;

    public GameService(GameFactory gameFactory, GameRepository gameRepository) {
        this.gameFactory = gameFactory;
        this.gameRepository = gameRepository;
    }

    public void create(String name) throws IOException, URISyntaxException {
        gameRepository.save(new Game(GAME_ID));
        gameFactory.makeGame(name);
    }

    public void createIfDoesNotExist() throws IOException, URISyntaxException {
        if (!gameRepository.existsById(GAME_ID)) create("default");
    }
}
