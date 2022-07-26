// You may need to add more methods here
import { act } from "@testing-library/react";

export const userSimulator = {
  click(element) {
    act(() => element.click());
  },
};
