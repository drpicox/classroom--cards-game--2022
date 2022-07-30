import { combineReducers } from "redux";

export function selectAllCardIds(state) {
  return state.cards.allIds;
}

export function selectCard(state, cardId) {
  return state.cards.byId[cardId];
}

const REPLACE_CARDS = "cards/REPLACE_CARDS";
export function replaceCards(cards) {
  return { type: REPLACE_CARDS, cards };
}

export const cardsReducer = combineReducers({
  byId: cardsByIdReducer,
  allIds: cardIdsReducer,
});

function cardsByIdReducer(state = {}, action) {
  switch (action.type) {
    case REPLACE_CARDS:
      return Object.fromEntries(action.cards.map((c) => [c.id, c]));
    default:
      return state;
  }
}

function cardIdsReducer(state = [], action) {
  switch (action.type) {
    case REPLACE_CARDS:
      return action.cards.map((c) => c.id);
    default:
      return state;
  }
}
