package lk.haniman.rentalsystem;

import java.util.Objects;

public class Motorbike extends Vehicle{
    private String starting;
    private String category;
    private String powerType;

    public Motorbike(String numberPlate, String brand, int maxSpeed, String model, String vehicleType, String starting, String category, String powerType) {
        super(numberPlate, brand, maxSpeed, model, vehicleType);
        this.starting = starting;
        this.category = category;
        this.powerType = powerType;
    }

    public Motorbike(String numberPlate, String starting, String category, String powerType) {
        super(numberPlate);
        this.starting = starting;
        this.category = category;
        this.powerType = powerType;
    }

    public Motorbike(String numberPlate) {
        super(numberPlate);
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    @Override
    public String toString() {
        return "------------------------------------\n"+
                "Vehicle    :\t"+"Motorbike"+"\n" +
                super.toString() + "\n" +
                "Power Type    :" + "\t"+powerType+"\n" +
                "Category  :" + "\t"+category+"\n" +
                "Staring type  :" + "\t"+starting+"\n";
    }


}
