package com.Model.dao;

import com.Controller.BDD;
import com.Model.map.Movie;
import com.Model.map.Person;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 */
public class MovieAccessor extends Accessor<Movie> {

    PersonAccessor personAccessor;

    public MovieAccessor() throws SQLException, ClassNotFoundException {
        super();

    }

    /**
     * Trouver un film par son ID
     * @param id ID du film
     * @return le film correspondant à l'ID
     * @throws SQLException erreur SQL
     * @throws ClassNotFoundException
     * @throws IOException
     * @warning Ne charge pas les poster des films, utiliser LoadPoster pour charger les poster
     */
    @Override
    public Movie findById(int id) throws SQLException, ClassNotFoundException, IOException {

        ArrayList<Person> actors = new ArrayList<Person>();

        ResultSet actorList = dataBase.getRequest().executeQuery(" SELECT * FROM Actor WHERE movie_ID = " + id );
        while ( actorList.next() ) {

            actors.add( new Person(personAccessor.findById(actorList.getInt(2))));
        }

        actorList.close();


        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Movie WHERE ID = " + id );

        if ( result.next() ) {
            String title = result.getString(2);

            String filePath = result.getString(3);
            LocalDate releaseDate = result.getDate(4).toLocalDate();
            int length = result.getInt(5);
            Person director = new Person(personAccessor.findById(result.getInt(6)));

            String type = result.getString(7);
            ArrayList<String> typeArray = new ArrayList<String>();
            String[] types = type.split(", ");
            for (String s : types) {
                typeArray.add(s);
            }
            String summary = result.getString(8);
            String teaserPath = result.getString(9);
            boolean awarded = result.getBoolean(10);
            int viewCount = result.getInt(12);
            int rating = result.getInt(13);

            result.close();

            return new Movie(id, title, null, filePath, releaseDate, length, director, actors, typeArray, summary, teaserPath, awarded, viewCount, rating);
        }
        result.close();
        System.out.println("Movie not found");
        return null;
    }

    /**
     * Charge le poster d'un film en mémoire
     * @param id ID du film
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public BufferedImage loadPoster(int id) throws SQLException, IOException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT thumbnail FROM Movie WHERE ID = " + id);
        if (result.next()) {
            InputStream is = result.getBinaryStream(1);
            BufferedImage image = ImageIO.read(is);
            return image;
        }
        return null;
    }
     * Trouver un film par son nom
     * @param query nom du film
     * @return le film correspondant au nom
     * @throws SQLException erreur SQL
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public ArrayList<Movie> search(String query) throws SQLException, ClassNotFoundException, IOException {

        ArrayList<Movie> movies = new ArrayList<Movie>();

        ResultSet result = dataBase.getRequest().executeQuery("SELECT Movie.Title, Person.name, Person.surname FROM Movie, Person JOIN Person p ON Movie.director_id = p.id JOIN Actor a ON movie_id = a.person_id JOIN Person p ON Movie.id = p.person_id WHERE Movie.title like '%"+query+"%' OR Person.name like '%"+query+"%' OR Person.surname like '%"+query+"%' LIMIT 10");

        while(result.next()){
            movies.add(findById(result.getInt(1)));
        }

        result.close();
        System.out.println("Movie not found");
        return movies;
    }

    public int countMovies() throws SQLException, ClassNotFoundException, IOException {


        ResultSet result = dataBase.getRequest().executeQuery("SELECT COUNT(*) FROM Movie");
        int cpt=0;
        if (result.next()){
            cpt=result.getInt(1);
        }

        result.close();
        System.out.println("Movie not found");
        return cpt;
    }

    /**
     * Création d'un film dans la base de données
     * @param movie film à créer
     * @return ID du film créé
     * @throws SQLException erreur SQL
     * @throws IOException
     */
    @Override
    public int create(Movie movie) throws SQLException, IOException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO Movie (title, file_path, release_date, length, director_ID,type, summary, teaser_path, award, thumbnail, view_counter, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        loadPreStatement(movie, pre, false);
        return movie.getId();
    }

    /**
     * Mise à jour d'un film entier dans la base de données
     * Si le film n'existe pas, il est créé
     * @param movie
     * @return
     * @throws SQLException
     * @throws IOException
     * @warning Ne pas utiliser pour mettre à jour seulement quelques champs
     */
    public int update(Movie movie) throws SQLException, IOException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Movie WHERE ID = " + movie.getId() );

        if( ! result.next() ) {
            System.out.println("Create new Movie");
            return create(movie);
        }
        else {

            PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                    "UPDATE Movie SET title = ?, file_path = ?, release_date = ?, length = ?, director_ID = ?" +
                            ",type = ?,summary = ?,teaser_path = ?,award = ?,thumbnail = ?,view_counter = ?,rating = ? WHERE id = " + movie.getId());

            loadPreStatement(movie, pre, true);

            return movie.getId();
        }
    }

    /**
     * Preparation de la requête préparée pour la création ou la mise à jour d'un film
     * @param movie film à mettre à jour
     * @param pre requête préparée
     * @param isUpdate true si le film existe déjà
     * @return requête préparée
     * @throws SQLException erreur SQL
     * @throws IOException
     */
    private PreparedStatement loadPreStatement(Movie movie, PreparedStatement pre, boolean isUpdate) throws SQLException, IOException {
        pre.setString(1, movie.getTitle());
        pre.setString(2, movie.getFilePath());
        pre.setDate(3, Date.valueOf(movie.getReleaseDate()));
        pre.setInt(4, movie.getLength());
        pre.setInt(5, personAccessor.update(movie.getDirector()));

        pre.setString(6, movie.getTypes());
        pre.setString(7, movie.getSummary());
        pre.setString(8, movie.getTeaserPath());
        pre.setBoolean(9, movie.isAwarded());

        ByteArrayOutputStream binaryStream = new ByteArrayOutputStream();
        // image to blob
        ImageIO.write(movie.getThumbnail(), "jpg", binaryStream);
        InputStream inputStream = new ByteArrayInputStream(binaryStream.toByteArray());
        pre.setBlob(10, inputStream);

        pre.setInt(11, movie.getViewCount());
        pre.setDouble(12, movie.getRating());

        pre.executeUpdate();

        int movieID = isUpdate ? movie.getId() : dataBase.getLastIdFromTable("Movie");
        if( isUpdate ) {
           dataBase.getRequest().executeUpdate(" DELETE FROM Person WHERE ID IN ( SELECT person_ID FROM Actor WHERE movie_ID = "+ movieID +" AND person_ID NOT IN (SELECT person_ID FROM Actor WHERE movie_ID != "+ movieID +") )" );
           dataBase.getRequest().executeUpdate(" DELETE FROM Actor WHERE movie_ID = " + movieID);
        }




        for( Person actor : movie.getActors()) {
            int personID = personAccessor.update(actor);
            System.out.println("Person ID: " + personID);
            ResultSet ActorIdExist = dataBase.getRequest().executeQuery(" SELECT * FROM Actor WHERE person_ID = " + personID + " AND movie_ID = " +  movieID );
            if( ! ActorIdExist.next()) {

                String queryForActorTable = "INSERT INTO Actor(person_ID, movie_ID) VALUE ( " + personID + ", " + movieID + " )";
                dataBase.getRequest().executeUpdate(queryForActorTable);

            }
            else {
                String queryForActorTable = "UPDATE Actor SET person_ID = " + personID + ", movie_ID = " + movieID + " WHERE person_ID = " + personID + " AND movie_ID = " +  movieID;
                dataBase.getRequest().executeUpdate(queryForActorTable);
            }
            ActorIdExist.close();
        }
        return pre;
    }

    /**
     * Suppression d'un film dans la base de données
     * @param id ID du film à supprimer
     * @throws SQLException erreur SQL
     */
    @Override
    public void delete( int id ) throws SQLException {
        ResultSet ActorList = dataBase.getRequest().executeQuery("SELECT * FROM Actor WHERE movie_ID = " + id );
        while ( ActorList.next() ) {
            personAccessor.delete(ActorList.getInt(2));
        }
        ActorList.close();

        ResultSet Director = dataBase.getRequest().executeQuery("SELECT director_ID FROM Movie WHERE  ID = " + id );

        dataBase.getRequest().executeUpdate("DELETE FROM Movie WHERE ID =" + id );

        if( Director.next() ) {
            personAccessor.delete(Director.getInt(1));
        }
        Director.close();
    }

    public ArrayList<Movie> findByType(String type, int max) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Movie> movies = new ArrayList<>();
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT ID FROM Movie WHERE type LIKE '%" + type + "%' LIMIT " + max);
        while (result.next()) {
            movies.add(findById(result.getInt(1)));
        }
        result.close();

        return movies;
    }

    public ArrayList<Movie> findByDate(int i) throws SQLException, IOException, ClassNotFoundException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT ID FROM Movie ORDER BY release_date DESC LIMIT " + i);
        ArrayList<Movie> movies = new ArrayList<>();
        while (result.next()) {
            movies.add(findById(result.getInt(1)));
        }
        result.close();
        return movies;
    }

    public void addView(int id) throws SQLException {
        dataBase.getRequest().executeUpdate("Update Movie SET view_counter = view_counter + 1 WHERE ID = " + id);
    }
}
