const chalk = require("chalk");

function reportPostError(post, line, messages) {
  const error = [
    chalk.red.inverse(`ERROR ${post.path}${line ? ` (at line ${line})` : ""}`),
  ];

  if (line) {
    error.push(getLineContext(post, line).map((f) => chalk.gray(f)));
  }

  if (messages && messages.length) {
    messages[0] = `The post ${post.id}.md ${messages[0]}`;
    let message = messages
      .flat(Infinity)
      .map((x) => (x.endsWith(" ") ? x : `${x}\n`))
      .join("");
    if (message.endsWith("\n")) message = message.slice(0, message.length - 1);
    error.push(chalk.red(message));
  }

  console.error([error].flat(Infinity).join("\n"));
}

exports.reportPostError = reportPostError;

function getLineContext(post, line) {
  const range = 2;
  const index = line - 1;
  const { lines } = post;
  const startIndex = Math.max(0, index - range);
  const endIndex = Math.min(lines.length - 1, index + range);

  const result = [];

  // if (startIndex !== 0) result.push(`   [...]  ...`);
  for (let i = startIndex; i <= endIndex; i += 1) {
    let context = `${i === index ? ">>" : "  "} [${leftPad(i + 1)}]  ${
      lines[i]
    }`;
    if (i < index) context = chalk.green(context);
    if (i === index) context = chalk.red(context);
    result.push(context);
  }
  // if (endIndex !== lines.length - 1) result.push(`   [...]  ...`);
  return result;
}

function leftPad(number) {
  let text = `${number}`;
  while (text.length < 3) text = ` ${text}`;
  return text;
}
