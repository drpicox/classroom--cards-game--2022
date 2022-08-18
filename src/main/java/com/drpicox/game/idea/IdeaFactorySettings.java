package com.drpicox.game.idea;

import com.drpicox.game.util.Settings;

public class IdeaFactorySettings extends Settings {

    public IdeaFactorySettings(String ideaName) {
        set("ideaName", ideaName);
    }

    public String getIdeaName() {
        return get("ideaName");
    }
}
