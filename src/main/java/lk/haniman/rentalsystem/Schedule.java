package lk.haniman.rentalsystem;

import java.util.Date;
import java.util.Objects;


public class Schedule {
    private Date pickUpDate;
    private Date dropOffDate;
    private Vehicle vehicle;
    private String CustomerName;
    private int mobileNumber;

    public Schedule() {
    }

    public Schedule(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Schedule(Date pickUpDate, Date dropOffDate, Vehicle vehicle, String customerName, int mobileNumber) {
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.vehicle = vehicle;
        CustomerName = customerName;
        this.mobileNumber = mobileNumber;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(vehicle, schedule.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle);
    }



}
