package com.drpicox.game;

import com.drpicox.game.runner.PostRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Post_20220715_HelloBlog_Test {

    @Autowired private PostRunner postRunner;

    @Test
    public void testPost() {
        postRunner.runPost("2022-07-15_hello_blog", this);
    }

    public void firstInstruction() {
    }

    public void instructionWithXString(String arg0) {
        /* arg0 = "text" */
    }

    public void instructionWithXNumber(String arg0) {
        /* arg0 = "12" */
    }

    public void xBeginWithText(String arg0) {
        /* arg0 = "this" */
    }

    public void rumXFuuX(String arg0, String arg1) {
        /* arg0 = "plo" */
        /* arg1 = "" */
    }
}
