package com.drpicox.game.hints.api;

import com.drpicox.game.hints.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {

    private final BlogService blogService;

    public PostsController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ListPostsResponse listPosts() throws Exception {
        var list = blogService.findAll();
        var result = new ListPostsResponse();
        list.forEach(post -> result.addPost(post));
        return result;
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable String postId) throws Exception {
        var post = blogService.findPost(postId).orElseThrow();
        var response = new PostResponse(post);
        return response;
    }
}
