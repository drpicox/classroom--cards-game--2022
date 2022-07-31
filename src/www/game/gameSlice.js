import { backend } from "../backend";
import { replaceCards } from "../cards/cardsSlice";
import {
  hideLoaddingSpinner,
  showLoadingSpinner,
} from "../loading/loadingSlice";

const REQUEST_GAME = "blog/REQUEST_GAME";
export function requestGame() {
  return { type: REQUEST_GAME };
}

const REQUEST_END_MOON = "blog/REQUEST_END_MOON";
export function requestEndMoon() {
  return { type: REQUEST_END_MOON };
}

async function fetchGame() {
  return backend.get(`/api/v1/game`);
}

async function fetchEndMoon() {
  return backend.post(`/api/v1/game/moon`, null);
}

export const gameMiddleware = (store) => (next) => async (action) => {
  next(action);

  if (action.type === REQUEST_GAME) {
    store.dispatch(showLoadingSpinner());
    const game = await fetchGame();
    store.dispatch(replaceCards(game.cards));
    store.dispatch(hideLoaddingSpinner());
  }

  if (action.type === REQUEST_END_MOON) {
    store.dispatch(showLoadingSpinner());
    const game = await fetchEndMoon();
    store.dispatch(replaceCards(game.cards));
    store.dispatch(hideLoaddingSpinner());
  }
};
