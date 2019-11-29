package lk.haniman.rentalsystem;

import java.util.Objects;

public class Car extends Vehicle {
    private int numberOfSeat;
    private String gearType;
    private String fuelType;

    public Car(String numberPlate, String brand, int maxSpeed, String model, String vehicleType, int numberOfSeat, String gearType, String fuelType) {
        super(numberPlate, brand, maxSpeed, model, vehicleType);
        this.numberOfSeat = numberOfSeat;
        this.gearType = gearType;
        this.fuelType = fuelType;
    }

    public Car(String numberPlate, int numberOfSeat, String gearType, String fuelType) {
        super(numberPlate);
        this.numberOfSeat = numberOfSeat;
        this.gearType = gearType;
        this.fuelType = fuelType;
    }

    public Car(String numberPlate) {
        super(numberPlate);
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {

        return "------------------------------------\n"+
                "Vehicle    :\t"+"Car"+"\n" +
                super.toString() + "\n" +
                "Number of Seats    :" + "\t"+numberOfSeat+"\n" +
                "Gear Type  :" + "\t"+gearType+"\n" +
                "Fuel Type  :" + "\t"+fuelType+"\n";
    }



}
