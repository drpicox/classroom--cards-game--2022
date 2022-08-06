package com.drpicox.game.game;

import com.drpicox.game.propertiesSyrup.PropertiesSyrupLoader;
import com.drpicox.game.propertiesSyrup.PropertiesSyrup;
import com.drpicox.game.cards.CardsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class GameBuilder {

    private final PropertiesSyrupLoader propertiesSyrupLoader;
    private final CardsService cardsService;

    public GameBuilder(PropertiesSyrupLoader propertiesSyrupLoader, CardsService cardsService) {
        this.propertiesSyrupLoader = propertiesSyrupLoader;
        this.cardsService = cardsService;
    }

    public GameInstanceBuilder prepare(String name) throws IOException, URISyntaxException {
        var gameProperties = propertiesSyrupLoader.load("games/" + name + ".properties");
        return new GameInstanceBuilder(gameProperties);
    }

    public class GameInstanceBuilder {
        private PropertiesSyrup gameProperties;

        public GameInstanceBuilder(PropertiesSyrup gameProperties) {
            this.gameProperties = gameProperties;
        }

        public void build() {
            addCards();
        }

        private void addCards() {
            var initialCardsProps = gameProperties.streamKeysStartWith("initial.cards.");
            initialCardsProps.forEach(initialCardKey -> {
                var cardName = initialCardKey.substring(14);
                var count = gameProperties.getInt(initialCardKey);
                for (var i = 0; i < count; i += 1)
                    cardsService.create(cardName);
            });
        }
    }
}
