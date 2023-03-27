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

    public static void main( String[] args) {
        try {
            MovieAccessor movieAccessor = new MovieAccessor();
            PersonAccessor personAccessor = new PersonAccessor();
            //Movie demoMovie = new UnitTest().loadDemoMovie();
            Movie demoMovie = movieAccessor.find(54);
            if( demoMovie == null ) {
                System.out.println("Movie not found");
                return;
            }

            for(Person actor : demoMovie.getActors()) {
                System.out.print("new actor : ");
                System.out.println(actor.getName() + " " + actor.getSurname());
            }

            movieAccessor.update(demoMovie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
