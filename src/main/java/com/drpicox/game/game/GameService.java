package com.drpicox.game.game;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameBuilder gameBuilder;

    public GameService(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    public void create() {
        gameBuilder.prepare().build();
    }
}
