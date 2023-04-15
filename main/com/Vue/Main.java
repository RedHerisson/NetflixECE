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
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ressources/View/adminStats.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
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
