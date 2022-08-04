package com.drpicox.game.game;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final String GAME_ID = "TheGameId";

    private final GameBuilder gameBuilder;
    private final GameRepository gameRepository;

    public GameService(GameBuilder gameBuilder, GameRepository gameRepository) {
        this.gameBuilder = gameBuilder;
        this.gameRepository = gameRepository;
    }

    public void create(String name) {
        gameRepository.save(new Game(GAME_ID));
        gameBuilder.prepare(name).build();
    }

    public void createIfDoesNotExist() {
        if (!gameRepository.existsById(GAME_ID)) create("default");
    }
}
