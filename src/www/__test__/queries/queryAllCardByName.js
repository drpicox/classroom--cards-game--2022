import { queryHelpers, buildQueries } from "@testing-library/react";
import { verifyContainer } from "./verifyContainer";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAllCardByName = (container, cardName, ...args) =>
  verifyContainer(container) &&
  queryHelpers.queryAllByAttribute(
    `data-cardname`,
    container,
    cardName,
    ...args
  );

const getMultipleError = (c, cardName) =>
  `Found multiple elements with the attribute data-cardname="${cardName}"`;
const getMissingError = (c, cardName) =>
  `Unable to find an element with the attribute data-cardname="${cardName}"`;

const [
  queryCardByName,
  getAllCardByName,
  getCardByName,
  findAllCardByName,
  findCardByName,
] = buildQueries(queryAllCardByName, getMultipleError, getMissingError);

export {
  queryCardByName,
  queryAllCardByName,
  getCardByName,
  getAllCardByName,
  findAllCardByName,
  findCardByName,
};
