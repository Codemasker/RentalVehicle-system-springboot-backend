package lk.haniman.rentalsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationManager implements Reservation {
    private List<Schedule> schedules;

    public ReservationManager() {
        schedules = new ArrayList<Schedule>();
    }

    @Override
    public void book(Schedule schedule) {
        schedules.add(schedule);
        System.out.println("New Schedule has been added!");
    }

    @Override
    public boolean isAvailable(Date date, Vehicle vehicle) {
        Schedule schedule = new Schedule(vehicle);
        if (schedules.contains(schedule)){
            int index = schedules.indexOf(schedule);
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
        if (schedules.contains(schedule)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void remove(Vehicle vehicle) {
        Schedule schedule = new Schedule(vehicle);
        if (this.exists(vehicle)){
            schedules.remove(schedule);
        }
    }
}
