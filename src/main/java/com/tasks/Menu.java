package com.tasks;


import com.tasks.cities.PathFinder;
import com.tasks.factorial.Factorial;
import com.tasks.parentheses.Parentheses;

import java.util.Scanner;

public class Menu {
    private static final String OPERATION = "Choose number of task \n 1 - Parentheses \n 2 - Find way \n 3 - Factorial or something else to exit";
    private Factorial factorial = new Factorial();
    private Parentheses parentheses = new Parentheses();
    private PathFinder pathFinder = new PathFinder();
    private Scanner scanner = new Scanner(System.in);

    public void runMenu() {
        printMessage(OPERATION);
        String operationNumber = nextLine();
        switch (operationNumber) {
            case "3":
                factorial.run();
                break;
            case "1":
                parentheses.run();
                break;
            case "2":
                pathFinder.run();
                break;
            default: break;
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private String nextLine() {
        return scanner.nextLine();
    }

}
