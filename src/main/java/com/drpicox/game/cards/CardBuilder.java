package com.drpicox.game.cards;

import com.drpicox.game.PropertiesLoader;
import com.drpicox.game.PropertiesSugar;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class CardBuilder {

    private final PropertiesLoader propertiesLoader;
    private final CardsRepository cardsRepository;

    public CardBuilder(PropertiesLoader propertiesLoader, CardsRepository cardsRepository) {
        this.propertiesLoader = propertiesLoader;
        this.cardsRepository = cardsRepository;
    }

    public CardInstanceBuilder prepare(String cardName) {
        var cardProperties = propertiesLoader.load("cards", cardName+".properties");
        return new CardInstanceBuilder(cardProperties);
    }

    public class CardInstanceBuilder {
        private PropertiesSugar cardProperties;

        public CardInstanceBuilder(Properties cardProperties) {
            this.cardProperties = new PropertiesSugar(cardProperties);
        }

        public Card build() {
            var cardName = getName();

            var card = new Card(getNextId(cardName), cardName);
            cardsRepository.save(card);
            return card;
        }

        private String getName() {
            return cardProperties.getString("name");
        }

        private final String getNextId(String cardName) {
            var allCards = cardsRepository.findAllByName(cardName);
            var maxId = allCards.stream()
                .map(c -> c.getId())
                .map(i -> i.substring(cardName.length() + 1))
                .map(i -> Integer.parseInt(i))
                .max(Integer::compare)
                .orElse(0);
            return cardName + "-" + (maxId + 1);
        }
    }
}
