package com.drpicox.game.idea;

import com.drpicox.game.card.GivenCardService;
import com.drpicox.game.card.GivenStackService;
import com.drpicox.game.card.api.MoveForm;
import com.drpicox.game.card.api.StackResponseList;
import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.idea.api.IdeaResponseList;
import org.springframework.stereotype.Component;

import static com.drpicox.game.card.api.CardResponseList.findAllCard;
import static com.drpicox.game.card.api.CardResponseList.getCard;
import static com.drpicox.game.idea.api.IdeaResponseList.getIdea;
import static com.drpicox.game.util.Names.byName;
import static com.drpicox.game.util.Names.byNames;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import com.drpicox.game.util.FrontendSimulator;
import com.drpicox.game.game.GivenGameService;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class Post_20220725_IdeasHaveLevels_Context {

    private final FrontendSimulator frontendSimulator;
    private final GivenGameService givenGameService;
    private final GivenIdeaService givenIdeaService;
    private final GivenStackService givenStackService;
    private final GivenCardService givenCardService;
    private GameResponse game;

    public Post_20220725_IdeasHaveLevels_Context(FrontendSimulator frontendSimulator, GivenGameService givenGameService, GivenIdeaService givenIdeaService, GivenStackService givenStackService, GivenCardService givenCardService) {
        this.frontendSimulator = frontendSimulator;
        this.givenGameService = givenGameService;
        this.givenIdeaService = givenIdeaService;
        this.givenStackService = givenStackService;
        this.givenCardService = givenCardService;
    }

    public void beforeTest() throws Throwable {
        // Do your setup here, and change this contents, if necessary
        givenGameService.givenGame("default");
    }

    public void enterTheGame() {
        // text:  * Enter the game.
        // code: this.enterTheGame()
        // hint: Post_20220723_Ideas_Context.enterTheGame
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void thereShouldBeTheSIdea(String expected) {
        // text:  * There should be the "Harvest Idea" idea.
        // code: this.thereShouldBeTheSIdea("Harvest Idea")
        // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

        var actual = getIdea(game, byName(expected));
        assertThat(actual.getName()).isEqualTo(expected);
    }

    public void theSShouldHaveLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * The "Harvest Idea" should have level 1 and 0 XP.
        // code: this.theSShouldHaveLevelNAndNXp("Harvest Idea", 1, 0)

        var idea = getIdea(game, byName(ideaName));
        assertThat(idea.getLevel()).isEqualTo(level);
        assertThat(idea.getXp()).isEqualTo(xp);
    }

    public void drawACardFromTheSIdea(String ideaName) {
        // text:  * Draw a card from the "Harvest Idea" idea.
        // code: this.drawACardFromTheSIdea("Harvest Idea")
        // hint: Post_20220723_Ideas_Context.drawACardFromTheSIdea

        var idea = getIdea(game, byName(ideaName));
        game = frontendSimulator.post("/api/v1/game/ideas/" + idea.getId() + "/draw", null, GameResponse.class);
    }

    public void moveTheSCardToItsOwnStack(String cardName) {
        // text:  * Move the "Harvest Idea" card to its own stack.
        // code: this.moveTheSCardToItsOwnStack("Harvest Idea")
        // hint: Post_20220723_Ideas_Context.moveTheSCardToItsOwnStack

        var card = getCard(game, byName(cardName));
        var cardId = card.getId();
        var position = StackResponseList.getFreePosition(game);
        var zindex = 0;

        game = frontendSimulator.post("/api/v1/game/cards/"+cardId+"/move", new MoveForm(position, zindex), GameResponse.class);
    }

    public void moveTheSCardOnTopOfTheSCard(String sourceCardName, String targetCardName) {
        // text:  * Move the "Villager" card on top of the "Harvest Idea" card.
        // code: this.moveTheSCardOnTopOfTheSCard("Villager", "Harvest Idea")
        // hint: Post_20220723_Ideas_Context.moveTheSCardOnTopOfTheSCard

        var targetCard = getCard(game, byName(targetCardName));
        var position = targetCard.getPosition();
        var zindex = targetCard.getZindex();

        var card = getCard(game, byName(sourceCardName));
        var cardId = card.getId();

        game = frontendSimulator.post("/api/v1/game/cards/"+cardId+"/move", new MoveForm(position, zindex + 1), GameResponse.class);
    }

    public void thereShouldBeNStacksOfNSNSAndNSCards(int expected, int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
        // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSAndNSCards

        var stacks = StackResponseList.findAllStack(game,
            byNames(count1, name1).and(count2, name2).and(count3, name3)
        );
        assertThat(stacks).hasSize(expected);
    }

    public void thereShouldBeNSCards(int expected, String cardName) {
        // text:  * There should be 1 "Berry" cards.
        // code: this.thereShouldBeNSCards(1, "Berry")
        // hint: Post_20220723_Ideas_Context.thereShouldBeNSCards

        var actual = findAllCard(game, byName(cardName)).size();
        assertThat(actual).isEqualTo(expected);
    }

    public void endTheCurrentMoon() {
        // text:  * End the current moon.
        // code: this.endTheCurrentMoon()
        // hint: Post_20220723_Ideas_Context.endTheCurrentMoon

        game = frontendSimulator.post("/api/v1/game/moon", null, GameResponse.class);
    }

    public void givenANewGame() throws IOException, URISyntaxException {
        // text:  * Given a new game.
        // code: this.givenANewGame()
        // hint: Post_20220723_Ideas_Context.givenANewGame

        givenGameService.givenGame("empty");
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void givenThereIsTheSIdea(String ideaName) {
        // text:  * Given there is the "Harvest Idea" idea.
        // code: this.givenThereIsTheSIdea("Harvest Idea")
        // hint: Post_20220723_Ideas_Context.givenThereIsTheSIdea

        givenIdeaService.givenIdea(ideaName);
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void givenThereAreNStacksOfNSNSAndNSCards(int count, int count1, String name1, int count2, String name2, int count3, String name3) {
        // text:  * Given there are 2 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
        // code: this.givenThereAreNStacksOfNSNSAndNSCards(2, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
        // hint: Post_20220723_Ideas_Context.givenThereAreNStacksOfNSNSAndNSCards

        givenStackService.givenStacks(count, byNames(count1, name1).and(count2, name2).and(count3, name3));
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void givenThereIsTheSIdeaAtLevelNAndNXp(String ideaName, int level, int xp) {
        // text:  * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
        // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 1, 9)

        givenIdeaService.givenIdea(ideaName, level, xp);
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void givenThereAreNSCards(int count, String cardName) {
        // text:  * Given there are 1 "Berry" cards.
        // code: this.givenThereAreNSCards(1, "Berry")
        // hint: Post_20220723_Ideas_Context.givenThereAreNSCards

        givenCardService.givenCards(count, cardName);
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void givenANewGameWithNSProductionStack(int count, String plantName) throws IOException, URISyntaxException {
        // text:  * Given a new game with 1 "Berry Bush" production stack.
        // code: this.givenANewGameWithNSProductionStack(1, "Berry Bush")

        givenGameService.givenGame("empty");
        givenCardService.givenCards(count, "Berry");
        givenStackService.givenStacks(count, byNames("Harvest Idea", "Villager", plantName));
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void thereShouldBeNoSIdea(String expected) {
        // text:  * There should be no "Plant Seed" idea.
        // code: this.thereShouldBeNoSIdea("Plant Seed")
        // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

        var actual = IdeaResponseList.findAllIdea(game, byName(expected));
        assertThat(actual).isEmpty();
    }

    public void theSCardProgressShouldBeNOfN(String cardName, int progress, int progressEnd) {
        // text:  * The "Seed Idea" card progress should be 1 of 5.
        // code: this.theSCardProgressShouldBeNOfN("Seed Idea", 1, 5)

        var idea = getCard(game, byName(cardName));
        assertThat(idea.getProgress()).isEqualTo(progress);
        assertThat(idea.getMaxProgress()).isEqualTo(progressEnd);
    }

    public void afterTest() {
        // Do your teardown here, if necessary
    }

    public void theSCardShouldHaveNInSTag(String cardName, int tagValue, String tagName) {
        var card = findAllCard(game, byName(cardName)).get(0);
        assertThat(card.getTag(tagName)).isEqualTo(tagValue);
    }

    public void theSCardDescriptionShouldSaySIsS(String cardName, String term, String description) {
        var card = findAllCard(game, byName(cardName)).get(0);
        assertThat(card.getDescriptionTerm(term)).isEqualTo(description);
    }

    public void givenThereAreNStacksOfNSCards(int count, int count1, String name1) {
        givenStackService.givenStacks(count, byNames(count1, name1));
        game = frontendSimulator.get("/api/v1/game", GameResponse.class);
    }

    public void theSIdeaShouldRequireNCardWithAtLeastNInSTag(String ideaName, int cardCount, int tagValue, String tagName) {
        // * The "Harvest Idea" idea should require 1 card with at least 1 in "Fruit Plant" tag.
        // theSIdeaShouldRequireNCardWithAtLeastNInSTag("Harvest Idea", 1, 1, "Fruit Plant");

        var idea = IdeaResponseList.getIdea(game, byName(ideaName));
        var requirement = idea.findTagRequirement(tagName).get();
        assertThat(requirement.getCardCount()).isEqualTo(cardCount);
        assertThat(requirement.getTagValue()).isEqualTo(tagValue);
    }
}
