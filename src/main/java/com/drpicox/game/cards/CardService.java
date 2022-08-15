package com.drpicox.game.cards;

import com.drpicox.game.tags.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void delete(Card card) {
        cardRepository.delete(card);
    }

    public List<Card> findAllByTagName(String tagName) {
        var tags = tagService.findAllByTagName(tagName);
        var ids = tags.stream().map(t -> t.getCardId()).toList();
        var cards = cardRepository.findAllById(ids);
        return cards;
    }

    public void deleteAllByName(String name) {
        var cards = cardRepository.findAllByName(name);
        cardRepository.deleteAll(cards);
    }
}
