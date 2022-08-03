import { queryHelpers, buildQueries } from "@testing-library/react";
import { verifyContainer } from "./verifyContainer";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAllCard = (container, ...args) =>
  verifyContainer(container) &&
  queryHelpers.queryAllByAttribute(`data-testid`, container, "card", ...args);

const getMultipleError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-testid="card"`;
const getMissingError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-testid="card"`;

const [queryCard, getAllCard, getCard, findAllCard, findCard] = buildQueries(
  queryAllCard,
  getMultipleError,
  getMissingError
);

export { queryCard, queryAllCard, getCard, getAllCard, findAllCard, findCard };
