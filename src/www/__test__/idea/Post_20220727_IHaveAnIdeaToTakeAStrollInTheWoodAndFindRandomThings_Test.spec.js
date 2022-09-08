import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context } from "./Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-07-27_i_have_an_idea_to_take_a_stroll_in_the_wood_and_find_random_things.md", async () => {
  await runBeforeTestStarts(
    "2022-07-27_i_have_an_idea_to_take_a_stroll_in_the_wood_and_find_random_things",
    "0117e5df1915a0ec3c12074e2e5054d9"
  );

  const context =
    new Post_20220727_IHaveAnIdeaToTakeAStrollInTheWoodAndFindRandomThings_Context();
  await context.beforeTest();

  // # I have an idea to take a stroll in the wood and find random things                                      // # I have an idea to take a stroll in the wood and find random things

  // ## Walk in the woods idea                                                                                 // ## Walk in the woods idea
  await context.enterTheGame(); //                                                                             // * Enter the game.
  await context.thereShouldBeTheSIdea("Harvest Idea"); //                                                      // * There should be the "Harvest Idea" idea.
  await context.theSShouldHaveLevelNAndNXp("Woods Stroll Idea", 1, 0); //                                      // * The "Woods Stroll Idea" should have level 1 and 0 XP.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Berry"); //                                          // * The "Woods Stroll Idea" may create a "Berry" card.
  await context.theSMayCreateASCard("Woods Stroll Idea", "Apple"); //                                          // * The "Woods Stroll Idea" may create a "Apple" card.
  await context.theSIdeaShouldRequireNCardWithAtLeastNInSTag(
    "Woods Stroll Idea",
    1,
    1,
    "Worker"
  ); //          // * The "Woods Stroll Idea" idea should require 1 card with at least 1 in "Worker" tag.

  // ## Finding things                                                                                         // ## Finding things
  // ### | Card Name |                                                                                         // ### | Card Name |
  // ### |-----------|                                                                                         // ### |-----------|
  // ### |   "Berry" |                                                                                         // ### |   "Berry" |
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //          // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Berry",
    "Woods Stroll Idea"
  ); //               // * Given that the odds are that we will get a "Berry" from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                        // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Berry"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Berry" cards.

  // ## Finding things                                                                                         // ## Finding things
  // ### | Card Name |                                                                                         // ### | Card Name |
  // ### |-----------|                                                                                         // ### |-----------|
  // ### |   "Apple" |                                                                                         // ### |   "Apple" |
  await context.givenANewGameWithAStackOfNSCardsAndNSCards(
    1,
    "Woods Stroll Idea",
    1,
    "Villager"
  ); //          // * Given a new game with a stack of 1 "Woods Stroll Idea" cards and 1 "Villager" cards.
  await context.givenThatTheOddsAreThatWeWillGetASFromTheSCard(
    "Apple",
    "Woods Stroll Idea"
  ); //               // * Given that the odds are that we will get a "Apple" from the "Woods Stroll Idea" card.
  await context.endTheCurrentMoon(); //                                                                        // * End the current moon.
  await context.thereShouldBeNStacksOfNSNSAndNSCards(
    1,
    1,
    "Woods Stroll Idea",
    1,
    "Villager",
    1,
    "Apple"
  ); // // * There should be 1 stacks of 1 "Woods Stroll Idea", 1 "Villager" and 1 "Apple" cards.

  await context.afterTest();
  await runWhenTestSuccessful();
});
