package com.Model.map;

import java.time.LocalDate;

public class MovieHistoric {

    private int id;

    private int ownerId;
    private Movie movie;

    private LocalDate seeingDate;

    public MovieHistoric(int id,int ownerId, Movie movie, LocalDate seeingDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.movie = movie;
        this.seeingDate = seeingDate;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDate getSeenDate() {
        return seeingDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setSeeingDate(LocalDate seeingDate) {
        this.seeingDate = seeingDate;
    }

}
