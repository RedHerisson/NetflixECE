package com.Model.map;

import com.Model.dao.MovieAccessor;

import java.io.IOException;
import java.sql.SQLException;

public class UserData extends BdModel {

    public int ownerId;

    public int movieId;

    public boolean view;

    public int lengthAlreadySeen;

    public String languageSelected;

    private double rate;


    public UserData(int id, int UserID, int movieId, boolean view, int lengthAlreadySeen, String languageSelected, double rate) {
        super(id);
        this.ownerId = UserID;
        this.movieId = movieId;
        this.view = view;
        this.lengthAlreadySeen = lengthAlreadySeen;
        this.languageSelected = languageSelected;
        this.rate = rate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getMovieId() {
        return movieId;
    }

    public Movie getMovie() {
        try {
            MovieAccessor movieAccessor = new MovieAccessor();
            Movie movie = movieAccessor.findById(movieId);
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void setMovieId(int movie) {
        this.movieId = movieId;
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
