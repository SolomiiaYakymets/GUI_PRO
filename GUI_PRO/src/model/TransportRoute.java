package model;

public class TransportRoute {
    private Country startCountry;
    private Country endCountry;
    private TransportType type;
    private double efficiency;
    private double initialEfficiency;
    private boolean isBlocked;

    public TransportRoute(Country startCountry, Country endCountry, TransportType type, double efficiency) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.type = type;
        this.efficiency = efficiency;
        this.initialEfficiency = efficiency;
        this.isBlocked = false;
    }

    public Country getStartCountry() { return startCountry; }

    public void setStartCountry(Country startCountry) { this.startCountry = startCountry; }

    public Country getEndCountry() { return endCountry; }

    public void setEndCountry(Country endCountry) { this.endCountry = endCountry; }

    public TransportType getType() { return type; }

    public void setType(TransportType type) { this.type = type; }

    public double getEfficiency() { return efficiency; }

    public void setEfficiency(double efficiency) { this.efficiency = efficiency; }

    public boolean isBlocked() { return isBlocked; }

    public void setBlocked(boolean blocked) { isBlocked = blocked; }

    public void blockRoute(){
        if (!isBlocked) {
            this.efficiency = 0;
            this.isBlocked = true;
        }
    }

    public void reopenRoute(){
        if (isBlocked){
            this.efficiency = initialEfficiency;
            this.isBlocked = false;
        }
    }
}
