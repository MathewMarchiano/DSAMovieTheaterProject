package theater;

import structs.ListCDLSBased;

public class MovieHouse {

    private ListCDLSBased<MovieTheater> theaters;
    private ListCDLSBased<Line> lines;
    private int currentLine;

    public MovieHouse() {
        this.theaters = new ListCDLSBased<>();
        this.lines = new ListCDLSBased<>();
        currentLine = 0;
    }
    
    public ListCDLSBased<Line> getLines()
    {
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
    
    public double getTotalRevenue()
    {
    	double result = 0.0;
    	int index = 0;
    	int numTheaters = theaters.size();
    	
    	while(index < numTheaters)
    	{
    		result += theaters.get(0).getSales();
    		index++;
    	}
    	
    	return result;
    }

    public void addParty(Party party)
    {
    	
    }
    
}
