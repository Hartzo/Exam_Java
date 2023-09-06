package progetto.task;

import java.util.*;

//Importing the Utils class in order to use the methods for the task
import progetto.methods.task1Utils;
import progetto.methods.task2Utils;
//Importing the Objects Classes directly into the task
import progetto.model.Airport;
import progetto.model.Flight;

//This is the class for the Second Task , since there are a lot of iteration it's not very modular
//Nonetheless there is a task 2 utils class that has two of the methods called in this class
public class Task2 {

    //creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Airport> airports_List, ArrayList<Flight> flights_List, int p, int q) {
        //We need to create a map to store the aircraft in flights for each day
        Map<Integer, Set<String>> aircraftInFlights_Day = new HashMap<>();

        //Initializing variables to zero,so we can increment them in the for-each loop
        int AircraftInFlights = 0;        //Counter of aircraft in flights
        int numFlights = 0;               //Total number of flights
        int FlightsCommonAircraft = 0;    //Counter of flights with common aircraft
        int FlightsDifferentAirports = 0; //Counter of flights with different departure and arrival airports

        //Get the count of airports with overboard flights using our utils method
        int AirportsInOverboard = task1Utils.isOverboard(flights_List);

        //Starting the big iteration
        for (Flight f : flights_List) {
            //Getting the aircraft code for the flight
            String aircraftCode = f.getAircraft().getAircraft_code();
            //Checking if the aircraftInFlights_Day map contains the day, if not, add it
            if (!aircraftInFlights_Day.containsKey(f.getDay())) {
                aircraftInFlights_Day.put(f.getDay(), new HashSet<>());
            }
            //Checking if the aircraft code is already in flights for the day, if not, add it
            if (!aircraftInFlights_Day.get(f.getDay()).contains(aircraftCode)) {
                aircraftInFlights_Day.get(f.getDay()).add(aircraftCode);
                AircraftInFlights++; //Incrementing the counter of aircraft in flights
            }
            numFlights++; //Incrementing the total number of flights

            //Getting the number of flights with common aircraft using our utils method
            FlightsCommonAircraft = task2Utils.getCommonAircraftFlights(p, q, f, numFlights, AircraftInFlights,
                    aircraftInFlights_Day, aircraftCode, FlightsCommonAircraft);

            //Calculating the number of flights with different departure and arrival airports
            FlightsDifferentAirports = task2Utils.getDifferentAirportsFlights(airports_List, f, FlightsDifferentAirports);
        }

        //Initializing the 4 points with the information of the track, every point of task 2 is a boolean
        boolean taskPoint1 = p <= AircraftInFlights && q <= numFlights;
        boolean taskPoint2 = FlightsCommonAircraft >= p;
        boolean taskPoint3 = AirportsInOverboard >= p && AirportsInOverboard <= q;
        boolean taskPoint4 = FlightsDifferentAirports >= p;

        //This is our output , if all of these conditions are valid we are printing YES
        if (taskPoint1 && taskPoint2 && taskPoint3 && taskPoint4) System.out.println("YES");
        //If only one of them is not valid we are printing NO
        else System.out.println("NO");
    }

}
