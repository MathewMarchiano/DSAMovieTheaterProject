package theater;

import structs.SLSQueue;

public class Line {

    private String name;
    private SLSQueue<Party> queue;
    private boolean isExpress;
    private int numParties;

    public Line(String name, boolean isExpress) {
        this.name = name;
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

    public String getName() {
        return name;
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

    public Party getNextParty() {
        Party result = null;
        if (!queue.isEmpty()) {
            result = queue.dequeue();
        }
        return result;
    }

}
