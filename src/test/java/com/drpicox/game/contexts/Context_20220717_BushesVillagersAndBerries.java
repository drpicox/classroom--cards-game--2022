package com.drpicox.game.contexts;

import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.game.GameService;
import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.fixtures.FrontendSimulator;

@Component
public class Context_20220717_BushesVillagersAndBerries {

    private final FrontendSimulator frontendSimulator;

    private final GameService gameService;

    private GameResponse game;

    public Context_20220717_BushesVillagersAndBerries(FrontendSimulator frontendSimulator, GameService gameService) {
        this.frontendSimulator = frontendSimulator;
        this.gameService = gameService;
    }

    public void enterInTheGame() {
        // example:  * Enter in the game.
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void thereShouldBeNCards(int expected) {
        // example:  * There should be 3 cards.
        // expected = 3
        var cards = game.streamCards();
        assertThat(cards).hasSize(expected);
    }

    public void thereShouldBeNSCard(int expectedCount, String expectedName) {
        // example:  * There should be 1 "villager" card.
        // expected = 1
        // arg1 = "villager"

        var matchingCards = game.streamCards().filter(c -> c.getName().equals(expectedName));
        assertThat(matchingCards).hasSize(expectedCount);
    }

    public void andThereShouldBeNSCard(int expectedCount, String expectedName) {
        thereShouldBeNSCard(expectedCount, expectedName);
    }

}
