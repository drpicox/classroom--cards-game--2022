package com.drpicox.game.runner;

import com.drpicox.game.blog.PostLine;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;

public class MethodPostInstruction extends PostInstruction {
    private String methodName;
    private String[] arguments;
    private Class<String>[] argumentTypes;

    public MethodPostInstruction(PostLine line) {
        super(line);

        parseMethodNameAndArguments();
    }

    @Override
    public String getPassString() {
        return "✅";
    }

    @Override
    public void run(Object context) {
        super.run(context);

        try {
            var contextClass = context.getClass();
            var method =  contextClass.getMethod(methodName, argumentTypes);
            method.invoke(context, arguments);
        } catch (NoSuchMethodException e) {
            throwNoMethod(context, e);
        } catch (InvocationTargetException e) {
            throwNoMethod(context, e);
        } catch (IllegalAccessException e) {
            throwNoMethod(context, e);
        }
    }

    private void throwNoMethod(Object context, Throwable e) {
        throw new IllegalArgumentException(
                "The context object of class \""+context.getClass().getName()+"\" " +
                        "does not have the expected method \"" + methodName + "\"" +
                        "and " + argumentTypes.length + " String arguments, " +
                        "or it maight be not public.\nMake sure that:\n" +
                        "- You are looking to the " + context.getClass().getName() + " class,\n" +
                        "- There is a method called " + methodName + ",\n" +
                        "- The method is public,\n" +
                        "- The method has " + argumentTypes.length + " arguments,\n" +
                        "- All method arguments are String (if there is any).\n" +
                        "If it does not exists, you can copy/paste this template:\n" +
                        generateCodeTemplate(context) +
                        "If it does not seems right, please examine all error causes."
        , e);
    }

    private String generateCodeTemplate(Object context) {
        var result = new StringBuilder();

        result.append("class ").append(context.getClass().getSimpleName()).append(" {\n");
        result.append("\n    /* ... */\n\n");

        result.append("    public void ");
        result.append(methodName);
        result.append("(");
        for (var i = 0; i < arguments.length; i += 1) {
            if (i > 0) result.append(", ");
            result.append("String arg").append(i);
        }
        result.append(") {\n");
        for (var i = 0; i < arguments.length; i += 1) {
            result.append("        /* arg").append(i);
            result.append(" = \"").append(arguments[i]).append("\"");
            result.append(" */\n");
        }
        result.append("    }\n");
        result.append("\n}\n");

        return result.toString();
    }

    private void parseMethodNameAndArguments() {
        var parser = new InstructionMethodParser(getLine());
        parser.parse();
        methodName = parser.getMethodName();
        arguments = parser.getArguments();
        argumentTypes = parser.getArgumentTypes();
    }

    private boolean isLetter(char current) {
        return 'a' <= current && current <= 'z';
    }

    private boolean isDigit(char current) {
        return '0' <= current && current <= '9';
    }

    private void appendLetter(StringBuilder methodNameBuilder, boolean caps, char current) {
        if (caps) current = Character.toUpperCase(current);
        methodNameBuilder.append(current);
    }

    private void appendDigit(StringBuilder methodNameBuilder, char current) {
        methodNameBuilder.append(current);
    }

}
