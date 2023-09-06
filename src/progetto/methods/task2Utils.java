package progetto.methods;

//Importing the class we need , only two of them , we are calling the aircraft through the flight list
import progetto.model.Airport;
import progetto.model.Flight;

import java.util.*;

//This Class contains all the methods that are needed in the Task2 Class
//In this way the program is well written , more modular and easy to maintain
public class task2Utils {

    //This method is a finder for the code of the matching airport, and it returns the country of that airport
    public static String getCountryByAirportCode(ArrayList<Airport> airports, String airportCode) {
        //Iterating through the list of airports
        for (Airport a : airports) {
            //Checking if the airport code of the current airport matches the target airport code
            if (a.getAirport_code().equals(airportCode)) {
                //If there's a match,return the country associated with that airport
                return a.getCountry();
            }
        }
        //If there is no match after iterating through all airports, return an empty string as requested
        return "";
    }


    //This method checks if there are at least 'q' flights and 'p' aircraft in flights
    //if this condition is met it does another checks for flights with the same aircraft and returns the counter
    //It has a lot of arguments in order to iterate and check all these things, the HashMap helps the method
    public static int getCommonAircraftFlights(int p, int q, Flight f, int numFlights, int AircraftInFlights, Map<Integer,
            Set<String>> aircraftInFlights_Day, String aircraftCode, int FlightsCommonAircraft) {
        //Checking if there are at least q flights and p aircraft in flights
        if (numFlights >= q && AircraftInFlights >= p) {
            boolean commonAircraft = false;
            //Looping over a range of days, including the current day (f.getDay())
            for (int i = f.getDay() - 2; i <= f.getDay() + 2; i++) {
                //Skip the current day itself
                if (i != f.getDay() && aircraftInFlights_Day.containsKey(i)) {
                    //Checking if the aircraft code is present in flights on the same day (i)
                    if (aircraftInFlights_Day.get(i).contains(aircraftCode)) {
                        commonAircraft = true;
                        break;
                    }
                }
            }
            //If there is a common aircraft on nearby days, increment the count
            if (commonAircraft) {
                FlightsCommonAircraft++;
            }
        }
        //Returning the updated counter of flights with a common aircraft
        return FlightsCommonAircraft;
    }

    //This method counts the number of flights with different airports , checking the departure and arrival airports
    public static int getDifferentAirportsFlights(ArrayList<Airport> airports_List, Flight f, int FlightsDifferentAirports) {
        //We need the country of departure airport, we are using the method declared before
        String departureCountry = task2Utils.getCountryByAirportCode(airports_List, f.getDepartureAirport());
        //We need the country of arrival airport, we are using the method declared before
        String arrivalCountry = task2Utils.getCountryByAirportCode(airports_List, f.getArrivalAirport());
        //Checking if the departure and arrival airports are in different countries
        if (!departureCountry.equals(arrivalCountry)) {
            FlightsDifferentAirports++; //Incrementing the counter of flights with different airports
        }
        //Returning the updated counter
        return FlightsDifferentAirports;
    }

}
