package theater;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class Party {

    private String representative;
    private int size;
    private boolean hasKids;
    private int seatNumber; // first seat in party's seating (-1 for unassigned)
    private String desiredMovie;

    public Party(String representative, int size, boolean hasKids, String desiredMovie) {
        this.representative = representative;
        this.size = size;
        this.hasKids = hasKids;
        this.seatNumber = -1;
        this.desiredMovie = desiredMovie;
    }
    
    public boolean getHasKids()
    {
    	return hasKids;
    }
    
    public String getRepresentative() {
        return representative;
    }

    public String toString()
    {
    	String result = "";
    	
    	result = "Customer " + representative + " party of " +
    			 size + " for " + desiredMovie + "\n";
    	
    	return result;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        if (seatNumber >= 0) {
            this.seatNumber = seatNumber;
        } else {
            throw new ValueException("Seat number must be assigned a" +
                    " positive number");
        }
    }

    public int getSize() {
        return size;
    }

    public String getDesiredMovie() {
        return desiredMovie;
    }
}
