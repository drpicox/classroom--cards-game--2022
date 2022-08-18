package com.drpicox.game.cards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CardRepository extends JpaRepository<Card, String> {

    List<Card> findAllByName(String name);
    List<Card> findAllByNameAndZindex(String name, int zindex);
    List<Card> findAllByPositionOrderByZindexAsc(int position);
    List<Card> findAllByOrderByPositionAsc();
    boolean existsByPosition(int freePosition);
}
