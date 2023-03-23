package com.Model.map;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Person {

    private int id;

    private String pseudo;

    private String pwd;

    private String email;

    private LocalDate acc_creation_date;

    private ArrayList<MovieHistoric> historic;

    private UserData data;

    public User(int id, int Person_id, String pseudo, String pwd, String name, String surname, String email, int age, String sexe, LocalDate acc_creation_date, UserData data) {
        super(Person_id, name, surname, age, sexe );
        this.id = id;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.email = email;
        this.acc_creation_date = acc_creation_date;
        this.historic = new ArrayList<MovieHistoric>();
        this.data = data;
    }

    public int getUsrId() {
        return id;
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

    public ArrayList<MovieHistoric> getHistoric() {
        return historic;
    }

    public UserData getData() {
        return data;
    }

    public void addMovieToHistoric(MovieHistoric movie) {
        this.historic.add(movie);
    }

    public void removeMovieFromHistoric(MovieHistoric movie) {
        this.historic.remove(movie);
    }


}
