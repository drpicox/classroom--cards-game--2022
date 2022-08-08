const chalk = require("chalk");
const levenshtein = require("fast-levenshtein");
const { writeJavaTestFile } = require("./writeJavaTestFile");
const { writeJavaContextFile } = require("./writeJavaContextFile");
const { verifyPost } = require("./verifyPost");
const { readBlogPost } = require("./readBlogPost");
const { writeJsTestFile } = require("./writeJsTestFile");
const { writeJsContextFile } = require("./writeJsContextFile");
const { join } = require("./join");

async function update(filePath) {
  const post = await readBlogPost(filePath);

  if (post.frontmatter.values.debug === "true") debugPost(post);

  post.failed = !verifyPost(post);
  if (post.failed) return post;

  let testWritten = false;
  let contextWritten = false;
  if (post.hasCoder) {
    testWritten = await writeJavaTestFile(post);
    contextWritten = await writeJavaContextFile(post);
    await writeJsTestFile(post);
    await writeJsContextFile(post);
  }

  let action = chalk.inverse(" Updated ");
  if (!post.hasCoder) action = chalk.yellowBright.inverse(" Skipped ");
  if (contextWritten) action = chalk.greenBright.inverse(" Created ");

  const stats = `(${post.contextMethods.length} methods, ${post.testCalls.length} calls)`;

  console.log(
    `[${new Date().toLocaleTimeString("ca")}] ${action} ${filePath} ${stats}` +
      `${testWritten ? ` => ${post.testName}` : ""}` +
      `${contextWritten ? ` => ${post.contextName}` : ""}` +
      `${post.hasCoder ? "" : " (no coder) "}`,
  );

  return post;
}

exports.update = update;

function debugPost(post) {
  const sortedMethods = [];
  let unusedMethods = post.contextMethods.slice();
  let current = unusedMethods.shift();
  current.distance = 0;
  sortedMethods.push(current);
  while (unusedMethods.length) {
    let candidate = unusedMethods[0];
    let candidateDistance = levenshtein.get(current.name, candidate.name);
    for (let i = 1; i < unusedMethods.length; i += 1) {
      let newCandidate = unusedMethods[i];
      let newDistance = levenshtein.get(current.name, newCandidate.name);
      if (newDistance < candidateDistance) {
        candidate = newCandidate;
        candidateDistance = newDistance;
      }
    }
    unusedMethods = unusedMethods.filter((m) => m !== candidate);
    sortedMethods.push(candidate);
    candidate.distance = candidateDistance;
    current = candidate;
  }

  console.log(
    join(
      `STATS FOR ${post.id}.md`,
      `Total calls: ${post.testCalls.length}`,
      `Total methods: ${post.contextMethods.length}`,
      `Methos sorted by similitude:`,
      sortedMethods.map((method) => {
        const line = `${method.text} (∆${
          method.distance
        }, lines: ${post.testCalls
          .filter((c) => c.name === method.name)
          .map((c) => c.lineNumber)})`;

        if (1 <= method.distance && method.distance <= 3)
          return chalk.yellow(line);
        return line;
      }),
    ),
  );
}
