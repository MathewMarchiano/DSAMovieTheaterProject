package theater;

import structs.ListCDLSBased;

public class MovieHouse {

    private ListCDLSBased<MovieTheater> theaters;
    private ListCDLSBased<Line> lines;

    public MovieHouse() {
        // default
    }

    public void addTheater(MovieTheater theater) {
        this.theaters.add(theaters.size(), theater);
    }

    public void addLine(Line line) {
        lines.add(lines.size(), line);
    }

}
