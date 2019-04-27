package theater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

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

        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Enter selection: ");

            int selection = stdin.nextInt();
            System.out.println(selection + "\n");

            switch (selection) {
                case 0:
                    //
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
                    //
                    break;
                case 5:
                    //
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


}