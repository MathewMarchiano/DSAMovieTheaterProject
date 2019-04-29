package theater;

import structs.*;

import java.util.Iterator;

/**
 * The Seats represents seats in a MovieTheater that can be claimed by
 * Parties. The Seats class is efficient in that it only stores in memory
 * the first seat occupied by the Party. It uses the size of the Party class
 * to determine if seats are available.
 */
public class Seats {

    private int size;
    private int seatsPerRow;
    private MyListReferenceBased<Party> seats;

    /**
     * Seats constructor
     * @param size The number of seats in Seats
     * @param seatsPerRow The number of seats per row
     */
    public Seats(int size, int seatsPerRow) {
        this.size = size;
        this.seatsPerRow = seatsPerRow;
        this.seats = new MyListReferenceBased<>();
    }

    /**
     * Attempts to seat a Party. Returns index of where the Party's seating
     * starts if the Party was able to be seated. Returns -1 otherwise
     * @param party The Party to try to seat
     * @return Index of seat at the start of Party's seating. -1 if Party
     * could not be seated
     */
    public int seatParty(Party party) {

        int result = -1;

        if (!seats.isEmpty()) {
            Iterator<Party> iterator = seats.iterator();
            int prevEnd = 0; // index after previous party. starts at 0.
            while (iterator.hasNext() && result == -1) {
                Party nextParty = iterator.next();
                if (nextParty.getSeatNumber() - prevEnd >= party.getSize()) {
                    result = prevEnd;
                    party.setSeatNumber(result);
                    seats.add(seats.size(), party);
                } else {
                    prevEnd = (nextParty.getSeatNumber() + nextParty.getSize());
                }
            }
            // if party wasn't assigned handle end case
            if (result == -1) {
                if (size - prevEnd >= party.getSize()) {
                    result = prevEnd;
                    party.setSeatNumber(result);
                    seats.add(seats.size(), party);
                }
            }

        } else {
            if (party.getSize() <= size) {
                result = 0;
                party.setSeatNumber(result);
                seats.add(result, party);
            }
        }

        return result;
    }

    /**
     * Removes Party (if present) from seating. Returns true if party was found
     * and removed. Returns false if the Party was not present
     * @param representative The name of the person representing the Party
     * @return True if party was found and removed, false otherwise
     */
    public boolean removeParty(String representative) {
        boolean removed = false;
        int size = seats.size();
        Iterator<Party> iterator = seats.iterator();
        int i = 0;
        while (i < size && !removed) {
            if (iterator.next().getRepresentative().equals(representative)) {
                seats.remove(i);
                removed = true;
            }
            i++;
        }
        return removed;
    }

    /**
     * Gets the row the seat's index is in
     * @param index The seat's index
     * @return The index of the row that seat's index is in
     */
    private int computeRow(int index) {
        return (index / seatsPerRow) + 1;
    }

    /**
     * Gets the seat number (relative to the row) belonging to the seat's index
     * @param index The seat's index
     * @return The seat number (relative to the row it is in)
     */
    private int computeSeat(int index) {
        return (index % seatsPerRow) + 1;
    }

    /**
     * Returns the total number of seats in Seats
     * @return The number of seats
     */
    public int getSize() {
        return size;
    }

    /**
     * Return string containing each seat and its availability
     * @return String representation of Seats
     */
    public String toString() {

        int index = 0;
        Iterator<Party> iterator = seats.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Party party = iterator.next();
            int nextIndex = party.getSeatNumber();
            if (index - nextIndex != 0) {
                for (int i = 0; i < nextIndex - index; i++) {
                    sb.append(String.format("Row %d seat %d is empty\n",
                            computeRow(i), computeSeat(i)));
                }
            }
            String rep = party.getRepresentative();
            for (int i = nextIndex; i < nextIndex + party.getSize(); i++) {
                sb.append(String.format("Row %d seat %d used by %s's " +
                        "party.\n", computeRow(i), computeSeat(i), rep));
            }
            index = nextIndex + party.getSize();
        }

        // check to see if there are any seats on the end
        while (index < size) {
            sb.append(String.format("Row %d seat %d is empty\n",
                    computeRow(index), computeSeat(index)));
            index++;
        }

        return sb.toString();
    }

}
