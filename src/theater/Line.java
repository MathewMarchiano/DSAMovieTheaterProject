package theater;

import structs.SLSQueue;

public class Line {

    private SLSQueue<Party> queue;
    private boolean isExpress;
    private int numParties;

    public Line(boolean isExpress) {
        this.queue = new SLSQueue<>();
        this.isExpress = isExpress;
        numParties = 0;
    }
    
    public SLSQueue<Party> getQueue()
    {
    	return queue;
    }
    
    public boolean getIsExpress()
    {
    	return isExpress;
    }
    
    public int getNumParties()
    {
    	return numParties;
    }
    
    public void addParty(Party party)
    {
    	queue.enqueue(party);
    	numParties++;
    }
    
    public void removeParty()
    {
    	queue.dequeue();
    	numParties--;
    }
    
    public boolean isEmpty()
    {
    	return numParties == 0;
    }
    
    public String toString()
    { 	
    	return queue.toString();
    }

}
