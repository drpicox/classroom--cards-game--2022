package com.drpicox.game.game;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {

    private final GameBuilder gameBuilder;

    public GameService(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    public Game enter() {
        return gameBuilder.prepare().build();
    }
}
