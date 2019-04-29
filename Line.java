package theater;

import structs.SLSQueue;

public class Line {

    private String name;
    private SLSQueue<Party> queue;
    private boolean isExpress;
    private int numParties;

    /**
     * Line constructor
     * @param name Name of the line
     * @param isExpress Is express line
     */
    public Line(String name, boolean isExpress) {
        this.name = name;
        this.queue = new SLSQueue<>();
        this.isExpress = isExpress;
        numParties = 0;
    }

    /**
     * Gets express line status
     * @return Returns true if it is an express line
     */
    public boolean getIsExpress()
    {
    	return isExpress;
    }

    /**
     * Returns number of parties in line
     * @return The number of parties in the line
     */
    public int getNumParties()
    {
    	return numParties;
    }

    /**
     * Returns the name of the line
     * @return The name of the line
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a party to the line
     * @param party The party to add
     */
    public void addParty(Party party)
    {
    	queue.enqueue(party);
    	numParties++;
    }

    /**
     * Returns empty status
     * @return Returns true if the line is empty
     */
    public boolean isEmpty()
    {
    	return numParties == 0;
    }

    /**
     * Returns line in its String representation
     * @return Returns String representation
     */
    public String toString()
    { 	
    	return queue.toString();
    }

    /**
     * Gets the next Party waiting in the line
     * @return The next Party
     */
    public Party getNextParty() {
        Party result = null;
        if (!queue.isEmpty()) {
            result = queue.dequeue();
        }
        return result;
    }

}
