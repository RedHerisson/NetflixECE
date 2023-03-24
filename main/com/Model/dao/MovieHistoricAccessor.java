package com.Model.dao;

import com.Model.map.Movie;
import com.Model.map.MovieHistoric;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieHistoricAccessor extends Accessor<MovieHistoric> {

    MovieAccessor movieAccessor = new MovieAccessor();
    UserAccessor userAccessor = new UserAccessor();

    public MovieHistoricAccessor() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public MovieHistoric find(int id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM MovieHistoric WHERE ID = " + id );

        if ( result.next() ) {
            int movieID = result.getInt(1);
            int userID = result.getInt(2);
            LocalDate seenDate = result.getDate(3).toLocalDate();

            return new MovieHistoric(id, movieAccessor.find(movieID), userAccessor.find(userID), seenDate);

        }
        return null;
    }

    @Override
    public int create(MovieHistoric movieHistoric) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO MovieHistoric (movie_ID, user_ID, seen_date) " +
                "VALUES (?, ?, ?)");

        pre.setInt(1, movieHistoric.getMovie().getId());
        pre.setInt(2, movieHistoric.getUser().getId());
        pre.setDate(3, Date.valueOf(movieHistoric.getSeenDate()) );

        return 0;
    }

    @Override
    public int update(MovieHistoric obj) throws SQLException {
        
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM MovieHistoric WHERE ID = " + obj.getId());
        
        if( result.next()) {
            return create(obj);
        }
        else {
            PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                    "UPDATE MovieHistoric SET movie_ID = ?, user_ID = ?, seen_date = ? ");

            pre.setInt(1, obj.getMovie().getId());
            pre.setInt(2, obj.getUser().getId());
            pre.setDate(3, Date.valueOf(obj.getSeenDate()) );
            
            pre.executeUpdate();
            return obj.getId();
        }
    }

    @Override
    public void delete( int id ) {

    }
}
