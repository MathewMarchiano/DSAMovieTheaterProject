package theater;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import structs.ListCDLSBased;
import structs.MyListReferenceBased;

import java.util.Iterator;

public class MovieHouse {

    private MyListReferenceBased<MovieTheater> theaters;
    private ListCDLSBased<Line> lines;
    private int currentLine;
    private int currentExpressLine;
    private Iterator<Line> iterator;

    public MovieHouse() {
        this.theaters = new MyListReferenceBased<>();
        this.lines = new ListCDLSBased<>();
        currentLine = 0;
        currentExpressLine = 0; // Will be set after lines are added (uses method below)
        this.iterator = null;
    }

    public ListCDLSBased<Line> getLines() {
        return lines;
    }

    public void addTheater(MovieTheater theater) {
        this.theaters.add(theaters.size(), theater);
    }

    public void addLine(Line line) {
        lines.add(lines.size(), line);
    }

    public MovieTheater getTheater(String movieTitle) {
        MovieTheater result = null;
        int size = theaters.size();
        for (int i = 0; i < size; i++) {
            MovieTheater theater = theaters.get(i);
            if (theater.getMovieTitle().equals(movieTitle)) {
                result = theater;
                break;
            }
        }
        return result;
    }

    public double getTotalRevenue() {
        double result = 0.0;
        int index = 0;
        int numTheaters = theaters.size();

        while (index < numTheaters) {
            result += theaters.get(0).getSales();
            index++;
        }

        return result;
    }

    /**
     * Adds a party to the appropriate line.
     *
     * @param party Party to add to line.
     */
    public void addPartyToLine(Party party) {
        // Check if party is eligible for express
        if (party.getHasKids()) {
            // Check if regular lines have 1/2 people of express
            boolean hasHalf = false;
            int index = 0;
            int expressIndex = 0;
            int numLines = lines.size();
            int peopleInExpress = 0;

            // Check ALL potential express lines
            while (!hasHalf && expressIndex < numLines) {
                // Find express line if not pointing to one
                while (lines.get(expressIndex).getIsExpress() != true) {
                    expressIndex++;
                }

                // Express line found. Get number of people in that line.
                peopleInExpress = lines.get(expressIndex).getNumParties();

                // Check numParties in express line against all regular lines
                while (!hasHalf && index < numLines) {
                    if (lines.get(index).getIsExpress() == false) // Retrieve regular lines only
                    {
                        if (lines.get(index).getNumParties() <= peopleInExpress * .5) {
                            hasHalf = true; // Better to place in regular line. Stop checking.
                        } else {
                            index++;
                        }
                    } else {
                        index++;
                    }

                }

                expressIndex++;
            }

            if (hasHalf) // Place in regular line
            {
                lines.get(index).addParty(party);
            } else // Place in express line
            {
                System.out.println("Party added to express line");
                lines.get(currentExpressLine).addParty(party);

                // Find next express line
                do {
                    currentExpressLine = (currentExpressLine + 1) % lines.size();
                } while (lines.get(currentExpressLine).getIsExpress() == false);
            }
        } else // Not eligible for express. Go to next regular line.
        {

            System.out.println("Party added to regular line");
            lines.get(currentLine).addParty(party);
            do {
                currentLine = (currentLine + 1) % lines.size();
            } while (lines.get(currentLine).getIsExpress() == true);

        }
    }

    /**
     * Finds the index of the first express line if there are any.
     * If there aren't any express lines, returns -1.
     *
     * @return Index of first express line. -1 if none.
     */
    public void setFirstExpressLine() {
        int result = -1;
        boolean found = false;
        int index = 0;
        int numLines = lines.size();

        while (!found && index < numLines) {
            if (lines.get(index).getIsExpress() == true) {
                result = index;
                found = true;
            } else {
                index++;
            }
        }

        currentExpressLine = result;
    }

    public boolean removePartyFromTheaters(String representative) {
        Iterator<MovieTheater> iterator = theaters.iterator();
        boolean removed = false;
        while (iterator.hasNext() && !removed) {
            removed = iterator.next().removeParty(representative);
        }
        return removed;
    }

    // Assumes iterator has been created and continues
    // with next iteration retrieving the next customer
    public Party getNextCustomer() {
        if (iterator == null) {
            throw new ValueException("Iterator has not been initialized!");
        }
        return iterator.next().getNextParty();
    }

    // Builds an iterator starting with the line specified
    public Party getNextCustomer(String lineName) {
        this.iterator = lines.iterator();
        Line result = iterator.next();
        while (!result.getName().equals(lineName)) {
            result = iterator.next();
        }
        return iterator.next().getNextParty();
    }

}
