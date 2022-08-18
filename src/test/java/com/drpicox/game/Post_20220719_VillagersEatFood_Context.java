package com.drpicox.game;

import com.drpicox.game.game.api.GameResponse;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardResponseList.findAllCard;
import static com.drpicox.game.card.api.CardResponseList.findCard;
import static com.drpicox.game.util.Names.byName;
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

    public void beforeTest() {
    }

    public void givenWeHaveEnteredIntoANewGame() {
        // example:  * Given we have entered into a new game.
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void theSCardShouldHaveNInSTag(String cardName, int count, String tagName) {
        // example:  * The "berry" card has 1 in "food" tag.
        // the = "berry"
        // has = 1
        // in = "food"
        var matchingCard = findCard(game, byName(cardName)).get();
        assertThat(matchingCard.getTag(tagName)).isEqualTo(count);
    }

    public void endTheCurrentMoon() {
        // example:  * End the current moon.
        game = frontendSimulator.post("/api/v1/game/moon", null, GameResponse.class);
    }

    public void thereShouldBeNoSCard(String cardName) {
        // example:  * There is no "berry" card.
        // no = "berry"

        var matchingCards = findAllCard(game, byName(cardName));
        assertThat(matchingCards).isEmpty();
    }

    public void thereShouldBeNSCard(int expectedCount, String cardName) {
        // example:  * There is 1 "villager" card.
        // is = 1
        // n = "villager"

        var matchingCards = findAllCard(game, byName(cardName));
        assertThat(matchingCards).hasSize(expectedCount);
    }

    public void afterTest() {
    }
}
