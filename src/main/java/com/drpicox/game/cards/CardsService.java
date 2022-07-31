package com.drpicox.game.cards;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsService {

    private final CardsRepository cardsRepository;
    private final CardBuilder cardBuilder;

    public CardsService(CardsRepository cardsRepository, CardBuilder cardBuilder) {
        this.cardsRepository = cardsRepository;
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
}
