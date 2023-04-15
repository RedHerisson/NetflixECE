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

    /**
     * Creation, destruction, find, update de la classe User
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public UserAccessor() throws SQLException, ClassNotFoundException {
        super();
        personAccessor = new PersonAccessor();
        movieAccessor = new MovieAccessor();
        userDataAccessor = new UserDataAccessor();
        playlistAccessor = new PlaylistAccessor();
    }

    /**
     * Recherche d'un utilisteur dans la base de données à l'aide de son ID
     * @param id ID de l'utilisateur
     * @return Utilisateur trouvé dans la base de données
     * @throws SQLException erreur SQL
     */
    @Override
    public User findById(int id) throws SQLException, IOException, ClassNotFoundException {
        ResultSet findUser = dataBase.getRequest().executeQuery(" SELECT * FROM User WHERE ID = " + id );
        ResultSet findFavType = dataBase.getRequest().executeQuery(" SELECT ID FROM Favourite_type WHERE user_id = " + id );
        ResultSet findPlaylist = dataBase.getRequest().executeQuery(" SELECT ID FROM Playlist WHERE user_id = " + id );
        ResultSet findHistory = dataBase.getRequest().executeQuery(" SELECT ID FROM Playlist WHERE user_id = " + id + " AND title = 'History' " );

        if ( findUser.next() ) {
            int personID = findUser.getInt(2);
            String pseudo = findUser.getString(3);
            String pwd = findUser.getString(4);
            String email = findUser.getString(5);
            Date accDateCreation = findUser.getDate(6);
            boolean admin = findUser.getBoolean(7);

            ArrayList<String> favTypeList = new ArrayList<String>();
            while( findFavType.next() ) {
                favTypeList.add( findFavType.getString(1) );
            }
            Playlist history;
            if( findHistory.next()  ) {
                history = playlistAccessor.findById(findHistory.getInt(1));
            } else
                history = null;

            // select all Playlist from the user in the database
            ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
            while( findPlaylist.next() ) {
                playlistList.add( playlistAccessor.findById(findPlaylist.getInt(1)) );
            }
            

            Person pDef = personAccessor.findById(personID);
            

            return new User(id,pseudo, pwd, pDef.getName(), pDef.getSurname(),email, pDef.getAge(), pDef.getSexe(), accDateCreation.toLocalDate(), playlistList,
                    history, favTypeList, userDataAccessor.findAllDataFromUser(id), admin);
        }
        return null;
    }

    public User findByName( String name ) throws SQLException, IOException, ClassNotFoundException {
        ResultSet findUser = dataBase.getRequest().executeQuery(" SELECT ID FROM User WHERE pseudo = " + "'" +  name + "'" );
        if( findUser.next() ) {
            return findById( findUser.getInt(1) );
        }
        else return null;
    }

    public User findByMail( String mail ) throws SQLException, IOException, ClassNotFoundException {
        ResultSet findUser = dataBase.getRequest().executeQuery(" SELECT ID FROM User WHERE email = " + "'" +  mail + "'" );
        if( findUser.next() ) {
            return findById( findUser.getInt(1) );
        }
        else return null;
    }

    public boolean checkPwd( int id, String pwd ) throws SQLException, IOException, ClassNotFoundException {
        ResultSet findUser = dataBase.getRequest().executeQuery(" SELECT pwd FROM User WHERE ID = " + id );
        if( findUser.next() ) {
            return findUser.getString(1).equals(pwd);
        }
        else return false;
    }

    /**
     * Creation d'un utilisateur
     * @warning Pas de vérification de l'unicité
     * @warning Les ID interne modifiés ne sont pas mis à jour
     * @param usr utilisateur à créer
     * @return ID de l'utilisteur créee dans la base de données
     * @throws SQLException
     */
    public int create(User usr) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO User (person_id, pseudo, pwd, email, acc_date_creation ,admin) " +
                "VALUES (?, ?, ?, ?, ?, ?)");

        pre.setInt(1, personAccessor.create(usr));
        pre.setString(2, usr.getPseudo());
        pre.setString(3, usr.getPwd());
        pre.setString(4, usr.getEmail());
        pre.setDate(5, Date.valueOf(usr.getCreationDate()));
        pre.setBoolean(6, usr.isAdmin());

        pre.executeUpdate();
        usr.setId(dataBase.getLastIdFromTable("User"));

        for(String type : usr.getFavouriteType()) {
            PreparedStatement TypePre = dataBase.getRequest().getConnection().prepareStatement( "INSERT INTO Favourite_type (Type, user_id) VALUES (?, ? )");
            TypePre.setString(1, type );
            TypePre.setInt(2, usr.getId());
            TypePre.executeUpdate();
        }
        usr.setId(dataBase.getLastIdFromTable("User"));

        for( Playlist pl : usr.getPlaylists() ) {
            pl.setOwnerId(usr.getId());
            playlistAccessor.create(pl);
        }
        Playlist history = usr.getHistory();
        history.setOwnerId(usr.getId());
        playlistAccessor.create(history);

        ArrayList<UserData> dataArray = usr.getData();
        for( UserData data : dataArray) {
            data.setOwnerId(usr.getId());
            userDataAccessor.create( data );
        }


        return usr.getId();
    }

    /**
     * Mise à jour du pseudo d'un utilisateur
     * @param usr utilisateur à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updatePseudo(User usr) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE Person SET name = ?, surname = ? WHERE id = " + usr.getId() );

        pre.setString(1, usr.getName());
        pre.setString(2, usr.getSurname());
        pre.executeUpdate();
    }

    /**
     * Mise à jour du mot de passe d'un utilisateur
     * @param usr utilisateur à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updatePwd(User usr) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User SET pwd = ? WHERE id = " + usr.getId() ); // TODO: hash pwd

        pre.setString(1, usr.getPwd());
        pre.executeUpdate();
    }
    /**
     * Mise à jour de l'email d'un utilisateur
     * @param usr utilisateur à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updateEmail(User usr) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "UPDATE User SET email = ? WHERE id = " + usr.getId() );

        pre.setString(1, usr.getEmail());
        pre.executeUpdate();
    }

    /**
     * Destruction d'un utilisateur dans la base de données
     * @param id ID de l'utilisateur à supprimer
     * @throws SQLException erreur SQL
     */
    @Override
    public void delete( int id ) throws SQLException {
        dataBase.getRequest().executeUpdate("DELETE FROM Favourite_type WHERE user_ID= " + id);
        ResultSet playlistList =  dataBase.getRequest().executeQuery("SELECT ID FROM Playlist WHERE user_ID = " + id);
        while(playlistList.next()) {
            playlistAccessor.delete(playlistList.getInt(1));
        }
        playlistList.close();
        // delete history from the database
        ResultSet history  = dataBase.getRequest().executeQuery("SELECT ID FROM Playlist WHERE user_ID = " + id + " AND title = 'History'");
        if( history.next() ) {
            playlistAccessor.delete(history.getInt(1));
        }
        history.close();
        ResultSet data = dataBase.getRequest().executeQuery("SELECT user_ID FROM User_Data WHERE user_ID = " + id);
        if( data.next() ) {
            userDataAccessor.delete(data.getInt(1));
        }
        dataBase.getRequest().executeUpdate("DELETE FROM User WHERE ID = " + id);
    }
}
