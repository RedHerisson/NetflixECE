package com.Model.dao;

import com.Model.map.Movie;
import com.Model.map.Person;
import com.Model.map.Playlist;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaylistAccessor extends Accessor<Playlist> {

    MovieAccessor movieAccessor;
    UserAccessor userAccessor;

    private static String GET_BY_ID = "SELECT * FROM PlayList WHERE ID = ";
    private static String GET_BY_USER = "SELECT * FROM Playlist WHERE user_ID = ";
    private static String GET_MOVIES = "SELECT movie_ID FROM MovieList WHERE playlist_ID = ";

    private static String INSERT = "INSERT INTO Playlist (user_ID, title) VALUES (?, ?)";

    private static String UPDATE_Title = "UPDATE Playlist SET title = ? WHERE ID = ?";
    private static String INSERT_MOVIE = "INSERT INTO MovieList (playlist_ID, movie_ID) VALUES (?, ?)";
    private static String REMOVE_MOVIE = "DELETE FROM MovieList WHERE playlist_ID = ? AND movie_ID = ?";


    public PlaylistAccessor() throws SQLException, ClassNotFoundException {
        super();
        movieAccessor = new MovieAccessor();
        userAccessor = new UserAccessor();
    }

    @Override
    public Playlist findById(int id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet result = dataBase.getRequest().executeQuery(GET_BY_ID + id );

        if ( result.next() ) {
            int userID = result.getInt(2);
            String title = result.getString(3);


            ArrayList<Movie> movies = new ArrayList<>();
            ResultSet moviesList = dataBase.getRequest().executeQuery(GET_MOVIES + id );
            while ( moviesList.next() ) {

                movies.add(movieAccessor.findById(moviesList.getInt(1)));
            }

            moviesList.close();
            result.close();
            return new Playlist(id, userID, title, movies );

        }
        return null;
    }

    public ArrayList<Playlist> findAll(int user_ID ) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Playlist> userPlaylists = new ArrayList<Playlist>();
        ResultSet result = dataBase.getRequest().executeQuery(GET_BY_USER + user_ID );
        while( result.next() ) {
            userPlaylists.add(findById( result.getInt(1)));
        }
        return userPlaylists;
    }

    @Override
    public int create(Playlist playlist) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(INSERT);

        pre.setInt(1, playlist.getOwnerId());
        pre.setString(2, playlist.getTitle());

        // Add all movies to the playlist so that the playlist is up-to-date
        for( Movie movie : playlist.getMoviesList() ) {

            PreparedStatement preMovieList = dataBase.getRequest().getConnection().prepareStatement("" +
                    "INSERT INTO MovieList (playlist_ID, movie_ID) " +
                    "VALUES (?, ?)");
            preMovieList.setInt(1, playlist.getId());
            preMovieList.setInt(2, movie.getId());
            preMovieList.executeUpdate();
            preMovieList.close();
        }

        pre.executeUpdate();
        pre.close();
        return playlist.getId();
    }

    public void updateTitle( Playlist playlist ) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(UPDATE_Title);
        pre.setString(1, playlist.getTitle());
        pre.setInt(2, playlist.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void addMovie( Movie movie, Playlist playlist ) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(INSERT_MOVIE);
        pre.setInt(1, playlist.getId());
        pre.setInt(2, movie.getId());
        pre.executeUpdate();
        pre.close();
    }

    public void removeMovie( Movie movie, Playlist playlist ) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(REMOVE_MOVIE);
        pre.setInt(1, playlist.getId());
        pre.setInt(2, movie.getId());
        pre.executeUpdate();
        pre.close();
    }

    @Override
    public void delete( int id ) {

    }
}
