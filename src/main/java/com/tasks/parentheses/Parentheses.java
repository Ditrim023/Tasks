package com.tasks.parentheses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Parentheses {
    private static final String ENTER_COUNT_PARENTHESES = "Enter count of parentheses, please";
    private static final String WRONG = "Count of parentheses must be number and more than 0";
    private static final String PROCEED = "You want complete? Y for Yes, something else to continue";
    private static final String BRACKETS = "()";
    private static final String COUNT = "Count parentheses - ";

    private Scanner scanner = new Scanner(System.in);


    public void run() {
        boolean exit = false;
        while (!exit) {
            try {
                printMessage(ENTER_COUNT_PARENTHESES);
                int countVar = readInt();
                countParentheses(countVar);
                exit = checkExit();
            } catch (NumberFormatException ex) {
                printMessage(WRONG);
            }
        }
    }

    private void countParentheses(int countVar) {
        List<String> expressions = new ArrayList<>();
        expressions.add(BRACKETS);
        List<String> newList = new ArrayList<>();
        String newExpression;
        int count = 1;
        while (count < countVar) {
            for (String expression : expressions) {
                for (int i = 0; i < expression.length(); i++) {
                    // Create new expressions and insert in list
                    newExpression = expression.substring(0, i) + BRACKETS + expression.substring(i);
                    if (!newList.contains(newExpression)){
                        newList.add(newExpression);
                    }
                }
            }
            // Save result before before next iteration
            expressions = newList;
            newList = new ArrayList<>();
            count++;
        }
        printMessage(expressions.toString());
        printMessage(COUNT + expressions.size());
    }

    private boolean checkExit() {
        printMessage(PROCEED);
        return nextLine().equalsIgnoreCase("Y");
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private int readInt() {
        return parseInt(nextLine());
    }

    private String nextLine() {
        return scanner.nextLine();
    }
}
