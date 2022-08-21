package com.drpicox.game.idea;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> findAll() {
        return ideaRepository.findAll();
    }

    public Optional<Idea> findByName(String expected) {
        return ideaRepository.findByName(expected);
    }

    public void increaseXp(Idea idea) {
        idea.increaseXp();
        ideaRepository.save(idea);
    }
}
