package com.drpicox.game;

import com.drpicox.game.game.api.GameResponse;
import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.fixtures.FrontendSimulator;

@Component
public class Post_20220719_VillagersEatFood_Context {

    private final FrontendSimulator frontendSimulator;
    private GameResponse game;

    Post_20220719_VillagersEatFood_Context(FrontendSimulator frontendSimulator) {
        this.frontendSimulator = frontendSimulator;
    }

    public void givenWeHaveEnteredIntoANewGame() {
        // example:  * Given we have entered into a new game.
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void theSCardHasNInSTag(String cardName, int count, String property) {
        // example:  * The "berry" card has 1 in "food" tag.
        // the = "berry"
        // has = 1
        // in = "food"
        var matchingCard = game.streamCardsByName(cardName).findAny().get();
        assertThat(matchingCard.getTag(property)).isEqualTo(count);
    }

    public void endTheCurrentMoon() {
        // example:  * End the current moon.
        game = frontendSimulator.post("/api/v1/game/moon", null, GameResponse.class);
    }

    public void thereShouldBeNCards(int expectedCards) {
        // example:  * There are 2 cards.
        // are = 2
        var matchingCards = game.streamCards();
        assertThat(matchingCards).hasSize(expectedCards);
    }

    public void thereShouldBeNoSCard(String cardName) {
        // example:  * There is no "berry" card.
        // no = "berry"

        var matchingCards = game.streamCardsByName(cardName);
        assertThat(matchingCards).isEmpty();
    }

    public void thereShouldBeNSCard(int expectedCount, String cardName) {
        // example:  * There is 1 "villager" card.
        // is = 1
        // n = "villager"

        var matchingCards = game.streamCardsByName(cardName);
        assertThat(matchingCards).hasSize(expectedCount);
    }

}
