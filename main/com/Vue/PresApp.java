package com.Vue;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class PresApp  extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/moviePres.fxml"));
        MovieAccessor movieAccessor = new MovieAccessor();


        Scene scenes = new Scene(loader.load(), 1275, 645, Color.BLACK);

        Movie movie = movieAccessor.findById(300);
        MoviePres controller = loader.<MoviePres>getController();
        AppController appController = new AppController();
        controller.setAppController(appController);
        controller.loadMovie(movie);

        stage.setScene(scenes);
        stage.setTitle(movie.getTitle());
        stage.setResizable(false);
        stage.show();

    }



    public static void main(String[] args) {
        Application.launch();
    }
}