import {
  mainView,
  getAllCardByName,
  getTagByName,
  queryCardByName,
} from "./queries";
import * as userSimulator from "./userSimulator";

export class Post_20220719_VillagersEatFood_Context {
  async beforeTest() {}

  async givenWeHaveEnteredIntoANewGame() {
    // example:  * Given we have entered into a new game.
    userSimulator.clickButton(mainView, "Enter the Game");
    await userSimulator.waitForLoading();
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // example:  * The "berry" card has 1 in "food" tag.
    // the = "berry"
    // has = 1
    // arg2 = "food"

    const [card] = getAllCardByName(mainView, cardName);
    const tag = getTagByName(card, tagName);
    expect(tag).toHaveTextContent("" + count);
  }

  async endTheCurrentMoon() {
    // example:  * End the current moon.
    userSimulator.clickButton(mainView, "End Moon");
    await userSimulator.waitForLoading();
  }

  async thereShouldBeNoSCard(cardName) {
    // example:  * There is no "berry" card.
    // no = "berry"

    const card = queryCardByName(mainView, cardName);
    expect(card).not.toBeInTheDocument();
  }

  async thereShouldBeNSCard(count, cardName) {
    // example:  * There is 1 "villager" card.
    // is = 1
    // n = "villager"

    const cards = getAllCardByName(mainView, cardName);
    expect(cards).toHaveLength(count);
  }

  async afterTest() {}
}
