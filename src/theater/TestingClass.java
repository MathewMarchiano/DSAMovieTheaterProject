package theater;

import structs.ListArrayBased;
import structs.ListCDLSBased;
import structs.MyListReferenceBased;
import structs.SLSQueue;

public class TestingClass {

    public static void main(String[] args) {

        MovieHouse mh = new MovieHouse();
        mh.addLine(new Line("reg", false));
        mh.addLine(new Line("exp", true));
        mh.addPartyToLine(new Party("Scott", 5, false, "movie"));
        mh.addPartyToLine(new Party("Scott2", 5, true, "movie"));
        System.out.println(mh.getNextCustomer("reg"));
    }

}
