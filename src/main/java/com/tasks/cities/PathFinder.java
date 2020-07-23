package com.tasks.cities;

import com.tasks.cities.exceptions.InvalidDataException;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PathFinder {
    private static final String ENTER_COUNT_TESTS = "Enter count tests";
    private static final String ENTER_COUNT_CITIES = "Enter count cities";
    private static final String NAME_OF_CITY = "Enter name (which contain letter a,.,z)  for the city";
    private static final String COUNT_OF_NEIGHBORS = "Enter count of neighbors for the city - ";
    private static final String ID_DISTANCE = "Enter id neighbor and distance";
    private static final String SOURCE_DESTINATION = "Enter name of source and destination cities";
    private static final String COUNT_PATH = "Enter count path need to find";
    private static final String WRONG_DATA = "You enter incorrect data!";
    private static final String NUMBER_TEST = "Test number ";

    private Cities citiesMap = new Cities();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            try {
                printMessage(ENTER_COUNT_TESTS);
                int countTests = readInt();
                printMessage(NUMBER_TEST + countTests);
                printMessage(ENTER_COUNT_CITIES);
                int countCities = readInt();
                // create cities in storage
                citiesMap.createCities(countCities);
                // set setting to every city in storage
                fillCitiesStorage(countCities);
                // calculate minimal distance
                findPathFromSourceToDestination();
                countTests--;
                exit = checkExit(countTests);
            } catch (NumberFormatException e) {
                printMessage(WRONG_DATA);
            }
        }
    }

    private void fillCitiesStorage(int countCities) {
        for (int i = 1; i <= countCities; ) {
            try {
                printMessage(NAME_OF_CITY + i);
                // find city by id and set it name
                City currentCity = citiesMap.getCity(i);
                String name = nextLine();
                checkName(name);
                currentCity.setName(name);
//                and neighbors
                printMessage(COUNT_OF_NEIGHBORS + name);
                int countNeighbors = readInt();
                enterNeighborsAndDistance(currentCity, countNeighbors);
                i++;
            } catch (NumberFormatException | InvalidDataException | ArrayIndexOutOfBoundsException e) {
                printMessage(WRONG_DATA);
            }
        }
    }

    private void findPathFromSourceToDestination() {
        try {
            printMessage(COUNT_PATH);
            int countPaths = readInt();
            printMessage(SOURCE_DESTINATION);
            for (int i = 0; i < countPaths; ) {
                //enter source and destination
                String nameOfSourceAndDestination = nextLine();
                String result = citiesMap.getDestinationFrom(nameOfSourceAndDestination);
                printMessage(result);
                i++;
            }
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            printMessage(WRONG_DATA);
        }
    }


    private void enterNeighborsAndDistance(City currentCity, int countNeighbors) {
        printMessage(ID_DISTANCE);
        for (int j = 0; j < countNeighbors; ) {
            try {
                String idAndDistance = nextLine();
                citiesMap.fillDistanceToNeighbors(currentCity, idAndDistance);
                j++;
            } catch (NumberFormatException e) {
                printMessage(WRONG_DATA);
            }
        }
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

    private boolean checkExit(int countTests) {
        return countTests == 0;
    }

    private void checkName(String name) {
        if (!name.matches("[a-z]+")) {
            throw new InvalidDataException();
        }
    }
}
