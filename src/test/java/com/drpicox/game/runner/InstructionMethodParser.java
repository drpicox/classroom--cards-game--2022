package com.drpicox.game.runner;

import com.drpicox.game.blog.PostLine;

import java.util.*;

class InstructionMethodParser {
    private final String text;
    private int index = 0;
    private StringBuilder methodName = new StringBuilder();
    private List<String> arguments = new LinkedList<>();

    private static List<Class<String>[]> typesCache = new ArrayList<>();

    public InstructionMethodParser(PostLine line) {
        this.text = line.getLineText();
    }

    public String getMethodName() {
        return methodName.toString();
    }

    void parse() {
        while (!isEnd()) {
            skipSpaces();
            acceptMethodNameFragment();
            skipSpaces();
            acceptMethodArgument();
            skipSpaces();
            acceptNumberArgument();
        }
    }

    private void skipSpaces() {
        while (!isEnd() && !isLetter() && !isVariable() && !isDigit()) next();
    }

    private void acceptMethodNameFragment() {
        if (!isLetter()) return;

        appendMethodCapital(current());
        next();

        while (isLetter()) {
            appendMethodCharacter(current());
            next();
        }
    }

    private void acceptMethodArgument() {
        if (!isVariable()) return;

        appendMethodCapital('X');

        var value = new StringBuilder();
        next();
        while (!isEnd() && !isVariable()) {
            value.append(current());
            next();
        }
        next();

        arguments.add(value.toString());
    }

    private void acceptNumberArgument() {
        if (!isDigit()) return;

        appendMethodCapital('X');

        var value = new StringBuilder();
        while (isDigit()) {
            value.append(current());
            next();
        }

        arguments.add(value.toString());
    }

    private void appendMethodCapital(char character) {
        if (methodName.isEmpty()) appendMethodCharacter(character);
        else methodName.append(Character.toUpperCase(character));
    }

    private void appendMethodCharacter(char character) {
        methodName.append(Character.toLowerCase(character));
    }

    private boolean isVariable() {
        return !isEnd() && current() == '"';
    }

    private boolean isLetter() {
        if (isEnd()) return false;
        var current = current();
        return 'a' <= current && current <= 'z' || 'A' <= current && current <= 'Z';
    }

    private boolean isDigit() {
        if (isEnd()) return false;
        var current = current();
        return '0' <= current && current <= '9';
    }

    private boolean isEnd() {
        return index >= text.length();
    }

    private char current() {
        return text.charAt(index);
    }

    private void next() {
        index += 1;
    }

    public String[] getArguments() {
        return arguments.toArray(new String[0]);
    }

    public Class<String>[] getArgumentTypes() {
        var size = arguments.size();
        fillTypesCacheWithMissingEntries(size);
        return typesCache.get(size);
    }

    private void fillTypesCacheWithMissingEntries(int size) {
        while (typesCache.size() <= size) {
            var currentSize = typesCache.size();
            var array = new Class[currentSize];
            Arrays.fill(array, String.class);
            typesCache.add(array);
        }
    }
}
