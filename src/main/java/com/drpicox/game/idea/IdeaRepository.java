package com.drpicox.game.idea;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface IdeaRepository extends JpaRepository<Idea, String> {
    Optional<Idea> findByName(String name);
}
