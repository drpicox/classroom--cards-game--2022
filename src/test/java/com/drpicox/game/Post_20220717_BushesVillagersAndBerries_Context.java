package com.drpicox.game;

import com.drpicox.game.cards.api.CardResponseList;
import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.game.GameService;
import org.springframework.stereotype.Component;

import static com.drpicox.game.cards.api.CardResponseList.findAllCards;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.fixtures.FrontendSimulator;

@Component
public class Post_20220717_BushesVillagersAndBerries_Context {

    private final FrontendSimulator frontendSimulator;

    private GameResponse game;

    public Post_20220717_BushesVillagersAndBerries_Context(FrontendSimulator frontendSimulator) {
        this.frontendSimulator = frontendSimulator;
    }

    public void beforeTest() {
    }

    public void enterTheGame() {
        // example:  * Enter in the game.
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void thereShouldBeNSCard(int expectedCount, String cardName) {
        // example:  * There should be 1 "villager" card.
        // expected = 1
        // arg1 = "villager"

        var matchingCards = findAllCards(game, byName(cardName));
        assertThat(matchingCards).hasSize(expectedCount);
    }

    public void andThereShouldBeNSCard(int expectedCount, String expectedName) {
        thereShouldBeNSCard(expectedCount, expectedName);
    }

    public void afterTest() {
    }
}
