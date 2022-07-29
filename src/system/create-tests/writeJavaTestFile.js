const path = require("node:path");
const { writeFile } = require("node:fs/promises");
const { join } = require("./join");

async function writeJavaTestFile(post) {
  const testContent = await makeTestContent(post);

  await writeFile(
    path.join(
      "src",
      "test",
      "java",
      "com",
      "drpicox",
      "game",
      post.testName + ".java"
    ),
    testContent
  );

  return true;
}
exports.writeJavaTestFile = writeJavaTestFile;

async function makeTestContent(post) {
  let testContent = ``;
  testContent += makeTestHeader(post);
  testContent += makeTestBody(post);
  testContent += makeTestFooter();

  return testContent;
}

function makeTestHeader(post) {
  const idValue = JSON.stringify(post.id);
  const md5Value = JSON.stringify(post.md5);

  return join(
    `package com.drpicox.game;`,
    ``,
    `import org.junit.jupiter.api.Test;`,
    `import org.springframework.beans.factory.annotation.Autowired;`,
    `import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;`,
    `import org.springframework.boot.test.context.SpringBootTest;`,
    ``,
    `import com.drpicox.game.contexts.${post.contextName};`,
    `import com.drpicox.game.fixtures.Fixtures;`,
    ``,
    `// !!! IMPORTANT !!!`,
    `// This test file is AUTOGENERATED by yarn create-tests`,
    `// If you need to update it, run yarn create-tests`,
    `// DO NOT MODIFY manually. Keep running yarn create-tests instead,`,
    `// while editing your posts.`,
    ``,
    `@SpringBootTest`,
    `@AutoConfigureMockMvc`,
    `public class ${post.testName} {`,
    ``,
    `    @Autowired ${post.contextName} context;`,
    `    @Autowired Fixtures fixtures;`,
    ``,
    `    @Test public void testPost() {`,
    `        fixtures.runBeforeTestStarts(${idValue}, ${md5Value});`,
    ``
  );
}

function makeTestFooter() {
  return join(
    ``,
    `        fixtures.runWhenTestSuccessful();`,
    `    }`,
    ``,
    `}`
  );
}

function makeTestBody(post) {
  return join(...post.testCalls.map(makeTestCall));
}

function makeTestCall(call) {
  if (!call.name) {
    return `        // ${call.text}`;
  }

  const methodCall = `context.${call.name}(${call.arguments
    .map((a) => a.value)
    .join(", ")});`;

  return `        ${methodCall}`;
}
