import chalk from "chalk";
import { readFileSync } from "fs";
import { codeFrameColumns } from "@babel/code-frame";

export function getCodeFrame(frame) {
  const locationStart = frame.indexOf("(") + 1;
  const locationEnd = frame.indexOf(")");
  const frameLocation = frame.slice(locationStart, locationEnd);

  const frameLocationElements = frameLocation.split(":");
  const [filename, line, column] = [
    frameLocationElements[0],
    parseInt(frameLocationElements[1], 10),
    parseInt(frameLocationElements[2], 10),
  ];

  let rawFileContents = "";
  try {
    rawFileContents = readFileSync(filename, "utf-8");
  } catch {
    return "";
  }

  const codeFrame = codeFrameColumns(
    rawFileContents,
    {
      start: { line, column },
    },
    {
      highlightCode: true,
      linesBelow: 0,
    },
  );
  return `${chalk.dim(frameLocation)}\n${codeFrame}\n`;
}
