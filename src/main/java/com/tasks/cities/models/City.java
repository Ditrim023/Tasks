package com.tasks.cities.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City {
    private String name;

    private List<City> shortestPath = new ArrayList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<City, Integer> neighbors = new HashMap<>();

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public void addDestination(City destination, int distance) {
        neighbors.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<City> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<City, Integer> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Map<City, Integer> neighbors) {
        this.neighbors = neighbors;
    }
}
