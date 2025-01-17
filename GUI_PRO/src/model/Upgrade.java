package model;

public class Upgrade {
    private final String name;
    private final double cost;
    private final String effect;
    private final boolean hasParameter;

    private final String parameterInfo;

    public Upgrade(String name, double cost, String effect, boolean hasParameter, String parameterInfo) {
        this.name = name;
        this.cost = cost;
        this.effect = effect;
        this.hasParameter = hasParameter;
        this.parameterInfo = parameterInfo;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getEffect() {
        return effect;
    }

    public boolean isHasParameter() {
        return hasParameter;
    }

    public String getParameterInfo() {
        return parameterInfo;
    }
}
