import { screen } from "@testing-library/react";

export class Context_20220717_BushesVillagersAndBerries {
  async enterInTheGame() {
    // example:  * Enter in the game.
    throw new Error("The method enterInTheGame() is not implemented yet.");
  }

  async thereShouldBeNCards(expected) {
    // example:  * There should be 3 cards.
    // expected = 3

    var actual = "";
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNCards(expected) is not implemented yet."
    );
  }

  async thereShouldBeNSCard(expected, arg1) {
    // example:  * There should be 1 "villager" card.
    // expected = 1
    // arg1 = "villager"

    var actual = "";
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSCard(expected, arg1) is not implemented yet."
    );
  }

  async andThereShouldBeNSCard(expected, arg1) {
    // example:  * And there should be 1 "berry" card.
    // expected = 1
    // arg1 = "berry"

    var actual = "";
    expect(actual).toEqual(expected);

    throw new Error(
      "The method andThereShouldBeNSCard(expected, arg1) is not implemented yet."
    );
  }
}
