package Model.dao;

import Model.map.Movie;
import Model.map.Person;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MovieAccessor extends Accessor<Movie> {


    public MovieAccessor() throws SQLException, ClassNotFoundException {
        super();
    }


    @Override
    public Movie find(int id) throws SQLException, ClassNotFoundException, IOException {

        PersonAccessor personAccessor = new PersonAccessor();
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

            return new Movie(id, title,thumbnail, filePath, releaseDate, length, director, actors, type, summary, teaserPath, rating, awarded, viewCount);
        }
        return null;
    }

    @Override
    public int create(Movie obj) {
        return 0;
    }

    @Override
    public int update(Movie obj) {
        return 0;
    }

    @Override
    public void delete( int id ) {

    }
}
