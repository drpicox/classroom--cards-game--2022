import { drag, waitForLoading } from "../../main/actions";

export async function waitForMoveCardOnTopOf(
  topCardElement,
  bottomCardElement,
) {
  drag(topCardElement, bottomCardElement);
  await waitForLoading();
}
