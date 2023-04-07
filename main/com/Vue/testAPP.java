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

import java.util.ArrayList;

public class testAPP extends Application {

    double x, y=0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/MovieIntegration.fxml"));
        MovieAccessor movieAccessor = new MovieAccessor();


        Scene scenes = new Scene(loader.load(), 1275, 160, Color.BLACK);

        ArrayList<Movie> movies = new ArrayList<Movie>();

        // charger 10 films
        for(int i = 0; i < 20; i++){
            movies.add(movieAccessor.findById(54));
        }

        Movie movie = movieAccessor.findById(54);
        MovieIntegrationController controller = loader.<MovieIntegrationController>getController();

        controller.LoadPoster(movie);

        primaryStage.setScene(scenes);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void Main(String[] args){
        launch(args);
    }

}
