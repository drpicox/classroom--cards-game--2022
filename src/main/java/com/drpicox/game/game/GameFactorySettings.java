package com.drpicox.game.game;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.util.Settings;

public class GameFactorySettings extends Settings {
    public GameFactorySettings(Constants gameConstants) {
        super();
        set("gameConstants", gameConstants);
    }

    public Constants getGameConstants() {
        return get("gameConstants");
    }
}
