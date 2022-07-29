package com.drpicox.game.game;

import com.drpicox.game.PropertiesLoader;
import com.drpicox.game.PropertiesSugar;
import com.drpicox.game.cards.Card;
import com.drpicox.game.cards.CardsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class GameBuilder {

    private final PropertiesLoader propertiesLoader;
    private final CardsService cardsService;

    public GameBuilder(PropertiesLoader propertiesLoader, CardsService cardsService) {
        this.propertiesLoader = propertiesLoader;
        this.cardsService = cardsService;
    }

    public GameInstanceBuilder prepare() {
        var gameProperties = propertiesLoader.load("game.properties");
        return new GameInstanceBuilder(gameProperties);
    }

    public class GameInstanceBuilder {
        private PropertiesSugar gameProperties;

        public GameInstanceBuilder(Properties gameProperties) {
            this.gameProperties = new PropertiesSugar(gameProperties);
        }

        public Game build() {
            addCards();

            return new Game(cardsService.findAll());
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
