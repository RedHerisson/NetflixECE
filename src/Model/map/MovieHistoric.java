package Model.map;

import java.time.LocalDate;

public class MovieHistoric {

    private Movie movie;

    private LocalDate seeingDate;

    public MovieHistoric(Movie movie, LocalDate seeingDate) {
        this.movie = movie;
        this.seeingDate = seeingDate;
    }
}
