package com.codecool.greencommitment.common;


import java.util.List;

public class Measurements {

    int id;
    List<Measurement> measurement;

    public Measurements(int id, List<Measurement> measurement) {
        this.id = id;
        this.measurement = measurement;
    }
    public Measurements(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "id=" + id +
                ", measurement=" + measurement +
                '}';
    }
}
