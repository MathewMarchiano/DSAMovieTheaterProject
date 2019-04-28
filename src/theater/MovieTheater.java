package theater;

import structs.ListArrayBased;
import structs.ListCDLSBased;
import structs.MyListReferenceBased;

import java.util.Iterator;

public class MovieTheater {

    private String movieTitle;
    private double ticketPrice;
    private double sales;
    private int ticketsSold;
    private Seats seats;
    private int seatsPerRow;
    private ListCDLSBased<Party> seatedParties;

    public MovieTheater(String movieTitle, int numOfRows, int seatsPerRow,
                        double ticketPrice) {
        this.movieTitle = movieTitle;
        this.seats = new Seats(numOfRows*seatsPerRow, seatsPerRow);
        this.seatsPerRow = seatsPerRow;
        this.ticketPrice = ticketPrice;
        this.seatedParties = new ListCDLSBased<>();
        this.sales = 0;
        this.ticketsSold = 0;
    }
    
    public double getSales()
    {
    	return sales;
    }

    public int getTicketsSold() {
        return this.ticketsSold;
    }

    public void incrementSales(double amount) {
        this.sales += amount;
    }

    public void decrementSales(double amount) {
        this.sales -= amount;
    }

    public void incrementTicketCount() {
        this.ticketsSold++;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void displaySeats() {
        System.out.println(seats.toString());
    }

    public boolean removeParty(String representative) {
        return seats.removeParty(representative);
    }

}
