package lk.haniman.rentalsystem;

        import java.util.Date;
        import java.util.List;

interface Reservation {
    public void book(Schedule schedule);
    public boolean isAvailable(Date date, Vehicle vehicle);
    public List<Schedule> list();
    public boolean exists(Vehicle vehicle);
    public void remove(Vehicle vehicle);

}
