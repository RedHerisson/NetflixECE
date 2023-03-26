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

    public static void main( String[] args) {
        try {
            MovieAccessor movieAccessor = new MovieAccessor();
            PersonAccessor personAccessor = new PersonAccessor();



            Movie demoMovie = movieAccessor.find(45);
            demoMovie.addActor(personAccessor.find(161));
            for(Person actor : demoMovie.getActors()) {
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
