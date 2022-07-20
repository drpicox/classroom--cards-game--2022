package com.drpicox.game.blog;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostParserTest {

    @Test
    public void parses_successfully_a_blog_post() {
        var post = parse(
                "2010-01-01_title",
                "---",
                "akey: avalue",
                "bkey: bvalue ",
                "---",
                " ",
                "# Title ",
                "",
                "first line  ",
                " second line"
        );

        assertThat(post.getId()).isEqualTo("2010-01-01_title");
        assertThat(post.getValue("akey")).isEqualTo("avalue");
        assertThat(post.getValue("bkey")).isEqualTo("bvalue");
        assertThat(post.getTitle()).isEqualTo("Title");
        assertThat(post.getBody()).isEqualTo("first line  \n second line\n");
    }

    @Test
    public void front_matter_allows_spaces_and_comments() {
        var post = parse("2010-01-01_title", "---", "foo: bar", "    ", "# some comment", "other: value", "---", "# Title", "content");

        assertThat(post.getId()).isEqualTo("2010-01-01_title");
        assertThat(post.getValue("foo")).isEqualTo("bar");
        assertThat(post.getValue("other")).isEqualTo("value");
        assertThat(post.getKeys()).containsExactly("foo", "other");
        assertThat(post.getTitle()).isEqualTo("Title");
        assertThat(post.getBody()).isEqualTo("content\n");
    }

    @Test void failures_in_parsing() {
        assertThrows(IllegalPostFileFormatException.class, () -> parse());
        assertThrows(IllegalPostFileFormatException.class, () -> parse("2021-2-10_title", "---", "x:a", "---", "# title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("2021-20-10_title", "---", "x:a", "---", "# title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("2021-10-4_title", "---", "x:a", "---", "# title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("2021-10-40_title", "---", "x:a", "---", "# title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("2021-10-10_wrong_title", "---", "x:a", "---", "# Right title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("2021-10-10_wrongid", "---", "x:a", "---", "# Wrong Id"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("2021-10-10_wrong_Id", "---", "x:a", "---", "# Wrong Id"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---", "x", "---", "# title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("no frontmatter", "---", "title:x", "---"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","foo: no frontmatter end", "other: prop"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","unexpected frontmatter line", "---", "bla"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","other:prop", "---", "no title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","only: frontmatter", "---", ""));
    }

    private static Post parse(String ...lines) {
        String postId;
        if (lines.length > 0 && lines[0].charAt(0) == '2') {
            postId = lines[0];
            lines = Arrays.copyOfRange(lines, 1, lines.length);
        } else {
            postId = "2010-01-01_title";
        }

        var parser = new PostParser(postId, Arrays.asList(lines));
        return parser.parse();
    }

}
