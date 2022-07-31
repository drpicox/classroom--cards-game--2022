package com.drpicox.game.cards;

import com.drpicox.game.PropertiesLoader;
import com.drpicox.game.PropertiesSugar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class TagBuilder {

    private final TagsRepository tagsRepository;

    public TagBuilder(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public List<Tag> createAll(PropertiesSugar cardProperties, String cardId) {
        var tagKeys = cardProperties.streamKeysStartWith("tags.");
        var tags = tagKeys.map(tagKey -> {
            var tagName = tagKey.substring(5);
            return prepare(cardProperties, cardId, tagName).build();
        }).toList();

        return tags;
    }

    public CardInstanceBuilder prepare(PropertiesSugar cardProperties, String cardId, String tagName) {
        return new CardInstanceBuilder(cardProperties, cardId, tagName);
    }

    public class CardInstanceBuilder {
        private PropertiesSugar cardProperties;
        private String cardId;
        private String tagName;

        public CardInstanceBuilder(PropertiesSugar cardProperties, String cardId, String tagName) {
            this.cardProperties = cardProperties;
            this.cardId = cardId;
            this.tagName = tagName;
        }

        public Tag build() {
            var tagKey = "tags." + tagName;
            var tagValue = cardProperties.getInt(tagKey);
            var tag = new Tag(cardId, tagName, tagValue);
            tagsRepository.save(tag);
            return tag;
        }
    }
}
