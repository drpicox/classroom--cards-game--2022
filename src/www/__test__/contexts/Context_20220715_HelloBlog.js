import { screen, waitFor } from "@testing-library/react";
import { userSimulator } from "../fixtures/userSimulator";

export class Context_20220715_HelloBlog {
  async goToTheBlogSection() {
    const link = screen.getByRole("link", { name: "Blog" });
    userSimulator.click(link);
  }

  async youShouldSeeAListOfPosts() {
    await screen.findAllByTestId("post-list--item");
  }

  async theLastPostTitleShouldBeXThisPost(expected) {
    // expected = "Hello Blog"

    const posts = await screen.findAllByTestId("post-list--item");
    const last = posts.at(-1);

    expect(last).toHaveTextContent(expected);
  }

  async goToTheXPost(the) {
    const link = screen.getByRole("link", { name: the });
    userSimulator.click(link);
  }

  async youShouldSeeTheXPost(the) {
    // the = "Hello Blog"

    const title = await screen.findByTestId("post-title");
    expect(title).toHaveTextContent(the);
  }

  async thePostShouldContainXWhichIsHere(contain) {
    const body = await screen.findByTestId("post-body");
    waitFor(() => expect(body).toHaveTextContent(contain));
  }
}
