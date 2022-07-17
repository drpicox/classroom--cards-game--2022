package com.drpicox.game.runner;

import com.drpicox.game.blog.BlogService;
import com.drpicox.game.blog.PostLine;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PostRunner {

    private final BlogService blogService;
    private final PostInstructionFactory postInstructionFactory;

    public PostRunner(BlogService blogService, PostInstructionFactory postInstructionFactory) {
        this.blogService = blogService;
        this.postInstructionFactory = postInstructionFactory;
    }

    public void runPost(String postId, Object context) {
        var post = blogService.findPost(postId).orElseThrow(() -> new RuntimeException("Cannot find the post with id \"" + postId + "\". Please check that this post exists and it is inside src/main/resources/blog."));
        verifyContextName(postId, context);
        verifyContextTestMethod(context);
        var instructions = postInstructionFactory.createList(post.getBodyLines());
        System.out.println("Runnint test \""+postId+"\":");
        runInstructions(instructions, context);
    }

    private void runInstructions(List<PostInstruction> instructions, Object context) {
        var index = 0;
        while (index < instructions.size()) {
            runInstruction(instructions.get(index), context);
            index += 1;
        }
    }

    private void runInstruction(PostInstruction instruction, Object context) {
        try {
            instruction.run(context);
            System.out.println("  " + instruction.getPassString() + "  " + instruction.toString());
        } catch (Throwable e) {
            System.out.println("  ❌  " + instruction.toString());
            throw e;
        }
    }

    private void verifyContextName(String postId, Object context) {
        var testClassNameBuilder = new StringBuilder();
        testClassNameBuilder.append("com.drpicox.game.Post_");
        testClassNameBuilder.append(postId.substring(0,10).replaceAll("-", ""));
        testClassNameBuilder.append("_");
        Arrays.stream(postId.toLowerCase().substring(11).split("_")).forEach(word -> {
            testClassNameBuilder.append(word.substring(0,1).toUpperCase());
            testClassNameBuilder.append(word.substring(1));
        });
        testClassNameBuilder.append("_Test");
        var expectedHelperName = testClassNameBuilder.toString();

        if (!expectedHelperName.equals(context.getClass().getName()))
            throw new IllegalArgumentException(
                    "The test class for the post \""+postId+"\" has a wrong name:\n" +
                    "- it was:   " + context.getClass().getName() + "\n" +
                    "- expected: " + expectedHelperName + "\n" +
                    "Please change the name of the test class, or, if you are using in two tests, duplicate it and extract logic into a common context."
            );
    }

    private void verifyContextTestMethod(Object helper) {
        try {
            var method = helper.getClass().getMethod("testPost");
            var annotation = method.getAnnotation(Test.class);
            if (annotation == null)
                throw new IllegalArgumentException("The test class \""+helper.getClass().getSimpleName()+"\" public void testPost() method is not @Test.\nPlease, make sure that the testPost method has @Test annotation imported from org.junit.jupiter.api.Test.");
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("The test class \""+helper.getClass().getSimpleName()+"\", does not have a testPost method.\nPlease, make sure that it defines is a public void testPost() method without arguments.");
        }
    }


}
