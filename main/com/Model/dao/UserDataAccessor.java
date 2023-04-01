package com.Model.dao;

import com.Model.map.UserData;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataAccessor extends Accessor<UserData> {

    MovieAccessor movieAccessor;

    public UserDataAccessor() throws SQLException, ClassNotFoundException {
        super();
        movieAccessor = new MovieAccessor();
    }

    @Override
    public UserData findById(int id) throws SQLException, IOException, ClassNotFoundException {

        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Movie WHERE ID = " + id );

        if ( result.next() ) {
            int UserID = result.getInt(1);
            int movieID = result.getInt(2);
            boolean view =  result.getBoolean(3);
            int lengthAs = result.getInt(4);
            String languageSelected = result.getString(5);
            double rate = result.getDouble(6);

            return new UserData(id, UserID, movieAccessor.findById( movieID) , view, lengthAs, languageSelected, rate);

        }
        return null;
    }

    @Override
    public int create(UserData usrData) throws SQLException {


        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO User_data (user_ID, Movie_ID, view, length_already_seen, language_selected, rate) " +
                "VALUES (?, ?, ?, ?, ?, ?)");

        pre.setInt(1, usrData.getOwnerId());
        pre.setInt(2, usrData.getMovie().getId());
        pre.setBoolean(1, usrData.isView());
        pre.setInt(4, usrData.getLengthAlreadySeen());
        pre.setString(5, usrData.getLanguageSelected());
        pre.setDouble(6, usrData.getRate());

        pre.executeUpdate();
        pre.close();
        return usrData.getOwnerId();
    }

    public void updateView(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_data SET view = ? WHERE ID = ?");

        pre.setBoolean(1, usrData.isView());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updateLengthAlreadySeen(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_data SET length_already_seen = ? WHERE ID = ?");

        pre.setInt(1, usrData.getLengthAlreadySeen());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updateLanguageSelected(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_data SET language_selected = ? WHERE ID = ?");

        pre.setString(1, usrData.getLanguageSelected());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void updateRate(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_data SET rate = ? WHERE ID = ?");

        pre.setDouble(1, usrData.getRate());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    @Override
    public void delete(int id ) throws SQLException {
        dataBase.getRequest().executeUpdate(" DELETE FROM User_data WHERE ID = " + id );
    }
}
