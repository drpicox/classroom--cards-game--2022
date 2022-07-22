const prettier = require("prettier");

function prettyJs(code) {
  return prettier.format(code, { parser: "babel" });
}
exports.prettyJs = prettyJs;
