import { applyMiddleware, createStore, combineReducers } from "redux";

import { blogReducer, blogMiddleware } from "./blog/blogSlice";

const reducer = combineReducers({ blog: blogReducer });

export function createAppStore() {
  return createStore(reducer, applyMiddleware(blogMiddleware));
}
