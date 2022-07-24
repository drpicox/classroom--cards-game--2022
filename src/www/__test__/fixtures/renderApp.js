import { render } from "@testing-library/react";
import { Provider } from "react-redux";
import { MemoryRouter } from "react-router";
import { App } from "../../App";
import { createAppStore } from "../../createAppStore";

export function renderApp() {
  render(
    <Provider store={createAppStore()}>
      <MemoryRouter>
        <App />
      </MemoryRouter>
    </Provider>
  );
}
