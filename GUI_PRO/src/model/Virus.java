package model;


public class Virus {
    private double spreadRate;
    private double mutationRate;

    public Virus(double spreadRate, double mutationRate) {
        this.spreadRate = spreadRate;
        this.mutationRate = mutationRate;
    }

    public double getSpreadRate() { return spreadRate; }

    public void setSpreadRate(double spreadRate) { this.spreadRate = spreadRate; }

    public void upgradeSpreadRate(double multiplier) {
        spreadRate *= multiplier;
    }

    public double setMutationRate(double mutationRate) {
        return this.mutationRate = mutationRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void mutate() {
        if (spreadRate < 1) {
            spreadRate += spreadRate * mutationRate;
        }
    }
}
