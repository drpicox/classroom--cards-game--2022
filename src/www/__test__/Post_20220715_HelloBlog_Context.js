import { screen } from "@testing-library/react";
import { userSimulator } from "./fixtures/userSimulator";

export class Post_20220715_HelloBlog_Context {
  async goToTheBlogSection() {
    const link = screen.getByRole("link", { name: "Blog" });
    userSimulator.click(link);
    await userSimulator.waitForLoading();
  }

  async youShouldSeeAListOfPosts() {
    const posts = screen.queryAllByTestId("post-list--item");
    expect(posts).not.toHaveLength(0);
  }

  async theLastPostTitleShouldBeSThisPost(expected) {
    // expected = "Hello Blog"

    const posts = screen.queryAllByTestId("post-list--item");
    const last = posts.at(-1);

    expect(last).toHaveTextContent(expected);
  }

  async goToTheSPost(the) {
    const link = screen.getByRole("link", { name: the });
    userSimulator.click(link);
    await userSimulator.waitForLoading();
  }

  async youShouldSeeTheSPost(the) {
    // the = "Hello Blog"

    const title = screen.getByTestId("post-title");
    expect(title).toHaveTextContent(the);
  }

  async thePostShouldContainSWhichIsHere(contain) {
    const body = screen.getByTestId("post-body");
    expect(body).toHaveTextContent(contain);
  }
}
