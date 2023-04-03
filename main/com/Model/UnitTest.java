package com.Model;

import com.Model.dao.*;
import com.Model.map.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UnitTest {


    public Movie loadDemoMovie() throws IOException {
        ArrayList<Person> actors = new ArrayList<Person>();

        actors.add(new Person(-1, "John", "Doe",10, "M"));
        actors.add(new Person(-1, "Jane", "Doe",10, "F"));
        actors.add(new Person(-1, "John", "Smith",10, "M"));

        Movie demoMovie = new Movie(-1, "Film test", ImageIO.read(new File("main/resources/tempo/star.jpg")), "star.mp4",
                LocalDate.of(1999, 02, 12), 130, new Person(-1,"Lucas", "George", 30, "M"), actors, "Action",
                "This is a demo film", "star.mp4", true,
                0, 5);
        return demoMovie;
    }

    public User LoadDemoUser() throws SQLException, ClassNotFoundException, IOException {
        MovieAccessor movieAccessor = new MovieAccessor();
        ArrayList<String> favTypeList = new ArrayList<String>();
        favTypeList.add("Action");
        favTypeList.add("Comedy");
        favTypeList.add("Horror");

        UserData data = new UserData(-1, -1, movieAccessor.findById(54), true, 20, "fr", 3);
        Playlist history = new Playlist(-1, -1, "History", new ArrayList<Movie>());

        return new User(-1, "redherisson", "123", "Raphaël", "Jeantet", "jeantet.raph@gmail.com", 30, "M", LocalDate.of(1999, 02, 12),
                new ArrayList<Playlist>(),history, favTypeList, data, false);
    }

    public static void main( String[] args) {
        try {
            PersonAccessor personAccessor = new PersonAccessor();
            UserAccessor userAccessor = new UserAccessor();
            //Movie demoMovie = new UnitTest().loadDemoMovie();

            //userAccessor.delete(23);

            //userAccessor.create(new UnitTest().LoadDemoUser());
            User demoUser = userAccessor.findById(25);
            System.out.println(demoUser.toString());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
