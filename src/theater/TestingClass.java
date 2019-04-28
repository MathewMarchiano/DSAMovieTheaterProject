package theater;

import structs.ListArrayBased;
import structs.MyListReferenceBased;
import structs.SLSQueue;

public class TestingClass {

    public static void main(String[] args) {

        Seats seats = new Seats(10,5);
        Party p1 = new Party("Scott", 2, false, "Movie");
        Party p2 = new Party("Autumn", 1, false, "Movie");
        Party p3 = new Party("Bree", 1, false, "Movie");
        seats.seatParty(p1);
        seats.seatParty(p2);
        seats.seatParty(p3);
        System.out.println(p1.getSeatNumber());
        System.out.println(p2.getSeatNumber());
        System.out.println(p3.getSeatNumber());

    }

}
