const { isAuthorValid, getAuthorUsernames } = require("./authors");
const { reportPostError } = require("./reportPostError");

const validKeys = ["writer", "coder"];
const mandatoryKeys = ["writer"];
const keysWithAuthors = ["writer", "coder"];

function verifyPostFrontmatterSemantics(post) {
  return (
    verifyPostFrontmatterKeys(post) &&
    verifyPostFrontmatterMandatoryKeys(post) &&
    verifyPostAuthors(post)
  );
}

exports.verifyPostFrontmatterSemantics = verifyPostFrontmatterSemantics;

function verifyPostFrontmatterKeys(post) {
  const actualKeys = Object.keys(post.frontmatter.values);
  const wrongKey = actualKeys.find((key) => !validKeys.includes(key));
  if (!wrongKey) return true;

  const wrongLine = post.frontmatter.valuesLines[wrongKey];
  reportPostError(post, wrongLine, [
    `fromatter key "${wrongKey}" is not valid.`,
    `- expected keys: "${validKeys.join('", "')}"`,
    `- actual key   : "${wrongKey}"`,
    `Please, fix the spelling or remove this key from the frontmatter.`,
  ]);

  return false;
}

function verifyPostFrontmatterMandatoryKeys(post) {
  const actualKeys = Object.keys(post.frontmatter.values);
  const missingKey = mandatoryKeys.find((key) => !actualKeys.includes(key));
  if (!missingKey) return true;

  reportPostError(post, post.frontmatter.closingIndex + 1, [
    `fromatter key "${missingKey}" is required but it is missing.`,
    `- mandatory keys: "${mandatoryKeys.join('", "')}"`,
    `- missing key   : "${missingKey}"`,
    `Please, add the missing key to the frontmatter.`,
  ]);

  return false;
}

function verifyPostAuthors(post) {
  const actualKeys = Object.keys(post.frontmatter.values);
  const existingKeysWithAuthors = actualKeys.filter((key) =>
    keysWithAuthors.includes(key)
  );
  const wrongKey = existingKeysWithAuthors.find(
    (key) => !isAuthorValid(post.frontmatter.values[key])
  );
  if (!wrongKey) return true;

  const wrongAuthor = post.frontmatter.values[wrongKey];
  const wrongLine = post.frontmatter.valuesLines[wrongKey];
  reportPostError(post, wrongLine, [
    `frontmatter key "${wrongKey}" username author name "${wrongAuthor}" was not found at authors.properties.`,
    `- valid author names    : "${getAuthorUsernames().join('", "')}"`,
    `- actual author name    : "${wrongAuthor}"`,
    `- actual frontmatter key: "${wrongKey}"`,
    `Please, verify that the name is correct, and `,
    `if you think that the author is missing, add it to authors.properties, `,
    `or make sure that it is in the right "username=Author Name" line format.`,
  ]);

  return false;
}
