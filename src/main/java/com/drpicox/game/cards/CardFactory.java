package com.drpicox.game.cards;

import com.drpicox.game.tags.TagFactory;
import com.drpicox.game.tags.TagFactorySettings;
import com.drpicox.game.constants.ConstantsCollection;
import com.drpicox.game.constants.ConstantsLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CardFactory {

    private final CardRepository cardRepository;
    private final TagFactory tagFactory;
    private final ConstantsCollection cardConstantsCollection;

    public CardFactory(ConstantsLoader constantsLoader, CardRepository cardRepository, TagFactory tagFactory) throws URISyntaxException, IOException {
        this.cardRepository = cardRepository;
        this.tagFactory = tagFactory;
        this.cardConstantsCollection = constantsLoader.loadCollection("cards");
    }

    public Card makeCard(CardFactorySettings settings) {
        var cardName = settings.getCardName();

        var cardId = getNextId(settings);

        var cardConstants = cardConstantsCollection.getByName(cardName);
        var tags = tagFactory.makeAllTags(new TagFactorySettings(cardId).withCardConstants(cardConstants));

        var card = new Card(cardId, cardName, tags);
        cardRepository.save(card);
        return card;
    }

    public List<Card> makeMany(int count, CardFactorySettings settings) {
        return Stream.generate(() -> makeCard(settings)).limit(count).toList();
    }

    private final String getNextId(CardFactorySettings settings) {
        var cardName = settings.getCardName();
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
