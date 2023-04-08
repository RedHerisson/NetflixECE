package com.Model.map;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    private String pseudo;

    private String pwd;

    private String email;

    private LocalDate acc_creation_date;

    private ArrayList<Playlist> playlists;

    private Playlist historic;

    private ArrayList<String> favouriteType;

    private UserData data;

    private boolean admin;

    public User(int id, String pseudo, String pwd, String name, String surname, String email, int age, String sexe, LocalDate acc_creation_date,
                ArrayList<Playlist> playlists ,Playlist historic, ArrayList<String> favouriteType, UserData data, boolean admin) {
        super(id, name, surname, age, sexe );
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.email = email;
        this.acc_creation_date = acc_creation_date;
        this.playlists = playlists;
        this.historic = historic; //TODO : force historic to be called "Historic"
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

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Playlist getHistoric() {
        return historic;
    }

    public UserData getData() {
        return data;
    }

    public ArrayList<String> getFavouriteType() {
        return favouriteType;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void addFavouriteType(String type) {
        this.favouriteType.add(type);
    }

    public void removeFavouriteType(String type) {
        this.favouriteType.remove(type);
    }

    public void addMovieToPlaylist(Playlist movie) {
        this.playlists.add(movie);
    }

    public void removeMovieFromPlaylist(Playlist movie) {
        this.playlists.remove(movie);
    }

    public void addMovieToHistoric(Movie movie) {
        this.historic.addMovie(movie);
    }

    public void removeMovieFromHistoric(Movie movie) {
        this.historic.removeMovie(movie.getId());
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    // to string
    @Override
    public String toString() {
        return "User{" +
                "pseudo='" + pseudo + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", acc_creation_date=" + acc_creation_date +
                ", playlists=" + playlists +
                ", historic=" + historic +
                ", favouriteType=" + favouriteType +
                ", data=" + data +
                ", admin=" + admin +
                '}';
    }

}
