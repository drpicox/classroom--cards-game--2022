package com.drpicox.stage1;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


public class TestStage1 {

    @Test
    public void testEverything() throws Exception {
        String packageName = "com.drpicox.game";

        List<Class<?>> classes = getClassesFromPackage(packageName);

        for (Class<?> clazz : classes) {
            try { executeMethods(clazz); } catch (Throwable t) {}
        }
    }

    private void executeMethods(Class<?> clazz) throws Exception {
        // Get the default constructor of the class
        var constructor = clazz.getConstructor();

        // Instantiate the class using the default constructor
        Object instance = constructor.newInstance();

        // Get all the methods of the class
        Method[] methods = clazz.getDeclaredMethods();

        // Iterate through the methods and invoke them
        for (Method method : methods) {
            // Check if the method is public and has no parameters
            if (method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers())) {
                System.out.println("Executing method: " + method.getName());
                // Invoke the method on the instance
                method.invoke(instance);
            }
        }
    }

    private static List<Class<?>> getClassesFromPackage(String packageName) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        for (java.net.URL resource : java.util.Collections.list(classLoader.getResources(path))) {
            String file = resource.getFile();
            if (file.contains("!")) {
                continue;
            }
            if (file.endsWith(".class")) {
                String className = packageName + "." + file.substring(path.length() + 1, file.length() - 6);
                classes.add(Class.forName(className));
            } else {
                File dir = new File(resource.getFile());
                if (!dir.isDirectory()) {
                    continue;
                }
                // Call the helper method to process the directory
                processDirectory(dir, packageName, classes);
            }
        }
        return classes;
    }

    private static void processDirectory(File dir, String packageName, List<Class<?>> classes) {
        for (var file : dir.listFiles()) {
            if (file.isDirectory()) {
                // If it's a directory, recurse into it
                processDirectory(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                // If it's a .class file, add it to the list of classes
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
