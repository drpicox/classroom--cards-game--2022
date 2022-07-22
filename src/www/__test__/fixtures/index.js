import { render } from "@testing-library/react";
import { App } from "../../App";
import { verifyPostMd5 } from "./verifyPostMd5";

export async function runBeforeTestStarts(postId, expectedMd5) {
  await verifyPostMd5(postId, expectedMd5);

  render(<App />);
}
