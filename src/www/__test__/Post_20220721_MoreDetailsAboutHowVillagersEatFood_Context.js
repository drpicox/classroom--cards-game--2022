import {
  mainView,
  getAllCardByName,
  getTagByName,
  getAllTagByName,
  queryAllTagByName,
  queryAllCardByName,
} from "./queries";
import * as userSimulator from "./userSimulator";

export class Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context {
  async givenThereAreNSNSAndNSCards() {
    userSimulator.clickLink(mainView, "Game");
    userSimulator.clickButton(mainView, "Reload");
    await userSimulator.waitForLoading();
  }

  async theSCardShouldHaveNInSTag(cardName, count, tagName) {
    // example:  * The "villager" card should have 1 in "eats" tag.
    // the = "villager"
    // have = 1
    // arg2 = "eats"

    var [card] = getAllCardByName(mainView, cardName);
    var tag = getTagByName(card, tagName);
    expect(tag).toHaveTextContent(count);
  }

  async theSumOfAllSTagsValueShouldBeN(tagName, expected) {
    // example:  * The sum of all "eats" tags value should be 12.
    // all = "eats"
    // expected = 12

    var tags = queryAllTagByName(mainView, tagName);
    var actual = tags.reduce((acc, t) => acc + +t.textContent, 0);
    expect(actual).toEqual(expected);
  }

  async endTheCurrentMoon() {
    // example:  * End the current moon.
    userSimulator.clickButton(mainView, "End Moon");
    await userSimulator.waitForLoading();
  }

  async thereShouldBeNSCards(expected, cardName) {
    // example:  * There should be 2 "berry" cards.
    // expected = 2
    // arg1 = "berry"

    var actual = queryAllCardByName(mainView, cardName);
    expect(actual).toHaveLength(expected);
  }
}
