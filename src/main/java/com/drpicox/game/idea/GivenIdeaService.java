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

    public void givenIdea(String ideaName) {
        if (ideaRepository.existsById(ideaName)) ideaRepository.deleteById(ideaName);
        ideaFactory.makeIdea(new IdeaFactorySettings(ideaName));
    }
}
