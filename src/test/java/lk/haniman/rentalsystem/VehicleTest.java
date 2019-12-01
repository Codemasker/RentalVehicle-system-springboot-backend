package lk.haniman.rentalsystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VehicleTest {

    // test models
    Vehicle car = new Car("045","Toyota",100,"SS0","CAR",6,"AUTO","Petrol");
    Motorbike motorbike;

    // Test Class
    private RentalVehicleManager rentalVehicleManager;

    @Before
    public void init(){
        rentalVehicleManager = new WestminsterRentalVehicleManager();
    }

//    @Test
//    public void addCarTest(){
//        assertEquals(car,rentalVehicleManager.add(car));
//    }
}
