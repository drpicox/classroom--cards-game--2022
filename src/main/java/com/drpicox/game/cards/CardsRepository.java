package com.drpicox.game.cards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CardsRepository extends JpaRepository<Card, String> {

    List<Card> findAllByName(String name);
}
