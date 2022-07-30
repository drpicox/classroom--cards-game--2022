import { screen, waitFor } from "@testing-library/react";
import { userSimulator } from "./fixtures/userSimulator";

export class Post_20220717_BushesVillagersAndBerries_Context {
  async enterInTheGame() {
    // example:  * Enter in the game.
    const button = screen.getByRole("button", { name: "Enter the Game" });
    userSimulator.click(button);
  }

  async thereShouldBeNCards(expectedCount) {
    // example:  * There should be 3 cards.
    // expected = 3

    await waitFor(async () => {
      const cards = await screen.findAllByTestId("card");
      expect(cards).toHaveLength(expectedCount);
    });
  }

  async thereShouldBeNSCard(expectedCount, cardName) {
    // example:  * There should be 1 "villager" card.
    // expected = 1
    // arg1 = "villager"

    const cards = screen.getAllByData("cardname", cardName);
    expect(cards).toHaveLength(expectedCount);
  }

  async andThereShouldBeNSCard(expectedCount, cardName) {
    this.thereShouldBeNSCard(expectedCount, cardName);
  }
}
