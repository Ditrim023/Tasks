package com.tasks.cities;

import java.util.HashMap;
import java.util.Map;

public class Cities {
    private Map<Integer, City> citiesMap = new HashMap<>();
    private Dijkstra pathFinder = new Dijkstra();

    public City getCity(int id) {
        return citiesMap.get(id);
    }

    private City getCity(String name) {
        City result = null;
        for (Map.Entry<Integer, City> entry : citiesMap.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                result = entry.getValue();
                break;
            }
        }
        return result;
    }

    public void createCities(int countCities) {
        for (int i = 1; i <= countCities; i++) {
            citiesMap.put(i, new City());
        }
        System.out.println(citiesMap.size());
    }


    public void fillDistanceToNeighbors(City currentCity, String neighborInfo) {
        String[] idAndDistance = neighborInfo.split(" ");
        int id = Integer.parseInt(idAndDistance[0]);
        int distance = Integer.parseInt(idAndDistance[1]);
        currentCity.addDestination(citiesMap.get(id), distance);
    }

    public String getDestinationFrom(String nameOfSourceAndDestination) {
        String[] sourceAndDestination = nameOfSourceAndDestination.split(" ");
        City source = getCity(sourceAndDestination[0]);
        City destination = getCity(sourceAndDestination[1]);
        return pathFinder.calculateShortestPathFromSourceToDestination(source, destination);
    }

}