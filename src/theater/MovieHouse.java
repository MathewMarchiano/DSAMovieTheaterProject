package theater;

import structs.ListCDLSBased;
import structs.MyListReferenceBased;

import java.util.Iterator;

public class MovieHouse {

    private MyListReferenceBased<MovieTheater> theaters;
    private ListCDLSBased<Line> lines;
    private int currentLine;
    private int currentExpressLine;
    private Iterator<Line> iterator;

    /**
     * MovieHouse constructor
     */
    public MovieHouse() {
        this.theaters = new MyListReferenceBased<>();
        this.lines = new ListCDLSBased<>();
        currentLine = 0;
        currentExpressLine = 0; // Will be set after lines are added (uses method below)
        this.iterator = null;
    }

    /**
     * Gets the Lines in the MovieHouse
     * @return The Lines of the MovieHouse
     */
    public ListCDLSBased<Line> getLines() {
        return lines;
    }

    /**
     * Add a Theater to the MovieHouse
     * @param theater The Theater to add
     */
    public void addTheater(MovieTheater theater) {
        this.theaters.add(theaters.size(), theater);
    }

    /**
     * Add a Line to the MovieHouse
     * @param line The Line to add
     */
    public void addLine(Line line) {
        lines.add(lines.size(), line);
    }

    /**
     * Get MovieTheater by the movie's title. Returns null if there is not
     * a MovieTheater playing a movie with this title.
     * @param movieTitle The title of the movie
     * @return The Theater playing a movie with the given title.
     * (Or null if no theater is playing movie with title)
     */
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

    /**
     * Prints out a summary of the sales from all MovieTheaters.
     */
    public void reportSales() {
        StringBuilder sb = new StringBuilder();
        Iterator<MovieTheater> iterator = theaters.iterator();
        double sales = 0;
        while (iterator.hasNext()) {
            MovieTheater theater = iterator.next();
            sb.append(String.format("%d have been sold for the %s Movie.\n",
                    theater.getTicketsSold(), theater.getMovieTitle()));
            sales += theater.getSales();
        }
        sb.append(String.format("Total earnings: %f\n", sales));
        System.out.println(sb);
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

    /**
     * Iterates through all MovieTheaters until it finds the given Party
     * to remove. Returns true if the Party was found and removed.
     * Returns false if the Party was not found and not removed.
     *
     * @param representative The name of the Party's representative to remove
     * @return True if the party was found and removed. False otherwise
     */
    public boolean removePartyFromTheaters(String representative) {
        Iterator<MovieTheater> iterator = theaters.iterator();
        boolean removed = false;
        while (iterator.hasNext() && !removed) {
            removed = iterator.next().removeParty(representative);
        }
        return removed;
    }

    /**
     * Gets the next customer that needs to be helped. It will start
     * helping customers based on the first line that was added.
     * @return The next Party that needs to be helped. If no Parties are in
     * any of the lines it will return null.
     */
    public Party getNextCustomer() {
        if (iterator == null) {
            // Since no line was specified MovieHouse will just
            // start with the first line that was added.
            this.iterator = lines.iterator();
        }
        Line line = iterator.next();
        Line start = line;
        Party party;
        Party result = null;
        do {
            party = line.getNextParty();
            if (party != null) {
                result = party;
                break;
            }
            line = iterator.next();
        } while (line != start);
        return result;
    }

    /**
     * Gets the next customer that needs to be helped. It will start
     * helping customers based on the given lineName.
     * @param lineName The name of the line where MovieHouse should start
     *                 serving customers.
     * @return The next Party that needs to be helped. If no Parties are in
     * any of the lines it will return null.
     */
    public Party getNextCustomer(String lineName) {
        this.iterator = lines.iterator();
        Line line = iterator.next();
        while (!line.getName().equals(lineName)) {
            line = iterator.next();
        }
        Line start = line;
        Party result = null;
        Party party = null;
        do {
            party = line.getNextParty();
            if (party != null) {
                result = party;
                break;
            }
            line = iterator.next();
        } while (line != start);

        return result;
    }

}
