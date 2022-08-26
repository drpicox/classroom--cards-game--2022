import { applyMiddleware, createStore, combineReducers } from "redux";

import { blogReducer, blogMiddleware } from "./blog/blogSlice";
import { cardReducer, cardMiddleware } from "./card/cardSlice";
import { ideaReducer, ideaMiddleware } from "./idea/ideaSlice";
import { stackReducer, stackMiddleware } from "./stack/stackSlice";
import { gameMiddleware } from "./game/gameSlice";
import { loadingReducer } from "./loading/loadingSlice";

const reducer = combineReducers({
  blog: blogReducer,
  card: cardReducer,
  idea: ideaReducer,
  stack: stackReducer,
  loading: loadingReducer,
});

const middleware = [
  blogMiddleware,
  cardMiddleware,
  gameMiddleware,
  ideaMiddleware,
  stackMiddleware,
];

export function createAppStore() {
  return createStore(reducer, applyMiddleware(...middleware));
}
