package com.drpicox.game.cards;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.constants.ConstantsCollection;
import com.drpicox.game.constants.ConstantsLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class CardBuilder {

    private final CardRepository cardRepository;
    private final TagBuilder tagBuilder;
    private final ConstantsCollection cardConstantsCollection;

    public CardBuilder(ConstantsLoader constantsLoader, CardRepository cardRepository, TagBuilder tagBuilder) throws URISyntaxException, IOException {
        this.cardRepository = cardRepository;
        this.tagBuilder = tagBuilder;
        this.cardConstantsCollection = constantsLoader.loadCollection("cards");
    }

    public CardInstanceBuilder prepare(String cardName) {
        var cardProperties = cardConstantsCollection.getByName(cardName);
        return new CardInstanceBuilder(cardProperties);
    }

    public class CardInstanceBuilder {
        private Constants cardProperties;

        public CardInstanceBuilder(Constants cardProperties) {
            this.cardProperties = cardProperties;
        }

        public Card build() {
            var cardName = getName();
            var cardId = getNextId(cardName);

            var tags = tagBuilder.createAll(cardProperties, cardId);

            var card = new Card(cardId, cardName, tags);
            cardRepository.save(card);
            return card;
        }

        private String getName() {
            return cardProperties.getString("name");
        }

        private final String getNextId(String cardName) {
            var allCards = cardRepository.findAllByName(cardName);
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
