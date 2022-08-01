import * as userSimulator from "./userSimulator";
import { mainView, queryAllByTestId, getByTestId } from "./queries";

export class Post_20220715_HelloBlog_Context {
  async goToTheBlogSection() {
    userSimulator.clickLink(mainView, "Blog");
    await userSimulator.waitForLoading();
  }

  async youShouldSeeAListOfPosts() {
    const posts = queryAllByTestId(mainView, "post-list--item");
    expect(posts).not.toHaveLength(0);
  }

  async theLastPostTitleShouldBeSThisPost(expected) {
    // expected = "Hello Blog"

    const posts = queryAllByTestId(mainView, "post-list--item");
    const last = posts.at(-1);

    expect(last).toHaveTextContent(expected);
  }

  async goToTheSPost(postTitle) {
    userSimulator.clickLink(mainView, postTitle);
    await userSimulator.waitForLoading();
  }

  async youShouldSeeTheSPost(the) {
    // the = "Hello Blog"

    const title = getByTestId(mainView, "post-title");
    expect(title).toHaveTextContent(the);
  }

  async thePostShouldContainSWhichIsHere(contain) {
    const body = getByTestId(mainView, "post-body");
    expect(body).toHaveTextContent(contain);
  }
}
