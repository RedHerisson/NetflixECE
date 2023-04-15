package com.Vue;

import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Vue.Carousel.CarouselController;
import com.Vue.Carousel.MovieIntegrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.Controller.tempoLoadDataBase.JsonToMovie;
import static com.Controller.tempoLoadDataBase.OMDBGetById;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/home.fxml"));
        MovieAccessor movieAccessor = new MovieAccessor();


        Scene scenes = new Scene(loader.load(), 1275, 645, Color.BLACK);



        // charger 10 film

        Movie movie = movieAccessor.findById(54);
        HomeController controller = loader.getController();

        for(int i = 0; i < 10; i++){
            ArrayList<Movie> movies = new ArrayList<Movie>();
            for(int j = 70 + i*10; j < 80 + i*10; j++){
                movies.add(movieAccessor.findById(j));

            }
            controller.AddPlaylist(movies, "Playlist " + i);
        }

        controller.AddPromotion(movie);

        //controller.AddPromotion(movie);

        controller.backToTop();
        primaryStage.setScene(scenes);
        primaryStage.setResizable(false);
        primaryStage.show();
        Movie movie2 = null;
       // try {
            //movie2 = JsonToMovie(OMDBGetById("tt0133093"));
            //System.out.println(movie2);
            //movieAccessor.create(movie2);
       // } catch (IOException e) {
         //   throw new RuntimeException(e);
        //}

    }

//    public static void Main(String[] args){
//        launch(args);
//    }

}
