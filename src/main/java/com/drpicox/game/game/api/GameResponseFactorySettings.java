package com.drpicox.game.game.api;

import com.drpicox.game.util.Settings;

public class GameResponseFactorySettings extends Settings {

    public GameResponseFactorySettings(GameResponse gameResponse) {
        super();

        set("gameResponse", gameResponse);
    }

    public GameResponse getGameResponse() {
        return get("gameResponse");
    }

}
