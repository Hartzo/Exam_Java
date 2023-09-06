package progetto.model;

//I like this class, it's simple and is not linked with the others , so we don't need to import other libraries

//Creating an Airport Object with the information provided within the exam files("Traccia A", "Esempi_A")
public class Airport {
    //Declaring private variables is better than declaring them public
    //In order to get them visible we need getter/setter methods
    private String airport_code;
    private String country;
    private int maxAircraft;

    //The Object constructor
    public Airport(String code, String country, int max){
        this.airport_code = code;
        this.country = country;
        this.maxAircraft = max;
    }


    /*LOMBOK SPAM
     *Project Lombok can be a useful library to prevent 'code bloat'!
     *with this library getter and setter methods can be generated during compilation with a simple annotation
     *for example writing '@Getter' and '@Setter' we can avoid writing all these methods.
     *Annotation processor (APT) libraries saves a lot of time and are helpful for lazy programmers like me
     */
    public String getAirport_code() {
        return airport_code;
    }


    public void setAirport_code(String airport_code) {
        this.airport_code = airport_code;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public int getMaxAircraft() {
        return maxAircraft;
    }


    public void setMaxAircraft(int maxAircraft) {
        this.maxAircraft = maxAircraft;
    }

    //Using a method to read data from lines of input
    //At the same time creating an Object that will be stored in an arrayList
    public static Airport ReadingData(String data) {

        //Using an array of strings to help us analyze data divided by "," .
        //We store them in the variables then we return directly the constructor with those variables
        String[] airportData = data.split(",");
        String code = airportData[0];
        String country= airportData[1];
        int max = Integer.parseInt(airportData[2]);

        return new Airport(code, country, max);
    }
}
