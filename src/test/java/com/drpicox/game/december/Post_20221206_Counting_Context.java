package com.drpicox.game.december;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;
import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.game.api.GameDTO;

@Component
public class Post_20221206_Counting_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenCardService givenCardService;
    private GameDTO gameDTO;

    Post_20221206_Counting_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here
        givenGameService.givenGame("empty");
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Villager" cards.
        // code: this.givenThereAreNSCards(1, "Villager")
        // hint: Post_20221206_LotsOfNewCards_Context.givenThereAreNSCards

        givenCardService.givenCards(count, cardName);
        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void reloadTheGame() {
        // text:  * Reload the game.
        // code: this.reloadTheGame()
        // hint: Post_20221206_LotsOfNewCards_Context.reloadTheGame

        gameDTO = frontendSimulator.get("/api/v1/game", GameDTO.class);
    }

    public void theCounterSShouldContainS(String counterName, String expected) {
        // text:  * The counter "Remaining food" should contain "2".
        // code: this.theCounterSShouldContainS("Remaining food", "2")

        // >> The computation is done in frontend
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }
}
