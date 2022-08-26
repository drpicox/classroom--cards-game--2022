import { drag, waitForLoading } from "../../main/actions";
import { mainView } from "../../main";
import { queryAllStackDigest } from "../queries";

export async function waitForMoveCardToItsOwnStack(card) {
  var stacks = queryAllStackDigest(mainView);
  var emptyStack = stacks.find((stack) => stack.cards.length === 0);

  drag(card, emptyStack.getElement());

  await waitForLoading();
}
