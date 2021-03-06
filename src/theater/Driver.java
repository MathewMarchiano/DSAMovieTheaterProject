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
        boolean justOpened = true;

        System.out.println("Welcome to the Movie Theater System!\n" +
                "Tonight's feature are: \"Shazam!\" and \"Dumbo\"\n");

        int rows, seatsPerRow;
        double ticketPrice;
        System.out.print("Please enter information about the Shazam! Movie " +
                "Theater:\n\tEnter the number of rows: ");
        rows = stdin.nextInt();
        System.out.println(rows);
        System.out.print("\tEnter the number of seats per row: ");
        seatsPerRow = stdin.nextInt();
        System.out.println(seatsPerRow);
        System.out.print("\tEnter the price per ticket: ");
        ticketPrice = stdin.nextDouble();
        System.out.println(ticketPrice);


        // Create the Shazam! Movie Theater
        house.addTheater(new MovieTheater("Shazam!", rows,
                seatsPerRow, ticketPrice));

        System.out.print("Please enter information about the Dumbo Movie " +
                "Theater:\n\tEnter the number of rows: ");
        rows = stdin.nextInt();
        System.out.println(rows);
        System.out.print("\tEnter the number of seats per row: ");
        seatsPerRow = stdin.nextInt();
        System.out.println(seatsPerRow);
        System.out.print("\tEnter the price per ticket: ");
        ticketPrice = stdin.nextDouble();
        System.out.println(ticketPrice);

        // Create the Dumbo Movie Theater
        house.addTheater(new MovieTheater("Dumbo", rows,
                seatsPerRow, ticketPrice));

        // Add lines to the movie house
        // Per instructions: 2 regular and 1 express lines
        house.addLine(new Line("Reg1", false));
        house.addLine(new Line("Reg2", false));
        house.addLine(new Line("Exp1", true));

        house.setFirstExpressLine();

        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Enter selection: ");

            int selection = stdin.nextInt();
            System.out.println(selection + "\n");

            switch (selection) {
                case 0:
                    System.out.println("Everyone has been kicked out of the " +
                            "Movie House. Final Report:");
                    house.reportSales();
                	running = false;
                    break;
                case 1:
                    customerEnters(house);
                    break;
                case 2:
                    customerBuys(house, justOpened);
                    if (justOpened) {
                        justOpened = false;
                    }
                    break;
                case 3:
                    customerLeaves(house);
                    break;
                case 4:
                    displayLineInfo(house);
                    break;
                case 5:
                    house.getTheater("Shazam!").displaySeats();
                    break;
                case 6:
                    house.getTheater("Dumbo").displaySeats();
                    break;
                case 7:
                    house.reportSales();
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

    private static void displayLineInfo(MovieHouse house) {
        ListCDLSBased<Line> lines = house.getLines();
        int numLines = lines.size();
        int index = 0;

        while (index < numLines) {
            Line line = lines.get(index);
            if (line.isEmpty()) {
                System.out.println("No customers in line " + (index + 1));
            } else {
                String expressString = line.getIsExpress() ? "Express" : "";
                System.out.printf("\n%s Line #%d\n" +
                        "----------\n", expressString, index + 1);
                System.out.println(line.toString());
            }

            index++;
        }

    }


    private static void customerEnters(MovieHouse house)
    {
    	String name = null;
    	// Ask for name. If there's a duplicate, keep asking.
    	do
    	{
    		System.out.print("Enter customer name: ");
        	name = stdin.next().trim();
        	System.out.println(name);
    	}while(house.findDupes(name));
    	
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
    	
    	house.addPartyToLine(new Party(name, size, hasChild, movieName));
    	
    	System.out.println("Customer " + name + " is in " +
    						"ticket line.");
    }
  
    private static void customerLeaves(MovieHouse house) {

        if (house.areLinesEmpty()) {
            System.out.println("No customers are in line right now.");
        } else {
            System.out.print("Enter customer name: ");
            String name = stdin.next().trim();
            System.out.println(name);

            boolean result = house.removePartyFromTheaters(name);
            if (result) {
                System.out.printf("Customer %s has left the Movie Theater\n",
                        name);
            } else {
                System.out.println("This customer is not in the Movie Theater!");
            }
        }
    }

    private static void customerBuys(MovieHouse house, boolean justOpened) {
        Party party = null;
        if (justOpened) {
            System.out.print("Which line would you like to serve first? ");
            String lineName = stdin.next().trim();
            System.out.println(lineName);
            party = house.getNextCustomer(lineName);
        } else {
            party = house.getNextCustomer();
        }
        if (party == null) {
            System.out.println("There are no customers in the movie house " +
                    "at the moment");
        } else {
            MovieTheater theater = house.getTheater(party.getDesiredMovie());
            int seatIndex = theater.seatParty(party);
            if (seatIndex == -1) {
                System.out.println("Movie cannot seat your party.\n" +
                        "Did you want to watch the other movie?");
                String response = stdin.next().trim().toUpperCase();
                if (response.equals("Y")) {
                    String otherMovieTitle;
                    if (party.getDesiredMovie().equals("Dumbo")) {
                        otherMovieTitle = "Shazam!";
                    } else {
                        otherMovieTitle = "Dumbo";
                    }
                    seatIndex = house.getTheater(otherMovieTitle)
                            .seatParty(party);
                    if (seatIndex == -1) {
                        System.out.println("Sorry we could not seat you there " +
                                "either. Have a nice day.");
                    } else {
                        System.out.printf("%s party of %d has been seated in " +
                                        "the %s Movie Theater\n", party.getRepresentative(),
                                party.getSize(), otherMovieTitle);
                        house.getTheater(otherMovieTitle).incrementSale(party.getSize());
                    }
                } else {
                    System.out.println("Thank you have a nice day.");
                }
            } else {
                System.out.printf("%s party of %d has been seated in the " +
                                "%s Movie Theater\n", party.getRepresentative(),
                        party.getSize(), party.getDesiredMovie());
                house.getTheater(party.getDesiredMovie()).incrementSale(party.getSize());
            }
        }
    }
}
