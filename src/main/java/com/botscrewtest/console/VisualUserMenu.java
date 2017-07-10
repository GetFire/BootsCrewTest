package com.botscrewtest.console;

import com.botscrewtest.Main;

import java.io.IOException;
import java.util.List;

public class VisualUserMenu {
    public static void outputSplitLine() {
        System.out.println("**************************************************************");
    }

    public static <T> void printListInConsole(List<String> header, List<T> options) {
        if (header != null) {
            header.forEach(System.out::println);
        }
        if (options != null) {
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%d %s %n", i + 1, options.get(i).toString());
            }
        }
    }

    public String getValidInput(String message, InputType type) {
        System.out.println(message);
        String input = "";
        try {
            input = Main.getReader().readLine();
            while (!type.getIsValid().test(input)) {
                System.out.println(type.getErrorMessage());
                input = Main.getReader().readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error reading user input from console", ex);
        }
        return input;
    }


}
