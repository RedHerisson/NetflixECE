package com.Model.dao;

import com.Model.map.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccessor extends PersonAccessor {

    PersonAccessor personAccessor;
    MovieAccessor movieAccessor;

    PlaylistAccessor playlistAccessor;

    UserDataAccessor userDataAccessor;

    public UserAccessor() throws SQLException, ClassNotFoundException {
        super();
        personAccessor = new PersonAccessor();
        movieAccessor = new MovieAccessor();
        userDataAccessor = new UserDataAccessor();
    }

    //pas sûr de cette partie-là au niveau de l'heritage de PersonAccessor
    @Override
    public User findById(int id) throws SQLException, IOException, ClassNotFoundException {
        ResultSet findUser = dataBase.getRequest().executeQuery(" SELECT * FROM User WHERE ID = " + id );
        ResultSet findFavType = dataBase.getRequest().executeQuery(" SELECT * FROM Favourite_type WHERE user_id = " + id );
        ResultSet findPlaylist = dataBase.getRequest().executeQuery(" SELECT ID FROM Playlist WHERE user_id = " + id );

        if ( findUser.next() ) {
            int personID = findUser.getInt(1);
            String pseudo = findUser.getString(2);
            String pwd = findUser.getString(3);
            String email = findUser.getString(4);
            Date accDateCreation = findUser.getDate(5);
            int dataID = findUser.getInt(6);

            ArrayList<String> favTypeList = new ArrayList<String>();
            while( findFavType.next() ) {
                favTypeList.add( findFavType.getString(1) );
            }
            // TODO: add playlist to the database
            // TODO : add historic to the database
            Playlist historic = playlistAccessor.findById(findUser.getInt(7));

            // select all Playlist from the user in the database
            ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
            while( findPlaylist.next() ) {
                playlistList.add( playlistAccessor.findById(findPlaylist.getInt(1)) );
            }
            

            Person pDef = personAccessor.findById(personID);

            return new User(id,pseudo, pwd, pDef.getName(), pDef.getSurname(),email, pDef.getAge(), pDef.getSexe(), accDateCreation.toLocalDate(), playlistList,
                    historic, favTypeList, userDataAccessor.findById(dataID));
        }
        return null;
    }

    public int create(User usr) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO User (person_id, pseudo, pwd, email, acc_date_creation, movie_historic_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");

        pre.setInt(1, personAccessor.create(usr));
        pre.setString(2, usr.getPseudo());
        pre.setString(3, usr.getPwd());
        pre.setString(4, usr.getEmail());
        pre.setDate(5, Date.valueOf(usr.getCreationDate()));


        for(String type : usr.getFavouriteType()) {
            PreparedStatement TypePre = dataBase.getRequest().getConnection().prepareStatement( "INSERT INTO Favourite_type (Type, user_id) VALUES (?, ? )");
            TypePre.setString(1, type );
            TypePre.setInt(2, usr.getId() );
            TypePre.executeUpdate();
        }

        for( Playlist pl : usr.getPlaylists() ) {
            pl.setOwnerId(usr.getId());
            playlistAccessor.create(pl);
        }
        pre.executeUpdate();

        return usr.getId();
    }

    public void updatePseudo(User usr) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE Person SET name = ?, surname = ? WHERE id = " + usr.getId() );

        pre.setString(1, usr.getName());
        pre.setString(2, usr.getSurname());
        pre.executeUpdate();
    }

    public void updatePwd(User usr) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User SET pwd = ? WHERE id = " + usr.getId() ); // TODO: hash pwd

        pre.setString(1, usr.getPwd());
        pre.executeUpdate();
    }

    public void updateEmail(User usr) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User SET email = ? WHERE id = " + usr.getId() );

        pre.setString(1, usr.getEmail());
        pre.executeUpdate();
    }


    @Override
    public void delete( int id ) {

    }
}
