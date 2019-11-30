package lk.haniman.rentalsystem;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Database {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;



    public Database() {
        // mongodb database connection code got from mongodb document site
        //http://mongodb.github.io/mongo-java-driver/3.10/driver/tutorials/
        mongoClient = MongoClients.create("mongodb://haniman:hello1234@rentalsystem-shard-00-00-ng0sk.mongodb.net:27017,rentalsystem-shard-00-01-ng0sk.mongodb.net:27017,rentalsystem-shard-00-02-ng0sk.mongodb.net:27017/test?ssl=true&replicaSet=Rentalsystem-shard-0&authSource=admin&retryWrites=true&w=majority");
        database = mongoClient.getDatabase("rentalsystem");
        collection = database.getCollection("vehicles");




    }


    public List<Vehicle> get(){
        System.out.println("Retrieving data...");
        // create Vehicle item array list
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        // get all data from database
        for(Document document : collection.find()){
            // check Vehicle item type by "vehicleType" key
            if(document.getString("vehicleType").equals("CAR")){
                // add item into array list
                vehicles.add(new Car(document.getString("numberPlate"),
                        document.getString("brand"),
                        document.getInteger("maxSpeed"),
                        document.getString("model"),
                        document.getString("vehicleType"),
                        document.getInteger("numberOfSeat"),
                        document.getString("gearType"),
                        document.getString("fuelType")));
            }else{
                vehicles.add(new Motorbike(document.getString("numberPlate"),
                        document.getString("brand"),
                        document.getInteger("maxSpeed"),
                        document.getString("model"),
                        document.getString("vehicleType"),
                        document.getString("Starting"),
                        document.getString("category"),
                        document.getString("powerType")));
            }
        }
        // return Vehicle item list
        return vehicles;
    }

    public void add(Vehicle vehicle){
        Document document = new Document();
        if(vehicle instanceof Car){
            document = new Document("numberPlate", vehicle.getNumberPlate())
                    .append("brand", vehicle.getBrand())
                    .append("maxSpeed", vehicle.getMaxSpeed())
                    .append("model",vehicle.getModel())
                    .append("vehicleType","CAR")
                    .append("numberOfSeat",((Car) vehicle).getNumberOfSeat())
                    .append("gearType",((Car) vehicle).getGearType())
                    .append("fuelType",((Car) vehicle).getFuelType());
        }else if(vehicle instanceof Motorbike){
            document = new Document("numberPlate", vehicle.getNumberPlate())
                    .append("brand", vehicle.getBrand())
                    .append("maxSpeed", vehicle.getMaxSpeed())
                    .append("model",vehicle.getModel())
                    .append("vehicleType","MOTORBIKE")
                    .append("starting",((Motorbike) vehicle).getStarting())
                    .append("category",((Motorbike) vehicle).getCategory())
                    .append("powerType",((Motorbike) vehicle).getPowerType());
        }

        collection.insertOne(document);
    }



    public void delete(Vehicle vehicle){
        collection.deleteOne(eq("numberPlate",vehicle.getNumberPlate()));
    }
}
