import { screen } from "@testing-library/dom";

export class Context_20220715_HelloBlog {
  async goToTheBlogSection() {
    screen.getByRole("link", { name: "Blog" });
  }

  async youShouldSeeAListOfPosts() {
    throw new Error(
      "The method youShouldSeeAListOfPosts() is not implemented yet."
    );
  }

  async theLastPostTitleShouldBeXThisPost(expected) {
    // expected = "Hello Blog"

    var actual = "";
    expect(actual).toEqual(expected);

    throw new Error(
      "The method theLastPostTitleShouldBeXThisPost(expected) is not implemented yet."
    );
  }

  async goToTheXPost(the) {
    // the = "Hello Blog"

    throw new Error("The method goToTheXPost(the) is not implemented yet.");
  }

  async youShouldSeeTheXPost(the) {
    // the = "Hello Blog"

    throw new Error(
      "The method youShouldSeeTheXPost(the) is not implemented yet."
    );
  }

  async thePostShouldContainXWhichIsHere(contain) {
    // contain = "this text"

    throw new Error(
      "The method thePostShouldContainXWhichIsHere(contain) is not implemented yet."
    );
  }
}
