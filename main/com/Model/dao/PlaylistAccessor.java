
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

/**
 * Accessor playlist in the database
 */
public class PlaylistAccessor extends Accessor<Playlist> {

    MovieAccessor movieAccessor;

    private static String GET_BY_ID = "SELECT * FROM Playlist WHERE ID = ";
    private static String GET_BY_USER = "SELECT * FROM Playlist WHERE user_ID = ";
    private static String GET_MOVIES = "SELECT movie_ID FROM MovieList WHERE playlist_ID = ";

    private static String INSERT = "INSERT INTO Playlist (user_ID, title) VALUES (?, ?)";

    private static String UPDATE_Title = "UPDATE Playlist SET title = ? WHERE ID = ?";
    private static String INSERT_MOVIE = "INSERT INTO MovieList (playlist_ID, movie_ID) VALUES (?, ?)";
    private static String REMOVE_MOVIE = "DELETE FROM MovieList WHERE playlist_ID = ? AND movie_ID = ?";

    /**
     * Constructeur
     * @throws SQLException si la connexion à la base de données a échoué
     * @throws ClassNotFoundException
     */
    public PlaylistAccessor() throws SQLException, ClassNotFoundException {
        super();
        movieAccessor = new MovieAccessor();
    }

    /**
     * Trouver une playlist dans la base de données en fonction de son ID
     * @param id ID de la playlist
     * @return la playlist correspondant à l'ID
     * @throws SQLException erreur SQL
     * @throws ClassNotFoundException
     * @throws IOException
     */
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

    /**
     * Mettre à jour une playlist dans la base de données
     * @param user_ID ID de l'utilisateur à qui appartienent les playlist
     * @return la liste des playlist de l'utilisateur
     * @throws SQLException erreur SQL
     * @throws IOException erreur d'entrée/sortie
     * @throws ClassNotFoundException erreur SQL
     */
    public ArrayList<Playlist> findAll(int user_ID ) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Playlist> userPlaylists = new ArrayList<Playlist>();
        ResultSet result = dataBase.getRequest().executeQuery(GET_BY_USER + user_ID );
        while( result.next() ) {
            userPlaylists.add(findById( result.getInt(1)));
        }
        return userPlaylists;
    }

    /**
     * Créer une playlist dans la base de données
     * @param playlist Playlist à créer
     * @return l'ID de la playlist crée
     * @throws SQLException
     */
    @Override
    public int create(Playlist playlist) throws SQLException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(INSERT);

        pre.setInt(1, playlist.getOwnerId());
        pre.setString(2, playlist.getTitle());
        pre.executeUpdate();
        pre.close();


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


        return dataBase.getLastIdFromTable("Playlist");
    }

    /**
     * Mettre à jour le titre d'une playlist
     * @param playlist Playlist à mettre à jour
     * @throws SQLException erreur SQL
     */
    public void updateTitle( Playlist playlist ) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(UPDATE_Title);
        pre.setString(1, playlist.getTitle());
        pre.setInt(2, playlist.getId());
        pre.executeUpdate();
        pre.close();
    }

    /**
     * Ajouter un film à une playlist
     * @param movie Film à ajouter
     * @param playlist Playlist à laquelle ajouter le film
     * @throws SQLException erreur SQL
     */
    public void addMovie( Movie movie, Playlist playlist ) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(INSERT_MOVIE);
        pre.setInt(1, playlist.getId());
        pre.setInt(2, movie.getId());
        pre.executeUpdate();
        pre.close();
    }

    /**
     * Supprimer un film d'une playlist
     * @param movie Film à supprimer
     * @param playlist Playlist de laquelle supprimer le film
     * @throws SQLException erreur SQL
     */
    public void removeMovie( Movie movie, Playlist playlist ) throws SQLException {
        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement(REMOVE_MOVIE);
        pre.setInt(1, playlist.getId());
        pre.setInt(2, movie.getId());
        pre.executeUpdate();
        pre.close();
    }

    /**
     * Supprimer une playlist de la base de données
     * @param id ID de la playlist à supprimer
     * @throws SQLException erreur SQL
     */
    @Override
    public void delete( int id ) throws SQLException {
        dataBase.getRequest().executeUpdate("DELETE FROM MovieList WHERE playlist_ID = " + id);
        dataBase.getRequest().executeUpdate("DELETE FROM Playlist WHERE ID = " + id);
    }
}
