package theater;

import java.util.InputMismatchException;

public class Party {

    private String representative;
    private int size;
    private boolean hasKids;
    private String desiredMovie;
    private int seatNumber;

    /**
     * Party constructor
     * @param representative Name of person representing the Party
     * @param size The number of people in the Party
     * @param hasKids If this party contains kids (<= 11 years old)
     * @param desiredMovie The movie the Party wants to watch
     */
    public Party(String representative, int size, boolean hasKids, String desiredMovie) {
        this.representative = representative;
        this.size = size;
        this.hasKids = hasKids;
        this.seatNumber = -1;
        this.desiredMovie = desiredMovie;
    }

    /**
     * Gets the status if the Party has kids
     * @return True if Party contains kids, false otherwise
     */
    public boolean getHasKids()
    {
    	return hasKids;
    }

    /**
     * Gets the name of the person representing the Party
     * @return Name of representative
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * Returns String representation of the Party
     * @return The String representation of the Party
     */
    public String toString()
    {
    	String result = "Customer " + representative + " party of " +
    			 size + " for " + desiredMovie + "\n";
    	
    	return result;
    }

    /**
     * Gets the seat number of where the Party's seating starts. It is set
     * to -1 if the Party has not been seated yet.
     * @return The seat number of where the Party's seating starts. -1 if the
     * Party has not been seated
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Set the seat number of where the Party's seating starts. Parties are
     * placed consecutively.
     * @param seatNumber The seat number of where the Party's seating starts
     */
    public void setSeatNumber(int seatNumber) {
        if (seatNumber >= 0) {
            this.seatNumber = seatNumber;
        } else {
            throw new InputMismatchException("Seat number must be assigned a" +
                    " positive number");
        }
    }

    /**
     * Gets the number of people in the Party
     * @return The size of the Party
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the title of the movie the Party wants to watch
     * @return The movie title
     */
    public String getDesiredMovie() {
        return desiredMovie;
    }
}
