import chalk from "chalk";

export function throwBackendSimulatorError(backendSimulator, ...messages) {
  const firstJumpIndex = messages.findIndex((m) => !m.endsWith(" "));
  const message = messages.slice(0, firstJumpIndex + 1).join("");
  const additional = messages.slice(firstJumpIndex + 1);

  const output = magentize(
    please(
      expectize(
        join(
          `${chalk.bold.magenta.inverse(" BACKEND INTERACTION ERROR ")} `,
          `${chalk.bold.magenta(`${backendSimulator.postId}.md`)}`,
          chalk.magenta(message),
          explainSituation(backendSimulator),
          additional.length && join(``, additional),
          `\n`
        )
      )
    )
  );

  process.stderr.write(chalk.gray(output));

  throw new Error(
    `Error: ${backendSimulator.postId} backend interactions failed. ${message}`
  );
}

function expectize(output) {
  return output
    .split("\n")
    .map((line) => {
      const match = line.match(/^-(.+:).+$/);
      if (!match) return line;
      const [, key] = match;
      if (key.includes("expect")) return chalk.green(line);
      if (key.includes("actual")) return chalk.red(line);
      return line;
    })
    .join("\n");
}

function magentize(output) {
  return output
    .split("\n")
    .map((line) => {
      const match = line.match(/^([A-Z-].+:)(.*)$/);
      if (!match) return line;
      return chalk.magenta(match[1]) + match[2];
    })
    .join("\n");
}

function please(output) {
  return output
    .split("\n")
    .map((line) => {
      if (!line.startsWith("Please")) return line;
      return chalk.magenta(line);
    })
    .join("\n");
}

function explainSituation(backendSimulator) {
  let result = [
    `\nThe interaction simulation between backend and frontend is recorded at:`,
  ];
  result.push(chalk.black.underline(backendSimulator.filePath));
  result.push(`\nThe recorded interactions are:`);

  for (let i = 0; i < backendSimulator.nextInteractionIndex; i += 1)
    result.push(
      chalk.green(`   ${explainLineInteraction(backendSimulator, i)}`)
    );

  if (
    backendSimulator.nextInteractionIndex < backendSimulator.interactions.length
  )
    result.push(
      chalk.red(
        `** ${explainLineInteraction(
          backendSimulator,
          backendSimulator.nextInteractionIndex
        )}`
      )
    );

  for (
    let i = backendSimulator.nextInteractionIndex + 1;
    i < backendSimulator.interactions.length;
    i += 1
  )
    result.push(
      chalk.grey(`   ${explainLineInteraction(backendSimulator, i)}`)
    );

  if (
    backendSimulator.nextInteractionIndex < backendSimulator.interactions.length
  ) {
    result.push(`\nThe next expected interaction is:`);
    result.push(
      explainInteraction(
        backendSimulator.interactions[backendSimulator.nextInteractionIndex]
      )
    );
  }

  return result;
}

function explainLineInteraction(backendSimulator, index) {
  const interaction = backendSimulator.interactions[index];
  return `[${pad(index + 1)}]  ${interaction.request.method} ${
    interaction.request.url
  }`;
}
export function explainRequest(interaction) {
  return [
    `- method: "${interaction.method}"`,
    `- url   : "${interaction.url}"`,
    interaction.body
      ? `- body  : ${JSON.stringify(interaction.body, null, 2)}`
      : `- body  : null`,
  ];
}

function explainInteraction(interaction) {
  return [
    `- request method : "${interaction.request.method}"`,
    `- request url    : "${interaction.request.url}"`,
    interaction.body
      ? `- request body   : ${JSON.stringify(interaction.body, null, 2)}`
      : `- request body   : null`,
    `- response body  : ${JSON.stringify(interaction.response.body, null, 2)}`,
  ];
}

function pad(n) {
  let o = `${n}`;
  while (o.length < 3) o = ` ${o}`;
  return o;
}

function join(...texts) {
  return texts
    .flat(Infinity)
    .filter((x) => x || x === "")
    .map((s) => (s.endsWith(" ") ? s : `${s}\n`))
    .join("");
}
