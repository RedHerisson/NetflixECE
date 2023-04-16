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

    private Playlist history;

    private ArrayList<String> favouriteType;

    private ArrayList<UserData> data;

    private boolean admin;

    public User(int id, String pseudo, String pwd, String name, String surname, String email, int age, String sexe, LocalDate acc_creation_date,
                ArrayList<Playlist> playlists ,Playlist history, ArrayList<String> favouriteType, ArrayList<UserData> data, boolean admin) {
        super(id, name, surname, age, sexe );
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.email = email;
        this.acc_creation_date = acc_creation_date;
        this.playlists = playlists;
        this.history = history; //TODO : force history to be called "History"
        this.favouriteType = favouriteType;
        this.data = data;
        this.admin = admin;
    }

    public User(User usr) {
        super(usr.getId(), usr.getName(), usr.getSurname(), usr.getAge(), usr.getSexe());
        this.pseudo = usr.getPseudo();
        this.pwd = usr.getPwd();
        this.email = usr.getEmail();
        this.acc_creation_date = usr.getCreationDate();
        this.playlists = usr.getPlaylists();
        this.history = usr.getHistory();
        this.favouriteType = usr.getFavouriteType();
        this.data = usr.getData();
        this.admin = usr.isAdmin();
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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
                ", playlists=" + playlists +
                ", history=" + history +
                ", favouriteType=" + favouriteType +
                ", data=" + data +
                ", admin=" + admin +
                '}';
    }

}
