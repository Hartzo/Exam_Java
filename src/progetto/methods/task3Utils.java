package progetto.methods;

import progetto.model.Airport;
import progetto.model.Flight;

import java.util.ArrayList;

//This Class contains all the methods that are needed in the Task3 Class
//In this way the program is well written , more modular and easy to maintain
public class task3Utils {

    //This method returns true if there is a missing airport in the new flight list
    //this help us with the conditions in task 3
    public static boolean missingAirports(ArrayList<Airport> airports_List, ArrayList<Flight> newFlight_List) {

        //Let's start initializing the result with false, assuming there aren't missing airports
        boolean result = false;
        //We start iterating each list in a nested loop of course
        for (Airport a : airports_List) {
            boolean found = false; //this guard is useful to find a missing airport
            for (Flight f : newFlight_List) {
                //now we are comparing the airport code with the airport of the new flights
                if (f.getArrivalAirport().equals(a.getAirport_code()) || f.getDepartureAirport().equals(a.getAirport_code())) {
                    found = true;
                    break;	//exiting the loop if we find an airport in the list - the result remains false
                }
            }
            if(!found) { //we enter this condition only if we don't find the airport
                result = true; //therefore we confirm there are missing airports
                break;
            }
        }
        return result;
    }

    //Creating a method to check if a Flight is ordinary as requested in the Task3 Class
    public static boolean isOrdinary(Flight flight) {
        int numPassengers = flight.getPassengers();
        int MaxPassengers = flight.getAircraft().getMaxPassengers();

        //Casting for the percentage
        int lowValue = (int)(MaxPassengers*(10.0f/100.0f)); //Capacity equals 10% of the MaxPassengers
        int highValue = (int)(MaxPassengers*(90.0f/100.0f)); //Capacity equals 90% of the MaxPassengers

        //Returning directly the boolean value of the simple expression
        return numPassengers >= lowValue && numPassengers <= highValue;
    }

    //This method returns a boolean if there is at least one ordinary flight in the new list of flights
    public static boolean hasOrdinaryFlight(ArrayList<Flight> newFlight_List) {
        //Starting the iteration within the new flight list to find the ordinary flight
        for (Flight f : newFlight_List) {
            //checking if 'f' is an ordinary flight
            if (isOrdinary(f)) {
                return true; //Returning true if we find the ordinary flight
            }
        }
        return false; //ordinary flight not found
    }

    //this method is called by the hasBridgeFlight method, it works as an identifier
    //if it respects the conditions explained in the exam track then is a bridge flight
    private static boolean isBridge(Flight Vx, Flight Va, Flight Vb) {
        //First condition for a bridge flight
        boolean condition1 = Vx.getDepartureAirport().equals(Va.getArrivalAirport());
        //Second condition for a bridge flight
        boolean condition2 = Vx.getArrivalAirport().equals(Vb.getDepartureAirport());
        // Third condition, next day of Va is the day of Vx and the previous day of Vb is the day of Vx
        boolean condition3 = (Vx.getDay() == Va.getDay() + 1) && (Va.getDay() == Vb.getDay() - 1);
        //Fourth condition , we compare if the aircraft is the same for our three flights
        boolean condition4 = Vx.getAircraft().getAircraft_code().equals(Va.getAircraft().getAircraft_code())
                && Va.getAircraft().getAircraft_code().equals(Vb.getAircraft().getAircraft_code());
        //Returning the result of the conditions
        return condition1 && condition2 && condition3 && condition4;
    }

    //Another boolean method to find if there is at least one bridge flight ,one of the condition of task3
    public static boolean hasBridgeFlight(ArrayList<Flight> newFlight_List, ArrayList<Flight> flights_List) {
        //First of all we need to store the two flight object of the flights_List
        //we start to iterate the list using the two variables Va and Vb
        for (Flight Va : flights_List) {
            for (Flight Vb : flights_List) {
                if (Va.equals(Vb)) { //This helps us avoid confronting the same flight
                    continue; //jumping the iteration if they are the same
                }
                //storing the possible bridge flight in the Vx Object, and we iterate in the new list
                for (Flight Vx : newFlight_List) {
                    if (isBridge(Vx, Va, Vb)) {
                        return true; //returning true if we find the bridge flight
                    }
                }
            }
        }
        return false; //False if we don't find it
    }
}
