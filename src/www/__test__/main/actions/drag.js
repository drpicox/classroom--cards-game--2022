import { mouseDown, mouseUp } from "./mouse";

export function drag(fromElement, toElement) {
  mouseDown(fromElement);
  mouseUp(toElement);
}
