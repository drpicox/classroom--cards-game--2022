import { screen, waitFor } from "@testing-library/react";
import { userSimulator } from "./fixtures/userSimulator";

export class Post_20220715_HelloBlog_Context {
  async goToTheBlogSection() {
    const link = screen.getByRole("link", { name: "Blog" });
    userSimulator.click(link);
  }

  async youShouldSeeAListOfPosts() {
    await screen.findAllByTestId("post-list--item");
  }

  async theLastPostTitleShouldBeSThisPost(expected) {
    // expected = "Hello Blog"

    const posts = await screen.findAllByTestId("post-list--item");
    const last = posts.at(-1);

    expect(last).toHaveTextContent(expected);
  }

  async goToTheSPost(the) {
    const link = screen.getByRole("link", { name: the });
    userSimulator.click(link);
  }

  async youShouldSeeTheSPost(the) {
    // the = "Hello Blog"

    const title = await screen.findByTestId("post-title");
    expect(title).toHaveTextContent(the);
  }

  async thePostShouldContainSWhichIsHere(contain) {
    const body = await screen.findByTestId("post-body");
    waitFor(() => expect(body).toHaveTextContent(contain));
  }
}
