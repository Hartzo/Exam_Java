package progetto.model;

//Importing ArrayList library,since we are linking the Aircraft Object with the Flight Object
//We need to pass the array of objects of the Aircraft to return the exact object into the Flight Object
import java.util.ArrayList;

//Creating an Aircraft Object with the information provided within the exam files("Traccia A", "Esempi_A")
public class Aircraft {
    //Declaring private variables is better than declaring them public
    //In order to get them visible we need getter/setter methods
    private String aircraft_code;
    private int maxPassengers;
    private int tankCapacity;
    private int flightsLastYear;

    //The Object constructor
    public Aircraft(String code, int passengers, int fuelTank, int flights){

        this.aircraft_code = code;
        this.maxPassengers = passengers;
        this.tankCapacity = fuelTank;
        this.flightsLastYear = flights;

    }

    /*
     *Project Lombok can be a useful library to prevent 'code bloat'!
     *with this library getter and setter methods can be generated during compilation with a simple annotation
     *for example writing '@Getter' and '@Setter' we can avoid writing all these methods.
     *Annotation processor (APT) libraries saves a lot of time and are helpful for lazy programmers like me
     */
    public String getAircraft_code() {
        return aircraft_code;
    }

    public void setAircraft_code(String aircraft_code) {
        this.aircraft_code = aircraft_code;
    }


    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public int getFlightsLastYear() {
        return flightsLastYear;
    }

    public void setFlightsLastYear(int flightsLastYear) {
        this.flightsLastYear = flightsLastYear;
    }

    //Using a method to read data from lines of input
    //At the same time creating an Object that will be stored in an arrayList
    public static Aircraft ReadingData(String data) {

        //Using an array of strings to help us analyze data divided by " " .
        //We store them in the variables then we return directly the constructor with those variables
        String[] aircraftData = data.split(" ");
        String code = aircraftData[0];
        int passengers = Integer.parseInt(aircraftData[1]);
        int fuelTank = Integer.parseInt(aircraftData[2]);
        int flights = Integer.parseInt(aircraftData[3]);

        return new Aircraft(code, passengers, fuelTank, flights);
    }

    //With this method we "simply" compare the aircraft Code with the Object we stored before
    //It's a finder that returns the exact object we want simply searching with the code
    //We will use this method to store an aircraft Object in the Flight Object
    public static Aircraft fromCode(ArrayList<Aircraft> aircraft_List, String aircraftCode) {
        for (Aircraft aircraft : aircraft_List) {
            if (aircraft.getAircraft_code().equals(aircraftCode)) {
                return aircraft;
            }
        }
        return null; //Returning null if the chosen Aircraft is not found
    }
}
