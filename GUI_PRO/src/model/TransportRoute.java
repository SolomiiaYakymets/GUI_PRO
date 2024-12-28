package model;

public class TransportRoute {
    Country startCountry;
    Country endCountry;
    TransportType type;

    public TransportRoute(Country startCountry, Country endCountry, TransportType type) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.type = type;
    }

    public Country getStartCountry() {
        return startCountry;
    }

    public void setStartCountry(Country startCountry) {
        this.startCountry = startCountry;
    }

    public Country getEndCountry() {
        return endCountry;
    }

    public void setEndCountry(Country endCountry) {
        this.endCountry = endCountry;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }
}
