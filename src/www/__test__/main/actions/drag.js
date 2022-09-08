import { click } from "./click";

export function drag(fromElement, toElement) {
  click(fromElement);
  click(toElement);
}
