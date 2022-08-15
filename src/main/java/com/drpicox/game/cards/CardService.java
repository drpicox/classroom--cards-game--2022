package com.drpicox.game.cards;

import com.drpicox.game.tags.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final TagService tagService;

    public CardService(CardRepository cardRepository, TagService tagService) {
        this.cardRepository = cardRepository;
        this.tagService = tagService;
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public List<Card> findAllByTagName(String tagName) {
        var tags = tagService.findAllByTagName(tagName);
        var ids = tags.stream().map(t -> t.getCardId()).toList();
        var cards = cardRepository.findAllById(ids);
        return cards;
    }

    public List<Card> findAllByName(String cardName) {
        var cards = cardRepository.findAllByName(cardName);
        return cards;
    }

    public Optional<Card> findCard(Predicate<? super Card> predicate) {
        return cardRepository.findAll().stream().filter(predicate).findAny();
    }

    public List<Card> findAllCards(Predicate<? super Card> predicate) {
        return cardRepository.findAll().stream().filter(predicate).toList();
    }

    public void discardCard(Card card) {
        cardRepository.delete(card);
    }

    public void discardCards(List<Card> cards) {
        cardRepository.deleteAll(cards);
    }
}
