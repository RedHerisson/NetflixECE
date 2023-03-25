package com.Model.dao;

import com.Model.map.MovieHistoric;
import com.Model.map.Person;
import com.Model.map.User;
import com.Model.map.UserData;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccessor extends Accessor<User> {

    PersonAccessor personAccessor;
    MovieAccessor movieAccessor;

    UserDataAccessor userDataAccessor;

    public UserAccessor() throws SQLException, ClassNotFoundException {
        super();
        personAccessor = new PersonAccessor();
        movieAccessor = new MovieAccessor();
        userDataAccessor = new UserDataAccessor();
    }

    @Override
    public User find(int id) throws SQLException, IOException, ClassNotFoundException {
        ResultSet findUser = dataBase.getRequest().executeQuery(" SELECT * FROM User WHERE ID = " + id );
        ResultSet findFavType = dataBase.getRequest().executeQuery(" SELECT * FROM Favourite_type WHERE user_id = " + id );
        ResultSet findMovieHistoric = dataBase.getRequest().executeQuery(" SELECT * FROM Movie_historic WHERE user_id = " + id );

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
            ArrayList<MovieHistoric> historic = new ArrayList<MovieHistoric>();
            while ( findMovieHistoric .next() ) {
                historic.add( new MovieHistoric( findMovieHistoric.getInt(1),personID, movieAccessor.find( findMovieHistoric.getInt(2) ),
                        findMovieHistoric.getDate(3).toLocalDate() ) );
            }
            

            Person pDef = personAccessor.find(personID);

            return new User(id,pseudo, pwd, pDef.getName(), pDef.getSurname(),email, pDef.getAge(), pDef.getSexe(), accDateCreation.toLocalDate(),
                    historic, favTypeList, userDataAccessor.find(dataID));
        }
        return null;
    }

    @Override
    public int create(User usr) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO User (person_id, pseudo, pwd, email, acc_date_creation, movie_historic_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");

        pre = loadPreStatement(pre, usr);
        pre.executeUpdate();

        return usr.getId();
    }

    @Override
    public int update(User usr) throws SQLException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM User WHERE ID = " + usr.getId() );

        if( ! result.next() ) {
            return create(usr);
        }
        else {
            PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                    "UPDATE User SET person_id = ?, pseudo = ?, pwd = ?, email = ?, acc_date_creation = ?, movie_historic_ID = ? WHERE id = " + usr.getId() );


           pre = loadPreStatement(pre, usr);
           pre.executeUpdate();

            return usr.getId();
        }
    }

    private PreparedStatement loadPreStatement( PreparedStatement pre, User usr) throws SQLException {
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

        for( MovieHistoric movie : usr.getHistoric() ) {
            PreparedStatement moviePre = dataBase.getRequest().getConnection().prepareStatement( "INSERT INTO Movie_historic (movieID, seen_date, user_ID) VALUES (?, ?, ? )");
            moviePre.setInt(1, movie.getMovie().getId() );
            moviePre.setDate(2, Date.valueOf(movie.getSeenDate() ) );
            moviePre.setInt(3, usr.getId());
            moviePre.executeUpdate();
        }
        return pre;
    }

    @Override
    public void delete( int id ) {

    }
}
