package com.Model.map;

import java.time.LocalDate;

public class MovieHistoric {

    private int id;
    private Movie movie;

    private User user;

    private LocalDate seeingDate;

    public MovieHistoric(int id,Movie movie, User user, LocalDate seeingDate) {
        this.id = id;
        this.movie = movie;
        this.user = user;
        this.seeingDate = seeingDate;
    }

    public int getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeeingDate(LocalDate seeingDate) {
        this.seeingDate = seeingDate;
    }

}
