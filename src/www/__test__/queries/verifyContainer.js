import chalk from "chalk";

const isQueryRegExp = /^(get|find|query)/;

export function verifyContainer(container) {
  if (typeof container?.querySelectorAll === "function") return true;

  const methodName = extractMethodNameFromStack();

  throw new TypeError(
    `The first argument of queries must be a valid DOM container. ` +
      `In this case, it seems that you have called to a ${methodName} ` +
      `method with a first argument that is not a container. ` +
      `If you do not know which container use, you may want to use mainView, example:\n\n` +
      chalk.red.strikethrough(`- ${methodName}(…);\n`) +
      chalk.green(`- ${methodName}(${chalk.bold("mainView")}, …);\n`)
  );
}

function extractMethodNameFromStack() {
  const { stack } = new Error();
  const methods = stack
    .split("\n")
    .slice(1)
    .map((l) => l.split("at")[1].split("(")[0].trim())
    .filter((n) => isQueryRegExp.test(n));

  if (methods.length === 0) return "<unknown>";

  let methodName = methods[0];
  if (!methodName.startsWith("queryAll")) return methodName;

  const byIndex = methodName.indexOf("By");
  const by = byIndex < 0 ? "" : methodName.slice(byIndex);
  const type = methodName.slice("queryAll".length, byIndex);
  const familyRegExp = new RegExp(`^(get|find|query)(All)?${type}${by}$`);

  const candidate = methods.reverse().find((n) => familyRegExp.test(n));
  return candidate || methodName;
}
