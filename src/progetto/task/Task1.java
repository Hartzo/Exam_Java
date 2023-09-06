package progetto.task;

import java.util.*;

//Importing the Utils class in order to use the methods for the task
import progetto.methods.task1Utils;
//Importing the Objects Classes directly into the task
import progetto.model.Aircraft;
import progetto.model.Airport;
import progetto.model.Flight;

//This is the Class for the First Task , it has only one method that calls the others
public class Task1 {

    //creating a run method with the lists as arguments in order to satisfy the task's requests
    public static void run(ArrayList<Aircraft> aircraft_List, ArrayList<Airport> airports_List, ArrayList<Flight> flights_List) {

        //the first point of the task is printing how many objects there are in the list , pretty simple with the size() method
        int numAircraft = aircraft_List.size();
        int numAirports = airports_List.size();
        int numFlights = flights_List.size();

        //Task point n.2 ,using these two methods we get exactly how many overboard/underboard flights there are in our data
        int numOverboard = task1Utils.isOverboard(flights_List);
        int numUnderboard = task1Utils.isUnderboard(flights_List);

        //Task point n.3 , this method gives us the airport code that has the highest number of aircraft
        String airportWithMaxAircraft = task1Utils.findAirportWithMaxAircraft(flights_List);

        //Task point n.4 , we are finding how many aircraft have the tank capacity higher than 108
        int numAircraftWithTankCapacityGreaterThan108 = task1Utils.aircraftTankMoreThan108(aircraft_List);

        //Task point n.5 , calling the method to get the first day which has the most flights
        long dayWithMostFlights = task1Utils.getMostFlights(flights_List);

        //Task point n.6, calling the method to get the number of aircraft which did exactly 42 flights in the last year
        int numAircraftWith42TripsLastYear = task1Utils.aircraftWith42Flights(aircraft_List);

        //Task point n.7 , the method is iterating the list of airports and flights to get the number of connections
        int numAirportsWithoutConnections = task1Utils.getNoConnections(airports_List, flights_List);

        //Task point n.8 , we call the method in order to get how many days at least 815 passengers have travelled
        int daysWithAtLeast815Passengers = task1Utils.get815Passengers(flights_List);

        //Now we print the results of the information the TASK1 is asking
        System.out.println(numAircraft + " " + numAirports + " " + numFlights);
        System.out.println(numOverboard + " " + numUnderboard);
        System.out.println(airportWithMaxAircraft);
        System.out.println(numAircraftWithTankCapacityGreaterThan108);
        System.out.println(dayWithMostFlights);
        System.out.println(numAircraftWith42TripsLastYear);
        System.out.println(numAirportsWithoutConnections);
        System.out.println(daysWithAtLeast815Passengers);
    }

}
