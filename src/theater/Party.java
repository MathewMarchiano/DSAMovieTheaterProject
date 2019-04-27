package theater;

public class Party {

    private String representative;
    private int size;
    private boolean hasKids;
    private int seatNumber; // first seat in party's seating (-1 for unassigned)

    public Party(String representative, int size, boolean hasKids) {
        this.representative = representative;
        this.size = size;
        this.hasKids = hasKids;
        this.seatNumber = -1;
    }




}
