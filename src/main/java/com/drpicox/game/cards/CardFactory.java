package com.drpicox.game.cards;

import com.drpicox.game.tags.TagFactory;
import com.drpicox.game.tags.TagFactorySettings;
import com.drpicox.game.constants.ConstantsCollection;
import com.drpicox.game.constants.ConstantsLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class CardFactory {

    private final CardRepository cardRepository;
    private final CardPositionService cardPositionService;
    private final TagFactory tagFactory;
    private final ConstantsCollection cardConstantsCollection;

    public CardFactory(CardRepository cardRepository, CardPositionService cardPositionService, TagFactory tagFactory, ConstantsLoader constantsLoader) throws IOException, URISyntaxException {
        this.cardRepository = cardRepository;
        this.cardPositionService = cardPositionService;
        this.tagFactory = tagFactory;
        this.cardConstantsCollection = constantsLoader.loadCollection("cards");
    }

    public Card makeCard(CardFactorySettings settings) {
        var cardName = settings.getCardName();

        var cardId = getNextId(settings);

        var cardConstants = cardConstantsCollection.getByName(cardName);
        var tags = tagFactory.makeAllTags(new TagFactorySettings(cardId).withCardConstants(cardConstants));
        var position = settings.getPosition();
        var zindex = cardPositionService.getStackByPosition(position).getMaxZindex() + 1;

        var card = new Card(cardId, cardName, tags, position, zindex);
        cardRepository.save(card);
        return card;
    }

    private final String getNextId(CardFactorySettings settings) {
        var cardName = settings.getCardName();
        var kebabCardName = cardName.replaceAll("[^\\w]", "-").toLowerCase();
        var allCards = cardRepository.findAllByName(cardName);
        var maxId = allCards.stream()
            .map(c -> c.getId())
            .map(i -> i.substring(kebabCardName.length() + 1))
            .map(i -> Integer.parseInt(i))
            .max(Integer::compare)
            .orElse(0);
        return kebabCardName + "-" + (maxId + 1);
    }
}
