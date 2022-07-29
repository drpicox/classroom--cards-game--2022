package com.drpicox.game.game;

import com.drpicox.game.PropertiesLoader;
import com.drpicox.game.PropertiesSugar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class GameBuilder {

    private final PropertiesLoader propertiesLoader;

    public GameBuilder(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

    public GameInstanceBuilder prepare() {
        var gameProperties = propertiesLoader.load("game.properties");
        return new GameInstanceBuilder(gameProperties);
    }

    public class GameInstanceBuilder {
        private List<Card> cards = new ArrayList<>();
        private PropertiesSugar gameProperties;

        public GameInstanceBuilder(Properties gameProperties) {
            this.gameProperties = new PropertiesSugar(gameProperties);
        }

        public Game build() {
            addCards();

            return new Game(cards);
        }

        private void addCards() {
            var initialCardsProps = gameProperties.streamKeysStartWith("initial.cards.");
            initialCardsProps.forEach(initialCardKey -> {
                var cardName = initialCardKey.substring(14);
                var count = gameProperties.getInt(initialCardKey);
                for (var i = 0; i < count; i += 1)
                    cards.add(new Card(cardName));
            });
        }
    }
}
