package lk.haniman.rentalsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationManager implements Reservation {
    // declare schedule list
    private List<Schedule> schedules;

    public ReservationManager() {
        // initial object
        schedules = new ArrayList<Schedule>();
    }

    @Override
    public void book(Schedule schedule) {
        // add schedule object to array list
        schedules.add(schedule);
        System.out.println("New Schedule has been added!");
    }

    // check vehicle in particular date range
    @Override
    public boolean isAvailable(Date date, Vehicle vehicle) {
        // get vehicle and date for check availability
        Schedule schedule = new Schedule(vehicle);
        // check vehicle in array list if already there in.
        if (schedules.contains(schedule)){
            // get index position of vehicle object
            int index = schedules.indexOf(schedule);
            // check date range
            // reference : https://stackoverflow.com/questions/494180/java-how-do-i-check-if-a-date-is-within-a-certain-range
            return !(date.after(schedules.get(index).getPickUpDate())&&date.before(schedules.get(index).getDropOffDate()));
        }
        return true;
    }

    @Override
    public List<Schedule> list() {
        return schedules;
    }

    @Override
    public boolean exists(Vehicle vehicle) {
        Schedule schedule = new Schedule(vehicle);
        // check vehicle in array list if already there in.
        if (schedules.contains(schedule)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void remove(Vehicle vehicle) {
        Schedule schedule = new Schedule(vehicle);
        // check vehicle in array list if already there in.
        if (this.exists(vehicle)){
            // delete vehicle object from array list
            schedules.remove(schedule);
        }
    }
}
