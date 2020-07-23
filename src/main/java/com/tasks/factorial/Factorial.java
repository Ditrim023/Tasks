package com.tasks.factorial;

import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Factorial {
    private static final String ENTER_NUMBER = "Enter number please";
    private static final String PROCEED = "You want complete? Y for Yes, something else to continue";
    private static final String WRONG = "Enter correct number,please";
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            try {
                printMessage(ENTER_NUMBER);
                int number = readInt();
                getSumDigitsFactorial(number);
                exit = checkExit();
            } catch (NumberFormatException ex) {
                printMessage(WRONG);
            }
        }
    }

    private void getSumDigitsFactorial(int number) {
        if (number < 0) {
            throw new NumberFormatException();
        }
        BigInteger fact = getFactorial(number);
        String result = BigInteger.valueOf(String.valueOf(fact)
                .chars()
                .map(Character::getNumericValue)
                .sum()).toString();
        printMessage(result);
    }

    private BigInteger getFactorial(long number) {
        BigInteger result = BigInteger.ONE;
        for (long i = 2; i <= number; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
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
