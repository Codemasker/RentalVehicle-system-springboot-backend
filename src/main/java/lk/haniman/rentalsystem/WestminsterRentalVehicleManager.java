package lk.haniman.rentalsystem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;


public class WestminsterRentalVehicleManager implements RentalVehicleManager {
    private List<Vehicle> vehicles;
    private Database database;

    public WestminsterRentalVehicleManager() {
        // reduce mongodb logging
        // reference from stackoverflow
        // https://stackoverflow.com/questions/30137564/how-to-disable-mongodb-java-driver-logging
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
        // initial array list with limit
        vehicles = new ArrayList<Vehicle>(50);
        // initial Database object class
        database = new Database();

        // assign database array to instance array
        vehicles = database.get();
    }

    @Override
    public void add(Vehicle vehicle) {
        // validate limits
        if(vehicles.size()<50){
            // add vehicle item into list
            vehicles.add(vehicle);
            database.add(vehicle);
            if(vehicle instanceof Car){
                System.out.println("Successfully Car added!");
            }else{
                System.out.println("Successfully Motorbike added!");
            }
        }else{
            System.out.println("Oops!. Sorry Vehicle storage limit exceeded.");
        }
    }

    @Override
    public boolean delete(Vehicle vehicle) {
        // check vehicle item
        if(vehicles.contains(vehicle)){
            // remove vehicle item from array list
            vehicles.remove(vehicle);
            database.delete(vehicle);
            // calculate list available space
            int space = 50-vehicles.size();
            if(vehicle instanceof Car){
                System.out.println(vehicle.getNumberPlate()+" This Car was deleted!.");
                System.out.println("Reminder : "+space+" Storage space available.");
            }else {
                System.out.println(vehicle.getNumberPlate()+" This Motorbike was deleted!.");
                System.out.println("Reminder : "+space+" Storage space available.");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Vehicle> list() {
        return vehicles;
    }

    @Override
    public void save() {
        if(!vehicles.isEmpty()){
            try {
                PrintWriter writer = new PrintWriter("Vehicles.txt", "UTF-8");
                // write vehicle data from arrayList
                for(Vehicle item : vehicles){
                    // check if the item of vehicle type
                    if(item instanceof Car){
                        // write vehicles info
                        writer.println(item.toString());
                    }
                    if(item instanceof Motorbike){
                        // write vehicles info
                        writer.println(item.toString());
                    }
                }
                writer.close();
                System.out.println("Text file generated successfully.");
                System.out.println("File Name : Vehicles.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // attach data columns
            // file genarete code reference from stackoverflow website
            // https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
        }else {
            System.out.println("Vehicle data not available.");
        }


    }

    @Override
    public boolean exsits(String numberPlate){
        // check vehicle item into array list
        Date date;
        return vehicles.contains(new Car(numberPlate))||vehicles.contains(new Motorbike(numberPlate));
    }
}
