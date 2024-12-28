package model;

public class Virus {
    String manner;
    float speed;
    String spreadEffect;

    public Virus(String manner, float speed, String spreadEffect){
        this.manner = manner;
        this.speed = speed;
        this.spreadEffect = spreadEffect;
    }

    public String getManner(){
        return manner;
    }

    public void setManner(String manner) {
        this.manner = manner;
    }

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getSpreadEffect(){
        return spreadEffect;
    }

    public void setSpreadEffect(String spreadEffect) {
        this.spreadEffect = spreadEffect;
    }
}
