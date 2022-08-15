package com.drpicox.game.tags;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagFactory {

    private final TagRepository tagRepository;

    public TagFactory(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> makeAllTags(TagFactorySettings settings) {
        var cardConstants = settings.getCardConstants();
        var cardId = settings.getCardId();

        var tagKeys = cardConstants.streamKeysStartWith("tags.");
        var tags = tagKeys.map(tagKey -> {
            var tagName = tagKey.substring(5);
            return makeTag(settings.withTagName(tagName));
        }).toList();

        return tags;
    }

    public Tag makeTag(TagFactorySettings settings) {
        var tagName = settings.getTagName();
        var cardId = settings.getCardId();
        var cardConstants = settings.getCardConstants();

        var tagKey = "tags." + tagName;
        var tagValue = cardConstants.getInt(tagKey);
        var tag = new Tag(cardId, tagName, tagValue);
        tagRepository.save(tag);

        return tag;
    }
}
