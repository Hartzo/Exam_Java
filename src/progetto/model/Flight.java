package progetto.model;

//Importing ArrayList library,since we are linking the Flight Object with the Aircraft Object
//We need to pass the array list of the Aircraft Object to call the method in the Aircraft Class
import java.util.ArrayList;

//Creating a Flight Object with the information provided within the exam files("Traccia A", "Esempi_A")
public class Flight {
    //Declaring private variables is better than declaring them public, in order to get them visible we need getter/setter methods
    private Aircraft aircraft;
    private int passengers;
    private String departureAirport; //Airport departureAirport;
    private String arrivalAirport;  //Airport arrivalAirport;
    private int day;

    //The Object constructor
    public Flight(Aircraft aircraft, int numPassengers, String departure, String arrival, int day){
        this.aircraft = aircraft;
        this.passengers = numPassengers;
        this.departureAirport = departure;
        this.arrivalAirport = arrival;
        this.day = day;
    }

    /*LOMBOK SPAM
     *Project Lombok can be a useful library to prevent 'code bloat'!
     *with this library getter and setter methods can be generated during compilation with a simple annotation
     *for example writing '@Getter' and '@Setter' we can avoid writing all these methods.
     *Annotation processor (APT) libraries saves a lot of time and are helpful for lazy programmers like me
     */

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    //Using a method to read data from lines of input
    //At the same time creating an Object that will be stored in an arrayList
    public static Flight ReadingData(ArrayList<Aircraft> aircraft_List, String data) {

        //Using an array of strings to help us analyze data divided by " " .
        //We store them in the variables then we return directly the constructor with those variables.
        //Since one of the variables is an Object we need to store the exact Object of the code given from input
        String[] flightData = data.split(" ");
        String aircraftCode= flightData[0]; //Storing the Aircraft code data into a string
        int numPassengers = Integer.parseInt(flightData[1]);
        String departure= flightData[2];
        String arrival= flightData[3];
        int numDay = Integer.parseInt(flightData[4]);

        //We are now passing the string to the method within the Aircraft class
        //this is a simple finder that gives us the exact Object with the same code
        Aircraft aircraft = Aircraft.fromCode(aircraft_List, aircraftCode);

        return new Flight(aircraft, numPassengers, departure, arrival, numDay);

    }

}
