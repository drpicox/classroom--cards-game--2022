const { keywords } = require("./keywords");

class MethodStepParser {
  #text;
  #index = 0;
  #methodName = "";
  #argumentValues = [];
  #argumentTypes = [];
  #argumentNames = [];
  #lastWord = "";

  constructor(text) {
    this.#text = text;
  }

  parse() {
    let lastIndex = -1;
    while (this.#index < this.#text.length && lastIndex !== this.#index) {
      lastIndex = this.#index;
      this.#skipSpaces();
      this.#acceptNameWord();
      this.#acceptStringArgument();
      this.#acceptNumberArgument();
    }

    return {
      name: this.#methodName,
      arguments: this.#argumentValues.map((value, i) => ({
        value,
        name: this.#argumentNames[i],
        type: this.#argumentTypes[i],
      })),
      text: this.#text,
    };
  }

  #skipSpaces() {
    this.#accept(/^[^a-z0-9"]+/i);
  }

  #acceptNameWord() {
    const word = this.#accept(/^[a-z]+/i);
    if (word) this.#appendNameWord(word[0]);
  }

  #acceptStringArgument() {
    const arg = this.#accept(/^"[^"]+"/);
    if (!arg) return;
    this.#appendArgument(arg[0]);
    this.#appendNameWord("S");
    this.#appendArgumentType("String");
  }

  #acceptNumberArgument() {
    const arg = this.#accept(/^[0-9]+/);
    if (!arg) return;
    this.#appendArgument(arg[0]);
    this.#appendNameWord("N");
    this.#appendArgumentType("int");
  }

  #appendNameWord(word) {
    this.#lastWord = word.toLowerCase();

    this.#methodName +=
      this.#methodName.length === 0
        ? word.toLowerCase()
        : word[0].toUpperCase() + word.slice(1).toLowerCase();
  }

  #appendArgument(value) {
    let name = this.#lastWord;
    if (/shouldBe/i.test(this.#methodName)) name = "expected";
    if (this.#argumentNames.includes(name) || keywords.has(name))
      name = `arg${this.#argumentValues.length}`;
    this.#argumentNames.push(name);

    this.#argumentValues.push(value);
  }

  #appendArgumentType(type) {
    this.#argumentTypes.push(type);
  }

  #accept(token) {
    const match = this.#matchToken(token);
    if (match) {
      this.#index += match[0].length;
      return match;
    }
    return undefined;
  }

  #matchToken(token) {
    const candidate = this.#text.slice(this.#index);
    return candidate.match(token);
  }
}
exports.MethodStepParser = MethodStepParser;
