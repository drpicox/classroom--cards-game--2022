import { useSelector } from "react-redux";
import { makeSelectAggregateTags } from "../stack/stackSlice";

const sum = (a, b) => a + b;
const selectSumFoodTags = makeSelectAggregateTags(sum, "Food");
const selectSumEatsTags = makeSelectAggregateTags(sum, "Eats");

export function GameCounters() {
  const food = useSelector(selectSumFoodTags);
  const eats = useSelector(selectSumEatsTags);
  const remainingFood = food - eats;

  return (
    <>
      <span data-testid="counter-Remaining food">
        Remaining food: {remainingFood}
      </span>
    </>
  );
}
