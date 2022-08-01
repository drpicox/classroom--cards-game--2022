// You may need to add more methods here
import { act, getByRole } from "@testing-library/react";

export function click(element) {
  act(() => element.click());
}

export function clickButton(viewContainer, buttonName) {
  const button = getByRole(viewContainer, "button", { name: buttonName });
  click(button);
}

export function clickLink(viewContainer, linkName) {
  const link = getByRole(viewContainer, "link", { name: linkName });
  click(link);
}
