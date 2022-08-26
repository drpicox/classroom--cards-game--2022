import { drag, waitForLoading } from "../../main/actions";
import { getByTestId } from "@testing-library/react";
import { mainView } from "../../main";

export async function waitForDiscardCard(card) {
  var discard = getByTestId(mainView, "discard");
  drag(card, discard);
  await waitForLoading();
}
