package com.drpicox.game.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TagRepository extends JpaRepository<Tag, TagId> {
    List<Tag> findAllByTagName(String tagName);

}
