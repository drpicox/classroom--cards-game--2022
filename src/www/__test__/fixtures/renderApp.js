import { render } from "@testing-library/react";
import { MemoryRouter } from "react-router";
import { App } from "../../App";

export function renderApp() {
  render(
    <MemoryRouter>
      <App />
    </MemoryRouter>
  );
}
