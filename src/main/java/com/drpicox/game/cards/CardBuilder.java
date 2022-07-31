package com.drpicox.game.cards;

import com.drpicox.game.PropertiesLoader;
import com.drpicox.game.PropertiesSugar;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class CardBuilder {

    private final PropertiesLoader propertiesLoader;
    private final CardsRepository cardsRepository;
    private final TagBuilder tagBuilder;

    public CardBuilder(PropertiesLoader propertiesLoader, CardsRepository cardsRepository, TagBuilder tagBuilder) {
        this.propertiesLoader = propertiesLoader;
        this.cardsRepository = cardsRepository;
        this.tagBuilder = tagBuilder;
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
            var cardId = getNextId(cardName);

            var tags = tagBuilder.createAll(cardProperties, cardId);

            var card = new Card(cardId, cardName, tags);
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
