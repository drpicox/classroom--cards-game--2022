import { renderApp } from "./renderApp";
import { verifyPostMd5 } from "./verifyPostMd5";

export async function runBeforeTestStarts(postId, expectedMd5) {
  await verifyPostMd5(postId, expectedMd5);

  renderApp();
}

export async function runWhenTestSuccessful() {
  //
}
