package com.Model.map;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

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

    /**
     * permet d'ajouter un film à la liste de l'utilisateur
     * @param movie
     */
    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    /**
     * permet d'enelever un film de la liste de l'utilisateur
     * @param id
     */
    public void removeMovie(int id) {
        Iterator<Movie> i = movies.iterator();
        while (i.hasNext()) {
            Movie tempoMovie = i.next();

            if (tempoMovie.getId() == id) {
                i.remove();
            }
        }
    }
}
