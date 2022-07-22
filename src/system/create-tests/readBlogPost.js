const { readFile } = require("node:fs/promises");
const { MethodStepParser } = require("./MethodStepParser");
const { makeJavaName } = require("./makeJavaName");
const { watchPath } = require("./watchPath");
const md5 = require("md5");

async function readBlogPost(path) {
  const id = path.slice(watchPath.length + 1).split(".")[0];
  const javaName = makeJavaName(id);
  const testName = `Post_${javaName}_Test`;
  const contextName = `Context_${javaName}`;

  const postContent = await readFile(path, "utf8");
  const postLines = postContent.split("\n");

  const testCalls = [];
  const contextMethods = [];

  const title = findPostTitle(postLines);
  const hasCoder = findHasCoder(postLines);

  postLines.forEach((line) => parseLine(line, testCalls, contextMethods));

  return {
    id,
    title,
    path,
    hasCoder,
    testName,
    testCalls,
    contextName,
    contextMethods,
    md5: md5(postContent),
    failed: false,
  };
}
exports.readBlogPost = readBlogPost;

function parseLine(postLine, testCalls, contextMethods) {
  const isCommand = postLine.startsWith(" * ");
  if (!isCommand) return;

  const method = new MethodStepParser(postLine).parse();
  testCalls.push(method);

  if (contextMethods.every((m) => m.name !== method.name))
    contextMethods.push(method);
}

function findPostTitle(lines) {
  const line = lines.find((l) => l.startsWith("# "));
  if (!line) return "It should have a title, but it does not!";

  return line;
}

function findHasCoder(lines) {
  for (
    let index = 1;
    index < lines.length && !lines[index].startsWith("---");
    index += 1
  ) {
    if (lines[index].match(/^\s*coder\s*:/)) return true;
  }

  return false;
}
