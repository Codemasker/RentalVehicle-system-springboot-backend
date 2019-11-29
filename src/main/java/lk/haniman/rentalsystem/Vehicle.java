package lk.haniman.rentalsystem;

import java.util.Objects;

public abstract class Vehicle implements Comparable<Vehicle> {
    private String numberPlate;
    private String brand;
    private int maxSpeed;
    private String model;
    private String vehicleType;

    public Vehicle(String numberPlate, String brand, int maxSpeed, String model, String vehicleType) {
        this.numberPlate = numberPlate;
        this.brand = brand;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.vehicleType = vehicleType;
    }


    public Vehicle(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Number Plate    :"+"\t"+numberPlate+"\n" +
                "Brand  :"+"\t"+brand+"\n"+
                "Maximum Speed  :"+"\t"+maxSpeed+"\n"+
                "Model  :"+"\t"+model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(numberPlate, vehicle.numberPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberPlate);
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.brand.compareToIgnoreCase(o.getBrand());
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
