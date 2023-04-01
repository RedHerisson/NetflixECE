package com.Model.map;

import java.time.LocalDate;
import java.util.ArrayList;

public class Playlist extends BdModel {
    private int ownerId;
    private String Title;

    private ArrayList<Movie> movies;


    public Playlist(int ownerId, int id, String Title ,ArrayList<Movie> movies) {
        super(id);
        this.ownerId = ownerId;
        this.Title = Title;
        this.movies = movies;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return Title;
    }

    public ArrayList<Movie> getMoviesList() {
        return movies;
    }

    @Override
    public String getTableName() {
        return "Playlist";
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setMoviesList(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void removeMovie(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                this.movies.remove(movie);
            }
        }
    }
}
