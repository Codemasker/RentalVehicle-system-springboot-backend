package lk.haniman.rentalsystem;

import lk.haniman.rentalsystem.datatype.FuelType;
import lk.haniman.rentalsystem.datatype.GearType;
import lk.haniman.rentalsystem.datatype.VehicleType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(maxAge = 3600)
@SpringBootApplication
@RestController
public class Main {

    private static RentalVehicleManager rentalVehicleManager;
    private static Reservation reservationManager;

    public static void main(String[] args) {
        rentalVehicleManager = new WestminsterRentalVehicleManager();
        reservationManager = new ReservationManager();


        System.out.println("Westminster Rental Vehicle Management System");
        System.out.println("Select Option : ");
        // initial option  with 0
        int option = 0;
        do{
            System.out.println("1 - Add a new Vehicle");
            System.out.println("2 - Delete an Vehicle");
            System.out.println("3 - Print list of Vehicles");
            System.out.println("4 - Save Vehicle list");
            System.out.println("5 - Start web service");
            System.out.println("6 - Exit");
            Scanner scanner = new Scanner(System.in);
            option = getInt(scanner);
            switch (option){
                case 1:
                    addItem();
                    break;
                case 2:
                    deleteItem();
                    break;
                case 3:
                    printList();
                    break;
                case 4:
                    saveList();
                    break;
                case 5:
                    SpringApplication.run(Main.class, args);
                    break;
                case 6:

                    break;
                default:
                    System.out.println("This Option does not exist, Try again.");
                    break;
            }
        }while (option!=6);
        System.out.println("Finishing Process...");
        System.exit(0);

    }

    private static void saveList() {
        // call manger save instance method
        rentalVehicleManager.save();

    }

    private static void printList() {
        // check availability of vehicle list
        if(!rentalVehicleManager.list().isEmpty()){
            // sorting vehicle item
            Collections.sort(rentalVehicleManager.list());
            for(Vehicle vehicle : rentalVehicleManager.list()){
                // check subclass for subclass attributes
                if(vehicle instanceof Car){
                    System.out.println(vehicle.toString());
                }
                // check subclass for subclass attributes
                if(vehicle instanceof Motorbike){
                    System.out.println(vehicle.toString());
                }
            }
        }else{
            System.out.println("Vehicles data Not-available.");
        }
    }

    private static void deleteItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Vehicle number Plate :");
        String numberPlate = scanner.next();
        Vehicle car = new Car(numberPlate);
        Vehicle motorbike = new Motorbike(numberPlate);
        if (rentalVehicleManager.delete(car)){
            //  check this vehicle in schedule list
            if (reservationManager.exists(car)){
                // remove this vehicle from schedule list
                reservationManager.remove(car);
            }else if(reservationManager.exists(motorbike)){
                reservationManager.remove(motorbike);
            }

        }else if (rentalVehicleManager.delete(motorbike)){
            //  check this vehicle in schedule list
            if (reservationManager.exists(car)){
                // remove this vehicle from schedule list
                reservationManager.remove(car);
            }else if(reservationManager.exists(motorbike)){
                reservationManager.remove(motorbike);
            }
        }else {
            System.out.println("Vehicle not found!");
        }



    }

    private static void addItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Vehicle type : ");
        int option = 0;
        String vehicleType = "";
        // validate multi choice input
        do{
            System.out.println("1 - Car");
            System.out.println("2 - Motorbike");
            option = getInt(scanner);

            switch (option){
                case 1:
                    vehicleType = VehicleType.CAR.toString();
                    break;
                case 2:
                    vehicleType = VehicleType.MOTORBIKE.toString();
                    break;
                default:
                    System.out.println("Invalid Vehicle type. Try again");
                    break;
            }
        }while (option!=1&&option!=2);
        System.out.println("Number plate : ");
        String numberPlate = scanner.next();
        while (rentalVehicleManager.exsits(numberPlate)){
            System.out.println("This Vehicle number plate already exists. Re-Enter : ");
            numberPlate= scanner.next();
        }
        System.out.println("Brand Name : ");
        String brand = scanner.next();
        System.out.println("Maximum speed of Vehicle :");
        int maxSpeed = getInt(scanner);
        System.out.println("Model Name : ");
        String model = scanner.next();
        // validate multi choice input
        if (vehicleType.equals(VehicleType.CAR.toString())){
            System.out.println("No of seats : ");
            int seats = getInt(scanner);
            System.out.println("Select Fuel type :");
            String fuelType = "";
            int fuelOption = 0;
            do{
                System.out.println("1 - Petrol");
                System.out.println("2 - Decal");
                System.out.println("3 - Battery");
                fuelOption = getInt(scanner);

                switch (fuelOption){
                    case 1:
                        fuelType = FuelType.PETROL.toString();
                        break;
                    case 2:
                        fuelType = FuelType.DECAL.toString();
                        break;
                    case 3:
                        fuelType = FuelType.BATTERY.toString();
                        break;
                    default:
                        System.out.println("Invalid Vehicle fuel type. Try again.");
                        break;
                }
            }while (fuelOption!=1&&fuelOption!=2&&fuelOption!=3);
            System.out.println("Select Gear type :");
            String gearType = "";
            int gearOption = 0;
            do{
                System.out.println("1 - Manual gear");
                System.out.println("2 - Auto gear");
                gearOption = getInt(scanner);

                switch (gearOption){
                    case 1:
                        gearType = GearType.MANUAL.toString();
                        break;
                    case 2:
                        gearType = GearType.AUTO.toString();
                        break;
                    default:
                        System.out.println("Invalid Vehicle gear type. Try again.");
                        break;
                }
            }while (gearOption!=1&&gearOption!=2&&gearOption!=3);
            // pass vehicle item into manager
            rentalVehicleManager.add(new Car(numberPlate,brand,maxSpeed,model,VehicleType.CAR.toString(),seats,gearType,fuelType));
        }
        if (vehicleType.equals(VehicleType.MOTORBIKE.toString())){
            System.out.println("Motorbike starting type : ");
            String starting = scanner.next();
            System.out.println("Motorbike Category : ");
            String category = scanner.next();
            System.out.println("Power type : ");
            String powerType = "";
            int powerOption = 0;
            do{
                System.out.println("1 - Patrol Power");
                System.out.println("2 - Battery Power");
                powerOption = getInt(scanner);

                switch (powerOption){
                    case 1:
                        powerType = FuelType.PETROL.toString();
                        break;
                    case 2:
                        powerType = FuelType.BATTERY.toString();
                        break;
                    default:
                        System.out.println("Invalid Vehicle Power type. Try again.");
                        break;
                }
            }while (powerOption!=1&&powerOption!=2&&powerOption!=3);
            // pass vehicle item into manager
            rentalVehicleManager.add(new Motorbike(numberPlate,brand,maxSpeed,model,VehicleType.MOTORBIKE.toString(),starting,category,powerType));
        }

    }

    private static int getInt(Scanner scanner){
        // check integer input
        while (!scanner.hasNextInt()){
            System.out.println("only non numeric characters are allowed! Try Again");
            scanner.next();
        }
        // return correct value;
        return scanner.nextInt();

    }


    // API -  List all vehicles in GUI
    // spring route for list all vehicles
    // set routes and allowed method,content type
    @CrossOrigin(origins = "http://localhost:3000") // to allow specific client to access
    @RequestMapping(value = "/vehicle",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity vehicles(){
        if(!rentalVehicleManager.list().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(rentalVehicleManager.list());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rentalVehicleManager.list());
        }
    }


    // API -  Get one vehicle object
    // get one vehicle details
    // set routes and allowed method
    @CrossOrigin(origins = "http://localhost:3000") // to allow specific client to access
    @RequestMapping(value = "/vehicle/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity vehicles(@PathVariable("id") String id){
        Vehicle testCar = new Car(id);
        Vehicle testBike = new Motorbike(id);
        if (rentalVehicleManager.list().contains(testCar)){
            int index = rentalVehicleManager.list().indexOf(testCar);
            return ResponseEntity.status(HttpStatus.OK).body(rentalVehicleManager.list().get(index));
        }else if (rentalVehicleManager.list().contains(testBike)){
            int index = rentalVehicleManager.list().indexOf(testBike);
            return ResponseEntity.status(HttpStatus.OK).body(rentalVehicleManager.list().get(index));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    // API -  list all reservation schedule list
    @CrossOrigin(origins = "http://localhost:3000") // to allow specific client to access
    @RequestMapping(value = "/reservation",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity reservation(){
        if(!reservationManager.list().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(reservationManager.list());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reservationManager.list());
        }
    }

    // API -  book vehicle
    @CrossOrigin(origins = "http://localhost:3000") // to allow specific client to access
    @RequestMapping(value = "/reservation",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity reservation(@RequestBody RequestBook book){
        Vehicle testCar = new Car(book.getVehicleNumber());
        Vehicle testBike = new Motorbike(book.getVehicleNumber());
        if (rentalVehicleManager.list().contains(testCar)){
            int index = rentalVehicleManager.list().indexOf(testCar);
            testCar = rentalVehicleManager.list().get(index);
            Schedule schedule = new Schedule(new Date(book.getPickUpDate()),new Date(book.getDropOffDate()),testCar,book.getCustomerName(),book.getMobileNumber());
            reservationManager.book(schedule);
            if (reservationManager.list().contains(schedule)){
                return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }else if (rentalVehicleManager.list().contains(testBike)){
            int index = rentalVehicleManager.list().indexOf(testBike);
            testBike = rentalVehicleManager.list().get(index);
            Schedule schedule = new Schedule(new Date(book.getPickUpDate()),new Date(book.getDropOffDate()),testBike,book.getCustomerName(),book.getMobileNumber());
            reservationManager.book(schedule);
            if (reservationManager.list().contains(schedule)){
                return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
            }else {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // API -  check vehicle availability
    @CrossOrigin(origins = "http://localhost:3000") // to allow specific client to access
    @RequestMapping(value = "/reservation/check",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity reservationCheck(@RequestBody RequestSchedule requestSchedule){
        Vehicle testCar = new Car(requestSchedule.getNumberPlate());
        Vehicle testBike = new Motorbike(requestSchedule.getNumberPlate());
        Date checkDate = new Date(requestSchedule.getDate());
        if (rentalVehicleManager.list().contains(testCar)){
            int index = rentalVehicleManager.list().indexOf(testCar);
            return ResponseEntity.status(reservationManager.isAvailable(checkDate,rentalVehicleManager.list().get(index))?HttpStatus.OK:HttpStatus.NOT_FOUND).body(null);
        }else if (rentalVehicleManager.list().contains(testBike)){
            int index = rentalVehicleManager.list().indexOf(testBike);
            return ResponseEntity.status(reservationManager.isAvailable(checkDate,rentalVehicleManager.list().get(index))?HttpStatus.OK:HttpStatus.NOT_FOUND).body(null);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


}
