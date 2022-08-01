import { queryHelpers, buildQueries } from "@testing-library/react";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAllByData = (container, key, ...args) =>
  queryHelpers.queryAllByAttribute(`data-${key}`, container, ...args);

const getMultipleError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-${dataKey}="${dataValue}"`;
const getMissingError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-${dataKey}="${dataValue}"`;

const [queryByData, getAllByData, getByData, findAllByData, findByData] =
  buildQueries(queryAllByData, getMultipleError, getMissingError);

export {
  queryByData,
  queryAllByData,
  getByData,
  getAllByData,
  findAllByData,
  findByData,
};
