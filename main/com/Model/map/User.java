package com.Model.map;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    private String pseudo;

    private String pwd;

    private String email;

    private LocalDate acc_creation_date;

    private Playlist WatchLater;

    private Playlist history;

    private ArrayList<String> favouriteType;

    private ArrayList<UserData> data;

    private boolean admin;

    public User(int id, String pseudo, String pwd, String name, String surname, String email, int age, String sexe, LocalDate acc_creation_date,
                Playlist WatchLater ,Playlist history, ArrayList<String> favouriteType, ArrayList<UserData> data, boolean admin) {
        super(id, name, surname, age, sexe );
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.email = email;
        this.acc_creation_date = acc_creation_date;
        this.WatchLater = WatchLater;
        this.history = history;
        this.favouriteType = favouriteType;
        this.data = data;
        this.admin = admin;
    }


    public String getPseudo() {
        return pseudo;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreationDate() {
        return acc_creation_date;
    }

    public Playlist getWatchList() {
        return WatchLater;
    }

    public Playlist getHistory() {
        return history;
    }

    public ArrayList<UserData> getData() {
        return data;
    }

    public ArrayList<String> getFavouriteType() {
        return favouriteType;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void addMovieToWatchList(Movie movie) {
        this.WatchLater.addMovie(movie);
    }

    public boolean testIfMovieIsInWatchList(Movie movie) {
        return this.WatchLater.getMoviesList().contains(movie);
    }

    public void RemoveMovieToWatchList(Movie movie) {
        this.WatchLater.removeMovie(movie.getId());
    }

    public void addMovieToHistory(Movie movie) {
        this.history.addMovie(movie);
    }

    public void removeMovieFromHistory(Movie movie) {
        this.history.removeMovie(movie.getId());
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void addData(UserData data) {
        this.data.add(data);
    }

    public ArrayList<String> getTypeFromHistory() {
        ArrayList<String> types = new ArrayList<>();
        if( this.history == null)
            return types;
        for(Movie movie : this.history.getMoviesList()) {
            for(String type : movie.getTypeArray())
                    types.add(type);
        }
        return types;
    }

    // to string
    @Override
    public String toString() {
        return "User{" +
                "pseudo='" + pseudo + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", acc_creation_date=" + acc_creation_date +
                ", playlists=" + WatchLater +
                ", history=" + history +
                ", favouriteType=" + favouriteType +
                ", data=" + data +
                ", admin=" + admin +
                '}';
    }

    public ArrayList<Movie> getMovieStarted() {
        ArrayList<Movie> movies = new ArrayList<>();
        for(UserData data : this.data) {
            System.out.println(data.getMovie().getTitle());
            if(data.isView()) {
                movies.add(data.getMovie());
            }
        }
        return movies;
    }
}
