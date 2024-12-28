package model;

import java.util.List;

public class Country {
    String name;
    int population;
    int infectedCount;
    List<TransportRoute> routes;

    public Country(String name, int population, int infectedCount, List<TransportRoute> routes) {
        this.name = name;
        this.population = population;
        this.infectedCount = infectedCount;
        this.routes = routes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getInfectedCount() {
        return infectedCount;
    }

    public void setInfectedCount(int infectedCount) {
        this.infectedCount = infectedCount;
    }

    public List<TransportRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<TransportRoute> routes) {
        this.routes = routes;
    }
}
