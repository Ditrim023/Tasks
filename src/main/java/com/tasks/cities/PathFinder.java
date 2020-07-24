package com.tasks.cities;

import com.tasks.cities.exceptions.InvalidDataException;
import com.tasks.cities.models.City;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PathFinder {
    private static final String WRONG_DATA = "You enter incorrect data!";
    private FileReader fileReader = new FileReader();
    private static final String INPUT_PATH = "src/main/resources/input.txt";

    private Cities citiesMap = new Cities();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            try {
                List<String> inputData = fileReader.read(INPUT_PATH);
                int countTests = readInt(inputData.get(0));
                int countCities = readInt(inputData.get(1));
//                remove already processed elements
                deleteElement(2, inputData);
                // create cities in storage
                citiesMap.createCities(countCities);
//                // set setting to every city in storage
                fillCitiesStorage(countCities, inputData);
//                // calculate minimal distance
                int countPath = readInt(inputData.get(0));
                String result = findPathFromSourceToDestination(countPath, inputData);
                printMessage(result);
//                deleteElement(countPath + 1, inputData);
                countTests--;
                exit = checkExit(countTests);
            } catch (NullPointerException | IOException | NumberFormatException e) {
                exit = true;
                printMessage(WRONG_DATA);
            }
        }
    }

    private String findPathFromSourceToDestination(int countPath, List<String> data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= countPath; i++) {
            sb.append(citiesMap.getDestinationFrom(data.get(i)) + "\n");
        }
        return sb.toString();
    }

    private void fillCitiesStorage(int countCities, List<String> data) {
        int countDelete = 2;
        for (int i = 1; i <= countCities; i++) {
            try {
                // find city by id and set it name
                City currentCity = citiesMap.getCity(i);
                String name = data.get(0);
                checkName(name);
                currentCity.setName(name);
                // and neighbors
                int countNeighbors = readInt(data.get(1));
                deleteElement(countDelete, data);
                enterNeighborsAndDistance(currentCity, countNeighbors, data);
                deleteElement(countNeighbors, data);
            } catch (NumberFormatException | InvalidDataException | ArrayIndexOutOfBoundsException e) {
                printMessage(WRONG_DATA);
            }
        }
    }

    private void enterNeighborsAndDistance(City currentCity, int countNeighbors, List<String> data) {
        for (int j = 0; j < countNeighbors; j++) {
            try {
                String idAndDistance = data.get(j);
                citiesMap.fillDistanceToNeighbors(currentCity, idAndDistance);
            } catch (NumberFormatException e) {
                printMessage(WRONG_DATA);
            }
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private int readInt(String countTest) {
        return parseInt(countTest);
    }

    private boolean checkExit(int countTests) {
        return countTests == 0;
    }

    private void checkName(String name) {
        if (!name.matches("[a-z]+")) {
            throw new InvalidDataException();
        }
    }

    private void deleteElement(int count, List<String> data) {
        for (int i = 0; i < count; i++) {
            data.remove(0);
        }
    }
}
