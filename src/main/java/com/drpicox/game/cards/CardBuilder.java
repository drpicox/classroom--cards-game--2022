package com.drpicox.game.cards;

import com.drpicox.game.propertiesSyrup.PropertiesSyrup;
import org.springframework.stereotype.Service;

@Service
public class CardBuilder {

    private final CardPropertiesCollection cardPropertiesCollection;
    private final CardsRepository cardsRepository;
    private final TagBuilder tagBuilder;

    public CardBuilder(CardPropertiesCollection cardPropertiesCollection, CardsRepository cardsRepository, TagBuilder tagBuilder) {
        this.cardPropertiesCollection = cardPropertiesCollection;
        this.cardsRepository = cardsRepository;
        this.tagBuilder = tagBuilder;
    }

    public CardInstanceBuilder prepare(String cardName) {
        var cardProperties = cardPropertiesCollection.find(cardName).get();
        return new CardInstanceBuilder(cardProperties);
    }

    public class CardInstanceBuilder {
        private PropertiesSyrup cardProperties;

        public CardInstanceBuilder(PropertiesSyrup cardProperties) {
            this.cardProperties = cardProperties;
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
