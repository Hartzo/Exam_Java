package progetto;

//Too lazy to import each library
import java.util.*;

//Importing other packages containing classes with similar features is a better way to structure the project
//Even if the packages don't show up correctly in the IDE , they are subpackages of the "progetto" package
import progetto.model.*;
import progetto.task.*;

public class progetto {
    //The main method is used to create what we need and storing data from input
    //After we store the data we call each of the tasks of the exam project
    public static void main(String[] args) {

        //Creating an Array of Objects for each class of the project is a must for storing data
        ArrayList<Aircraft> aircraft_List = new ArrayList<>();
        ArrayList<Airport> airports_List = new ArrayList<>();
        ArrayList<Flight> flights_List = new ArrayList<>();

        //Opening a Scanner with a try command is more professional, remembering to close the scanner is a good practice
        try (Scanner input = new Scanner(System.in)) {

            //I chose a 'do-while' so we can call each task without closing the program
            //if we enter an empty string the program closes
            do {
                String line = input.nextLine();

                //Naming the array of strings "rows" to remember how many lines of input are going to be afterwards
                String[] rows = line.split(" ");
                int numAircraft = Integer.parseInt(rows[0]); //Simply storing numbers of the text as integers
                int numAirports = Integer.parseInt(rows[1]);
                int numFlights = Integer.parseInt(rows[2]);


                //Storing Aircraft Data reading n lines as many as numAircraft
                for(int i = 0; i < numAircraft; i++) {

                    line = input.nextLine();
                    aircraft_List.add(Aircraft.ReadingData(line));

                }
                //Storing Airports Data reading n lines as many as numAirports
                for(int i = 0; i < numAirports; i++) {

                    line = input.nextLine();
                    airports_List.add(Airport.ReadingData(line));

                }
                //Storing Flight data reading n lines as many numFlights
                for(int i = 0; i < numFlights; i++) {
                    line = input.nextLine();
                    flights_List.add(Flight.ReadingData(aircraft_List, line));
                }

                //Reading last line to identify which task the program has to run
                String[] tasks = input.nextLine().split(" ");
                //Calling each run method of the tasks ; task 2 and 3 have further data to collect
                //Using 'equalsIgnoreCase()' method as muscle memory ,we don't really need it in this program
                if (tasks[0].equalsIgnoreCase("TASK1"))
                    Task1.run(aircraft_List, airports_List, flights_List);

                else if (tasks[0].equalsIgnoreCase("TASK2")) {
                    int p = Integer.parseInt(tasks[1]);
                    int q = Integer.parseInt(tasks[2]);
                    Task2.run(airports_List, flights_List, p , q);
                }

                else if (tasks[0].equalsIgnoreCase("TASK3")) {
                    int newFlights = Integer.parseInt(tasks[1]);
                    //Creating a new array of objects for the new flights
                    ArrayList<Flight> newFlight_List = new ArrayList<>();
                    //Same 'for-loop' as before , we need to pass new flights
                    //Having a 'Reading' method makes the code easy to read
                    for(int i = 0; i < newFlights; i++) {
                        line = input.nextLine();
                        newFlight_List.add(Flight.ReadingData(aircraft_List, line));
                    }
                    Task3.run(airports_List, flights_List, newFlight_List);  //We don't need aircraft list
                }

            } while (input.hasNextLine());

//In the Exam track the input is considered always correct, so we don't need to look up for exceptions
//By the way a nice 'try-catch' for the scanner is a fancy way to close the program while printing an error
        } catch (Exception e) {
            System.err.println("Error!" + e.getMessage());
        }

    }
}
