package theater;

import structs.ListCDLSBased;

public class MovieTheater {

    private String movieTitle;
    private double ticketPrice;
    private int sales;
    private Party[][] seats;
    private ListCDLSBased<Party> seatedParties;

    public MovieTheater(String movieTitle, int numOfRows, int seatsPerRow,
                        double ticketPrice) {
        this.movieTitle = movieTitle;
        this.seats = new Party[numOfRows][seatsPerRow];
        this.ticketPrice = ticketPrice;
        this.seatedParties = new ListCDLSBased<>();
    }

    public void incrementSales(double amount) {
        this.sales += amount;
    }

    public void decrementSales(double amount) {
        this.sales -= amount;
    }

}
