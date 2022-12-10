package com.drpicox.game.december;

import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardListDTO.getCard;
import static com.drpicox.game.util.Names.byName;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class Post_20221206_LotsOfNewCards_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221206_LotsOfNewCards_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenANewExample() throws IOException, URISyntaxException {
        // text:  * Given a new example.
        // code: this.givenANewExample()
        // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenANewExample

        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Demon" cards.
        // code: this.givenThereAreNSCards(1, "Demon")
        // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNSCards

        givenCardService.givenCards(count, cardName);
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void reloadTheGame() {
        // text:  * Reload the game.
        // code: this.reloadTheGame()

        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theSCardShouldHaveNInSTag(String cardName, int value, String tagName) {
        // text:  * The "Demon" card should have 3 in "Critter" tag.
        // code: this.theSCardShouldHaveNInSTag("Demon", 3, "Critter")
        // hint: Post_20220723_Ideas_Context.theSCardShouldHaveNInSTag

        var matchingCard = getCard(gameDTO, byName(cardName));
        assertThat(matchingCard.getTag(tagName)).isEqualTo(value);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
