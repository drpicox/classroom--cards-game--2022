package com.drpicox.game;

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
public class Post_20220721_MoreDetailsAboutHowVillagersEatFood_Test {

    @Autowired Post_20220721_MoreDetailsAboutHowVillagersEatFood_Context context;
    @Autowired TestUtils testUtils;

    @Test public void testPost() throws Throwable {
        testUtils.runBeforeTestStarts("2022-07-21_more_details_about_how_villagers_eat_food", "ca6db820f29e4946660368140a798634");
        context.beforeTest();

        // # More Details About How Villagers Eat Food                  // # More Details About How Villagers Eat Food

        // ## More kinds of villagers                                   // ## More kinds of villagers
        context.givenThereAreNSAndNSCards(1, "Villager", 1, "Militia"); // * Given there are 1 "Villager" and 1 "Militia" cards.
        context.theSCardShouldHaveNInSTag("Villager", 1, "Eats");       // * The "Villager" card should have 1 in "Eats" tag.
        context.theSCardShouldHaveNInSTag("Militia", 2, "Eats");        // * The "Militia" card should have 2 in "Eats" tag.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);              // * The sum of all "Eats" tags value should be 3.

        // ## More kinds of food                                        // ## More kinds of food
        context.givenThereAreNSAndNSCards(1, "Berry", 1, "Apple");      // * Given there are 1 "Berry" and 1 "Apple" cards.
        context.theSCardShouldHaveNInSTag("Berry", 1, "Food");          // * The "Berry" card should have 1 in "Food" tag.
        context.theSCardShouldHaveNInSTag("Apple", 2, "Food");          // * The "Apple" card should have 2 in "Food" tag.
        context.theSumOfAllSTagsValueShouldBeN("Food", 3);              // * The sum of all "Food" tags value should be 3.

        // ## Eating                                                    // ## Eating
        context.endTheCurrentMoon();                                    // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);              // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 0);              // * The sum of all "Food" tags value should be 0.

        // ## Too much food                                             // ## Too much food
        context.givenThereAreNSAndNSCards(4, "Berry", 0, "Apple");      // * Given there are 4 "Berry" and 0 "Apple" cards.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);              // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 4);              // * The sum of all "Food" tags value should be 4.
        context.endTheCurrentMoon();                                    // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);              // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 1);              // * The sum of all "Food" tags value should be 1.
        context.thereShouldBeNSCards(1, "Berry");                       // * There should be 1 "Berry" cards.
        context.givenThereAreNSAndNSCards(0, "Berry", 2, "Apple");      // * Given there are 0 "Berry" and 2 "Apple" cards.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);              // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 4);              // * The sum of all "Food" tags value should be 4.
        context.endTheCurrentMoon();                                    // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 3);              // * The sum of all "Eats" tags value should be 3.
        context.theSumOfAllSTagsValueShouldBeN("Food", 0);              // * The sum of all "Food" tags value should be 0.
        context.thereShouldBeNSCards(0, "Apple");                       // * There should be 0 "Apple" cards.

        // ## Too few food                                              // ## Too few food
        context.givenThereAreNSAndNSCards(0, "Villager", 2, "Militia"); // * Given there are 0 "Villager" and 2 "Militia" cards.
        context.givenThereAreNSAndNSCards(3, "Berry", 0, "Apple");      // * Given there are 3 "Berry" and 0 "Apple" cards.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 4);              // * The sum of all "Eats" tags value should be 4.
        context.theSumOfAllSTagsValueShouldBeN("Food", 3);              // * The sum of all "Food" tags value should be 3.
        context.endTheCurrentMoon();                                    // * End the current moon.
        context.theSumOfAllSTagsValueShouldBeN("Eats", 2);              // * The sum of all "Eats" tags value should be 2.
        context.theSumOfAllSTagsValueShouldBeN("Food", 1);              // * The sum of all "Food" tags value should be 1.
        context.thereShouldBeNSCards(1, "Militia");                     // * There should be 1 "Militia" cards.
        context.thereShouldBeNSCards(1, "Berry");                       // * There should be 1 "Berry" cards.

        context.afterTest();
        testUtils.runWhenTestSuccessful();
    }

}
