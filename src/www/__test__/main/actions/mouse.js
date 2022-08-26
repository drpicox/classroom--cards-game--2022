import { fireEvent } from "@testing-library/react";

export function mouseDown(element) {
  fireEvent.mouseDown(element);
}

export function mouseUp(element) {
  fireEvent.mouseUp(element);
}
