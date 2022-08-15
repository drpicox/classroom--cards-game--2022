package com.drpicox.game;

import com.drpicox.game.cards.CardFactorySettings;
import com.drpicox.game.cards.GivenCardService;
import com.drpicox.game.game.GameService;
import com.drpicox.game.game.api.GameResponse;
import org.springframework.stereotype.Component;

import static com.drpicox.game.cards.api.CardResponseList.findAllCards;
import static com.drpicox.game.cards.api.CardResponseList.findCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.fixtures.FrontendSimulator;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context {

    private final FrontendSimulator frontendSimulator;
    private final GameService gameService;
    private final GivenCardService givenCardService;

    private GameResponse game;

    public Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context(FrontendSimulator frontendSimulator, GameService gameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.gameService = gameService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws IOException, URISyntaxException {
        gameService.create("empty");
    }

    public void givenThereAreNSAndNSCards(int count1, String name1, int count2, String name2) {
        // example:  * Given there are 2 "villager" and 2 "trader" cards.
        givenCardService.givenCard(count1, new CardFactorySettings(name1));
        givenCardService.givenCard(count2, new CardFactorySettings(name2));

        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void theSCardShouldHaveNInSTag(String cardName, int count, String tagName) {
        // example:  * The "villager" card should have 1 in "eats" tag.
        var matchingCard = findCard(game, byName(cardName)).get();
        assertThat(matchingCard.getTag(tagName)).isEqualTo(count);
    }

    public void theSumOfAllSTagsValueShouldBeN(String tagName, int expected) {
        // example:  * The sum of all "eats" tags value should be 12.
        var cards = findAllCards(game);
        var sum = cards.stream().mapToInt(c -> c.getTag(tagName)).sum();
        assertThat(sum).isEqualTo(expected);
    }

    public void endTheCurrentMoon() {
        // example:  * End the current moon.
        game = frontendSimulator.post("/api/v1/game/moon", null, GameResponse.class);
    }

    public void thereShouldBeNSCards(int expected, String cardName) {
        // example:  * There should be 2 "berry" cards.
        var actual = findAllCards(game, byName(cardName)).size();
        assertThat(actual).isEqualTo(expected);
    }

    public void afterTest() {
    }
}
