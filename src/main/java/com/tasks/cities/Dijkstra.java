package com.tasks.cities;


import com.tasks.cities.models.City;

import java.util.*;

public class Dijkstra {
    public String calculateShortestPathFromSourceToDestination(City source, City destination) {
        String result = null;
        source.setDistance(0);

        Set<City> finalResultsPath = new HashSet<>();
        Set<City> unsettledCities = new HashSet<>();
        unsettledCities.add(source);

        while (!unsettledCities.isEmpty()) {
            //choose lowest distance on the way between current city and it neighbors
            City currentCity = getLowestDistanceToNextCity(unsettledCities);
            unsettledCities.remove(currentCity);
            for (Map.Entry<City, Integer> neighbors : currentCity.getNeighbors().entrySet()) {
                City neighborCity = neighbors.getKey();
                Integer distance = neighbors.getValue();

                if (!finalResultsPath.contains(neighborCity)) {
                    //calculate minimal distance all path from source
                    calculateMinimumDistance(neighborCity, distance, currentCity);
                    unsettledCities.add(neighborCity);
                }
            }
            //save final distance from source to every city in storage
            finalResultsPath.add(currentCity);
            if (currentCity.getName().equals(destination.getName())) {
                //return distance from source to destination
                result = currentCity.getDistance().toString();
            }
        }
        return result;
    }

    private void calculateMinimumDistance(City neighborCity, Integer distance, City currentCity) {
        Integer sourceDistance = currentCity.getDistance();
        if (sourceDistance + distance < neighborCity.getDistance()) {
            neighborCity.setDistance(sourceDistance + distance);
            List<City> shortestPath = new ArrayList<>(currentCity.getShortestPath());
            shortestPath.add(currentCity);
            neighborCity.setShortestPath(shortestPath);
        }
    }

    private City getLowestDistanceToNextCity(Set<City> unsettledCities) {
        City lowestDistanceCity = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (City city : unsettledCities) {
            int cityDistance = city.getDistance();
            if (cityDistance < lowestDistance) {
                lowestDistance = cityDistance;
                lowestDistanceCity = city;
            }
        }
        return lowestDistanceCity;
    }
}
