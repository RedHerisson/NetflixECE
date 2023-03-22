package com.Model.dao;

import com.Model.map.Movie;
import com.Model.map.Person;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MovieAccessor extends Accessor<Movie> {

    PersonAccessor personAccessor;
    public MovieAccessor() throws SQLException, ClassNotFoundException {
        super();
        personAccessor = new PersonAccessor();
    }


    @Override
    public Movie find(int id) throws SQLException, ClassNotFoundException, IOException {


        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Movie WHERE ID = " + id );

        if ( result.next() ) {
            String title = result.getString(2);

            BufferedImage thumbnail =  ImageIO.read(result.getBlob(3).getBinaryStream());
            String filePath = result.getString(4);
            LocalDate releaseDate = result.getDate(5).toLocalDate();
            int length = result.getInt(6);
            Person director = new Person(personAccessor.find(result.getInt(7)));
            int actorListID = result.getInt(8);

            ResultSet actorList = dataBase.getRequest().executeQuery(" SELECT * FROM Actor WHERE ID = " + actorListID );
            ArrayList<Person> actors = new ArrayList<Person>();
            while ( actorList.next() ) {

                actors.add( new Person(personAccessor.find(actorList.getInt(2))) );
            }

            String type = result.getString(9);
            String summary = result.getString(10);
            String teaserPath = result.getString(11);
            int rating = result.getInt(12);
            boolean awarded = result.getBoolean(13);
            int viewCount = result.getInt(14);

            return new Movie(id, title,thumbnail, filePath, releaseDate, length, director, actors, type, summary, teaserPath, awarded, viewCount, rating);
        }
        return null;
    }

    @Override
    public int create(Movie movie) {
        return 0;
    }

    @Override
    public int update(Movie movie) throws SQLException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Movie WHERE ID = " + movie.getId() );

        if( ! result.next() ) {
            return create(movie);
        }
        else {

            String query = "UPDATE Movie SET " +
                    "ID = " + movie.getId() +
                    ", title = '" + movie.getTitle() +
                    ", file_path = '" + movie.getFilePath() +
                    ", release_date = '" +  movie.getReleaseDate() +
                    ", length = '" + movie.getLength() +
                    ", director_ID = '" + personAccessor.update(movie.getDirector()) +
                    ", actor_ID = '" + movie.getActors() +
                    "',type = '" + movie.getType() +
                    "',summary = '" + movie.getSummary() +
                    "',teaser_path = '" + movie.getTeaserPath() +
                    "',award = '" + movie.isAwarded() +
                    "',thumbnail = '" + movie.getThumbnail() + // raph à modif
                    "',view_counter = '" + movie.getViewCount() +
                    "',rating = '" + movie.getRating() +
                    "' WHERE 1 ";

            PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                    "UPDATE into Movie V");
            System.out.println(query);
            dataBase.getRequest().executeUpdate(query);
            return movie.getId();
        }
    }

    @Override
    public void delete( int id ) {

    }
}
