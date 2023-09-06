package progetto.task;

import java.util.*;

//Importing the Utils class in order to use the methods for the task
import progetto.methods.task3Utils;
//Importing the Objects Classes directly into the task
import progetto.model.Airport;
import progetto.model.Flight;

//This is the class for the Third Task ,it has a run method that calls the every method of its util class
public class Task3 {

    //creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Airport> airports_List, ArrayList<Flight> flights_List, ArrayList<Flight> newFlight_List) {

        //Initializing boolean variables with the methods
        boolean taskPoint1 = task3Utils.missingAirports(airports_List, newFlight_List);
        boolean taskPoint2 = task3Utils.hasBridgeFlight(newFlight_List, flights_List);
        boolean taskPoint3 = task3Utils.hasOrdinaryFlight(newFlight_List);

        //if all points are true then we print "VALID" as requested
        if(taskPoint1 && taskPoint2 && taskPoint3) System.out.println("VALID");
        //if just one condition is false then we print "NOT VALID"
        else System.out.println("NOT VALID");
    }

}
