---
writer: drpicox
coder: nobody
---
# More Details About How Villagers Eat Food

We have seen that we have villagers, and we have berries.
It turns out that villagers need food to survive. If not, they die.

Let's see how it goes.

## More kinds of villagers

Villagers is the simplest kind of Villager card that you can
have, but there are more. And each one requires its amount of
food. So:

 * Given there are 2 "Villager", 2 "Militia", and 2 "Trader" cards.
 * The "Villager" card should have 1 in "eats" tag.
 * The "Militia" card should have 2 in "eats" tag.
 * The "Trader" card should have 3 in "eats" tag.
 * The sum of all "eats" tags value should be 12.

## More kinds of food

And there are also different kinds of food cards:

 * Given there are 2 "Berry", 2 "Apple", and 2 "Fruit salad" cards.
 * The "Berry" card should have 1 in "food" tag.
 * The "Apple" card should have 2 in "food" tag.
 * The "Fruit salad" card should have 3 in "food" tag.
 * The sum of all "food" tags value should be 12.

## Eating

Eating works as expected, in this case, all villagers eats all available food:

 * End the current moon.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 0.

## Too much food

If there is too much food, villagers will eat first the foods with the biggest tags.

 * Given there are 2 "Berry", 2 "Apple", and 3 "Fruit salad" cards.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 15.

In this example there is an excess of three points of food, but:

 * End the current moon.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 2.

What happened? It turns out that there the last apple was only half eaten, and
all the apple has ruined. So, there is only left two Berry cards:

 * There should be 2 "Berry" cards.
 * There should be 0 "Apple" cards.

## Too few food

And the same goes when there is not enough food. The ones that survive are the ones
that require less food. So:

 * Given there are 3 "Berry", 0 "Apple", and 0 "Fruit salad" cards.
 * The sum of all "eats" tags value should be 12.
 * The sum of all "food" tags value should be 3.

In this example everyone dies except the two villagers. The militia does not
survive because cannot eat only one Berry. The Berry is kept for the future.

 * End the current moon.
 * The sum of all "eats" tags value should be 2.
 * The sum of all "food" tags value should be 1.

And exactly, the only survivals are the two villagers:

 * There should be 1 "Berry" cards.
 * There should be 2 "Villager" cards.
 * There should be 0 "Militia" cards.
 * There should be 0 "Trader" cards.
