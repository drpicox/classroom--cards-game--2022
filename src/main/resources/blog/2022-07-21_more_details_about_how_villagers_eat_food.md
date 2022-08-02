---
writer: drpicox
---
# More Details About How Villagers Eat Food

We have seen that we have villagers, and we have berries.
It turns out that villagers need food to survive. If not, they die.

Let's see how it goes.

## More kinds of villagers

Villagers is the simplest kind of Villager card that you can
have, but there are more. And each one requires its amount of
food. So:

 * Given there are 2 "villager", 2 "militia", and 2 "trader" cards.
 * The "villager" card should have 1 in "eats" tag.
 * The "militia" card should have 2 in "eats" tag.
 * The "trader" card should have 3 in "eats" tag.
 * The sum of all "eats" tags value should be 12.

## More kinds of food

And there are also different kinds of food cards:

 * Given there are 2 "berry", 2 "apple", and 2 "fruit salad" cards.
 * The "berry" card should have 1 in "food" tag.
 * The "apple" card should have 2 in "food" tag.
 * The "fruit salad" card should have 3 in "food" tag.
 * The sum of all "food" tags value should be 12.

## Eating

Eating works as expected, in this case, all villagers eats all available food:

 * End the current moon.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 0.

## Too much food

If there is too much food, villagers will eat first the foods with the biggest tags.

 * Given there are 2 "berry", 2 "apple", and 3 "fruit salad" cards.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 15.

In this example there is an excess of three points of food, but:

 * End the current moon.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 2.

What happened? It turns out that there the last apple was only half eaten, and
all the apple has ruined. So, there is only left two berry cards:

 * There should be 2 "berry" cards.
 * There should be 0 "apple" cards.

## Too few food

And the same goes when there is not enough food. The ones that survive are the ones
that require less food. So:

 * Given there are 0 "berry", 2 "apple", and 3 "fruit salad" cards.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 9.

In this example there is an excess of three points of food, but:

 * End the current moon.
 * The sum of all "eats" tags value should be 2.
 * The sum of all "food" tags value should be 0.

And exactly, the only survivals are the two villagers:

 * There should be 2 "villager" cards.
 * There should be 0 "militia" cards.
 * There should be 0 "trader" cards.
