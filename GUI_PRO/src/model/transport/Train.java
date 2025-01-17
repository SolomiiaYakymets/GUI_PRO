package model.transport;

import model.Country;

import javax.swing.*;

public class Train implements TransportMethod{
    private static double speed = 2;

    @Override
    public String getName() {
        return "Train";
    }
    @Override
    public int getSpeedModifier() {
        return (int) speed;
    }
    public static void applySpeedUpgrade(double multiplier) {
        speed *= multiplier;
    }
}
