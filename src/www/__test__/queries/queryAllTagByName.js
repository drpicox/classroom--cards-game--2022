import { queryHelpers, buildQueries } from "@testing-library/react";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAllTagByName = (container, tagName, ...args) =>
  queryHelpers.queryAllByAttribute(`data-tagname`, container, tagName, ...args);

const getMultipleError = (c, tagName) =>
  `Found multiple elements with the attribute data-tagname="${tagName}"`;
const getMissingError = (c, tagName) =>
  `Unable to find an element with the attribute data-tagname="${tagName}"`;

const [
  queryTagByName,
  getAllTagByName,
  getTagByName,
  findAllTagByName,
  findTagByName,
] = buildQueries(queryAllTagByName, getMultipleError, getMissingError);

export {
  queryTagByName,
  queryAllTagByName,
  getTagByName,
  getAllTagByName,
  findAllTagByName,
  findTagByName,
};
