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



            ArrayList<Person> actors = new ArrayList<Person>();
            actors.add(new Person(-1, "Noémie", "dussart", 30, "M" ));

            BufferedImage poster = ImageIO.read(new File("main/resources/tempo/star.jpg"));
            Movie demoMovie = new Movie(45, "TD09 : A New Hope 43", poster, "star.mp4", LocalDate.of(1999, 5, 19),
                    136, new Person(-1, "igor", "serbe", 30, "M"),
                    actors, "Action",
                    "Avant de devenir un célèbre chevalier Jedi, et bien avant de se révéler l'âme la plus noire de la galaxie, igor est un jeune esclave sur la planète Tatooine. La Force est déjà puissante en lui et il est un remarquable pilote de Podracer. Le maître Jedi Qui-Gon Jinn le découvre et entrevoit alors son immense potentiel. Pendant ce temps, l'armée de droïdes de l'insatiable Fédération du Commerce a envahi Naboo dans le cadre d'un plan secret des Sith visant à accroître leur pouvoir.",
                    "star.mp4",true ,8999, 4.2);

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
