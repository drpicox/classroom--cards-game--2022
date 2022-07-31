package com.drpicox.game.cards;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsService {

    private final CardsRepository cardsRepository;

    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public Card create(String cardName) {
        var id = getNextId(cardName);
        var card = new Card(id, cardName);
        cardsRepository.save(card);
        return card;
    }

    public List<Card> findAll() {
        return cardsRepository.findAll();
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

    public void deleteCard(Card card) {
        cardsRepository.delete(card);
    }
}
