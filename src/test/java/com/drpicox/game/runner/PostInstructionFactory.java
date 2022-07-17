package com.drpicox.game.runner;

import com.drpicox.game.blog.PostLine;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
class PostInstructionFactory {

    public List<PostInstruction> createList(List<PostLine> lines) {
        return lines.stream().map(l -> create(l)).toList();
    }

    private PostInstruction create(PostLine line) {
        var text = line.getLineText();
        if (text.startsWith(" * ")) return new MethodPostInstruction(line);
        return new IgnoreLinePostInstruction(line);
    }
}
