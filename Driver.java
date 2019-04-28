package theater;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import structs.ListCDLSBased;

public class Driver {

    private final static Scanner stdin = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {

        MovieHouse house = new MovieHouse();

        System.out.println("Welcome to the Movie Theater System!\n" +
                "Tonight's feature are: \"Shazam!\" and \"Dumbo\"\n");

        int rows, seatsPerRow;
        double ticketPrice;
        System.out.print("Please enter information about the Shazam! Movie " +
                "Theater:\n\tEnter the number of rows: ");
        rows = stdin.nextInt();
        System.out.print("\tEnter the number of seats per row: ");
        seatsPerRow = stdin.nextInt();
        System.out.print("\tEnter the price per ticket: ");
        ticketPrice = stdin.nextDouble();


        // Create the Shazam! Movie Theater
        house.addTheater(new MovieTheater("Shazam!", rows,
                seatsPerRow, ticketPrice));

        System.out.print("Please enter information about the Dumbo Movie " +
                "Theater:\n\tEnter the number of rows: ");
        rows = stdin.nextInt();
        System.out.print("\tEnter the number of seats per row: ");
        seatsPerRow = stdin.nextInt();
        System.out.print("\tEnter the price per ticket: ");
        ticketPrice = stdin.nextDouble();

        // Create the Dumbo Movie Theater
        house.addTheater(new MovieTheater("Dumbo", rows,
                seatsPerRow, ticketPrice));
        
        // Add lines to the movie house
        house.addLine(new Line(false));
        house.addLine(new Line(false));
        house.addLine(new Line(true));

        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Enter selection: ");

            int selection = stdin.nextInt();
            System.out.println(selection + "\n");

            switch (selection) {
                case 0:
                    // TODO
                    // needs to report sales and kick out customers.
                	System.out.println("The Wonderful Movie Theater who earned $" + 
                				      house.getTotalRevenue() + " kicks out remaining "
                				      + "customers and closes...\n" +
                				      "Good Bye!");
                	running = false;
                    break;
                case 1:
                    //
                    break;
                case 2:
                    //
                    break;
                case 3:
                    //
                    break;
                case 4:
                    displayLineInfo(house);
                    break;
                case 5:
                    house.getTheater("Shazam!").displaySeats();
                    break;
                case 6:
                    //
                    break;
                case 7:
                    //
                    break;
            }
        }

    }

    private static void printMenu() {
        System.out.println("\nMenu:\n" +
                "0. End the program\n" +
                "1. Customer(s) enter(s) the Movie Theater\n" +
                "2. Customer buys ticket(s)\n" +
                "3. Customer(s) leave(s) the Theater\n" +
                "4. Display info about customers waiting for tickets\n" +
                "5. Display seating chart for Shazam! Movie Theater\n" +
                "6. Display seating chart for Dumbo Movie Theater\n" +
                "7. Display number of tickets sold and total earnings\n");
    }
    
    private static void displayLineInfo(MovieHouse house)
    {
    	ListCDLSBased<Line> lines = house.getLines();
    	int numLines = lines.size();
    	int index = 0;

    	while(index < numLines)
    	{
    		if(lines.get(index).isEmpty())
    		{
    			System.out.println("No customers in line " + (index + 1));
    		}
    		else
    		{
    			System.out.println(lines.toString());
    		}
    		
    		index++;
    	}
    	
    }


    private static void customerEnters(MovieHouse house)
    {
    	System.out.print("Enter customer name: ");
    	String name = stdin.next().trim();
    	System.out.println(name);
    	
    	System.out.print("Enter party size: ");
    	int size = stdin.nextInt();
    	System.out.println(size);
    	
    	System.out.print("Enter movie name: ");
    	String movieName = stdin.next().trim();
    	System.out.println(movieName);
    	
    	System.out.print("Is a child 11 or younger in this part(Y/N)? ");
    	String hasChildString = stdin.next().trim().toUpperCase();
    	System.out.println(hasChildString);
    	boolean hasChild = false;
    	if(hasChildString.equals("Y"))
    	{
    		hasChild = true;
    	}
    	
    	
    	house.getLines().get(0).addParty(new Party(name, size, hasChild, movieName));
    	
    	// TODO: Method for getting current line
    	System.out.println("Customer " + name + " is in " +
    						"ticket line.");

    }
    
    
    
    
}