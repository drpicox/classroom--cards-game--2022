const path = require("node:path");
const { outputFile } = require("fs-extra");
const { join } = require("./join");
const { findClosestMethod } = require("./posts");

async function writeJavaContextFile(post) {
  const contextPath = path.join(
    "src",
    "test",
    "java",
    "com",
    "drpicox",
    "game",
    ...post.subPath,
    post.contextName + ".java",
  );

  const contextContent = makeContextContent(post);

  try {
    await outputFile(contextPath, contextContent, { flag: "wx" });
    return true;
  } catch (e) {
    if (e.code !== "EEXIST") console.error(e);
  }

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
    `package com.drpicox.game${post.subPackage};`,
    ``,
    `import org.springframework.stereotype.Component;`,
    `import static com.google.common.truth.Truth.assertThat;`,
    `import static com.google.common.truth.Truth8.assertThat;`,
    `import com.drpicox.game.util.FrontendSimulator;`,
    `import com.drpicox.game.game.GivenGameService;`,
    ``,
    `@Component`,
    `public class ${post.contextName} {`,
    ``,
    `    private final FrontendSimulator frontendSimulator;`,
    `    private final GivenGameService givenGameService;`,
    ``,
    `    ${post.contextName}(FrontendSimulator frontendSimulator, GivenGameService givenGameService) {`,
    `        this.frontendSimulator = frontendSimulator;`,
    `        this.givenGameService = givenGameService;`,
    `    }`,
    ``,
    `    public void beforeTest() throws Throwable {`,
    `        // Do your setup here, and change this contents, if necessary`,
    `        givenGameService.givenGame("default");`,
    `        throw new UnsupportedOperationException("Please, review the implementation of beforeTest() and remove this exception when it is correct.");`,
    `    }`,
  );
}

function makeContextBody(post) {
  return join(
    ...post.contextMethods.flatMap((m) => makeContextMethod(post, m)),
  );
}

function makeContextMethod(post, method) {
  const { name, arguments: args, text } = method;
  const formalArguments = args.map(({ name, type }) => `${type} ${name}`);
  const methodSignature = `${name}(${formalArguments.join(", ")})`;
  const closest = findClosestMethod(post, method);

  return [
    ``,
    `    public void ${methodSignature} {`,
    `        // text: ${text}`,
    `        // code: this.${name}(${args.map((a) => a.value).join(", ")})`,
    // ...args.map(({ name, value }) => `        // ${name} = ${value}`),
    closest?.distance < 4 &&
      `        // hint: ${closest.post.contextName}.${closest.name}`,
    args.length && ``,
    args.some(({ name }) => name === "expected") && [
      `        var actual = expected; // FIXME`,
      `        assertThat(actual).isEqualTo(expected);`,
      ``,
    ],
    `        throw new UnsupportedOperationException("The method ${methodSignature} is not implemented yet.");`,
    `    }`,
  ];
}

function makeContextFooter() {
  return join(
    ``,
    `    public void afterTest() {`,
    `        // Do your teardown here, if necessary`,
    `    }`,
    `}`,
  );
}

exports.writeJavaContextFile = writeJavaContextFile;
