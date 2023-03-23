package com.Model.dao;

import com.Model.map.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserAccessor extends Accessor<User> {

    public UserAccessor() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public int create(User usr) throws SQLException { {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO User (person_id, pseudo, pwd, email, acc_date_creation, favourite_type_ID, movie_historic_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");

        pre.setInt(1, usr.getID());
        pre.setString(2, usr.getPseudo());
        pre.setString(3, usr.getPwd());
        pre.setString(4, usr.getEmail());
        pre.setDate(5, Date.valueOf(usr.getCreationDate()));
        pre.setInt(6, usr.getFavouriteTypeID());
        pre.setInt(7, usr.getMovieHistoricID());

        return 0;
    }

    @Override
    public int update(User usr) {

        return 0;
    }

    @Override
    public void delete( int id ) {

    }
}
