import { screen } from "@testing-library/react";
import { getByData } from "./fixtures/queryByData";
import { userSimulator } from "./fixtures/userSimulator";

export class Post_20220719_VillagersEatFood_Context {
  async givenWeHaveEnteredIntoANewGame() {
    // example:  * Given we have entered into a new game.
    const button = screen.getByRole("button", { name: "Enter the Game" });
    userSimulator.click(button);
    await userSimulator.waitForLoading();
  }

  async theSCardHasNInSTag(cardName, count, tagName) {
    // example:  * The "berry" card has 1 in "food" tag.
    // the = "berry"
    // has = 1
    // arg2 = "food"

    const [card] = screen.getAllByData("cardname", cardName);
    const tag = getByData(card, "tagname", tagName);
    expect(tag).toHaveTextContent("" + count);
  }

  async endTheCurrentMoon() {
    // example:  * End the current moon.
    const button = screen.getByRole("button", { name: "End Moon" });
    userSimulator.click(button);
    await userSimulator.waitForLoading();
  }

  async thereShouldBeNCards(expected) {
    // example:  * There are 2 cards.
    const actual = screen.getAllByTestId("card");
    expect(actual).toHaveLength(expected);
  }

  async thereShouldBeNoSCard(cardName) {
    // example:  * There is no "berry" card.
    // no = "berry"

    const card = screen.queryByData("cardname", cardName);
    expect(card).not.toBeInTheDocument();
  }

  async thereShouldBeNSCard(count, cardName) {
    // example:  * There is 1 "villager" card.
    // is = 1
    // n = "villager"

    const cards = screen.getAllByData("cardname", cardName);
    expect(cards).toHaveLength(count);
  }
}
