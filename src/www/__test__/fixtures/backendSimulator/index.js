import { BackendSimulator } from "./BackendSimulator";

let backendSimulator;

jest.doMock("../../../backend", () => {
  const backend = {
    async get(url) {
      return backendSimulator.get(url);
    },
  };

  return { backend };
});

export function prepareBackendResponses(currentPostId) {
  backendSimulator = new BackendSimulator(currentPostId);
}

export async function closeBackendResponses() {
  await backendSimulator.close();
}
