package progetto.methods;

//Importing the class needed
import progetto.model.Aircraft;
import progetto.model.Airport;
import progetto.model.Flight;

import java.util.*;
/*This API is very powerful when it comes to process sequences of elements , it reduces operations and collects
  everything into a collection ,which is a useful framework to manipulate every group of objects;
  They can do operations such as searching, sorting, insertion, manipulation, and deletion. */
import java.util.stream.Collectors;


//This Class contains all the methods that are needed in the Task1 Class
//In this way the program is well written , more modular and easy to maintain
public class task1Utils {

    //Creating a method to check if a Flight is overboard, we need it in the Task1 Class
    public static int isOverboard(ArrayList<Flight> flights_List) {
        int numOverboard = 0;
        for(Flight f : flights_List) {
            int numPassengers = f.getPassengers();
            int MaxPassengers = f.getAircraft().getMaxPassengers();
            //A simple casting for the percentage
            int capacity = (int)(MaxPassengers*(90.0f/100.0f));
            if (numPassengers > capacity) numOverboard++;
        }
        return numOverboard;
    }

    //Creating a method to check if a Flight is under-board, we need it in the Task1 Class
    public static int isUnderboard(ArrayList<Flight> flights_List) {
        int numUnderboard = 0;
        for(Flight f : flights_List) {
            int numPassengers = f.getPassengers();
            int MaxPassengers = f.getAircraft().getMaxPassengers();
            //A simple casting for the percentage
            int capacity = (int)(MaxPassengers*(10.0f/100.0f));
            if (numPassengers < capacity) numUnderboard++;
        }
        return numUnderboard;
    }

    //Creating a method to find the airport code that has the max amount of Aircraft
    public static String findAirportWithMaxAircraft(ArrayList<Flight> flights_List) {
        //Creating a HashMap to store the count of aircraft for each airport
        Map<String, Integer> airportAircraftCount = new HashMap<>();

        //Iterating through the flight list
        for (Flight f : flights_List) {
            String departureAirportCode = f.getDepartureAirport();
            String arrivalAirportCode = f.getArrivalAirport();

            //Updating the count for the departure airport code in the HashMap
            //If it doesn't exist, initialize it to 0 and then increment by 1
            airportAircraftCount.put(departureAirportCode, airportAircraftCount.getOrDefault(departureAirportCode, 0) + 1);
            airportAircraftCount.put(arrivalAirportCode, airportAircraftCount.getOrDefault(arrivalAirportCode, 0) + 1);
        }
        //Finding the maximum count of aircraft among all airports
        int maxCount = Collections.max(airportAircraftCount.values());

        //Creating a list to store airport codes with the maximum count of aircraft
        List<String> airportsWithMaxAircraft = new ArrayList<>();

        //Iterating through the entries in the HashMap
        for (Map.Entry<String, Integer> entry : airportAircraftCount.entrySet()) {
            //If the count of aircraft for an airport matches the maximum count, add it to the list
            if (entry.getValue() == maxCount) {
                airportsWithMaxAircraft.add(entry.getKey());
            }
        }
        //Sorting the list of airports with the maximum count, using collections makes it simpler
        Collections.sort(airportsWithMaxAircraft);
        //Check if the list is not empty
        if (!airportsWithMaxAircraft.isEmpty()) {
            return airportsWithMaxAircraft.get(0); //Returning the airport code of the first airport in the sorted list
        } else {
            return "";//If the list is empty, return an empty string as requested from the task
        }
    }

    //This method counts how many aircraft there are with the tank capacity greater than 108
    public static int aircraftTankMoreThan108(ArrayList<Aircraft> aircraft_List){
        //This method can also be coded with a stream API, streaming the list and using the count() method
        int count = 0;
        //iterating the list and when we find an object with the condition we add it to the count
        for (Aircraft a : aircraft_List) {
            if (a.getTankCapacity() > 108) count++;
        }
        return count;
    }

    //This method calculates the day with the most flights and returns that day's value as an integer
    //It's using Stream API because with the for each iterating with map values doesn't work very well
    //It needs to have distinct values to store
    public static long getMostFlights(ArrayList<Flight> flights_List) {
        //Creating a HashMap to store the count of flights for each day
        Map<Integer, Long> flightsPerDay = flights_List.stream()
                .collect(Collectors.groupingBy(Flight::getDay, Collectors.counting()));
        //Initializing a variable to store the day with the most flights
        int dayWithMostFlights = flightsPerDay.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()) //Find the entry with the maximum count
                .map(Map.Entry::getKey)//Extract the key (day) from the entry
                .orElse(0); //If there are no flights,set default to day 0
        //Returning the day with the most flights
        return dayWithMostFlights;
    }

    //Same method as the previous one, we count if there is an aircraft with 42 flights in the last year
    public static int aircraftWith42Flights(ArrayList<Aircraft> aircraft_List){
        //This method can also be coded with a stream API, streaming the list and using the count() method
        int count = 0;
        //iterating the list and when we find an object with the condition we add it to the count
        for(Aircraft a : aircraft_List){
            if(a.getFlightsLastYear() == 42) count++;
        }
        return count;
    }

    //This method helps us calculate the number of airports without connections
    //using HashSet,so we don't count the same airport code multiple times
    public static int getNoConnections(ArrayList<Airport> airports_List, ArrayList<Flight> flights_List) {
        //Creating a HashSet to store unique airport codes with connections
        //Using a HashSet is more efficient, optimized for memory usage and for tests
        Set<String> airportCodesWithConnections = new HashSet<>();
        //Iterating through the list of flights
        for (Flight f : flights_List) {
            //Adding the departure airport code of each flight into the HashSet
            airportCodesWithConnections.add(f.getDepartureAirport());
        }
        //Let's initialize a counter for airports with no connections
        int numAirportsNoConnections = 0;
        //Iterating through the list of airports
        for (Airport a : airports_List) {
            //Doing a check if the airport code is not in the HashSet , so there is no connection
            if (!airportCodesWithConnections.contains(a.getAirport_code())) {
                //Incrementing the counter of airports with no connections
                numAirportsNoConnections++;
            }
        }
        //Returning the count of airports with no connections
        return numAirportsNoConnections;
    }

    //This method returns the number of days when the total number of passengers across all flights on that day is 815 or more
    public static int get815Passengers(ArrayList<Flight> flights_List) {
        //Creating a HashMap to store the total passengers for each day
        HashMap<Integer, Integer> totalPassengersPerDay = new HashMap<>();

        //Iterating through each flight in the flights_List
        for (Flight f : flights_List) {
            int day = f.getDay(); //The day of the flight
            int passengers = f.getPassengers(); //The number of passengers for the flight

            //Updating the total passengers for the specific day in the HashMap
            //If the day is not in the HashMap, initialize it with 0 and add passengers
            totalPassengersPerDay.put(day, totalPassengersPerDay.getOrDefault(day, 0) + passengers);
        }
        //Initializing a counter to keep track of days with 815 or more passengers
        int days815Passengers = 0;
        //Iterating through the values of the HashMap , in this case the passengers
        for (int passengers : totalPassengersPerDay.values()) {
            if (passengers >= 815) { //Check if the passenger count for a day is greater than or equal to 815
                days815Passengers++; //Incrementing the counter
            }
        }
        //Here's the total count of days with 815 or more passengers
        return days815Passengers;
    }
}
