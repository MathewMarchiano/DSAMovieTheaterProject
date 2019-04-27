package theater;

import structs.ListCDLSBased;

public class MovieHouse {

    private ListCDLSBased<MovieTheater> theaters;
    private ListCDLSBased<Line> lines;

    public MovieHouse() {
        this.theaters = new ListCDLSBased<>();
        this.lines = new ListCDLSBased<>();
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

}
