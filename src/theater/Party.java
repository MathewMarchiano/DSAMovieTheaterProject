package theater;

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
    
    

    public String toString()
    {
    	String result = "";
    	
    	result = "Customer " + representative + " part of " +
    			 size + " for " + desiredMovie + "\n";
    	
    	return result;
    }



}
