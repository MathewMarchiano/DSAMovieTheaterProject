package theater;

public class MovieTheater {

    private String movieTitle;
    private double ticketPrice;
    private double sales;
    private int ticketsSold;
    private Seats seats;

    /**
     * MovieTheater constructor
     * @param movieTitle The title of the movie
     * @param numOfRows Number of rows the movie has
     * @param seatsPerRow Number of seats per row
     * @param ticketPrice Price per ticket to see this movie
     */
    public MovieTheater(String movieTitle, int numOfRows, int seatsPerRow,
                        double ticketPrice) {
        this.movieTitle = movieTitle;
        this.seats = new Seats(numOfRows*seatsPerRow, seatsPerRow);
        this.ticketPrice = ticketPrice;
        this.sales = 0;
        this.ticketsSold = 0;
    }

    /**
     * Gets the dollar amount of sales this MovieTheater has made
     * @return The dollar amount of sales
     */
    public double getSales()
    {
    	return sales;
    }

    /**
     * Gets the number of tickets sold by this MovieTheater
     * @return The number of tickets sold
     */
    public int getTicketsSold() {
        return this.ticketsSold;
    }

    /**
     * Maintains bookkeeping for making a sale
     * @param ticketQuantity The number of tickets sold
     */
    public void incrementSale(int ticketQuantity) {
        this.ticketsSold += ticketQuantity;
        this.sales += ticketQuantity*ticketPrice;
    }

    /**
     * Gets the title of the movie playing in the MovieTheater
     * @return The movie title
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Prints out the seats and their availability
     */
    public void displaySeats() {
        System.out.println(seats.toString());
    }

    /**
     * Removes a party from the theater
     * @param representative The Party's representative to remove
     * @return Returns true if the Party was found and removed. Returns false
     * otherwise
     */
    public boolean removeParty(String representative) {
        return seats.removeParty(representative);
    }

    /**
     * Attempts to seat a part in the MovieTheater. If the Party can fit
     * it returns the index of where the Party's seating begins (Party
     * seating is consecutive). If the party cannot be seated it will return
     * -1
     * @param party The Party to attempt to seat
     * @return The index of where the Party's seating begins (-1 otherwise)
     */
    public int seatParty(Party party) {
        return seats.seatParty(party);
    }

}
