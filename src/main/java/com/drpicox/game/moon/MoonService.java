package com.drpicox.game.moon;

import com.drpicox.game.cards.CardsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoonService {

    private final List<EndMoonRule> endMoonRules;

    public MoonService(List<EndMoonRule> endMoonRules) {
        this.endMoonRules = endMoonRules;
        this.endMoonRules.sort((a, b) -> a.getClass().getSimpleName().compareTo(b.getClass().getSimpleName()));
    }

    public void endMoon() {
        for (var rule: endMoonRules) rule.applyRule();
    }
}
