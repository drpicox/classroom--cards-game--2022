package com.drpicox.game.fixtures;

import com.drpicox.game.blog.BlogService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Fixtures {

    private final BlogService blogService;
    private final FrontendSimulator frontendSimulator;

    private String postId;

    public Fixtures(BlogService blogService, FrontendSimulator frontendSimulator) {
        this.blogService = blogService;
        this.frontendSimulator = frontendSimulator;
    }

    public void runBeforeTestStarts(String postId, String expectedMd5) {
        this.postId = postId;

        verifyExpectedMd5(postId, expectedMd5);
        frontendSimulator.clear(postId);
    }

    private void verifyExpectedMd5(String postId, String expectedMd5) {
        var post = blogService.findPost(postId).get();
        var actualMd5 = post.getMd5();

        if (actualMd5.equals(expectedMd5)) return;

        throw new AssertionError("Post '" + postId + ".md' has changed and the MD5 does not match.\n" +
            "expected md5: " + expectedMd5 + "\n" +
            "actual md5  : " + actualMd5 + "\n" +
            "This error raises probably because you have modified the blogpost but you do not have updated the test. Please, verify that:\n" +
            "- Verify that you have \"yarn create-tests\" running,\n" +
            "- Verify that you not changed the "+ postId +".md contents,\n" +
            "- In a Unix environment you can run the command md5 "+ postId +".md and verify that the result matches.\n" +
            "Please, verify that you have the yarn create-tests running correctly."
        );
    }

    public void runWhenTestSuccessful() {
        frontendSimulator.save(postId);
    }
}
