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
    public UserData find(int id) throws SQLException, IOException, ClassNotFoundException {

        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Movie WHERE ID = " + id );

        if ( result.next() ) {
            int UserID = result.getInt(1);
            int movieID = result.getInt(2);
            boolean view =  result.getBoolean(3);
            int lengthAs = result.getInt(4);
            String languageSelected = result.getString(5);
            double rate = result.getDouble(6);

            return new UserData(UserID, movieAccessor.find( movieID) , view, lengthAs, languageSelected, rate);

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


        return 0;
    }

    @Override
    public int update(UserData usrData) throws SQLException {

        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM User_data WHERE ID = " + usrData.getOwnerId());

        if( ! result.next() ) {
            return create(usrData);
        }

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_data SET user_ID = ?, Movie_ID = ?, view = ?, length_already_seen = ?, language_selected = ?, rate = ? ");

        pre.setInt(1, usrData.getOwnerId());
        pre.setInt(2, usrData.getMovie().getId());
        pre.setBoolean(1, usrData.isView());
        pre.setInt(4, usrData.getLengthAlreadySeen());
        pre.setString(5, usrData.getLanguageSelected());
        pre.setDouble(6, usrData.getRate());
        return usrData.getOwnerId();
    }

    @Override
    public void delete(int id ) throws SQLException {
        dataBase.getRequest().executeUpdate(" DELETE FROM User_data WHERE ID = " + id );
    }
}
