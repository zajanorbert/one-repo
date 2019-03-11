package com.codecool.greencommitment.common;

public class Measurement {
    private long time;
    private int value;
    private String type;

    public Measurement(long time, int value, String type) {
        this.time = time;
        this.value = value;
        this.type = type;
    }
    public Measurement(){

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "time=" + time +
                ", value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}
