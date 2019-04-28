package theater;

import structs.*;

import java.util.Iterator;

public class Seats {

    private int size;
    private int seatsPerRow;
    private MyListReferenceBased<Party> seats;

    public Seats(int size, int seatsPerRow) {
        this.size = size;
        this.seatsPerRow = seatsPerRow;
        this.seats = new MyListReferenceBased<>();
    }

    // if party can be seated it will return the index of the first seat
    // of where they were placed. otherwise it will return -1 if they
    // cannot be seated.
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
                    prevEnd = (nextParty.getSeatNumber()+nextParty.getSize());
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

}
