package com.drpicox.game.game;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    public Game enter() {
        return new Game();
    }
}
