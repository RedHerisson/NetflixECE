package com.Model.dao;

import com.Model.map.Movie;
import com.Model.map.Person;

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

            ResultSet actorList = dataBase.getRequest().executeQuery(" SELECT * FROM Actor WHERE movie_ID = " + id );
            ArrayList<Person> actors = new ArrayList<Person>();
            while ( actorList.next() ) {

                actors.add( new Person(personAccessor.find(actorList.getInt(2))) );
            }

            String type = result.getString(8);
            String summary = result.getString(9);
            String teaserPath = result.getString(10);
            int rating = result.getInt(11);
            boolean awarded = result.getBoolean(12);
            int viewCount = result.getInt(13);

            return new Movie(id, title,thumbnail, filePath, releaseDate, length, director, actors, type, summary, teaserPath, awarded, viewCount, rating);
        }
        return null;
    }

    @Override
    public int create(Movie movie) throws SQLException, IOException {

        PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                "INSERT INTO Movie (ID, title, file_path, release_date, length, director_ID, actor_ID, type, summary, teaser_path, award, thumbnail, view_counter, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        loadPreStatement(movie, pre);
        pre.executeUpdate();
        return movie.getId();
    }

    @Override
    public int update(Movie movie) throws SQLException, IOException {
        ResultSet result = dataBase.getRequest().executeQuery(" SELECT * FROM Movie WHERE ID = " + movie.getId() );

        if( ! result.next() ) {
            return create(movie);
        }
        else {

            PreparedStatement pre = dataBase.getRequest().getConnection().prepareStatement("" +
                    "UPDATE Movie SET ID = ?, title = ?, file_path = ?, release_date = ?, length = ?, director_ID = ?, actor_ID = ?" +
                            ",type = ?,summary = ?,teaser_path = ?,award = ?,thumbnail = ?,view_counter = ?,rating = ? WHERE id = " + movie.getId());

            loadPreStatement(movie, pre);
            pre.executeUpdate();

            return movie.getId();
        }
    }

    private PreparedStatement loadPreStatement(Movie movie, PreparedStatement pre) throws SQLException, IOException {
        pre.setInt(1, movie.getId());
        pre.setString(2, movie.getTitle());
        pre.setString(3, movie.getFilePath());
        pre.setDate(4, Date.valueOf(movie.getReleaseDate()));
        pre.setInt(5, movie.getLength());
        pre.setInt(6, movie.getDirector().getId());

        String queryForActorTable = "INSERT INTO Actor(person_ID, movie_ID) VALUE ";
        for( Person actor : movie.getActors()) {
            queryForActorTable+= "( "+ movie.getId() +", "+ personAccessor.update(actor) + "),";
        }
        queryForActorTable = queryForActorTable.substring(0, queryForActorTable.length()  -1);
        System.out.println(queryForActorTable);
        dataBase.getRequest().executeQuery(queryForActorTable);
        pre.setInt(7, dataBase.getLastIdFromTable("Actor"));

        pre.setString(8, movie.getType());
        pre.setString(9, movie.getSummary());
        pre.setString(10, movie.getTeaserPath());
        pre.setBoolean(11, movie.isAwarded());

        ByteArrayOutputStream binaryStream = new ByteArrayOutputStream();
        ImageIO.write(movie.getThumbnail(), "jpg", binaryStream);
        InputStream inputStream = new ByteArrayInputStream(binaryStream.toByteArray());
        pre.setBlob(12, inputStream);

        pre.setInt(13, movie.getViewCount());
        pre.setDouble(14, movie.getRating());

        return pre;
    }

    @Override
    public void delete( int id ) throws SQLException {
        ResultSet result = dataBase.getRequest().executeQuery("SELECT actor_ID FROM Movie WHERE ID =" + id );
                dataBase.getRequest().executeUpdate("DELETE * FROM Movie WHERE ID =" + id );
                dataBase.getRequest().executeUpdate("DELETE * FROM Actor WHERE ID =" + id );

    }
}
