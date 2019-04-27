package theater;

import structs.SLSQueue;

public class Line {

    private SLSQueue<Party> queue;
    private boolean isExpress;

    public Line(boolean isExpress) {
        this.queue = new SLSQueue<>();
        this.isExpress = isExpress;
    }

}
