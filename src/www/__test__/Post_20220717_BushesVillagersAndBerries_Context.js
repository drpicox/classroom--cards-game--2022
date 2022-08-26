import { mainView } from "./main";
import { getAllCardByName } from "./card/queries";
import { waitForEnterTheGame } from "./main/actions";

export class Post_20220717_BushesVillagersAndBerries_Context {
  async beforeTest() {}

  async enterTheGame() {
    // example:  * Enter in the game.
    await waitForEnterTheGame();
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

  async afterTest() {}
}
