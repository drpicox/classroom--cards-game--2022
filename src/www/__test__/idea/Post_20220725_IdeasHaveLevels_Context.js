import { mainView, getByTestId } from "../queries";
import * as userSimulator from "../userSimulator";

export class Post_20220725_IdeasHaveLevels_Context {
  async beforeTest() {
    // Do your setup here, if necessary
  }

  async enterTheGame() {
    // text:  * Enter the game.
    // code: this.enterTheGame()
    // hint: Post_20220723_Ideas_Context.enterTheGame
    throw new Error("The method enterTheGame() is not implemented yet.");
  }

  async thereShouldBeTheSIdea(expected) {
    // text:  * There should be the "Harvest Idea" idea.
    // code: this.thereShouldBeTheSIdea("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeTheSIdea(expected) is not implemented yet."
    );
  }

  async theSShouldHaveLevelNAndNXp(s1, n1, n2) {
    // text:  * The "Harvest Idea" should have level 1 and 0 XP.
    // code: this.theSShouldHaveLevelNAndNXp("Harvest Idea", 1, 0)

    throw new Error(
      "The method theSShouldHaveLevelNAndNXp(s1, n1, n2) is not implemented yet."
    );
  }

  async drawACardFromTheSIdea(s1) {
    // text:  * Draw a card from the "Harvest Idea" idea.
    // code: this.drawACardFromTheSIdea("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.drawACardFromTheSIdea

    throw new Error(
      "The method drawACardFromTheSIdea(s1) is not implemented yet."
    );
  }

  async moveTheSCardToItsOwnStack(s1) {
    // text:  * Move the "Harvest Idea" card to its own stack.
    // code: this.moveTheSCardToItsOwnStack("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.moveTheSCardToItsOwnStack

    throw new Error(
      "The method moveTheSCardToItsOwnStack(s1) is not implemented yet."
    );
  }

  async moveTheSCardOnTopOfTheSCard(s1, s2) {
    // text:  * Move the "Villager" card on top of the "Harvest Idea" card.
    // code: this.moveTheSCardOnTopOfTheSCard("Villager", "Harvest Idea")
    // hint: Post_20220723_Ideas_Context.moveTheSCardOnTopOfTheSCard

    throw new Error(
      "The method moveTheSCardOnTopOfTheSCard(s1, s2) is not implemented yet."
    );
  }

  async thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) {
    // text:  * There should be 1 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
    // code: this.thereShouldBeNStacksOfNSNSAndNSCards(1, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNStacksOfNSNSAndNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSAndNSCards(expected, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async thereShouldBeNSCards(expected, s1) {
    // text:  * There should be 1 "Berry" cards.
    // code: this.thereShouldBeNSCards(1, "Berry")
    // hint: Post_20220723_Ideas_Context.thereShouldBeNSCards

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNSCards(expected, s1) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: this.endTheCurrentMoon()
    // hint: Post_20220723_Ideas_Context.endTheCurrentMoon
    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async givenANewGame() {
    // text:  * Given a new game.
    // code: this.givenANewGame()
    // hint: Post_20220723_Ideas_Context.givenANewGame
    throw new Error("The method givenANewGame() is not implemented yet.");
  }

  async givenThereIsTheSIdea(s1) {
    // text:  * Given there is the "Harvest Idea" idea.
    // code: this.givenThereIsTheSIdea("Harvest Idea")
    // hint: Post_20220723_Ideas_Context.givenThereIsTheSIdea

    throw new Error(
      "The method givenThereIsTheSIdea(s1) is not implemented yet."
    );
  }

  async givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) {
    // text:  * Given there are 2 stacks of 1 "Harvest Idea", 1 "Villager", and 1 "Berry Bush" cards.
    // code: this.givenThereAreNStacksOfNSNSAndNSCards(2, 1, "Harvest Idea", 1, "Villager", 1, "Berry Bush")
    // hint: Post_20220723_Ideas_Context.givenThereAreNStacksOfNSNSAndNSCards

    throw new Error(
      "The method givenThereAreNStacksOfNSNSAndNSCards(n1, n2, s1, n3, s2, n4, s3) is not implemented yet."
    );
  }

  async givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) {
    // text:  * Given there is the "Harvest Idea" idea at level 1 and 9 XP.
    // code: this.givenThereIsTheSIdeaAtLevelNAndNXp("Harvest Idea", 1, 9)

    throw new Error(
      "The method givenThereIsTheSIdeaAtLevelNAndNXp(s1, n1, n2) is not implemented yet."
    );
  }

  async givenThereAreNSCards(n1, s1) {
    // text:  * Given there are 1 "Berry" cards.
    // code: this.givenThereAreNSCards(1, "Berry")
    // hint: Post_20220723_Ideas_Context.givenThereAreNSCards

    throw new Error(
      "The method givenThereAreNSCards(n1, s1) is not implemented yet."
    );
  }

  async givenANewGameWithNSProductionStack(n1, s1) {
    // text:  * Given a new game with 1 "Berry Bush" production stack.
    // code: this.givenANewGameWithNSProductionStack(1, "Berry Bush")

    throw new Error(
      "The method givenANewGameWithNSProductionStack(n1, s1) is not implemented yet."
    );
  }

  async thereShouldBeNoSIdea(expected) {
    // text:  * There should be no "Plant Seed" idea.
    // code: this.thereShouldBeNoSIdea("Plant Seed")
    // hint: Post_20220723_Ideas_Context.thereShouldBeTheSIdea

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNoSIdea(expected) is not implemented yet."
    );
  }

  async theSIdeaShouldRequireTheSumOfNInSTagCards(s1, n1, s2) {
    // text:  * The "Seed Idea" idea should require the sum of 1 in "Seed" tag cards.
    // code: this.theSIdeaShouldRequireTheSumOfNInSTagCards("Seed Idea", 1, "Seed")

    throw new Error(
      "The method theSIdeaShouldRequireTheSumOfNInSTagCards(s1, n1, s2) is not implemented yet."
    );
  }

  async theSCardProgressShouldBeNOfN(s1, expected, n2) {
    // text:  * The "Seed Idea" card progress should be 1 of 5.
    // code: this.theSCardProgressShouldBeNOfN("Seed Idea", 1, 5)

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method theSCardProgressShouldBeNOfN(s1, expected, n2) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
