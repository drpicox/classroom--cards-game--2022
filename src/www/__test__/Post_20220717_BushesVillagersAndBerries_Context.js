import { mainView, getAllCard, getAllCardByName } from "./queries";
import * as userSimulator from "./userSimulator";

export class Post_20220717_BushesVillagersAndBerries_Context {
  async enterInTheGame() {
    // example:  * Enter in the game.
    userSimulator.clickButton(mainView, "Enter the Game");
    await userSimulator.waitForLoading();
  }

  async thereShouldBeNCards(expectedCount) {
    // example:  * There should be 3 cards.
    // expected = 3

    const cards = getAllCard(mainView);
    expect(cards).toHaveLength(expectedCount);
  }

  async thereShouldBeNSCard(expectedCount, cardName) {
    // example:  * There should be 1 "villager" card.
    // expected = 1
    // arg1 = "villager"

    const cards = getAllCardByName(mainView, cardName);
    expect(cards).toHaveLength(expectedCount);
  }

  async andThereShouldBeNSCard(expectedCount, cardName) {
    this.thereShouldBeNSCard(expectedCount, cardName);
  }
}
