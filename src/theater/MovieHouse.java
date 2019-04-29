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


        Iterator<MovieTheater> iterator = theaters.iterator();
        MovieTheater result = null;
        while (iterator.hasNext() && result == null) {
            MovieTheater theater = iterator.next();
            if (theater.getMovieTitle().equals(movieTitle)) {
                result = theater;
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
    /**
     * Adds a party to the appropriate line.
     *
     * @param party Party to add to line.
     */
    public void addPartyToLine(Party party) {

        int minSize = -1;
        Iterator<Line> iterator = lines.iterator();
        Line line = iterator.next();
        Line minLine = line;
        Line start = line;
        do {
            if ((!party.getHasKids()) || (line.getIsExpress() && party.getHasKids())) {
                if (line.getNumParties() < minSize ||
                        minSize == -1) {
                    minSize = line.getNumParties();
                    minLine = line;
                }
            }
            line = iterator.next();
        } while (iterator.hasNext() && line != start);

        minLine.addParty(party);
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

    /**
     * Gets status if there is any customers in any line
     * @return Returns true if there are no customers in line, false otherwise
     */
    public boolean areLinesEmpty() {
        boolean result = true;
        Iterator<Line> iterator = lines.iterator();
        if (iterator.hasNext()) {
            Line line = iterator.next();
            Line start = line;
            do {
                if (!line.isEmpty()) {
                    result = false;
                }
                line = iterator.next();
            } while (line != start && result);
        }
        return result;
    }

}
