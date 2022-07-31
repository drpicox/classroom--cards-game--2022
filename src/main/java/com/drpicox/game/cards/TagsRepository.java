package com.drpicox.game.cards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TagsRepository extends JpaRepository<Tag, TagId> {
    List<Tag> findAllByTagName(String tagName);

}
