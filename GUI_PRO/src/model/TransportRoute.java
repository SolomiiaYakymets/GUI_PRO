package model;

import model.transport.TransportMethod;

public class TransportRoute {
    private final Country startCountry;
    private final Country endCountry;
    private final TransportMethod method;
    private boolean isBlocked;

    public TransportRoute(Country startCountry, Country endCountry, TransportMethod method) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.method = method;
        this.isBlocked = false;
    }

    public Country getStartCountry() { return startCountry; }

    public Country getEndCountry() { return endCountry; }

    public TransportMethod getMethod() {
        return method;
    }

    public boolean isBlocked() { return isBlocked; }

    public void blockRoute(){
        if (!isBlocked) {
            this.isBlocked = true;
        }
    }

    public void reopenRoute(){
        if (isBlocked){
            this.isBlocked = false;
        }
    }
}
