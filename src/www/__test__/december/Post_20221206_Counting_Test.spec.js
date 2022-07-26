import { runBeforeTestStarts, runWhenTestSuccessful } from "../util";
import { Post_20221206_Counting_Context } from "./Post_20221206_Counting_Context";

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

test("2022-12-06_counting.md", async () => {
  await runBeforeTestStarts(
    "2022-12-06_counting",
    "f6d114c869ec54fc84865be776f0bcdf"
  );

  const context = new Post_20221206_Counting_Context();
  await context.beforeTest();

  // # Counting                                                      // # Counting

  // ## The counters                                                 // ## The counters
  await context.givenThereAreNSCards(1, "Villager"); //              // * Given there are 1 "Villager" cards.
  await context.givenThereAreNSCards(2, "Militia"); //               // * Given there are 2 "Militia" cards.
  await context.givenThereAreNSCards(5, "Berry"); //                 // * Given there are 5 "Berry" cards.
  await context.givenThereAreNSCards(1, "Apple"); //                 // * Given there are 1 "Apple" cards.
  await context.reloadTheGame(); //                                  // * Reload the game.
  await context.theCounterSShouldContainS("Remaining food", "2"); // // * The counter "Remaining food" should contain "2".

  await context.afterTest();
  await runWhenTestSuccessful();
});
