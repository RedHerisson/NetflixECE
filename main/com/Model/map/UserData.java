package com.Model.map;

public class UserData extends BdModel {

    public int ownerId;

    public Movie movie;

    public boolean view;

    public int lengthAlreadySeen;

    public String languageSelected;

    private double rate;


    public UserData(int id, int UserID, Movie movie, boolean view, int lengthAlreadySeen, String languageSelected, double rate) {
        super(id);
        this.ownerId = UserID;
        this.movie = movie;
        this.view = view;
        this.lengthAlreadySeen = lengthAlreadySeen;
        this.languageSelected = languageSelected;
        this.rate = rate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isView() {
        return view;
    }

    public int getLengthAlreadySeen() {
        return lengthAlreadySeen;
    }

    public String getLanguageSelected() {
        return languageSelected;
    }

    public double getRate() {
        return rate;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public void setLengthAlreadySeen(int lengthAlreadySeen) {
        this.lengthAlreadySeen = lengthAlreadySeen;
    }

    public void setLanguageSelected(String languageSelected) {
        this.languageSelected = languageSelected;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String getTableName() {
        return null;
    }
}
