import { backend } from "../backend";
import { replaceCards } from "../cards/cardsSlice";

const REQUEST_GAME = "blog/REQUEST_GAME";
export function requestGame() {
  return { type: REQUEST_GAME };
}

async function fetchGame(postId) {
  return backend.get(`/api/v1/game`);
}

export const gameMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REQUEST_GAME) {
    const game = await fetchGame();
    store.dispatch(replaceCards(game.cards));
  }
};
