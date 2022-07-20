package com.drpicox.game;

import com.drpicox.game.blog.api.ListPostsResponse;
import com.drpicox.game.blog.api.ListPostsResponseEntry;
import com.drpicox.game.blog.api.PostResponse;
import com.drpicox.game.runner.FrontendSimulator;
import com.drpicox.game.runner.PostRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class Post_20220715_HelloBlog_Test {

    @Autowired private PostRunner postRunner;
    @Autowired private FrontendSimulator frontendSimulator;

    private ListPostsResponse postsList;
    private PostResponse post;

    @Test
    public void testPost() {
        postRunner.runPost("2022-07-15_hello_blog", this);
    }

    public void goToTheBlogSection() {
        postsList = frontendSimulator.get("/api/v1/posts", ListPostsResponse.class);
    }
    public void youShouldSeeAListOfPosts() {
        assertThat(postsList.getPosts()).isNotEmpty();
    }
    public void theLastPostTitleShouldBeXThisPost(String expectedTitle) {
        var query = findPostEntry(expectedTitle);
        assertThat(query).isPresent();
    }

    public void goToTheXPost(String postTitle) {
        var entry = findPostEntry(postTitle);
        var id = entry.get().getId();
        post = frontendSimulator.get("/api/v1/posts/" + id, PostResponse.class);
    }

    public void youShouldSeeTheXPost(String expectedTitle) {
        assertThat(post.getTitle()).isEqualTo(expectedTitle);
    }

    public void thePostShouldContainXWhichIsHere(String bodyContent) {
        assertThat(post.getBody()).contains(bodyContent);
    }
    private Optional<ListPostsResponseEntry> findPostEntry(String expectedTitle) {
        return postsList.getPosts().stream().filter(p -> p.getTitle().equals(expectedTitle)).findAny();
    }
}
