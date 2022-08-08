package com.drpicox.game.game;

import com.drpicox.game.constants.Constants;

public interface GameBuilderHandler {
    String getName();
    void build(String key, Constants gameConstants, String... keySegments);
}
