package com.drpicox.game.cards;

import com.drpicox.game.constants.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagBuilder {

    private final TagRepository tagRepository;

    public TagBuilder(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> createAll(Constants cardProperties, String cardId) {
        var tagKeys = cardProperties.streamKeysStartWith("tags.");
        var tags = tagKeys.map(tagKey -> {
            var tagName = tagKey.substring(5);
            return prepare(cardProperties, cardId, tagName).build();
        }).toList();

        return tags;
    }

    public CardInstanceBuilder prepare(Constants cardProperties, String cardId, String tagName) {
        return new CardInstanceBuilder(cardProperties, cardId, tagName);
    }

    public class CardInstanceBuilder {
        private Constants cardProperties;
        private String cardId;
        private String tagName;

        public CardInstanceBuilder(Constants cardProperties, String cardId, String tagName) {
            this.cardProperties = cardProperties;
            this.cardId = cardId;
            this.tagName = tagName;
        }

        public Tag build() {
            var tagKey = "tags." + tagName;
            var tagValue = cardProperties.getInt(tagKey);
            var tag = new Tag(cardId, tagName, tagValue);
            tagRepository.save(tag);
            return tag;
        }
    }
}
