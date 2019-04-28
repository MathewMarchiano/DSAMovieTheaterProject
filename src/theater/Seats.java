package theater;

import structs.MyListReferenceBased;
import structs.RABStack;
import structs.SLSQueue;

public class Seats {

    private int size;
    private int seatsPerRow;
    private SLSQueue<Party> seats;

    public Seats(int size, int seatsPerRow) {
        this.size = size;
        this.seatsPerRow = seatsPerRow;
        this.seats = new SLSQueue<>();
    }

    // if party can be seated it will return the index of the first seat
    // of where they were placed. otherwise it will return -1 if they
    // cannot be seated.
    public int seatParty(Party party) {

        int result = -1;
        SLSQueue<Party> hold = new SLSQueue<>();

        if (!seats.isEmpty()) {
            int prevEnd = 0; // index after previous party. starts at 0.
            while (!seats.isEmpty() && result == -1) {
                Party nextParty = seats.dequeue();
                hold.enqueue(nextParty);
                if (nextParty.getSeatNumber() - prevEnd >= party.getSize()) {
                    result = prevEnd;
                    party.setSeatNumber(result);
                    hold.enqueue(party);
                } else {
                    prevEnd = (nextParty.getSeatNumber()+nextParty.getSize());
                }
            }
            // if party wasn't assigned handle end case
            if (result == -1) {
                if (size - prevEnd >= party.getSize()) {
                    result = prevEnd;
                    party.setSeatNumber(result);
                    hold.enqueue(party);
                }
            }

            // Move Parties from hold to result queue
            SLSQueue<Party> resultQueue = new SLSQueue<>();
            while (!hold.isEmpty()) {
                resultQueue.enqueue(hold.dequeue());
            }
            // Move left over Parties from old seats queue
            while (!seats.isEmpty()) {
                resultQueue.enqueue(seats.dequeue());
            }
            seats = resultQueue;
        } else {
            if (party.getSize() <= size) {
                result = 0;
                party.setSeatNumber(result);
                seats.enqueue(party);
            }
        }

        return result;
    }

}
