package com.Model;

import com.Model.dao.*;
import com.Model.map.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UnitTest {


    public Movie loadDemoMovie() throws IOException {
        ArrayList<Person> actors = new ArrayList<Person>();

        actors.add(new Person(-1, "Marc", "Du train",10, "M"));
        actors.add(new Person(-1, "Roger", "antoine",10, "F"));
        actors.add(new Person(-1, "Raph", "Smith",10, "M"));

        Movie demoMovie = new Movie(-1, "Super film", ImageIO.read(new File("main/resources/images/stars-wars.jpg")), "demo.mp4",
                LocalDate.of(1999, 02, 12), 130, new Person(-1,"Nolan", "christophe", 30, "M"), actors, new ArrayList<String>(),
                "Super film, avec un mechant qui meurt à la fin, déso pour le spoil", "demo.mp4", true,
                0, 5);
        return demoMovie;
    }

    public User LoadDemoUser() throws SQLException, ClassNotFoundException, IOException {
        MovieAccessor movieAccessor = new MovieAccessor();
        ArrayList<String> favTypeList = new ArrayList<String>();
        favTypeList.add("Action");
        favTypeList.add("Comedy");
        favTypeList.add("Horror");

        UserData data = new UserData(-1, -1, 54, true, 20, "fr", 3);

        Playlist history = new Playlist(-1, -1, "History", new ArrayList<Movie>());

       // return new User(-1, "redherisson", "123", "Raphaël", "Jeantet", "jeantet.raph@gmail.com", 30, "M", LocalDate.of(1999, 02, 12),
       //         new ArrayList<Playlist>(),history, favTypeList, data, false);
        return null;
    }

    public static void main( String[] args) {
        try {
            //PersonAccessor personAccessor = new PersonAccessor();
            //UserAccessor userAccessor = new UserAccessor();
            Movie demoMovie = new UnitTest().loadDemoMovie();
            MovieAccessor movieAccessor = new MovieAccessor();
            movieAccessor.create(demoMovie);
            //userAccessor.create(new UnitTest().LoadDemoUser());
            //User demoUser = userAccessor.findById(25);
            //System.out.println(demoUser.toString());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
