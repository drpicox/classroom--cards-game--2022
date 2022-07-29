const chalk = require("chalk");
const { writeJavaTestFile } = require("./writeJavaTestFile");
const { writeJavaContextFile } = require("./writeJavaContextFile");
const { verifyPost } = require("./verifyPost");
const { readBlogPost } = require("./readBlogPost");
const { writeJsTestFile } = require("./writeJsTestFile");
const { writeJsContextFile } = require("./writeJsContextFile");

async function update(filePath) {
  const post = await readBlogPost(filePath);

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

  console.log(
    `${action} ${filePath}` +
      `${testWritten ? ` => ${post.testName}` : ""}` +
      `${contextWritten ? ` => ${post.contextName}` : ""}`
  );

  return post;
}

exports.update = update;
