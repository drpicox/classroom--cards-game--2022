import { applyMiddleware, createStore, combineReducers } from "redux";

import { blogReducer, blogMiddleware } from "./blog/blogSlice";
import { cardsReducer } from "./cards/cardsSlice";
import { gameMiddleware } from "./game/gameSlice";

const reducer = combineReducers({
  blog: blogReducer,
  cards: cardsReducer,
});

const middleware = [blogMiddleware, gameMiddleware];

export function createAppStore() {
  return createStore(reducer, applyMiddleware(...middleware));
}
