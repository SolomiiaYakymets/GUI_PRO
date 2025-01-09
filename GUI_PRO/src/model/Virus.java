package model;

import java.util.List;

public class Virus {
    private double spreadRate;
    private double mutationRate;
    private int infectedCount;

    public Virus(double spreadRate, double mutationRate, int infectedCount) {
        this.spreadRate = spreadRate;
        this.mutationRate = mutationRate;
        this.infectedCount = infectedCount;
    }

    public double getSpreadRate() { return spreadRate; }

    public void setSpreadRate(double spreadRate) { this.spreadRate = spreadRate; }

    public double getMutationRate() { return mutationRate; }

    public void setMutationRate(double mutationRate) { this.mutationRate = mutationRate; }

    public int getInfectedCount() { return infectedCount; }

    public void setInfectedCount(int infectedCount) { this.infectedCount = infectedCount; }

    public void spread(Country originCountry) {
        List<TransportRoute> routes = originCountry.getRoutes();
        if (routes != null) {
            for (TransportRoute route : routes) {
                Country destination = route.getEndCountry();
                double routeEfficiency = route.getEfficiency();

                int infectionsToSpread = (int) (originCountry.getInfectedCount() * spreadRate * routeEfficiency);
                destination.infect(infectionsToSpread);
            }
        }
    }

    public void mutate() {
        spreadRate += spreadRate * mutationRate;
        if (spreadRate > 1.0) {
            spreadRate = 1.0;
        }
    }
}
