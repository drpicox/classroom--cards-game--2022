package com.drpicox.game.cards;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsService {

    private final CardsRepository cardsRepository;
    private final TagsRepository tagsRepository;
    private final CardBuilder cardBuilder;

    public CardsService(CardsRepository cardsRepository, TagsRepository tagsRepository, CardBuilder cardBuilder) {
        this.cardsRepository = cardsRepository;
        this.tagsRepository = tagsRepository;
        this.cardBuilder = cardBuilder;
    }

    public Card create(String cardName) {
        var card = cardBuilder.prepare(cardName).build();
        return card;
    }

    public List<Card> findAll() {
        return cardsRepository.findAll();
    }

    public void deleteCard(Card card) {
        cardsRepository.delete(card);
    }

    public List<Card> findAllByTagName(String tagName) {
        var tags = tagsRepository.findAllByTagName(tagName);
        var ids = tags.stream().map(t -> t.getCardId()).toList();
        var cards = cardsRepository.findAllById(ids);
        return cards;
    }
}
