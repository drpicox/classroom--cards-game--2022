const path = require("node:path");
const { writeFile } = require("node:fs/promises");
const { join } = require("./join");

async function writeJavaContextFile(post) {
  const contextPath = path.join(
    "src",
    "test",
    "java",
    "com",
    "drpicox",
    "game",
    "contexts",
    post.contextName + ".java"
  );

  const contextContent = makeContextContent(post);

  try {
    await writeFile(contextPath, contextContent, { flag: "wx" });
    return true;
  } catch (e) {}

  return false;
}

function makeContextContent(post) {
  let contextContent = "";

  contextContent += makeContextHeader(post);
  contextContent += makeContextBody(post);
  contextContent += makeContextFooter();

  return contextContent;
}

function makeContextHeader(post) {
  return join(
    `package com.drpicox.game.contexts;`,
    ``,
    `import org.springframework.stereotype.Component;`,
    `import static com.google.common.truth.Truth.assertThat;`,
    `import static com.google.common.truth.Truth8.assertThat;`,
    `import com.drpicox.game.fixtures.FrontendSimulator;`,
    ``,
    `@Component`,
    `public class ${post.contextName} {`,
    ``,
    `    private final FrontendSimulator frontendSimulator;`,
    ``,
    `    ${post.contextName}(FrontendSimulator frontendSimulator) {`,
    `        this.frontendSimulator = frontendSimulator;`,
    `    }`,
    ``
  );
}

function makeContextBody(post) {
  return join(...post.contextMethods.flatMap(makeContextMethod));
}

function makeContextMethod({ name, arguments: args, text }) {
  const formalArguments = args.map(({ name, type }) => `${type} ${name}`);
  const methodSignature = `${name}(${formalArguments.join(", ")})`;

  return [
    `    public void ${methodSignature} {`,
    `        // example: ${text}`,
    ...args.map(({ name, value }) => `        // ${name} = ${value}`),
    args.length && ``,
    args.some(({ name }) => name === "expected") && [
      `        var actual = "";`,
      `        assertThat(actual).isEqualTo(expected);`,
      ``,
    ],
    `        throw new UnsupportedOperationException("The method ${methodSignature} is not implemented yet.");`,
    `    }`,
    ``,
  ];
}

function makeContextFooter() {
  return join(`}`);
}

exports.writeJavaContextFile = writeJavaContextFile;
