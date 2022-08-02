const chalk = require("chalk");
const { reportPostError } = require("./reportPostError");

exports.verifyPostMethods = verifyPostMethods;

const companionWords = ["there", "is", "are", "has", "have"];
const companionWordRegExps = companionWords.map(
  (w) => new RegExp(`\\b${w}\\b`, `i`)
);
const needsCompanionRegExp = new RegExp(
  `\\b(${companionWords.join("|")})\\b`,
  "i"
);
const haveCompanionRegExp = /\b(given|should)\b/i;

function verifyPostMethods(post) {
  return (
    verifyPostHaveAnyMethod(post) &&
    verifyPostHaveShouldMethod(post) &&
    verifyPostCompanionMethods(post) &&
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

function verifyPostCompanionMethods(post) {
  const methodsShouldHaveCompanion = post.contextMethods.filter((m) =>
    needsCompanionRegExp.test(m.text)
  );

  const withoutCompanions = methodsShouldHaveCompanion.filter(
    (m) => !haveCompanionRegExp.test(m.text)
  );

  if (withoutCompanions.length === 0) return true;

  const withoutCompanion = withoutCompanions[0];
  const companionIndex = companionWordRegExps.findIndex((re) =>
    re.test(withoutCompanion.text)
  );
  const companion = companionWords[companionIndex];
  const includesCompanionRegExp = companionWordRegExps[companionIndex];

  const color = ({ text }) => {
    if (!includesCompanionRegExp.test(text)) return chalk.grey;
    if (haveCompanionRegExp.test(text)) return chalk.green;
    return chalk.red;
  };

  reportPostError(post, withoutCompanion.lineNumber, [
    `have instructions with the word "${companion}" but no "should" or "given". `,
    `The word "${companion}" can be confusing.`,
    `Without the "should" or "given" keywords, they can be confused with the.`,
    `- actual "${companion}" " * " instructions without "given" or "should"  : ${withoutCompanions.length}`,
    `- expected "${companion}" " * " instructions without "given" or "should": 0`,
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
