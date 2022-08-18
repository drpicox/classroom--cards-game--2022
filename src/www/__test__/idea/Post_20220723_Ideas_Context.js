import { mainView, getByTestId } from "../queries";
import * as userSimulator from "../userSimulator";

export class Post_20220723_Ideas_Context {
  async beforeTest() {
    // Do your setup here, if necessary
  }

  async enterTheGame() {
    // text:  * Enter the game.
    // code: this.enterTheGame()
    // hint: Post_20220717_BushesVillagersAndBerries_Context.enterTheGame
    throw new Error("The method enterTheGame() is not implemented yet.");
  }

  async thereShouldBeTheSIdea(expected) {
    // text:  * There should be the "Harvest Idea" idea.
    // code: this.thereShouldBeTheSIdea("Harvest Idea")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeTheSIdea(expected) is not implemented yet."
    );
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 0 "Harvest Idea" cards.
    // code: this.thereShouldBeNSCards(0, "Harvest Idea")
    // hint: Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSCards(expected, s1) is not implemented yet."
    );
  }

  async drawACardFromTheSIdea(s1) {
    // text:  * Draw a card from the "Harvest Idea" idea.
    // code: this.drawACardFromTheSIdea("Harvest Idea")

    throw new Error(
      "The method drawACardFromTheSIdea(s1) is not implemented yet."
    );
  }

  async givenThereIsTheSIdea(s1) {
    // text:  * Given there is the "Harvest Idea" idea.
    // code: this.givenThereIsTheSIdea("Harvest Idea")

    throw new Error(
      "The method givenThereIsTheSIdea(s1) is not implemented yet."
    );
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Harvest Idea" cards.
    // code: this.givenThereAreNSCards(1, "Harvest Idea")

    throw new Error(
      "The method givenThereAreNSCards(n1, s1) is not implemented yet."
    );
  }

  async discardNSCards(n1, s1) {
    // text:  * Discard 1 "Harvest Idea" cards.
    // code: this.discardNSCards(1, "Harvest Idea")

    throw new Error(
      "The method discardNSCards(n1, s1) is not implemented yet."
    );
  }

  async theSIdeaShouldRequireNSCard(s1, n1, s2) {
    // text:  * The "Harvest Idea" idea should require 1 "Fruit Plant" card.
    // code: this.theSIdeaShouldRequireNSCard("Harvest Idea", 1, "Fruit Plant")

    throw new Error(
      "The method theSIdeaShouldRequireNSCard(s1, n1, s2) is not implemented yet."
    );
  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: this.givenANewGame()
    throw new Error("The method givenANewGame() is not implemented yet.");
  }

  async stackNSCardOnTopOfTheSCard(n1, s1, s2) {
    // text:  * Stack 1 "Villager" card on top of the "Harvest Idea" card.
    // code: this.stackNSCardOnTopOfTheSCard(1, "Villager", "Harvest Idea")

    throw new Error(
      "The method stackNSCardOnTopOfTheSCard(n1, s1, s2) is not implemented yet."
    );
  }

  async thereShouldBeNStackOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stack of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: this.thereShouldBeNStackOfNSNSAndNSCards(1, 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStackOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) {
    // text:  * Given there are 1 stacks of 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: this.givenThereAreNStacksOfNSNSAndNSCards(1, 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    throw new Error(
      "The method givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: this.endTheCurrentMoon()
    // hint: Post_20220719_VillagersEatFood_Context.endTheCurrentMoon
    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async givenThereAreNStacksOfNSNSNSAndNSCards(
    n1,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4
  ) {
    // text:  * Given there are 1 stacks of 1 "Corpse", 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: this.givenThereAreNStacksOfNSNSNSAndNSCards(1, 1, "Corpse", 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    throw new Error(
      "The method givenThereAreNStacksOfNSNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3, n5, s4) is not implemented yet."
    );
  }

  async thereShouldBeNStackOfNSNSNSAndNSCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4,
    s3,
    n5,
    s4
  ) {
    // text:  * There should be 1 stack of 1 "Corpse", 1 "Berry Bush", 1 "Villager", and 1 "Harvest Idea" cards.
    // code: this.thereShouldBeNStackOfNSNSNSAndNSCards(1, 1, "Corpse", 1, "Berry Bush", 1, "Villager", 1, "Harvest Idea")

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStackOfNSNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3, n5, s4) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
