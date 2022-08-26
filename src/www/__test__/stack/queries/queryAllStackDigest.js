import { createQueries } from "../../util/createQueries";
import { digestStackElement } from "./digestStackElement";
import { queryAllStack } from "./queryAllStack";

// The queryAllByAttribute is a shortcut for attribute-based matchers
// You can also use document.querySelector or a combination of existing
// testing library utilities to find matching nodes for your query
const queryAll = (...args) => queryAllStack(...args).map(digestStackElement);

const getMultipleError = (c, dataKey, dataValue) =>
  `Found multiple elements with the attribute data-testid="stack"`;
const getMissingError = (c, dataKey, dataValue) =>
  `Unable to find an element with the attribute data-testid="stack"`;

export const {
  queryStackDigest,
  getAllStackDigest,
  queryAllStackDigest,
  getStackDigest,
  findAllStackDigest,
  findStackDigest,
} = createQueries("StackDigest", queryAll, getMultipleError, getMissingError);
