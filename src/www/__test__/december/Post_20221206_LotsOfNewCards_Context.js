import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";
import { getAllCardDigestByName } from "../card/queries";

export class Post_20221206_LotsOfNewCards_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenANewExample() {
    // text:  * Given a new example.
    // code: await this.givenANewExample()
    // hint: Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context.givenANewExample

    await waitForReloadGame();
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Demon" cards.
    // code: await this.givenThereAreNSCards(1, "Demon")
    // hint: Post_20220725_IdeasHaveLevels_Context.givenThereAreNSCards

    await waitForReloadGame();
  }

  async reloadTheGame() {
    // text:  * Reload the game.
    // code: await this.reloadTheGame()

    await waitForReloadGame();
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // text:  * The "Demon" card should have 3 in "Critter" tag.
    // code: await this.theSCardShouldHaveNInSTag("Demon", 3, "Critter")
    // hint: Post_20220723_Ideas_Context.theSCardShouldHaveNInSTag

    const [card] = getAllCardDigestByName(mainView, cardName);
    expect(card.tags).toMatchObject({ [tagName]: count });
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
