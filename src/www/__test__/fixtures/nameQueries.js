import * as queries from "../queries";

export function nameQueries() {
  Object.entries(queries).forEach(([fnName, fn]) => {
    if (typeof fn !== "function") return;
    if (fn.name) return;

    Object.defineProperty(fn, "name", { value: fnName, configurable: true });
  });
}
