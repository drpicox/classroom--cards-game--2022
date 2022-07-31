package com.drpicox.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.drpicox.game.fixtures.Fixtures;

// !!! IMPORTANT !!!
// This test file is AUTOGENERATED by yarn create-tests
// If you need to update it, run yarn create-tests
// DO NOT MODIFY manually. Keep running yarn create-tests instead,
// while editing your posts.

@SpringBootTest
@AutoConfigureMockMvc
public class Post_20220719_VillagersEatFood_Test {

    @Autowired Post_20220719_VillagersEatFood_Context context;
    @Autowired Fixtures fixtures;

    @Test public void testPost() {
        fixtures.runBeforeTestStarts("2022-07-19_villagers_eat_food", "bc155c52fa090491dbd1ac36dbacf0bb");

        // # Villagers Eat Food
        // ## Berries are food
        context.givenWeHaveEnteredIntoANewGame();
        context.theSCardHasNInSTag("berry", 1, "food");
        context.theSCardHasNInSTag("villager", 1, "eats");
        // ## Moons
        context.endTheCurrentMoon();
        context.thereShouldBeNCards(2);
        context.thereShouldBeNoSCard("berry");
        context.thereShouldBeNSCard(1, "villager");
        context.thereShouldBeNSCard(1, "bush");
        // ## Starving
        context.endTheCurrentMoon();
        context.thereShouldBeNCards(2);
        context.thereShouldBeNoSCard("berry");
        context.thereShouldBeNoSCard("villager");
        context.thereShouldBeNSCard(1, "bush");
        context.thereShouldBeNSCard(1, "corpse");

        fixtures.runWhenTestSuccessful();
    }

}