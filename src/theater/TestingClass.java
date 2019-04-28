package theater;

import structs.ListArrayBased;
import structs.ListCDLSBased;
import structs.MyListReferenceBased;
import structs.SLSQueue;

public class TestingClass {

    public static void main(String[] args) {

        ListCDLSBased<String> ref = new ListCDLSBased<>();
        for (int i = 0; i < 100; i++) {
            ref.add(i, "test");
        }

        Seats seats = new Seats(10,5);
        Party p1 = new Party("Scott", 2, false, "Movie");
        Party p2 = new Party("Autumn", 1, false, "Movie");
        Party p3 = new Party("Bree", 1, false, "Movie");
        Party p4 = new Party("Bree", 1, false, "Movie");
        Party p5 = new Party("Bree", 6, false, "Movie");
        seats.seatParty(p1);
        seats.seatParty(p2);
        seats.seatParty(p3);
        seats.seatParty(p4);
        seats.seatParty(p5);
        System.out.println(p1.getSeatNumber());
        System.out.println(p2.getSeatNumber());
        System.out.println(p3.getSeatNumber());
        System.out.println(p4.getSeatNumber());
        System.out.println(p5.getSeatNumber());

    }

}
