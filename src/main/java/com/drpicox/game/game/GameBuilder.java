package com.drpicox.game.game;

import com.drpicox.game.constants.ConstantsLoader;
import com.drpicox.game.constants.Constants;
import com.drpicox.game.cards.CardService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameBuilder {

    private final ConstantsLoader constantsLoader;
    private final Map<String, GameBuilderHandler> gameBuilderHandlers = new HashMap<>();

    public GameBuilder(ConstantsLoader constantsLoader, List<GameBuilderHandler> gameBuilderHandlers) {
        this.constantsLoader = constantsLoader;

        for (var handler: gameBuilderHandlers) {
            this.gameBuilderHandlers.put(handler.getName(), handler);
        }
    }

    public GameInstanceBuilder prepare(String name) throws IOException, URISyntaxException {
        var gameConstants = constantsLoader.load("games/" + name + ".properties");
        return new GameInstanceBuilder(gameConstants);
    }

    public class GameInstanceBuilder {
        private Constants gameConstants;

        public GameInstanceBuilder(Constants gameConstants) {
            this.gameConstants = gameConstants;
        }

        public void build() {
            var keys = gameConstants.keySet();
            for (var key: keys) {
                var keySegments = key.split("\\.");
                var handlerName = keySegments[0];
                System.out.println(key);
                System.out.println(handlerName);
                var handler = gameBuilderHandlers.get(handlerName);
                handler.build(key, gameConstants, keySegments);
            }
        }
    }
}
