import { getByTestId, screen } from "@testing-library/react";
import { mainView } from "../main";
import { waitForEnterTheGame, waitForReloadGame } from "../game/actions";
import { waitForEndMoon } from "../moon/actions";

export class Post_20221206_Counting_Context {
  async beforeTest() {
    // Do your setup here
    await waitForEnterTheGame();
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Villager" cards.
    // code: await this.givenThereAreNSCards(1, "Villager")
    // hint: Post_20221206_LotsOfNewCards_Context.givenThereAreNSCards

    await waitForReloadGame();
  }

  async reloadTheGame() {
    // text:  * Reload the game.
    // code: await this.reloadTheGame()
    // hint: Post_20221206_LotsOfNewCards_Context.reloadTheGame

    await waitForReloadGame();
  }

  async theCounterSShouldContainS(conterName, expected) {
    // text:  * The counter "Remaining food" should contain "2".
    // code: await this.theCounterSShouldContainS("Remaining food", "2")

    const counter = screen.getByTestId(`counter-${conterName}`);
    expect(counter).toHaveTextContent(expected);
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
