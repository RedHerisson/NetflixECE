package com.Model.dao;

import com.Model.map.Movie;
import com.Model.map.User;
import com.Model.map.UserData;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDataAccessor extends Accessor<UserData> {

    MovieAccessor movieAccessor;

    public UserDataAccessor() throws SQLException, ClassNotFoundException {
        super();
        movieAccessor = new MovieAccessor();
    }

    /**
     * Recherche un UserData dans la BDD à l'aide de son ID
     * @param id ID de l'UserData
     * @return UserData trouvé dans la BDD
     * @throws SQLException erreur SQL
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public UserData findById(int id) throws SQLException, IOException, ClassNotFoundException {

        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM User_Data WHERE ID = " + id );

        if ( result.next() ) {
            int UserID = result.getInt(2);
            int movieID = result.getInt(3);
            boolean view =  result.getBoolean(4);
            int lengthAs = result.getInt(5);
            String languageSelected = result.getString(6);
            int rate = result.getInt(7);

            return new UserData(id, UserID, movieID , view, lengthAs, languageSelected, rate);

        }
        return null;
    }

    public UserData findByMovieAndUser(int uId, int mId) throws Exception {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT ID FROM User_Data WHERE User_ID = " + uId + " AND Movie_ID = " + mId);
        if(result.next()) {
            return findById(result.getInt(1));
        }
        return null;
    }

    public ArrayList<UserData> findAllDataFromUser(int UserId) throws SQLException, IOException, ClassNotFoundException {

        ResultSet result = dataBase.getRequest().executeQuery(" SELECT user_ID FROM User_Data WHERE user_ID = " + UserId );
        ArrayList<UserData> userDataArray = new ArrayList<UserData>();
        while(result.next()) {
            userDataArray.add(findById(result.getInt(1)));
        }
        return userDataArray;
    }


    /**
     * Création d'un UserData dans la BDD
     * @param usrData UserData à créer
     * @return ID de l'UserData créé
     * @throws SQLException erreur SQL
     */
    @Override
    public int create(UserData usrData) throws SQLException {


        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO User_Data (user_ID, Movie_ID, view, length_already_seen, language_selected, rate) " +
                "VALUES (?, ?, ?, ?, ?, ?)");

        pre.setInt(1, usrData.getOwnerId());
        pre.setInt(2, usrData.getMovie().getId());
        pre.setBoolean(3, usrData.isView());
        pre.setInt(4, usrData.getLengthAlreadySeen());
        pre.setString(5, usrData.getLanguageSelected());
        pre.setDouble(6, usrData.getRate());

        pre.executeUpdate();
        pre.close();
        return usrData.getOwnerId();
    }

    /**
     * Met à jour si un film est vue dans la BDD
     * @param usrData UserData à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updateView(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_Data SET view = ? WHERE User_ID = ?");

        pre.setBoolean(1, usrData.isView());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    /**
     * Met à jour la durée de visionnage d'un film dans la BDD
     * @param usrData
     * @throws SQLException
     */
    public void updateLengthAlreadySeen(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_data SET length_already_seen = ? WHERE User_ID = ?");

        pre.setInt(1, usrData.getLengthAlreadySeen());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    /**
     * Met à jour la langue sélectionnée pour un film dans la BDD
     * @param usrData UserData à mettre à jour dans la BDD
     * @throws SQLException erreur SQL
     */
    public void updateLanguageSelected(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_Data SET language_selected = ? WHERE User_ID = ?");

        pre.setString(1, usrData.getLanguageSelected());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    /**
     * Met à jour la note d'un film dans la BDD
     * @param usrData UserData à mettre à jour dans la BDD
     * @throws SQLException erreur SQL
     */
    public void updateRate(UserData usrData) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User_Data SET rate = ? WHERE User_ID = ?");

        pre.setDouble(1, usrData.getRate());
        pre.setInt(2, usrData.getId());
        pre.executeUpdate();
        pre.close();
    }

    /**
     * Supprime un UserData de la BDD
     * @param id ID de l'UserData à supprimer
     * @throws SQLException erreur SQL
     */
    @Override
    public void delete(int id ) throws SQLException {
        dataBase.getRequest().executeUpdate(" DELETE FROM User_Data WHERE user_ID = " + id );
    }
}
