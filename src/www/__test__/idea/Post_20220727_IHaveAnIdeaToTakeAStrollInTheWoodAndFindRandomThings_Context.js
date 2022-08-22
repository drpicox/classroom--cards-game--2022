import { mainView, getByTestId } from "../queries";
import * as userSimulator from "../userSimulator";

export class Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context {
  async beforeTest() {
    // Do your setup here, if necessary
  }

  async enterTheGame() {
    // text:  * Enter the game.
    // code: this.enterTheGame()
    // hint: Post_20220725_IdeasHaveLevels_Context.enterTheGame
    throw new Error("The method enterTheGame() is not implemented yet.");
  }

  async thereShouldBeTheSIdea(expected) {
    // text:  * There should be the "Harvest Idea" idea.
    // code: this.thereShouldBeTheSIdea("Harvest Idea")
    // hint: Post_20220725_IdeasHaveLevels_Context.thereShouldBeTheSIdea

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeTheSIdea(expected) is not implemented yet."
    );
  }

  async theSShouldHaveLevelNAndNXp(s1, n1, n2) {
    // text:  * The "Woods Stroll Idea" should have level 1 and 0 XP.
    // code: this.theSShouldHaveLevelNAndNXp("Woods Stroll Idea", 1, 0)
    // hint: Post_20220725_IdeasHaveLevels_Context.theSShouldHaveLevelNAndNXp

    throw new Error(
      "The method theSShouldHaveLevelNAndNXp(s1, n1, n2) is not implemented yet."
    );
  }

  async theSMayCreateASCard(s1, s2) {
    // text:  * The "Woods Stroll Idea" may create a "Berry" card.
    // code: this.theSMayCreateASCard("Woods Stroll Idea", "Berry")

    throw new Error(
      "The method theSMayCreateASCard(s1, s2) is not implemented yet."
    );
  }

  async theSIdeaShouldRequireNCardWithAtLeastNInSTag(s1, n1, n2, s2) {
    // text:  * The "Woods Stroll Idea" idea should require 1 card with at least 1 in "Worker" tag.
    // code: this.theSIdeaShouldRequireNCardWithAtLeastNInSTag("Woods Stroll Idea", 1, 1, "Worker")
    // hint: Post_20220725_IdeasHaveLevels_Context.theSIdeaShouldRequireNCardWithAtLeastNInSTag

    throw new Error(
      "The method theSIdeaShouldRequireNCardWithAtLeastNInSTag(s1, n1, n2, s2) is not implemented yet."
    );
  }

  async givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) {
    // text:  * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
    // code: this.givenANewGameWithAStackOfNSCardsAndNSCards(1, "Woods Stroll Idea", 1, "Villager")

    throw new Error(
      "The method givenANewGameWithAStackOfNSCardsAndNSCards(n1, s1, n2, s2) is not implemented yet."
    );
  }

  async givenThatTheOddsAreThatWeWillGetACardNameFromTheSCard(s1) {
    // text:  * Given that the odds are that we will get a {Card Name} from the "Woods Stroll Idea" card.
    // code: this.givenThatTheOddsAreThatWeWillGetACardNameFromTheSCard("Woods Stroll Idea")

    throw new Error(
      "The method givenThatTheOddsAreThatWeWillGetACardNameFromTheSCard(s1) is not implemented yet."
    );
  }

  async endTheCurrentMoon() {
    // text:  * End the current moon.
    // code: this.endTheCurrentMoon()
    // hint: Post_20220725_IdeasHaveLevels_Context.endTheCurrentMoon
    throw new Error("The method endTheCurrentMoon() is not implemented yet.");
  }

  async thereShouldBeNStacksOfNSNSAndNCardNameCards(
    expected,
    n2,
    s1,
    n3,
    s2,
    n4
  ) {
    // text:  * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 {Card Name} cards.
    // code: this.thereShouldBeNStacksOfNSNSAndNCardNameCards(1, 1, "Woods Stroll Idea", 1, "Villager", 1)

    var actual = expected; // FIXME
    expect(actual).toEqual(expected);

    throw new Error(
      "The method thereShouldBeNStacksOfNSNSAndNCardNameCards(expected, n2, s1, n3, s2, n4) is not implemented yet."
    );
  }

  async afterTest() {
    // Do your teardown here, if necessary
  }
}
