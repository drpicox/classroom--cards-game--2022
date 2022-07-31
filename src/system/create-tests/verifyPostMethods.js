const chalk = require("chalk");
const { reportPostError } = require("./reportPostError");

exports.verifyPostMethods = verifyPostMethods;

function verifyPostMethods(post) {
  return (
    verifyPostHaveAnyMethod(post) &&
    verifyPostHaveShouldMethod(post) &&
    verifyPostThereMethods(post) &&
    verifyPostNoInvalidCalls(post)
  );
}

function verifyPostHaveAnyMethod(post) {
  if (post.contextMethods.length > 0) return true;

  const startLines = [...new Set(post.lines.map((l) => l.slice(0, 3)))];
  reportPostError(post, post.lines.length + 1, [
    `does not have any executable instruction by tests.`,
    `- post lines start with        : "${startLines.join('", "')}"`,
    `- expected some line start with: " * "`,
    `Please, fix executable lines if they do not begin with " * " or `,
    `add test executable lines.`,
  ]);

  return false;
}

function verifyPostHaveShouldMethod(post) {
  const someMethodsWithShould = post.contextMethods.some((m) =>
    m.name.toLowerCase().includes("should")
  );
  if (someMethodsWithShould) return true;

  reportPostError(post, post.contextMethods.at(-1).lineNumber, [
    `does not have any executable instruction by tests that cointains "should". `,
    `It is very important to follow naming conventions and having at least `,
    `one executable instruction that tests that the outcome is the expected. `,
    `Without the "should" keyword they can be confused with "givens".`,
    `- actual " * " instructions with should: 0`,
    `- expected " * " minimum instructions with should: 1`,
    listMethods(post),
    `Please, fix executable lines that assert for outcomes adding should in the sentence, `,
    `or if there is none, please, add asserts with should for outcomes.`,
  ]);

  return false;
}

function verifyPostThereMethods(post) {
  const namesWithThere = post.contextMethods
    .map((m) => m.name.toLowerCase())
    .filter((n) => n.includes("there"));

  const subsetWithoutSpecify = namesWithThere.filter(
    (n) => !n.includes("given") && !n.includes("should")
  );

  if (subsetWithoutSpecify.length === 0) return true;

  const color = ({ name }) => {
    name = name.toLowerCase();
    if (!name.includes("there")) return chalk.grey;
    if (name.includes("given")) return chalk.green;
    if (name.includes("should")) return chalk.green;
    return chalk.red;
  };

  reportPostError(post, post.contextMethods.at(-1).lineNumber, [
    `have instructions with the word "there" but no "should" or "given". `,
    `The word "there" can be confusing.`,
    `one executable instruction that tests that the outcome is the expected. `,
    `Without the "should" keyword they can be confused with "givens".`,
    `- actual "there" " * " instructions without "given" or "should"  : ${subsetWithoutSpecify.length}`,
    `- expected "there" " * " instructions without "given" or "should": 0`,
    listMethods(post, color),
    `Please, `,
    `add "given" if they are setting up the environment, or `,
    `add "should" if they are checking the results.`,
  ]);

  return false;
}

function verifyPostNoInvalidCalls(post) {
  const callWithoutMethod = post.testCalls.find(
    (call) =>
      !call.isComment &&
      !post.contextMethods.every(
        (method) =>
          method.name !== call.name || areArgumentsTypesEqual(method, call)
      )
  );

  if (!callWithoutMethod) return true;

  const firstOccurrence = post.testCalls.find(
    (c) => c.name === callWithoutMethod.name
  );
  const color = (other) => {
    if (other.name !== firstOccurrence.name) return chalk.grey;
    if (areArgumentsTypesEqual(other, firstOccurrence)) return chalk.green;
    return chalk.red;
  };

  reportPostError(post, callWithoutMethod.lineNumber, [
    `have no colliding executable instructions. `,
    `In this case, there is at least two executable instructions that `,
    `generate the same name `,
    `but have different arguments.`,
    listCalls(post, color),
    `Please, `,
    `verify that it has no "s" or "n" words, or modify `,
    `the writting to generate a different name.`,
  ]);

  return false;
}

function areArgumentsTypesEqual(a, b) {
  return (
    a.arguments &&
    a.arguments.length === b.arguments.length &&
    a.arguments.every(
      (argument, index) => b.arguments[index].type === argument.type
    )
  );
}

function listMethods(post, color = (method) => chalk.grey) {
  return [
    `Methods found:`,
    post.contextMethods.map(
      (method) =>
        `${color(method)`${method.text} ${chalk.dim(
          `(${countCalls(post, method)} calls)`
        )}`}`
    ),
  ];
}

function listCalls(post, color = (method) => chalk.grey) {
  return [
    `Calls found:`,
    post.testCalls
      .filter((c) => !c.isComment)
      .map(
        (call) =>
          `${color(call)`${call.text} ${chalk.dim(
            `(line ${call.lineNumber})`
          )}`}`
      ),
  ];
}

function countCalls(post, method) {
  return post.testCalls.filter((call) => call.name === method.name).length;
}
