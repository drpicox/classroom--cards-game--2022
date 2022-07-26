package com.drpicox.game.december;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.drpicox.game.util.TestUtils;

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class Post_20221206_Counting_Test {

    @Autowired Post_20221206_Counting_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-12-06_counting", "f6d114c869ec54fc84865be776f0bcdf");
        context.beforeTest();

        // # Counting                                             // # Counting

        // ## The counters                                        // ## The counters
        context.givenThereAreNSCards(1, "Villager");              // * Given there are 1 "Villager" cards.
        context.givenThereAreNSCards(2, "Militia");               // * Given there are 2 "Militia" cards.
        context.givenThereAreNSCards(5, "Berry");                 // * Given there are 5 "Berry" cards.
        context.givenThereAreNSCards(1, "Apple");                 // * Given there are 1 "Apple" cards.
        context.reloadTheGame();                                  // * Reload the game.
        context.theCounterSShouldContainS("Remaining food", "2"); // * The counter "Remaining food" should contain "2".

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
