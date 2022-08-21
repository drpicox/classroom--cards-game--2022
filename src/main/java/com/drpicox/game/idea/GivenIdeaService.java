package com.drpicox.game.idea;

import org.springframework.stereotype.Service;

@Service
public class GivenIdeaService {

    private final IdeaFactory ideaFactory;
    private final IdeaRepository ideaRepository;

    public GivenIdeaService(IdeaFactory ideaFactory, IdeaRepository ideaRepository) {
        this.ideaFactory = ideaFactory;
        this.ideaRepository = ideaRepository;
    }

    public void givenIdea(String ideaName, int level, int xp) {
        if (ideaRepository.existsById(ideaName)) ideaRepository.deleteById(ideaName);
        ideaFactory.makeIdea(new IdeaFactorySettings(ideaName).withLevel(level).withXp(xp));
    }

    public void givenIdea(String ideaName) {
        givenIdea(ideaName, 1, 0);
    }

}
