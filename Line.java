package theater;

import structs.SLSQueue;

public class Line {

    private SLSQueue<Party> queue;
    private boolean isExpress;

    public Line(boolean isExpress) {
        this.queue = new SLSQueue<>();
        this.isExpress = isExpress;
    }
    
    public SLSQueue<Party> getQueue()
    {
    	return queue;
    }
    
    public boolean getIsExpress()
    {
    	return isExpress;
    }
    
    public void addParty(Party party)
    {
    	queue.enqueue(party);
    }
    
    public boolean isEmpty()
    {
    	return queue.isEmpty();
    }
    
    public String toString()
    { 	
    	return queue.toString();
    }

}
