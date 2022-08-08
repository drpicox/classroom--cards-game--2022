package com.drpicox.game.cards;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final TagRepository tagRepository;
    private final CardBuilder cardBuilder;

    public CardService(CardRepository cardRepository, TagRepository tagRepository, CardBuilder cardBuilder) {
        this.cardRepository = cardRepository;
        this.tagRepository = tagRepository;
        this.cardBuilder = cardBuilder;
    }

    public Card create(String cardName) {
        var card = cardBuilder.prepare(cardName).build();
        return card;
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public void delete(Card card) {
        cardRepository.delete(card);
    }

    public List<Card> findAllByTagName(String tagName) {
        var tags = tagRepository.findAllByTagName(tagName);
        var ids = tags.stream().map(t -> t.getCardId()).toList();
        var cards = cardRepository.findAllById(ids);
        return cards;
    }

    public void deleteAllByName(String name) {
        var cards = cardRepository.findAllByName(name);
        cardRepository.deleteAll(cards);
    }

    public List<Card> createMany(int count, String name) {
        return Stream.generate(() -> create(name)).limit(count).toList();
    }
}
